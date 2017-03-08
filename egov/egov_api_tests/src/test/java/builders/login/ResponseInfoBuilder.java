package builders.login;

import entities.login.ResponseInfo;

public class ResponseInfoBuilder {

    ResponseInfo responseInfo = new ResponseInfo();

    public ResponseInfoBuilder() {
        responseInfo.setApi_id("1");
    }

    public ResponseInfo build(){
        return responseInfo;
    }
}
