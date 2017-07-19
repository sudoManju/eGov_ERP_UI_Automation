package tests.assetManagement;

import entities.requests.assetManagement.RequestInfo;
import org.testng.annotations.Test;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import java.io.IOException;
import static data.UserData.NARASAPPA;

public class AssetServiceTest {

    RequestInfo requestInfo;


    /* Tests Description
   ==========================
   Create Asset and
   Search Asset.
   Modify Asset and
   Search Asset.
*/
    //Create Land Asset
    @Test(groups = {Categories.ASSET, Categories.DEV})
    public void createLandAssetTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); //login

    }
}
