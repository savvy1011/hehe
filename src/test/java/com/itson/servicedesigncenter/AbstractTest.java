package com.itson.servicedesigncenter;

import com.itson.operatorplatform.EntitlementChangeFeePages;
import com.itson.operatorplatform.LogInPages;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * This sets up the @Before and @After Class. It should not do anything with
 *
 * @BeforeMethod or @AfterMethod
 */
public abstract class AbstractTest {

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
  protected MyFavSdcPages myFavSdcPages;
  protected FeaturePages featurePages;
  protected Browser browser;
  protected String blAdminUser;
  protected String blAdminPass;
  protected String broadleafUrl;

  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();
  protected LogInPages logInPages;
  protected EntitlementChangeFeePages entitlementPages;

  @BeforeClass(alwaysRun = true)
  protected void classSetUp() {
    url = config.getHostUrl("sdc");
    adminPass = restCalls.getPassword();
    if (!url.contains("zact-portal")) {
      adminUser = restCalls.createRandomEmail();
      restCalls.createAdminAccount("admin", adminUser);
    }
    if (url.contains("zact-portal")) {
      adminUser = "gurtej@itsoninc.com";
      adminPass = "Pass1234";
    }

    this.browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    portalPages = new PortalPages(browser);
    featurePages = new FeaturePages(browser);
    logInPages = new LogInPages(browser);
    entitlementPages = new EntitlementChangeFeePages(browser);
    browser.logAction("Got url: " + url);
    portalPages.setPortalLogin(adminUser, adminPass);
    if (url.contains("zact")) { //select zact mobile
      partnerId = config.getParnterID();
      browser.click(portalPages.logOutTopMenu);
      browser.waitForClickableElement(By.id(partnerId)).click();
    }

    broadleafUrl = config.getHostBlUrl();
  }

  @AfterClass(alwaysRun = true)
  protected void classCleanUp() {
    if (browser != null) {
      browser.close();
    }
    if (url != null && !url.contains("zact")) {
      restCalls.deleteAccountRole();
    }

  }

//  @AfterSuite(alwaysRun = true)
//  protected void suiteCleanUpp() {
//    if (url != null && !url.contains("zact")) {
//      restCalls.deleteAccountRole();
//    }
//
//  }
}
