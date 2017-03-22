package builders.eGovEIS;

import entities.requests.eGovEIS.createAttendance.Attendance;
import entities.requests.eGovEIS.createAttendance.CreateAttendanceRequest;
import entities.requests.eGovEIS.createAttendance.RequestInfo;


public class CreateAttendanceRequestBuilder {

    CreateAttendanceRequest createAttendanceRequest = new CreateAttendanceRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();
//    RequestInfo requestInfo = new RequestInfoBuilder().build();
    Attendance attendance = new AttendanceBuilder().build();

    public CreateAttendanceRequestBuilder() {
        createAttendanceRequest.setRequestInfo(requestInfo);
        createAttendanceRequest.setAttendance(attendance);
    }

    public CreateAttendanceRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createAttendanceRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateAttendanceRequestBuilder withAttendance(Attendance Attendance) {
        createAttendanceRequest.setAttendance(Attendance);
        return this;
    }

    public CreateAttendanceRequest build() {
        return createAttendanceRequest;
    }
}
