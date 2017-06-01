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
import java.util.Comparator;
import java.util.List;
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
import org.egov.bpa.service.BpaThirdPartyService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.eis.service.PositionMasterService;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.workflow.entity.StateHistory;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
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
	private static final String REDIRECT_LETTERTOPARTY_RESULT = "redirect:/lettertoparty/result/";
	private static final String CHECK_LIST_DETAIL_LIST = "checkListDetailList";
	private static final String LETTERTOPARTYDOC_LIST = "lettertopartydocList";
	private static final String LETTERTO_PARTY = "lettertoParty";
	private static final String BPA_APPLICATION = "bpaApplication";
	private static final String MESSAGE = "message";
	static final String LPCHK = "lp";
	static final String LPREPLYCHK = "lpreply";

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
	protected ResourceBundleMessageSource messageSource;
	@Autowired
	private LettertoPartyDocumentService lettertoPartyDocumentService;
	@Autowired
	private SecurityUtils securityUtils;
	@Autowired
    private PositionMasterService positionMasterService;
	@Autowired
	protected BpaThirdPartyService bpaThirdPartyService;
	@ModelAttribute("lpReasonList")
	public List<LpReason> getLpReasonList() {
		return lpReasonService.findAll();
	}

	public List<CheckListDetail> getCheckListDetailList(final Long serviceTypeId) {
		return checkListDetailService.findActiveCheckListByServiceType(serviceTypeId, BpaConstants.CHECKLIST_TYPE);
	}

	@RequestMapping(value = "/create/{applicationNumber}", method = GET)
	public String createLetterToParty(@ModelAttribute final LettertoParty lettertoParty,
			@PathVariable final String applicationNumber, final Model model, final HttpServletRequest request) {
		prepareData(lettertoParty, applicationNumber, model);
		return "lettertoparty-create";
	}

	private void prepareData(final LettertoParty lettertoParty, final String applicationNumber, final Model model) {
		model.addAttribute("mode", "new");
		BpaApplication bpaApplication = applicationBpaService.findByApplicationNumber(applicationNumber);
		model.addAttribute(BPA_APPLICATION, bpaApplication);
		model.addAttribute(CHECK_LIST_DETAIL_LIST, getCheckListDetailList(bpaApplication.getServiceType().getId()));
		lettertoParty.setApplication(bpaApplication);
	}

	public void validateCreateLetterToParty(LettertoParty lettertoParty, BindingResult errors) {
		if (lettertoParty.getLpReason() == null)
			errors.rejectValue("lpReason", "lbl.lp.reason.required");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLetterToParty(@ModelAttribute final LettertoParty lettertoParty,
			final BindingResult resultBinder, final Model model, final HttpServletRequest request,
			final BindingResult errors, final RedirectAttributes redirectAttributes) {
		validateCreateLetterToParty(lettertoParty, errors);
		if (errors.hasErrors()) {
			prepareData(lettertoParty, lettertoParty.getApplication().getApplicationNumber(), model);
			return "lettertoparty-create";
		}
		processAndStoreLetterToPartyDocuments(lettertoParty);
		lettertoParty.setCurrentApplnStatus(lettertoParty.getApplication().getStatus());
		lettertoParty.setCurrentStateValueOfLP(lettertoParty.getApplication().getStateHistory().stream()
				.sorted(Comparator.comparing(StateHistory::getId).reversed()).collect(Collectors.toList()).get(0)
				.getValue());
		lettertoParty.setStateForOwnerPosition(lettertoParty.getApplication().getState().getValue());
		List<LettertoParty> existingLettertoParties = lettertoPartyService.findByBpaApplicationOrderByIdDesc(lettertoParty.getApplication());
		if(!existingLettertoParties.isEmpty()){
			LettertoParty existingLpParty = existingLettertoParties.get(0);
			if(existingLpParty.getCreatedBy().equals(securityUtils.getCurrentUser()) && existingLpParty.getCurrentApplnStatus().equals(lettertoParty.getCurrentApplnStatus())){
				lettertoParty.setCurrentStateValueOfLP(existingLpParty.getCurrentStateValueOfLP());
				lettertoParty.setStateForOwnerPosition(existingLpParty.getStateForOwnerPosition());
			}
		}
		
		lettertoPartyService.save(lettertoParty);
		Long approverPosition = lettertoPartyService.getDocScutinyUser(lettertoParty.getApplication());
		Position pos = positionMasterService.getPositionById(approverPosition);
		User user = bpaThirdPartyService.getUserPositionByPassingPosition(approverPosition);
		String message = messageSource.getMessage("msg.lp.forward.create", new String[] {
                user != null ? user.getUsername().concat("~")
                        .concat(pos.getDeptDesig() != null && pos.getDeptDesig().getDesignation() != null
                                ? pos.getDeptDesig().getDesignation().getName() : "")
                        : "",
                        lettertoParty.getLpNumber(), lettertoParty.getApplication().getApplicationNumber() }, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute(MESSAGE, message);
		return REDIRECT_LETTERTOPARTY_RESULT + lettertoParty.getId();
	}

	@RequestMapping(value = "/update/{applicationNumber}", method = RequestMethod.GET)
	public String editLetterToParty(@PathVariable final String applicationNumber, final Model model) {
		prepareLetterToParty(applicationNumber, model);
		return "lettertoparty-update";
	}

	private void prepareLetterToParty(String applicationNumber, Model model) {
		final BpaApplication bpaApplication = applicationBpaService.findByApplicationNumber(applicationNumber);
		final List<LettertoParty> lettertoPartyList = lettertoPartyService
				.findByBpaApplicationOrderByIdDesc(bpaApplication);
		LettertoParty lettertoParty = null;
		if (!lettertoPartyList.isEmpty())
			lettertoParty = lettertoPartyList.get(0);
		if (lettertoParty != null) {
			model.addAttribute(LETTERTO_PARTY, lettertoParty);
			model.addAttribute(LETTERTOPARTYDOC_LIST, lettertoParty.getLettertoPartyDocument());
		}
		model.addAttribute(CHECK_LIST_DETAIL_LIST, getCheckListDetailList(bpaApplication.getServiceType().getId()));
		model.addAttribute(BPA_APPLICATION, bpaApplication);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateLettertoparty(@ModelAttribute final LettertoParty lettertoparty, final Model model,
			final HttpServletRequest request, final BindingResult errors, final RedirectAttributes redirectAttributes) {
		processAndStoreLetterToPartyDocuments(lettertoparty);
		lettertoPartyService.save(lettertoparty);
		redirectAttributes.addFlashAttribute(MESSAGE,
				messageSource.getMessage("msg.lettertoparty.update.success", null, null));
		return REDIRECT_LETTERTOPARTY_RESULT + lettertoparty.getId();
	}

	@RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
	public String resultLettertoParty(@PathVariable final Long id, final Model model) {
		model.addAttribute(LETTERTO_PARTY, lettertoPartyService.findById(id));
		LettertoParty lettertoParty = lettertoPartyService.findById(id);
		model.addAttribute(LETTERTOPARTYDOC_LIST, lettertoParty.getLettertoPartyDocument());
		model.addAttribute("lettertopartylist",
				lettertoPartyService.findByBpaApplicationOrderByIdDesc(lettertoParty.getApplication()));
		return "lettertoparty-result";
	}

	protected Set<FileStoreMapper> addToFileStore(final MultipartFile[] files) {
		if (ArrayUtils.isNotEmpty(files))
			return Arrays.asList(files).stream().filter(file -> !file.isEmpty()).map(file -> {
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
				lettertoPartyDocument.setChecklistDetail(
						checkListDetailService.load(lettertoPartyDocument.getChecklistDetail().getId()));
				lettertoPartyDocument.setLettertoParty(lettertoParty);
				lettertoPartyDocument.setSupportDocs(addToFileStore(lettertoPartyDocument.getFiles()));
			}
	}

	@RequestMapping(value = "/lettertopartyprint/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> generateLettertoParty(@PathVariable final String type,
			final HttpServletRequest request, final HttpSession session) {
		final LettertoParty lettertoParty = lettertoPartyService.findById(new Long(request.getParameter("pathVar")));
		return lettertoPartyService.generateReport(lettertoParty, type, request);
	}

	@RequestMapping(value = "/viewdetails/{type}/{id}", method = RequestMethod.GET)
	public String viewchecklist(@PathVariable final Long id, @PathVariable final String type, final Model model) {
		LettertoParty lettertoParty = lettertoPartyService.findById(id);
		if (type.equals(LPCHK))
			model.addAttribute(LETTERTOPARTYDOC_LIST, lettertoPartyDocumentService
					.findByIsrequestedTrueAndLettertoPartyOrderByIdAsc(lettertoPartyService.findById(id)));
		else if (type.equals(LPREPLYCHK))
			model.addAttribute(LETTERTOPARTYDOC_LIST,
					lettertoPartyDocumentService.findByIsrequestedTrueAndIssubmittedTrueAndLettertoPartyOrderByIdAsc(
							lettertoPartyService.findById(id)));
		model.addAttribute(LETTERTO_PARTY, lettertoParty);
		return "lettertoparty-view";
	}

	@RequestMapping(value = "/capturesentdate/{id}", method = RequestMethod.GET)
	public String capturesentdate(@PathVariable final Long id, final Model model) {
		LettertoParty lettertoParty = lettertoPartyService.findById(id);
		model.addAttribute(LETTERTO_PARTY, lettertoParty);
		model.addAttribute(LETTERTOPARTYDOC_LIST, lettertoParty.getLettertoPartyDocument());
		model.addAttribute(BPA_APPLICATION, lettertoParty.getApplication());
		return "lettertoparty-capturesentdate";
	}

	@RequestMapping(value = "/lettertopartyreply/{id}", method = RequestMethod.GET)
	public String createLettertoPartyReply(@PathVariable final Long id, final Model model) {
		LettertoParty lettertoParty = lettertoPartyService.findById(id);
		model.addAttribute(LETTERTO_PARTY, lettertoParty);
		model.addAttribute(LETTERTOPARTYDOC_LIST, lettertoParty.getLettertoPartyDocument());
		model.addAttribute(BPA_APPLICATION, lettertoParty.getApplication());
		model.addAttribute(CHECK_LIST_DETAIL_LIST,
				getCheckListDetailList(lettertoParty.getApplication().getServiceType().getId()));
		return "lettertoparty-lpreply";
	}

	@RequestMapping(value = "/lettertopartyreply", method = RequestMethod.POST)
	public String createLettertoPartyReply(@ModelAttribute final LettertoParty lettertoparty, final Model model,
			final HttpServletRequest request, final BindingResult errors, final RedirectAttributes redirectAttributes) {
		processAndStoreLetterToPartyDocuments(lettertoparty);
		lettertoPartyService.save(lettertoparty);
		redirectAttributes.addFlashAttribute(MESSAGE,
				messageSource.getMessage("msg.lettertoparty.reply.success", null, null));
		return REDIRECT_LETTERTOPARTY_RESULT + lettertoparty.getId();
	}
}