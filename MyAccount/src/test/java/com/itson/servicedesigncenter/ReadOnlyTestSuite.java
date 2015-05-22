/*
/**Work in progress
 *
 * @author gurtejphangureh
 */

package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.*;


public class ReadOnlyTestSuite extends AbstractTest {


  protected String email;
  protected String password;
  protected String page = "features";
  private String savedFeature;
  private Browser browser;

  @BeforeClass
  @Parameters({"browser_type"})
  protected void beforeSetUp(String browser_type) {
 
      browser = new Browser.Builder()
                  .setBrowser_type(browser_type)
                  .build();
      password = browser.adminPass();
      email = browser.adminUser();
      portalPages.setPortalLogin(email, password);
      portalPages.setTopNavLink("features");
      browser.logAction("Creating Feature");
      savedFeature = browser.randomName() + "_readonly";
      //create new feature
      featurePages
        .setNewFeatureBtn("publishable")
        .setNewFeatureName(savedFeature)
        .setNewFeatureCode(savedFeature)
        .setNewFeatureDescription("Test 1")
        .setNewFeatureSavebtn();
      featurePages.addpolicy("data")
        .addNewComponent("new", savedFeature);

      //data polcy event
      featurePages
        .dataPolicyAddPolicyEvent(savedFeature);
      featurePages
        .dataSessionDefaultPolicyEventBlockNetworkGroupsIN("in_new", savedFeature);

    

  }
  /*
  @BeforeMethod
  @Parameters({"browser_type", "platform", "version", "setup"})
  public void setUp(String browser_type, String platform, String version, String setup)  {
    browser = new Browser.Builder()
                .setBrowser_type(browser_type)
                .setPlatform(platform)
                .setVersion(version)

                .build();
    environmentSetup = setup;
    FeaturePages featurePages = new FeaturePages(browser);
    SdcPages portalPages = new SdcPages(browser);
    //LogIn
    portalPages.setPortalLogin(viewOnly, viewOnlyPassword);
  }
  */

  @Test(dataProvider = "roles", groups = {"SDC","Zact","Sprint","ReadOnly"})
  public void checkRoles(String givenPage)  {
    FeaturePages featurePages = new FeaturePages(browser);
    //goto correct page
    portalPages.setTopNavLink(givenPage);

    if (givenPage.toLowerCase().equals("features")) {
      portalPages.verifyGroupsAdded(savedFeature);
    }
    if (browser.findElements(By.id("delete_row_0")).size() != 0) {
      throw new RuntimeException("Delete Should not be dispalyed, failed test");
    }
    //click on view
    portalPages.setTableEdit();


    if (givenPage.equals("features")) {
      //lock button should not be present
      if (browser.findElements(By.cssSelector("button.btn:nth-child(3)")).size() != 0) {
        throw new RuntimeException("Lock button Should not be dispalyed, failed test");
      }
      //click on network group
      browser.findElement(By.cssSelector(".tag-label")).click();
      //save button should not be present
      portalPages.checkSaveBtn();

      //clean up delete feature
      featurePages.delete(savedFeature, page);

    } else {
      //lock button should not be present
      if (browser.findElements(By.id("lock-btn")).size() != 0) {
        throw new RuntimeException("Lock button Should not be dispalyed, failed test");
      }
      //save button should not be present
      portalPages.checkSaveBtn();
    }
  }

  @DataProvider(name = "roles")
  public Object[][] createData11() {
    return new Object[][]{
                           {"features",},
                           {"subscriber"},
                           {"network",},
    };
  }

  @AfterMethod
  public void cleanUp()  {
    browser.close();
  }
}

  

