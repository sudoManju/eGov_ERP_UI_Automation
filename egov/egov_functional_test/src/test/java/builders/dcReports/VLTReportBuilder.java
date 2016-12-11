package builders.dcReports;

import entities.dcReports.VLTReport;

/**
 * Created by vinaykumar on 6/12/16.
 */
public class VLTReportBuilder {

    VLTReport vltReport = new VLTReport();

    public VLTReportBuilder withFromDate(String date) {
        vltReport.setFromDate(date);
        return this;
    }

    public VLTReportBuilder withToDate(String date) {
        vltReport.setToDate(date);
        return this;
    }

    public VLTReport build(){
        return vltReport;
    }
}
