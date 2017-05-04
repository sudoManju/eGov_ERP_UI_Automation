package builders.eGovEIS.hrMaster.designation;

import entities.requests.eGovEIS.hrMaster.designation.UserInfo;

public class UserInfoBuilder {

    UserInfo userInfo = new UserInfo();

    public UserInfoBuilder() {
        userInfo.setId(1);
    }

    public UserInfoBuilder withId(int id) {
        userInfo.setId(id);
        return this;
    }

    public UserInfo build() {
        return userInfo;
    }
}
