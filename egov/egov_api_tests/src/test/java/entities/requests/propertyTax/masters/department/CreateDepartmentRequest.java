package entities.requests.propertyTax.masters.department;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDepartmentRequest {

    private Departments[] departments;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public Departments[] getDepartments() {
        return this.departments;
    }

    public void setDepartments(Departments[] departments) {
        this.departments = departments;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
