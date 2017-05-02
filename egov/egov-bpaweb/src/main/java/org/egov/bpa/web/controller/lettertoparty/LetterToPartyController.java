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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.LettertoParty;
import org.egov.bpa.application.entity.LettertoPartyDocument;
import org.egov.bpa.application.entity.LpReason;
import org.egov.bpa.application.service.ApplicationBpaService;
import org.egov.bpa.application.service.CheckListDetailService;
import org.egov.bpa.application.service.LettertoPartyDocumentService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.application.service.LpReasonService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.infra.reporting.engine.ReportRequest;
import org.egov.infra.reporting.engine.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private ReportService reportService;
    @Autowired
    protected ResourceBundleMessageSource messageSource;
    @Autowired
    private LettertoPartyDocumentService lettertoPartyDocumentService;
    private static final String MESSAGE = "message";
    static final String LPCHK = "lp";
    static final String LPREPLYCHK = "lpreply";

    @ModelAttribute("lpReasonList")
    public List<LpReason> getLpReasonList() {
        return lpReasonService.findAll();
    }

    @ModelAttribute("checkListDetailList")
    public List<CheckListDetail> checkListDetailList() {
        return checkListDetailService.findActiveCheckListByChecklistType(BpaConstants.CHECKLIST_TYPE);
    }

    @RequestMapping(value = "/create/{applicationNumber}", method = GET)
    public String createLetterToParty(@ModelAttribute final LettertoParty lettertoParty,
            @PathVariable final String applicationNumber, final Model model, final HttpServletRequest request) {
        model.addAttribute("mode", "new");
        BpaApplication bpaApplication = applicationBpaService.findByApplicationNumber(applicationNumber);
        model.addAttribute("bpaApplication", bpaApplication);
        lettertoParty.setApplication(bpaApplication);
        return "lettertoparty-create";
    }

    public void validateCreateLetterToParty(LettertoParty lettertoParty, BindingResult errors) {
        boolean required = false;
        if (lettertoParty.getLpReason() == null)
            errors.rejectValue("lpReason", "lbl.lp.reason.required");
        for (final LettertoPartyDocument lettertoPartyDocument : lettertoParty.getLettertoPartyDocument()) {
            if (lettertoPartyDocument.getIsrequested())
                required = Boolean.TRUE;
        }
        if (!required)
            errors.rejectValue("lettertoPartyDocument[1].isrequested", "lbl.dorequired.required");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLetterToParty(@ModelAttribute final LettertoParty lettertoParty,
            final BindingResult resultBinder, final Model model,
            final HttpServletRequest request, final BindingResult errors, final RedirectAttributes redirectAttributes) {
        validateCreateLetterToParty(lettertoParty, errors);
        if (errors.hasErrors())
            return "lettertoparty-create";
        processAndStoreLetterToPartyDocuments(lettertoParty);
        lettertoPartyService.save(lettertoParty);
        redirectAttributes.addFlashAttribute(MESSAGE, messageSource.getMessage("msg.lettertoparty.create.success", null, null));
        return "redirect:/lettertoparty/result/" + lettertoParty.getId();
    }

    @RequestMapping(value = "/update/{applicationNumber}", method = RequestMethod.GET)
    public String editLetterToParty(@PathVariable final String applicationNumber, final Model model) {
        prepareLetterToParty(applicationNumber, model);
        return "lettertoparty-update";
    }

    private void prepareLetterToParty(String applicationNumber, Model model) {
        final BpaApplication bpaApplication = applicationBpaService.findByApplicationNumber(applicationNumber);
        final List<LettertoParty> lettertoPartyList = lettertoPartyService.findByBpaApplicationOrderByIdAsc(bpaApplication);
        LettertoParty lettertoParty = null;
        if (!lettertoPartyList.isEmpty())
            lettertoParty = lettertoPartyList.get(0);
        if (lettertoParty != null) {
            model.addAttribute("lettertoParty", lettertoParty);
            model.addAttribute("lettertopartydocList", lettertoParty.getLettertoPartyDocument());
        }
        model.addAttribute("bpaApplication", bpaApplication);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateLettertoparty(@ModelAttribute final LettertoParty lettertoparty,
            final Model model,
            final HttpServletRequest request,
            final BindingResult errors, final RedirectAttributes redirectAttributes) {
        processAndStoreLetterToPartyDocuments(lettertoparty);
        lettertoPartyService.save(lettertoparty);
        redirectAttributes.addFlashAttribute(MESSAGE,
                messageSource.getMessage("msg.lettertoparty.update.success", null, null));
        return "redirect:/lettertoparty/result/" + lettertoparty.getId();
    }

    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public String resultLettertoParty(@PathVariable final Long id, final Model model) {
        model.addAttribute("lettertoParty", lettertoPartyService.findById(id));
        LettertoParty lettertoParty = lettertoPartyService.findById(id);
        model.addAttribute("lettertopartydocList", lettertoParty.getLettertoPartyDocument());
        model.addAttribute("lettertopartylist",
                lettertoPartyService.findByBpaApplicationOrderByIdAsc(lettertoParty.getApplication()));
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

    @RequestMapping(value = "/lettertopartyprint/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> generateLettertoParty(@PathVariable final String type, final HttpServletRequest request,
            final HttpSession session) {
        final LettertoParty lettertoParty = lettertoPartyService.findById(new Long(request.getParameter("pathVar")));
        return generateReport(lettertoParty, type);
    }

    private ResponseEntity<byte[]> generateReport(final LettertoParty lettertoParty,
            String type) {
        ReportRequest reportInput = null;
        ReportOutput reportOutput;
        if (lettertoParty != null) {
            Map<String, Object> reportParams = null;
            if (LPCHK.equals(type))
                reportInput = new ReportRequest("lettertoparty", lettertoParty, reportParams);
            else if (LPREPLYCHK.equals(type))
                reportInput = new ReportRequest("lettertopartyreply", lettertoParty, reportParams);
            reportInput.setPrintDialogOnOpenReport(true);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        if (LPCHK.equals(type))
            headers.add("content-disposition", "inline;filename=lettertoparty.pdf");
        else if (LPREPLYCHK.equals(type))
            headers.add("content-disposition", "inline;filename=reply.pdf");
        reportOutput = reportService.createReport(reportInput);
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/viewchecklist/{type}/{id}", method = RequestMethod.GET)
    public String viewchecklist(@PathVariable final Long id, @PathVariable final String type, final Model model) {
        if (type.equals(LPCHK))
            model.addAttribute("lettertopartydocList",
                    lettertoPartyDocumentService
                            .findByIsrequestedTrueAndLettertoPartyOrderByIdAsc(lettertoPartyService.findById(id)));
        else if (type.equals(LPREPLYCHK))
            model.addAttribute("lettertopartydocList",
                    lettertoPartyDocumentService
                            .findByIsrequestedTrueAndIssubmittedTrueAndLettertoPartyOrderByIdAsc(
                                    lettertoPartyService.findById(id)));
        return "lettertoparty-checklist";
    }

    @RequestMapping(value = "/capturesentdate/{id}", method = RequestMethod.GET)
    public String capturesentdate(@PathVariable final Long id, final Model model) {
        LettertoParty lettertoParty = lettertoPartyService.findById(id);
        model.addAttribute("lettertoParty", lettertoParty);
        model.addAttribute("lettertopartydocList", lettertoParty.getLettertoPartyDocument());
        model.addAttribute("bpaApplication", lettertoParty.getApplication());
        return "lettertoparty-capturesentdate";
    }

    @RequestMapping(value = "/lettertopartyreply/{id}", method = RequestMethod.GET)
    public String createLettertoPartyReply(@PathVariable final Long id, final Model model) {
        LettertoParty lettertoParty = lettertoPartyService.findById(id);
        model.addAttribute("lettertoParty", lettertoParty);
        model.addAttribute("lettertopartydocList", lettertoParty.getLettertoPartyDocument());
        model.addAttribute("bpaApplication", lettertoParty.getApplication());
        return "lettertoparty-lpreply";
    }

    @RequestMapping(value = "/lettertopartyreply", method = RequestMethod.POST)
    public String createLettertoPartyReply(@ModelAttribute final LettertoParty lettertoparty,
            final Model model,
            final HttpServletRequest request,
            final BindingResult errors, final RedirectAttributes redirectAttributes) {
        processAndStoreLetterToPartyDocuments(lettertoparty);
        lettertoPartyService.save(lettertoparty);
        redirectAttributes.addFlashAttribute(MESSAGE,
                messageSource.getMessage("msg.lettertoparty.reply.success", null, null));
        return "redirect:/lettertoparty/result/" + lettertoparty.getId();
    }
}