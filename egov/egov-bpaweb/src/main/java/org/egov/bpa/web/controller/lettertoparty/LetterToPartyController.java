/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.bpa.web.controller.lettertoparty;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.LettertoParty;
import org.egov.bpa.application.entity.LettertoPartyDocument;
import org.egov.bpa.application.entity.LpReason;
import org.egov.bpa.application.service.ApplicationBpaService;
import org.egov.bpa.application.service.CheckListDetailService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.application.service.LpReasonService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/lettertoparty")
public class LetterToPartyController {
    @Autowired
    ApplicationBpaService applicationBpaService;
    @Autowired
    LpReasonService lpReasonService;
    @Autowired
    LettertoPartyService lettertoPartyService;
    @Autowired
    private CheckListDetailService checkListDetailService;
    @Autowired
    @Qualifier("fileStoreService")
    protected FileStoreService fileStoreService;

    @ModelAttribute("lpReasonList")
    public List<LpReason> getLpReasonList() {
        return lpReasonService.findAll();
    }

    @ModelAttribute("checkListDetailList")
    public List<CheckListDetail> checkListDetailList() {
        return checkListDetailService.findActiveCheckListByChecklistType(BpaConstants.CHECKLIST_TYPE);
    }

    @RequestMapping(value = "/create", method = GET)
    public String showLetterToPartyForm(@ModelAttribute final LettertoParty lettertoParty,
            final Model model, final HttpServletRequest request) {
        model.addAttribute("mode", "new");
        return "lettertoparty-create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLetterToParty(@ModelAttribute final LettertoParty lettertoParty,
            final BindingResult resultBinder,
            final Model model,
            final HttpServletRequest request,
            final BindingResult errors, final RedirectAttributes redirectAttributes) {
        final List<LettertoPartyDocument> lpDocs = new ArrayList<>(0);
        int i = 0;
        if (!lettertoParty.getLettertoPartyDocument().isEmpty())
            for (final LettertoPartyDocument lettertoPartyDocument : lettertoParty.getLettertoPartyDocument()) {
                i++;
            }
        processAndStoreLetterToPartyDocuments(lettertoParty);
        lettertoPartyService.save(lettertoParty);
        return "redirect:/lettertoparty/result/" + lettertoParty.getId();
    }

    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public String resultStakeHolder(@PathVariable final Long id, final Model model) {
        model.addAttribute("lettertoParty", lettertoPartyService.findById(id));
        LettertoParty lettertoParty = lettertoPartyService.findById(id);
        model.addAttribute("lettertopartydocList", lettertoParty.getLettertoPartyDocument());
        return "lettertoparty-result";
    }

    protected Set<FileStoreMapper> addToFileStore(final MultipartFile[] files) {
        if (ArrayUtils.isNotEmpty(files))
            return Arrays
                    .asList(files)
                    .stream()
                    .filter(file -> !file.isEmpty())
                    .map(file -> {
                        try {
                            return fileStoreService.store(file.getInputStream(), file.getOriginalFilename(),
                                    file.getContentType(), BpaConstants.FILESTORE_MODULECODE);
                        } catch (final Exception e) {
                            throw new ApplicationRuntimeException("Error occurred while getting inputstream", e);
                        }
                    }).collect(Collectors.toSet());
        else
            return null;
    }

    protected void processAndStoreLetterToPartyDocuments(final LettertoParty lettertoParty) {
        if (!lettertoParty.getLettertoPartyDocument().isEmpty())
            for (final LettertoPartyDocument lettertoPartyDocument : lettertoParty.getLettertoPartyDocument()) {
                lettertoPartyDocument.setChecklistDetail(checkListDetailService.load(lettertoPartyDocument.getChecklistDetail()
                        .getId()));
                lettertoPartyDocument.setLettertoParty(lettertoParty);
                lettertoPartyDocument.setSupportDocs(addToFileStore(lettertoPartyDocument.getFiles()));
            }
    }

}