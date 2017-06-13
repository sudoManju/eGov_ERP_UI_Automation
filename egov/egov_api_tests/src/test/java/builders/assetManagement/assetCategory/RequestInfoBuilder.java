package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.RequestInfo;
import entities.requests.assetManagement.assetCategory.UserInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    UserInfo userInfo = new UserInfoBuilder().build();

    public RequestInfoBuilder(){
        requestInfo.setUserInfo(userInfo);
    }

    public RequestInfo build(){
        return requestInfo;
    }
}
