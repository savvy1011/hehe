/**
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecurityTestSuite {

  private String adminUser;
  private String adminPass;
  protected String url;
  private PortalPages portalPages;
  protected Browser browser;

  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();

  @AfterClass
  public void suiteCleanUp() {
    restCalls.deleteAccountRole();

  }

  @AfterMethod
  public void browserCleanUp() {
    browser.close();

  }

  @BeforeClass
  public void createUser() {
    url = config.getHostUrl("sdc");
    adminUser = restCalls.createRandomEmail();
    restCalls.createAdminAccount("admin", adminUser);
    adminPass = "Pass1234";
    if (url.contains("zact-portal")) { //not able to add partners by restcalls
      adminUser = "gurtej@itsoninc.com";
    }

  }

  @BeforeMethod
  public void setUp() {
    browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    this.portalPages = new PortalPages(browser);
    
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Security"})
  public void PHPSESSID() {
    portalPages.setPortalLogin(adminUser, adminPass);
    //check cookie is there
    //String cookieName = conf.getCookieName();
    String checkText = browser.getCookieNamed("PHPSESSID").toString();
    browser.logAction("Got this: " + checkText);
    browser.compareText(checkText, "secure");
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Security","Jira"})
  public void iFrame() {
    browser.logAction("Checking iFrame");
    String url = browser.getCurrentUrl();
    browser.logAction("Got this: " + url);
    String iframeSite = "https://sprint-portal-di01.stg.itsonsaas.net//iframetest.php?iframetest="+url;
    browser.get(iframeSite);
    //switch frame
    browser.switchTo_frameIndex(0);
    if (browser.findElements(By.id("LoginForm_username")).size() != 0) {
      throw new RuntimeException("FAILED: Loaded in iframe");
    }

  }

}
