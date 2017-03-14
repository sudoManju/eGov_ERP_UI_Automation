package entities.error;

import java.util.List;

public class ErrorResponse {

    private String errorMessage;
    private String errorCode;

    private List<ErrorList> errorList;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<ErrorList> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorList> errorList) {
        this.errorList = errorList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
