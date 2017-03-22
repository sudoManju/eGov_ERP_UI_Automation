package entities.responses.eGovEIS.createAttendance;

public class CreateAttendanceResponse {
    private ResponseInfo ResponseInfo;
    private Attendance[] Attendance;

    public ResponseInfo getResponseInfo() {
        return ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public Attendance[] getAttendance() {
        return Attendance;
    }

    public void setAttendance(Attendance[] Attendance) {
        this.Attendance = Attendance;
    }
}
