package org.egov.bpa.application.workflow;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.eis.entity.Assignment;
import org.egov.eis.service.AssignmentService;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.workflow.entity.State;
import org.egov.infra.workflow.entity.StateAware;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.egov.infra.workflow.matrix.service.CustomizedWorkFlowService;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaWorkFlowService {

    @Autowired
    protected AssignmentService assignmentService;
    @Autowired
    protected CustomizedWorkFlowService customizedWorkFlowService;

    public Assignment getWorkFlowInitiator(final BpaApplication application) {
        Assignment wfInitiator = null;
        List<Assignment> assignment;
        if (application != null)
            if (application.getState() != null
                    && application.getState().getInitiatorPosition() != null) {
                wfInitiator = getUserAssignmentByPassingPositionAndUser(application
                        .getCreatedBy(), application.getState().getInitiatorPosition());

                if (wfInitiator == null) {
                    assignment = assignmentService
                            .getAssignmentsForPosition(application.getState().getInitiatorPosition().getId(),
                                    new Date());
                    wfInitiator = getActiveAssignment(assignment);
                }
            } else
                wfInitiator = assignmentService.getPrimaryAssignmentForUser(application
                        .getCreatedBy().getId());
        return wfInitiator;
    }

    private Assignment getActiveAssignment(final List<Assignment> assignment) {
        Assignment wfInitiator = null;
        for (final Assignment assign : assignment)
            if (assign.getEmployee().isActive()) {
                wfInitiator = assign;
                break;
            }
        return wfInitiator;
    }

    public boolean validateUserHasSamePositionAsInitiator(final Long userId, final Position position) {

        Boolean userHasSamePosition = false;

        if (userId != null && position != null) {
            final List<Assignment> assignmentList = assignmentService.findByEmployeeAndGivenDate(userId, new Date());
            for (final Assignment assignment : assignmentList)
                if (position.getId() == assignment.getPosition().getId())
                    userHasSamePosition = true;
        }
        return userHasSamePosition;
    }

    private Assignment getUserAssignmentByPassingPositionAndUser(final User user, final Position position) {

        Assignment wfInitiatorAssignment = null;

        if (user != null && position != null) {
            final List<Assignment> assignmentList = assignmentService.findByEmployeeAndGivenDate(user.getId(), new Date());
            for (final Assignment assignment : assignmentList)
                if (position.getId() == assignment.getPosition().getId())
                    wfInitiatorAssignment = assignment;
        }

        return wfInitiatorAssignment;
    }

    /**
     * @param model
     * @param container
     * @return NextAction From Matrix With Parameters Type,CurrentState,CreatedDate
     */
    public String getNextAction(final StateAware model, final WorkflowContainer container) {

        WorkFlowMatrix wfMatrix = null;
        if (null != model && null != model.getId())
            if (null != model.getCurrentState())
                wfMatrix = customizedWorkFlowService.getWfMatrix(model.getStateType(),
                        container.getWorkFlowDepartment(), container.getAmountRule(), container.getAdditionalRule(),
                        model.getCurrentState().getValue(), container.getPendingActions(), model.getCreatedDate());
            else
                wfMatrix = customizedWorkFlowService.getWfMatrix(model.getStateType(),
                        container.getWorkFlowDepartment(), container.getAmountRule(), container.getAdditionalRule(),
                        State.DEFAULT_STATE_VALUE_CREATED, container.getPendingActions(), model.getCreatedDate());
        return wfMatrix == null ? "" : wfMatrix.getNextAction();
    }

    /**
     * @param model
     * @param container
     * @return List of WorkFlow Buttons From Matrix By Passing parametres Type,CurrentState,CreatedDate
     */
    public List<String> getValidActions(final StateAware model, final WorkflowContainer container) {
        List<String> validActions = Collections.emptyList();
        if (null == model
                || null == model.getId() || (model.getCurrentState() == null)
                || (model != null && model.getCurrentState() != null ? model.getCurrentState().getValue()
                        .equals("Closed")
                        || model.getCurrentState().getValue().equals("END") : false))
            validActions = Arrays.asList("Forward");
        else if (null != model.getCurrentState())
            validActions = customizedWorkFlowService.getNextValidActions(model.getStateType(), container
                    .getWorkFlowDepartment(), container.getAmountRule(), container.getAdditionalRule(), model
                            .getCurrentState().getValue(),
                    container.getPendingActions(), model.getCreatedDate());
        else
            validActions = customizedWorkFlowService.getNextValidActions(model.getStateType(),
                    container.getWorkFlowDepartment(), container.getAmountRule(), container.getAdditionalRule(),
                    State.DEFAULT_STATE_VALUE_CREATED, container.getPendingActions(), model.getCreatedDate());
        return validActions;
    }
}
