package entities.responses.assetManagement.assetCategory.searchCategory;

public class AssetFieldsDefination {
    private Object localText;
    private Object columns;
    private Object values;
    private String name;
    private String type;
    private boolean isActive;
    private Object isMandatory;
    private Object regExFormate;
    private Object url;
    private Object order;

    public Object getLocalText() {
        return this.localText;
    }

    public void setLocalText(Object localText) {
        this.localText = localText;
    }

    public Object getColumns() {
        return this.columns;
    }

    public void setColumns(Object columns) {
        this.columns = columns;
    }

    public Object getValues() {
        return this.values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Object getIsMandatory() {
        return this.isMandatory;
    }

    public void setIsMandatory(Object isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Object getRegExFormate() {
        return this.regExFormate;
    }

    public void setRegExFormate(Object regExFormate) {
        this.regExFormate = regExFormate;
    }

    public Object getUrl() {
        return this.url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getOrder() {
        return this.order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }
}
