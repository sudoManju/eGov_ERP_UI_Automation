package tests.assetManagement;

import entities.responses.assetManagement.assetCategory.createCategory.CreateAssetCategoryResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;

import java.io.IOException;

import static data.SearchParameterData.WITH_CODE;
import static data.UserData.ADMIN;
import static data.UserData.AssetServiceUser;
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
        LoginAndLogoutHelper.loginFromPilotService(NARASAPPA);  // Login
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
<<<<<<< HEAD

    // Asset Service Tests
    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createAssetService() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(create.getAssetCategory()[0].getCode()); // Search Asset Category
        String assetCode = assetServiceHelper.createAssetService(create.getAssetCategory()[0].getCode(), // Create Asset Service
                create.getAssetCategory()[0].getName(), id);
        assetServiceHelper.searchAssetService(WITH_CODE, assetCode); // Search Asset Service
        pilotLogoutService(); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetService() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser); // Login
        assetServiceHelper.searchAssetService("", ""); // Search Asset Service
        pilotLogoutService(); // Logout
    }
=======
>>>>>>> [EGSVC-35]-UI: Changes updated for Create Employee
}