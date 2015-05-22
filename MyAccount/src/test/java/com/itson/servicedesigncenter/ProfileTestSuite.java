/**
 * Checks the policy section
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.AfterClass;

public class ProfileTestSuite {

  private  String givenPassword = "Pass1234";
  protected final String newPassword = "NewPassword67";
  protected final String wrongPassword = "password";
  protected String currentPassword;
  protected String profileEmail;
  protected final String givenFirstName = "Automated";
  protected final String givenLastName = "Policy";
  protected final String newFirstName = "Newfirst";
  protected final String newLastName = "Newlast";
  protected RestCalls restCalls;
  protected FeaturePages featurePages;
  private Browser browser;
  protected PortalPages portalPages;
  protected ProfilePages profilePages;

  protected Config config = new Config();

  @BeforeMethod
  protected void beforeSetUp() {
    restCalls = new RestCalls();
    String url = config.getHostUrl("sdc");
    //create account
    if (!url.contains("zact")) {
      profileEmail = restCalls.createRandomEmail();
      givenPassword = restCalls.getPassword();
      restCalls.createAdminAccount("admin", profileEmail);

    }
    else {
      profileEmail = "gurtej+zact@itsoninc.com";

    }
    this.browser = new Browser.Builder()
            .build();
    featurePages = new FeaturePages(browser);
    profilePages = new ProfilePages(browser);

    browser.logAction("Got url: " + url);
    browser.getSite(url);
    currentPassword = givenPassword;

    portalPages = new PortalPages(browser);

    portalPages.setPortalLogin(profileEmail, givenPassword);
    if (url.contains("zact")) { //select zact mobile
      browser.click(portalPages.logOutTopMenu);
      browser.waitForClickableElement(By.id(config.getParnterID())).click();

    }
    portalPages.setProfileTopNavLink();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"})
  public void checkNames() {
    browser.reportLinkLog("checkNames");
    profilePages.verifyNameFields(givenFirstName, givenLastName);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"})
  public void changeName() {
    browser.reportLinkLog("changeName");
    //set new names
    profilePages.inputNameFields(newFirstName, newLastName);
    //logout to check
    //log back
    portalPages.setPortalLogoutLogin(profileEmail, currentPassword);
    portalPages.setProfileTopNavLink();
    //verify name change
    profilePages.verifyNameFields(newFirstName, newLastName);

    //change name back
    profilePages.inputNameFields(givenFirstName, givenLastName);
    //logout to check
    //log back
    portalPages.setPortalLogoutLogin(profileEmail, currentPassword);
    portalPages.setProfileTopNavLink();
    //verify name change back
    profilePages.verifyNameFields(givenFirstName, givenLastName);

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"}) //ConfirmationField not match
  public void wrongConfirmation() {
    browser.reportLinkLog("wrongConfirmation");
    //input wrong password
    profilePages.setOldPasswordField(currentPassword);
    //input new password that meets Password Requirements
    profilePages.setNewPasswordField(newPassword);
    //input pass that does not match
    profilePages.setNewPasswordConfirmationField(wrongPassword);
    // get error meeage
    browser.findElement(By.id("passwordHint")).click();
    browser.waitForClickableElement(By.cssSelector("div.error:nth-child(3)"));
    //change password chould be disabled
    profilePages.setCheckDisableChangePasswordButton();

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"}) //Password Requirements not met
  public void notMeetingRequirment() {
    browser.reportLinkLog("notMeetingRequirment");
    //input wrong password
    profilePages.setOldPasswordField(currentPassword);
    //input new password that meets Password Requirements
    profilePages.setNewPasswordField(wrongPassword);
    //input matching password
    profilePages.setNewPasswordConfirmationField(wrongPassword);
    //should get error meeage
    browser.waitForVisibilityOfElement(By.cssSelector("div.error:nth-child(4)"));
    //change password chould be disabled
    profilePages.setCheckDisableChangePasswordButton();
  }
  /*
   @Test(groups = "Jira") //password strength indicator should only be green when Requirements are met//SAAS-4313 
   public void passwordStrengthIndicator() {
   //input wrong password
   profilePages.setOldPasswordField(currentPassword);
   //input new password that meets Password Requirements
   profilePages.setNewPasswordField(wrongPassword);

   }
   
   */

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"}) //Password Requirements not met
  public void wrongOldPassword() {
    browser.reportLinkLog("wrongOldPassword");
    //input wrong password
    profilePages.setOldPasswordField(wrongPassword);
    //input new password that meets Password Requirements
    profilePages.setNewPasswordField(newPassword);
    //input matching password
    profilePages.setNewPasswordConfirmationField(newPassword);
    //click change password
    profilePages.setChangePasswordBtn();
    //force input to lose focus
    browser.findElement(By.cssSelector("body")).click();
    //check error
    browser.waitForClickableElement(By.cssSelector("button.close"));
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Profile"}) //Password Requirements not met
  public void changePassword() {
    browser.reportLinkLog("changePassword");
    //change password
    profilePages.changePassword(currentPassword, newPassword);
    //save password
    currentPassword = newPassword;
    //logout
    //log back
    portalPages.setPortalLogoutLogin(profileEmail, currentPassword);
    //change password back
    portalPages.setProfileTopNavLink();
    profilePages.changePassword(currentPassword, givenPassword);
    currentPassword = givenPassword;

  }

  @AfterMethod
  protected void cleanUp() {
    browser.logAction("\nIn method Cleanup\n");
    browser.close();
  }

  @AfterClass
  protected void deleteAccount() {
    restCalls.deleteAccountRole();
  }

}
