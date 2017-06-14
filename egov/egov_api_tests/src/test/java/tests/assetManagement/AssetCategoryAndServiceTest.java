package tests.assetManagement;

import entities.responses.assetManagement.assetCategory.createCategory.CreateAssetCategoryResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;

import java.io.IOException;

import static data.UserData.ADMIN;
import static data.UserData.AssetServiceUser;

public class AssetCategoryAndServiceTest extends BaseAPITest {

    /* Tests Description
       ==========================
       Create Asset Category and Search the Created One
       Search Asset Category
       Create Asset Service Along with Create Asset Category and also Search the Created Asset Service
       Search Asset Service
    */

    private AssetCategoryHelper assetCategoryHelper;
    private AssetServiceHelper assetServiceHelper;

    public AssetCategoryAndServiceTest() {
        assetCategoryHelper = new AssetCategoryHelper();
        assetServiceHelper = new AssetServiceHelper();
    }

    // Asset Category Tests
    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createAssetCategoryTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(sessionId); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(sessionId, create.getAssetCategory()[0].getCode()); // Search Asset Category
        pilotLogoutService(sessionId); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetCategoryTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        assetCategoryHelper.searchAssetCategory(sessionId, null); // Search Asset Category
        pilotLogoutService(sessionId); // Logout
    }

    // Asset Service Tests
    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createAssetService() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(sessionId); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(sessionId, create.getAssetCategory()[0].getCode()); // Search Asset Category
        String assetCode = assetServiceHelper.createAssetService(sessionId, create.getAssetCategory()[0].getCode(), // Create Asset Service
                create.getAssetCategory()[0].getName(), id);
        assetServiceHelper.searchAssetService(sessionId, assetCode); // Search Asset Service
        pilotLogoutService(sessionId); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetService() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser); // Login
        assetServiceHelper.searchAssetService(sessionId, null); // Search Asset Service
        pilotLogoutService(sessionId); // Logout
    }
}
