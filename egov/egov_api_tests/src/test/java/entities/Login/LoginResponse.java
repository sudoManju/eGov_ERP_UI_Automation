package entities.Login;

public class LoginResponse {
    private User User;

    private ResponseInfo responseInfo;

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    @Override
    public String toString() {
        return "ClassPojo [User = " + User + ", Response = " + responseInfo + "]";
    }
}