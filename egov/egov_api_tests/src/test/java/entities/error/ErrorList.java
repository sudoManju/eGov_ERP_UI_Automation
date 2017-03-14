package entities.error;

public class ErrorList {
    private String param;

    private String value;

    private String msg;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorList errorList = (ErrorList) o;

        if (param != null ? !param.equals(errorList.param) : errorList.param != null) return false;
        if (value != null ? !value.equals(errorList.value) : errorList.value != null) return false;
        return msg != null ? msg.equals(errorList.msg) : errorList.msg == null;

    }

}
