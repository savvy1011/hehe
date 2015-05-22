/**
 * cleans up by deleting features , subscriber and network groups
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GroupsCleanUp {

  protected String adminUser;
  protected String adminPass;
  protected String phoneNumber;
  protected String givenName = "webqe";
  protected long suiteTimer = 0;
  protected String groupName;
  protected String savedNetworkinout;
  protected String groupName2;
  protected String groupName3;
  protected String gotText;
  protected String topnav;
  protected String partnerId;
  protected String url;
  protected PortalPages portalPages;
  protected FeaturePages featurePages;
  protected Browser browser;
  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();

  @BeforeClass(alwaysRun = true)
  protected void classSetUp() {
    url = config.getHostUrl("sdc");
    adminPass = restCalls.accountPassword;
    // restCalls.unPublishFeature("97928e6a-75c3-4f62-b435-e09784c723aa");
    if (!url.contains("zact-portal")) {
      adminUser = restCalls.createRandomEmail();
      restCalls.createAdminAccount("admin", adminUser);
    }
    if (url.contains("zact-portal")) {
      adminUser = "gurtej@itsoninc.com";
    }
    this.browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    portalPages = new PortalPages(browser);
    featurePages = new FeaturePages(browser);
    browser.logAction("Got url: " + url);
    portalPages.setPortalLogin(adminUser, adminPass);
    if (url.contains("zact")) { //select zact mobile
      partnerId = config.getParnterID();
      browser.click(portalPages.logOutTopMenu);
      browser.waitForClickableElement(By.id(partnerId)).click();
    }
  }

  @Test(dataProvider = "qa", groups = "Cleanup")
  public void sdcCleanUp(String sdcSection) {
    int rowCount = 4;
    String getText;
    givenName = "webqeauto";
    portalPages.setTopNavLink(sdcSection).setSearchfield(givenName);
    browser.sleep(12500);

    while (rowCount < 9) {
      getText = "none";

      browser.logAction("Checking Row " + rowCount);
      if (sdcSection.equals("features")) {
        getText = browser.findElement(By.cssSelector(".label")).getText();
        browser.logAction("======Got Text " + getText);
        getText = getText.toLowerCase();
        browser.sleep(3000);
      }
      browser.sleep(2000);
      featurePages.verifyView();
      browser.sleep(2000);

      browser.waitForClickableElement(By.id("label_featureName"));
      String checkName = browser.findElement(By.id("label_featureName")).getText();
      browser.logAction("+++Got Name " + checkName);

      if (!getText.toLowerCase().equals("testing") && !getText.toLowerCase().equals("published")) {//stop testing mode
        featurePages.testFeatureBtn.click();
        browser.sleep(1500);
        //click confirm
        featurePages.setConfirmOkBtn();
      }
      if (checkName.contains(givenName)) {
        featurePages.getFeatureId();
        restCalls.unPublishFeature(featurePages.returnFeatureId());
        browser.sleep(2000);
        portalPages.setTopNavLink(sdcSection);
        portalPages.setSearchfield(givenName);
        browser.sleep(12500);

      }

    }
  
}

@DataProvider(name = "qa")
        public Object[][] createData11() {
    return new Object[][]{
      {"features"},
};
  }

}
