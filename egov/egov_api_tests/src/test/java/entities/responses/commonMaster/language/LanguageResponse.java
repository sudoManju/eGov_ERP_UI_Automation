package entities.responses.commonMaster.language;

import org.codehaus.jackson.annotate.JsonProperty;

public class LanguageResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;
    @JsonProperty("Language")
    private Language[] Language;

    public entities.responses.commonMaster.language.ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(entities.responses.commonMaster.language.ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public entities.responses.commonMaster.language.Language[] getLanguage() {
        return this.Language;
    }

    public void setLanguage(entities.responses.commonMaster.language.Language[] Language) {
        this.Language = Language;
    }
}
