package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.UserInfo;

public class UserInfoBuilder {

    UserInfo userInfo = new UserInfo();

    public UserInfoBuilder() {
        userInfo.setId(1);
    }

    public UserInfo build() {
        return userInfo;
    }
}
