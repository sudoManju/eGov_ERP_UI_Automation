package builders.works;

import entities.works.ApproverDetails;

/**
 * Created by karthik on 28/12/16.
 */
public class ApproverDetailsBuilder {

    ApproverDetails approverDetails = new ApproverDetails();

    public ApproverDetailsBuilder(){}

    public ApproverDetailsBuilder withApproverDesignation(String approverDesignation){
        approverDetails.setApproverDesignation(approverDesignation);
        return this;
    }

    public ApproverDetailsBuilder withApproverDepartment(String approverDepartment){
        approverDetails.setApproverDepartment(approverDepartment);
        return this;
    }

    public ApproverDetailsBuilder withApprover(String approver){
        approverDetails.setApprover(approver);
        return this;
    }

    public ApproverDetailsBuilder withApproverComment(String approverComment)
    {
        approverDetails.setApproverComment(approverComment);
        return this;
    }

    public ApproverDetails build(){
        return approverDetails;
    }

}
