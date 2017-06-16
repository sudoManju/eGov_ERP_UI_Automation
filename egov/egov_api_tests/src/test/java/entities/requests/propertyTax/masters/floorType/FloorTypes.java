package entities.requests.propertyTax.masters.floorType;

import entities.requests.propertyTax.masters.AuditDetails;

public class FloorTypes
{
    private String tenantId;

    private int id;

    private String description;

    private String name;

    private String code;

    private String nameLocal;

    private AuditDetails auditDetails;

    public String getTenantId ()
    {
        return tenantId;
    }

    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getNameLocal ()
    {
        return nameLocal;
    }

    public void setNameLocal (String nameLocal)
    {
        this.nameLocal = nameLocal;
    }

    public AuditDetails getAuditDetails ()
    {
        return auditDetails;
    }

    public void setAuditDetails (AuditDetails auditDetails)
    {
        this.auditDetails = auditDetails;
    }
}
