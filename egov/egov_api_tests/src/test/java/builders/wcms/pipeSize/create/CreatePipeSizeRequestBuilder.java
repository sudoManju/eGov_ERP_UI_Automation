package builders.wcms.pipeSize.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.pipeSize.create.CreatePipeSizeRequest;
import entities.requests.wcms.pipeSize.create.PipeSize;

public class CreatePipeSizeRequestBuilder {
    CreatePipeSizeRequest createPipeSizeRequest = new CreatePipeSizeRequest();

    public CreatePipeSizeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createPipeSizeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreatePipeSizeRequestBuilder withPipeSize(PipeSize PipeSize) {
        createPipeSizeRequest.setPipeSize(PipeSize);
        return this;
    }

    public CreatePipeSizeRequest build() {
        return createPipeSizeRequest;
    }
}
