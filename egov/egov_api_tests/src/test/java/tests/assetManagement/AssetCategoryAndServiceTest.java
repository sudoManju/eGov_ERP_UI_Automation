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
        LoginAndLogoutHelper.loginFromPilotService(ADMIN);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(create.getAssetCategory()[0].getCode()); // Search Asset Category
        pilotLogoutService(); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetCategoryTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        assetCategoryHelper.searchAssetCategory(null); // Search Asset Category
        pilotLogoutService(); // Logout
    }

    // Asset Service Tests
    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createAssetService() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN);  // Login
        CreateAssetCategoryResponse create = assetCategoryHelper.createAssetCategory(); // Create Asset Category
        int id = assetCategoryHelper.searchAssetCategory(create.getAssetCategory()[0].getCode()); // Search Asset Category
        String assetCode = assetServiceHelper.createAssetService(create.getAssetCategory()[0].getCode(), // Create Asset Service
                create.getAssetCategory()[0].getName(), id);
        assetServiceHelper.searchAssetService(assetCode); // Search Asset Service
        pilotLogoutService(); // Logout
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetService() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser); // Login
        assetServiceHelper.searchAssetService(null); // Search Asset Service
        pilotLogoutService(); // Logout
    }
}
