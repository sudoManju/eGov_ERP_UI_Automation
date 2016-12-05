package entities.ptis;

/**
 * Created by karthik on 22/11/16.
 */
public class ConnectionDetails {

    String waterSourceType;
    String connectionType;
    String propertyType;
    String category;
    String usageType;
    String hscPipeSize;

    public String getWaterSourceType() {
        return waterSourceType;
    }

    public void setWaterSourceType(String waterSourceType) {
        this.waterSourceType = waterSourceType;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getHscPipeSize() {
        return hscPipeSize;
    }

    public void setHscPipeSize(String hscPipeSize) {
        this.hscPipeSize = hscPipeSize;
    }
}
