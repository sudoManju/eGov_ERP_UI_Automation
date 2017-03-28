package entities.responses.eGovEIS.employeeMasters;

import entities.responses.eGovEIS.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchEmployeeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("Employee")
    private Employee[] Employee;

    public ResponseInfo getResponseInfo() {
        return ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public Employee[] getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee[] Employee) {
        this.Employee = Employee;
    }
}