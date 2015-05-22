package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestSuite {

  public String createdUser;

  protected final String testpassword = "Pass1234";

  private String adminUser;
  private String adminPassword;
  private String partnerId;
  private final String topnav = "users";
  protected String url;
  private Browser browser;
  private PortalPages portalPages;
  private UserPages userPages;
  private DisposableEmailPages disposableEmailPages;
  private LostPasswordPages lostPasswordPages;

  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();

  @BeforeClass
  public void setUp() {
    url = config.getHostUrl("sdc");
    browser = new Browser.Builder()
            .build();
    browser.getSite(url);

    browser.getSite(url);
    adminUser = config.getAdminUsername();
    adminPassword = config.getAdminPassword();
    partnerId = config.getParnterID();
    
    this.portalPages = new PortalPages(browser);
    this.userPages = new UserPages(browser);
    this.disposableEmailPages = new DisposableEmailPages(browser);
    this.lostPasswordPages = new LostPasswordPages(browser);
    createdUser = disposableEmailPages.createRandomEmail();
  }

  @AfterClass
  public void cleanUp() {
    browser.close();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "User"})
  public void createUserAdmin() {
    browser.reportLinkLog("CreateUserAdmin");
    portalPages.setPortalLogin(adminUser, adminPassword);
    if (url.contains("zact")) { //select zact 
      portalPages.logOutTopMenu.click();
      browser.waitForClickableElement(By.id(partnerId)).click();
    }
    portalPages.setTopNavLink(topnav);
    browser.sleep(5000); // need to wait or roles are empty
    userPages.setCreateNewUserBtn();
    userPages.setCreateNewUserEmailField(createdUser)
            .setCreateNewUserVerifyEmailField(createdUser);

    userPages.setPartners()
            .setAddRole("Admin");//adds all
    browser.sleep(1000);
    userPages.setSendInviteBtn();
    browser.sleep(1000);
    portalPages.setLogOutTopNavLink();
    browser.sleep(10000);
    //goto email
    disposableEmailPages.checkIndex("setup");

    userPages.accountSetup("Auto", "Test", testpassword, testpassword);
    browser.sleep(3000);
    portalPages.setSaasUserNameTextField(createdUser)
            .setSaasUserPasswordTextField(testpassword).setLogInBTTN();
    portalPages.setLogOutTopNavLink();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Lostpassword"},dependsOnMethods = {"createUserAdmin"})
  public void lostPasword() {
    lostPasswordPages.setForgotPassword(createdUser);
    browser.sleep(10000);
    disposableEmailPages.checkIndex("password");
    String newPassword = Integer.toString(browser.hashCode());
    newPassword = "Aa" + newPassword; //needs upper and lower case
    lostPasswordPages.changePassword(newPassword, newPassword);
    //check by logging in
    portalPages.setPortalLogin(createdUser,newPassword);    
    portalPages.setLogOutTopNavLink();
  }

}
