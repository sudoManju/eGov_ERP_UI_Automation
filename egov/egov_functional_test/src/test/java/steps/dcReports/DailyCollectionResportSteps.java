package steps.dcReports;


import cucumber.api.java8.En;
import entities.ptis.VLTReport;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import utils.ExcelReader;


/**
 * Created by vinaykumar on 9/12/16.
 */
public class DailyCollectionResportSteps extends BaseSteps implements En {

    public DailyCollectionResportSteps() {

        And("^user need to enter the date and get the report details$", () -> {
            String vltReportInfo = "report1";

            VLTReport vltReport = new ExcelReader(ptisTestDataFileName).getVLTReportInfo(vltReportInfo);
            pageStore.get(PropertyDetailsPage.class).enterVLTReportDetails(vltReport);
        });
    }
}
