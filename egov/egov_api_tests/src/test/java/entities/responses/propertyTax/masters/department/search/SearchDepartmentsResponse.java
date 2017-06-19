package entities.responses.propertyTax.masters.department.search;

public class SearchDepartmentsResponse {
    private Departments[] departments;
    private ResponseInfo responseInfo;

    public Departments[] getDepartments() {
        return this.departments;
    }

    public void setDepartments(Departments[] departments) {
        this.departments = departments;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
