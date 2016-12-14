package steps.dcReports;

import cucumber.api.java8.En;
import entities.dcReports.PTReport;
import entities.dcReports.VLTReport;
import pages.dcReports.DailyCollectionReportPage;
import pages.ptis.PropertyAcknowledgementPage;
import steps.BaseSteps;
import utils.ExcelReader;


/**
 * Created by vinaykumar on 9/12/16.
 */
public class DailyCollectionReportSteps extends BaseSteps implements En {

    public DailyCollectionReportSteps() {

        And("^user need to enter the date to get the vlt report details$", () -> {
            String vltReportInfo = "report1";

            VLTReport vltReport = new ExcelReader(ptisTestDataFileName).getVLTReportInfo(vltReportInfo);
            pageStore.get(DailyCollectionReportPage.class).enterVLTReportDetails(vltReport);
            pageStore.get(PropertyAcknowledgementPage.class).toCloseAdditionalConnectionPage();
        });

        And("^user need to enter the date to get the pt report details$", () -> {
            String ptReportInfo = "report2";

            PTReport ptReport = new ExcelReader(ptisTestDataFileName).getPTReportInfo(ptReportInfo);
            pageStore.get(DailyCollectionReportPage.class).enterPTReportDetails(ptReport);
            pageStore.get(PropertyAcknowledgementPage.class).toCloseAdditionalConnectionPage();

        });
    }
}
