package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.ServiceHistory;

public final class ServiceHistoryBuilder {

    ServiceHistory serviceHistory = new ServiceHistory();

    public ServiceHistoryBuilder() {
        serviceHistory.setOrderNo("A1");
        serviceHistory.setCreatedBy(1);
        serviceHistory.setServiceFrom("18/09/2016");
        serviceHistory.setLastModifiedBy(1);
        serviceHistory.setLastModifiedDate("18/09/2016");
        serviceHistory.setRemarks("None");
        serviceHistory.setCreatedDate("18/09/2016");
        serviceHistory.setServiceInfo("ADS1");
    }

    public ServiceHistoryBuilder withOrderNo(String orderNo) {
        serviceHistory.setOrderNo(orderNo);
        return this;
    }

    public ServiceHistoryBuilder withCreatedBy(int createdBy) {
        serviceHistory.setCreatedBy(createdBy);
        return this;
    }

    public ServiceHistoryBuilder withServiceFrom(String serviceFrom) {
        serviceHistory.setServiceFrom(serviceFrom);
        return this;
    }

    public ServiceHistoryBuilder withLastModifiedBy(int lastModifiedBy) {
        serviceHistory.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public ServiceHistoryBuilder withLastModifiedDate(String lastModifiedDate) {
        serviceHistory.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public ServiceHistoryBuilder withRemarks(String remarks) {
        serviceHistory.setRemarks(remarks);
        return this;
    }

    public ServiceHistoryBuilder withCreatedDate(String createdDate) {
        serviceHistory.setCreatedDate(createdDate);
        return this;
    }

    public ServiceHistoryBuilder withServiceInfo(String serviceInfo) {
        serviceHistory.setServiceInfo(serviceInfo);
        return this;
    }

    public ServiceHistory build() {
        return serviceHistory;
    }
}
