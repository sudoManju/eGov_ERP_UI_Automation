package builders.eGovEIS.leaveManagement.create;

import entities.requests.eGovEIS.leaveManagement.create.UserInfo;

public class UserInfoBuilder {
    UserInfo userInfo = new UserInfo();

    public UserInfoBuilder() {
        userInfo.setId(250903);
        userInfo.setTenantId("ap.kurnool");
        userInfo.setUserName("KEMP210639");
    }

    public UserInfoBuilder withId(int id) {
        userInfo.setId(id);
        return this;
    }

    public UserInfo build() {
        return userInfo;
    }
}
