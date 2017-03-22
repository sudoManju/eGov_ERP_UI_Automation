package entities.requests.eGovEIS.createAttendance;

public class CreateAttendanceRequest
{
    private RequestInfo RequestInfo;

    private Attendance Attendance;

    public RequestInfo getRequestInfo ()
    {
        return RequestInfo;
    }

    public void setRequestInfo (RequestInfo RequestInfo)
    {
        this.RequestInfo = RequestInfo;
    }

    public entities.requests.eGovEIS.createAttendance.Attendance getAttendance ()
    {
        return Attendance;
    }

    public void setAttendance (entities.requests.eGovEIS.createAttendance.Attendance Attendance)
    {
        this.Attendance = Attendance;
    }
}