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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CspAdminTestSuite {

  protected String adminUser;
  protected String adminPass;
  protected String phoneNumber;
  private PortalPages portalPages;
  private CustomerSupportPages cspPages;

  protected Browser browser;
  private Config conf;
  public String page = "customersupport";
  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();

  @BeforeClass
  public void createUser() {
    adminUser = restCalls.createRandomEmail();

    restCalls.createAdminAccount("cspadmin", adminUser);
    adminPass = restCalls.getPassword();
    //get sprint number
    phoneNumber = config.getMDN();
    //restCalls.provisioningDeviceSprint();
  }

  @AfterClass
  public void suiteCleanUp() {
    restCalls.deleteAccountRole();
    //remove device
    restCalls.deprovisioningDevice();
  }

  @BeforeMethod
  public void setUp() {
    conf = new Config();
    String url = config.getHostUrl("sdc");
    browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    this.portalPages = new PortalPages(browser);
    this.cspPages = new CustomerSupportPages(browser);

    portalPages.setPortalLogin(adminUser, adminPass);
    portalPages.setTopNavLink(page);
  }

  @AfterMethod
  public void methodCleanUp() {
    browser.close();
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, priority = 1)
  public void phoneNumberFound() {
    browser.reportLinkLog("phoneNumberFound");
    //check phone found and no error message is given
    cspPages
            .setSearch(phoneNumber)
            .checkForErrorMessage()
            .checkNumber(phoneNumber);
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, priority = 2)
  public void checkOnlyCspMenu() {
    browser.reportLinkLog("checkOnlyCspMenu");
    if (browser.findElements(By.id("navigLinkToPolicy")).size() != 0) {
      throw new RuntimeException("FAILED: Policy is Visible");
    }
    if (browser.findElements(By.id("navigLinkToCommerce")).size() != 0) {
      throw new RuntimeException("FAILED: Commerce is Visible");
    }
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, priority = 3)
  public void addedViewerRole() {
    browser.reportLinkLog("addedViewerRole");
    //add role viewer
    restCalls.accountRole("viewer");
    //check phone found and no error message is given

    cspPages.setSearch(phoneNumber)
            .checkForErrorMessage()
            .checkNumber(phoneNumber);
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"addedViewerRole"}, priority = 4)
  public void checkViewerRoleMenu() {
    browser.reportLinkLog("checkViewerRoleMenu");
    //check if libary meny is shown
    browser.waitForVisibilityOfElement(By.id("navigLinkToPolicy"));
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"addedViewerRole"}, dataProvider = "menu", priority = 5)
  public void checkViewerRoleNoDeleteBtn(String menu) {
    browser.reportLinkLog("checkViewerRoleNoDeleteBtn");
    //check no delete button avavible 
    portalPages.setTopNavLink(menu);
    if (browser.findElements(By.id("delete_row_0")).size() != 0) {
      throw new RuntimeException("FAILED: Delete Button is Visible");
    }

  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"addedViewerRole"}, dataProvider = "menu", priority = 6)
  public void checkViewerRoleNoDelete(String menu) {
    browser.reportLinkLog("checkViewerRoleNoDelete");
    //check no delete button avavible 
    portalPages.setTopNavLink(menu);
    if (browser.findElements(By.id("delete_row_0")).size() != 0) {
      throw new RuntimeException("FAILED: Delete Button is Visible");
    }

  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"checkViewerRoleNoDelete"}, priority = 7)
  public void addedAdminRole() {
    browser.reportLinkLog("addedAdminRole");
    //add role viewer
    restCalls.accountRole("admin");
    //check phone found and no error message is given
    cspPages.setSearch(phoneNumber)
            .checkForErrorMessage()
            .checkNumber(phoneNumber);
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"addedAdminRole"}, priority = 8)
  public void checkAdminRoleMenu() {
    browser.reportLinkLog("checkAdminRoleMenu");
    //check if libary meny is shown
    browser.waitForVisibilityOfElement(By.id("navigLinkToPolicy"));
    browser.waitForVisibilityOfElement(By.xpath("//section[@id='nav-cog']/article/button"));
  }

  @Test(groups = {"CustomerSupport", "SDC", "Sprint", "noZact"}, dependsOnMethods = {"addedAdminRole"}, dataProvider = "menu", priority = 9)
  public void checkAdminRoleDeleteBtn(String menu) {
    browser.reportLinkLog("checkAdminRoleDeleteBtn");
    browser.logAction("*******Role Admin Del btn");
    //check no delete button avavible 
    portalPages.setTopNavLink(menu);
    browser.sleep(1500);
    portalPages.setTableDelete();

  }

  @DataProvider(name = "menu")
  public Object[][] createData11() {
    return new Object[][]{
      {"features",},
      {"subscriber"},
      {"network",},};
  }

}
