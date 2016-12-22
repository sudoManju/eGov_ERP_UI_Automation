package steps.works;


import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.DashboardPage;
import pages.works.MilestoneTemplatePage;
import steps.BaseSteps;

/**
 * Created by karthik on 21/12/16.
 */
public class MilestoneTemplateSteps extends BaseSteps implements En {

   public MilestoneTemplateSteps(){
       And("^he chooses to create milestone template$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateMilestoneTemplate();
       });
       And("^he enters the milestone template creation details$", () -> {
          pageStore.get(MilestoneTemplatePage.class).enterMilestoneTemplateDetails();
       });
       And("^he save the file and closes the acknowledgement$", () -> {
           pageStore.get(MilestoneTemplatePage.class).saveAndClose();
       });
   }
}
