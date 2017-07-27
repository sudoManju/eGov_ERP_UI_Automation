package tests.assetManagement;

import entities.responses.assetManagement.createCategory.CreateAssetCategoryResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class AssetCategoryAndServiceTest extends BaseAPITest {

    /* Tests Description
       ==========================
       Create Asset Category and Search the Created One
       Search Asset Category
    */

    private AssetCategoryHelper assetCategoryHelper;

    public AssetCategoryAndServiceTest() {
        assetCategoryHelper = new AssetCategoryHelper();
    }

    // Asset Category Tests
    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createAssetCategoryTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(create.getAssetCategory()[0].getCode()); // Search Asset Category
        pilotLogoutService(); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetCategoryTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(NARASAPPA); // Login
        assetCategoryHelper.searchAssetCategory(null); // Search Asset Category
        pilotLogoutService(); // Logout
    }

}