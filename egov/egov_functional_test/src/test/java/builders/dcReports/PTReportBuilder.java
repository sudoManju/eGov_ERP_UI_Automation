package builders.dcReports;

import entities.dcReports.PTReport;

/**
 * Created by vinaykumar on 11/12/16.
 */
public class PTReportBuilder {

    PTReport ptReport = new PTReport();

    public PTReportBuilder withFromDate(String date) {
        ptReport.setFromDate(date);
        return this;
    }

    public PTReportBuilder withToDate(String date) {
        ptReport.setToDate(date);
        return this;
    }

    public PTReport build(){
        return ptReport;
    }
}
