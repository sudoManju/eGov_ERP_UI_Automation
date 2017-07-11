package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.WorkFlowDetails;

public class WorkFlowDetailsBuilder {

    WorkFlowDetails workFlowDetails = new WorkFlowDetails();

    public WorkFlowDetailsBuilder(){
        workFlowDetails.setAction("no");
        workFlowDetails.setAssignee(14);
        workFlowDetails.setDepartment("incomeTax");
        workFlowDetails.setDesignation("manager");
        workFlowDetails.setStatus("processing");
    }

    public WorkFlowDetails build(){
        return workFlowDetails;
    }
}
