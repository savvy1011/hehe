/*
 /**Testing user creations
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTestSuite extends AbstractMethod {

  protected String newPassword;

  @BeforeMethod
  protected void logInSetUp() {
    logIn();
  }

  @Test(priority = 1)
  public void createUser() {
    browser.logAction("Creating User");
    browser.reportLinkLog("createUser" + accountName);
    accountName = browser.randomName();
    browser.reporterLogger("Account Name: " + accountName + "Password: " + accountPassword);
    securityPages.createUser(accountName, accountPassword);
    //add role
    securityPages.setAdminRole();

  }

  @Test(dependsOnMethods = {"createUser"}, priority = 2)
  public void checkCreatedUser() { // check only unquie user is created
    browser.logAction("check  User: " + accountName);
    browser.reportLinkLog("createUser" + accountName);
    logInPages.checkLogin(accountName, accountPassword);

  }

  @Test(dependsOnMethods = {"createUser"}, priority = 3)
  public void unquieUser() { // check only unquie user is created
    browser.logAction("Checking unquieUser");
    browser.reportLinkLog("unquieUser" + accountName);
    browser.reporterLogger("Account Name: " + accountName + "Password: " + accountPassword);
    securityPages.createUser(accountName, accountPassword);
    browser.waitForTextToAppear(browser.findElementByCssSelector(".error"), "Login must be unique");

  }

  @Test(dependsOnMethods = {"createUser"}, priority = 4)
  public void changePassword() {
    browser.logAction("Change password User");
    browser.reportLinkLog("changePassword" + accountName);
    browser.reporterLogger("Account Name: " + accountName + "Password: " + accountPassword);
    //log in with current account 
    newPassword = securityPages.changePassword(accountName);
    logInPages.checkLogin(accountName, newPassword);

  }

  @Test(dependsOnMethods = {"createUser"}, priority = 5)
  public void deleteUser() {
    browser.logAction("deleteUser" + accountName);
    browser.reportLinkLog("deleteUser" + accountName);
    securityPages.deleteUser(accountName);
    securityPages.verifyUserDeleted(accountName);
  }

}
