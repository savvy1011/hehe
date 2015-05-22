package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.*;

public class PortalLoginEulaTestSuite {

  private String adminUser;
  private String adminPass;
  private Browser browser;
  private PortalPages portalPages;
  protected String url;
  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();

  @AfterClass
  public void suiteCleanUp() {
    browser.close();
  }

  @BeforeClass
  public void createUser() {
    url = config.getHostUrl("sdc");
    adminPass = "Pass1234";
    if (!url.contains("zact")) {
      adminUser = restCalls.createRandomEmail();
      adminPass = restCalls.getPassword();
      restCalls.createAdminAccount("csp", adminUser);
    }
    else {
      adminUser = "gurtej@itsoninc.com";
    }
    

  }

  @BeforeMethod
  public void setUp() {
    browser = new Browser.Builder()
            .build();
    this.portalPages = new PortalPages(browser);
    browser.getSite(url);
  }

  @AfterMethod
  public void cleanUp() {
    browser.close();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkBlankUserName() {
    browser.reportLinkLog("checkBlankUserName");
    portalPages.setSaasUserNameTextField("").setSaasUserPasswordTextField(adminPass).setLogInBTTN().errorMessage();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkWrongUserName() {
    browser.reportLinkLog("checkWrongUserName");
    portalPages.setSaasUserNameTextField("test@itsoninc.com").setSaasUserPasswordTextField(adminPass).setLogInBTTN().errorMessage();

  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkBlankPassword() {
    browser.reportLinkLog("checkBlankPassword");
    portalPages.setSaasUserNameTextField(adminUser).setSaasUserPasswordTextField("").setLogInBTTN().errorMessage();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkWrongPasword() {
    browser.reportLinkLog("checkWrongPasword");
    portalPages.setSaasUserNameTextField(adminUser).setSaasUserPasswordTextField("wrongpassword").setLogInBTTN().errorMessage();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkLoastPassworkLink() {
    browser.reportLinkLog("checkLoastPassworkLink");
    portalPages.setForgotPasswordLink().setForgotPasswdEmailField("automtion@itsoninc.com");
  }

  /**
   * Clear eula cookie Set username Set password Click login Eula Accept Eula
   * Return to Login
   *
   * @
   */
  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkEula() {
    browser.reportLinkLog("checkEula");
    portalPages.clearCookieNamed("eulaAccepted");
    portalPages.setSaasUserNameTextField(adminUser);
    portalPages.setSaasUserPasswordTextField(adminPass);
    portalPages.setLogInBTTN();
    portalPages.setIAgreeEulaAcceptCheckbox();
    portalPages.setEulaReturnToLoginLink();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checksetEulaReturnToLoginLink() {
    browser.reportLinkLog("checksetEulaReturnToLoginLink");
    portalPages.setSaasUserNameTextField(adminUser);
    portalPages.setSaasUserPasswordTextField(adminPass);
    portalPages.setLogInBTTN();
    portalPages.setEulaReturnToLoginLink().errorMessage();
  }

  /*
   @Test(groups = {"Login", "SDC","Zact","Sprint"})
   public void C31362()  {

   browser.logAction("");
   browser.logAction("C31362");
   portalPages.clearCookieNamed("eulaAccepted");
   //default state is EULA box is not checked
   //browser.reportLinkLog("C31362",saucelabs);
   this.portalPages.setSaasUserNameTextField(accountEmail).setSaasUserPasswordTextField(password)
   .setLogInBTTN();
   browser.executeScript("window.scrollBy(0,450)", "");
   if (browser.findElement(By.id("EulaSubmitBtn")).getAttribute("disabled").equals("true")) {
   browser.logAction("Button is disabled");
   } else {
   System.err.println("Button is endabled");
   throw new RuntimeException();
   }
   //was not able check i agree 
   this.portalPages.setEulaReturnToLoginLink();
   }
   */
  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkEulaSubmitBtnIsDisabled() {
    browser.reportLinkLog("checkEulaSubmitBtnIsDisabled");
    //once you checked "agree to EULA" checkbox, the "Enter Service Design Center" button becomes active/clickable.
    //once you unchecked it, the "Enter Service Design Center" button becomes inactive
    //browser.reportLinkLog("C31363",saucelabs);
    portalPages.setSaasUserNameTextField(adminUser).setSaasUserPasswordTextField(adminPass).setLogInBTTN();

    try {
      if (browser.findElement(By.id("EulaSubmitBtn")).getAttribute("disabled").equals("true")) {
        browser.logAction("Button is disabled");
      }
      else {
        String source = browser.getHTMLSource();
        System.err.println("Failed to find EulaSubmitBtn on the following page:" + source);
        throw new RuntimeException("Button is enabled");
      }
    }
    catch (TimeoutException e) {
      String source = browser.getHTMLSource();
      System.err.println("Failed to find EulaSubmitBtn on the following page:" + source);
      throw e;
    }
    this.portalPages.setEulaReturnToLoginLink();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkEulaSubmitBtnDisabled() {
    browser.reportLinkLog("checkEulaSubmitBtnDisabled");
    //once you checked "agree to EULA" checkbox, the "Enter Service Design Center" button becomes active/clickable.
    //once you unchecked it, the "Enter Service Design Center" button becomes inactive
    //browser.reportLinkLog("C31364",saucelabs);
    portalPages.setSaasUserNameTextField(adminUser).setSaasUserPasswordTextField(adminPass)
            .setLogInBTTN();

    if (browser.findElement(By.id("EulaSubmitBtn")).getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");

    }
    else {
      throw new RuntimeException("Button is endabled");
    }

    portalPages.setIAgreeEulaAcceptCheckbox();

    //need to be able to check setEulaSubmitBtnwhen able
    portalPages.setIAgreeEulaAcceptCheckbox();
    if (browser.findElement(By.id("EulaSubmitBtn")).getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");
    }
    else {
      throw new RuntimeException("Button is endabled");
    }
    portalPages.setEulaReturnToLoginLink();
  }

  @Test(groups = {"Login", "SDC", "Zact", "Sprint"})
  public void checkEulaSubmitBtnDisabledClickingIagreeEnables() {
    browser.reportLinkLog("checkEulaSubmitBtnDisabledClickingIagreeEnables");
    //once you checked "agree to EULA" checkbox, the "Enter Service Design Center" button becomes active/clickable.
    //once you unchecked it, the "Enter Service Design Center" button becomes inactive
    portalPages.setSaasUserNameTextField(adminUser).setSaasUserPasswordTextField(adminPass).setLogInBTTN();
    browser.executeScript("window.scrollBy(0,450)", "");
    String chrometest = browser.findElementById("EulaSubmitBtn").getAttribute("disabled");

    browser.logAction(chrometest);

    if (browser.findElement(By.id("EulaSubmitBtn")).getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");

    }
    else {
      throw new RuntimeException("Button is enabled");
    }
    portalPages.setIAgreeEulaAcceptCheckbox().setEulaSubmitBtn();
    portalPages.setLogOutTopNavLink();

  }
}
