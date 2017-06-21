package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.ServiceHistory;

public class ServiceHistoryBuilder {

    ServiceHistory serviceHistory = new ServiceHistory();

    String[] documents = new String[0];

    public ServiceHistoryBuilder(){
        serviceHistory.setId(1);
        serviceHistory.setServiceInfo("efrthg");
        serviceHistory.setServiceFrom("15/03/2016");
        serviceHistory.setRemarks("efrgth");
        serviceHistory.setOrderNo("efgrthy");
        serviceHistory.setCreatedBy(61);
        serviceHistory.setLastModifiedBy(61);
        serviceHistory.setTenantId("ap.kurnool");
        serviceHistory.setDocuments(documents);
    }

    public ServiceHistoryBuilder(String s){
        serviceHistory.setId(2);
        serviceHistory.setServiceInfo("asdfgh");
        serviceHistory.setServiceFrom("15/03/2016");
        serviceHistory.setRemarks("asdfgh");
        serviceHistory.setOrderNo("asdfgh");
        serviceHistory.setCreatedBy(61);
        serviceHistory.setCreatedDate("21/03/2016");
        serviceHistory.setLastModifiedBy(61);
        serviceHistory.setLastModifiedDate("21/03/2016");
        serviceHistory.setTenantId("ap.kurnool");
        serviceHistory.setDocuments(documents);
    }

    public ServiceHistory build(){
        return serviceHistory;
    }
}
