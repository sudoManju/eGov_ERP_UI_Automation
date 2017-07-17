package data;

public interface SearchParameterData {

    String WITH_IDS = "&ids=";
    String WITH_CODE = "&code=";
    String WITH_NAME = "&name=";
    String WITH_ID = "&id=";
    String WITH_PARAMETER = ""; // We can use this static constant whenever there is more than 2 query parameters
    String WITH_MILLIMETERSIZE = "&sizeInMilimeter=";
    String WITH_APPLICATION_TYPE = "&applicationType";
    String WITH_DOCUMENT_TYPE = "&documentType=";
    String WITH_NAMELOCAL = "&nameLocal=";
    String WITH_CODE1 = "?code=";
    String WITH_STATUS = "&status=";
    String TENANT_KURNOOL = "ap.kurnool";
    String TENANT_DEFAULT = "default";
}
