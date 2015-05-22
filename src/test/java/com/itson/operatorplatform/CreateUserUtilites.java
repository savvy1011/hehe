/*
 /**Testing user creations
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserUtilites extends AbstractMethod {

  @Test(dataProvider = "section")
  public void createUser(String section) {
    accountName = "verify+" + section;
    browser.logAction("Creating User");
    browser.reporterLogger("Account Name: " + accountName + " Password: " + accountPassword);
    securityPages.createUser(accountName, accountPassword);
    //add role
    securityPages.setAdminRole();
    browser.logAction("check  User: " + accountName);
    browser.reportLinkLog("createUser" + accountName);
    logInPages.checkLogin(accountName, accountPassword);
  }

  @Test(dataProvider = "section")
  public void changePassword(String section) {
    accountName = adminUser + "+" + section;
    browser.logAction("Change password User");
    browser.reportLinkLog("changePassword" + accountName);
    browser.reporterLogger("Account Name: " + accountName + "Password: " + accountPassword);
    //log in with current account 
    logInPages.lognIn(adminUser, adminPass);
    String newPassword = securityPages.changePassword(accountName);

    //check logged in
    logInPages.checkLogin(accountName, "SOASTA650");
  }

  @DataProvider(name = "section")
  public Object[][] createData1() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"data"},
      {"text"},
      {"voice"},
      {"update"},
      {"createplan"},};

  }
}
