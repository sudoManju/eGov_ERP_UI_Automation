package builders.eGovEIS.Attendances;

import entities.requests.eGovEIS.Attendances.Attendance;
import entities.requests.eGovEIS.Attendances.CreateAttendanceRequest;
import entities.requests.eGovEIS.Attendances.RequestInfo;


public class CreateAttendanceRequestBuilder {

    CreateAttendanceRequest createAttendanceRequest = new CreateAttendanceRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();
    Attendance attendance = new AttendanceBuilder().build();
    Attendance[] attendances = new Attendance[1];

    public CreateAttendanceRequestBuilder() {
        createAttendanceRequest.setRequestInfo(requestInfo);
        attendances[0] = attendance;
        createAttendanceRequest.setAttendance(attendances);
    }

    public CreateAttendanceRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createAttendanceRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateAttendanceRequestBuilder withAttendance(Attendance Attendance) {
        attendances[0] = Attendance;
        createAttendanceRequest.setAttendance(attendances);
        return this;
    }

    public CreateAttendanceRequest build() {
        return createAttendanceRequest;
    }
}
