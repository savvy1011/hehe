/*
 /**Login testsuite
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTestSuite extends SetUpAbstract {

  @BeforeMethod
  protected void setUp() {
    browserSetUp();
    wrongPassword = browser.randomName();
    wrongUserName = browser.randomName();
  }

  @Test()
  public void correctCredentials() {
    browser.reportLinkLog("correctCredentials");
    logInPages.lognIn(adminUser, adminPass);
    checkPartnerName();
  }

  @Test()
  public void wrongUserName() {
    browser.reportLinkLog("wrongUserName");
    logInPages.lognInTest(wrongUserName, adminPass);
    logInPages.checkErrorMsg();
  }

  @Test()
  public void wrongPassword() {
    browser.reportLinkLog("wrongPassword");
    logInPages.lognInTest(adminUser, wrongPassword);
    logInPages.checkErrorMsg();
  }

  @Test()
  public void wrongPasswordUserName() {
    browser.reportLinkLog("wrongPasswordUserName");
    logInPages.lognInTest(wrongUserName, wrongPassword);
    logInPages.checkErrorMsg();
  }

  @Test()
  public void checkSandBox() {
    browser.reportLinkLog("checkSandBox");
    logInPages.lognInTest(adminUser, adminPass);
    browser.waitForClickableElement(By.id("sandbox-ribbon"));
    browser.compareText(browser.findElementById("sandbox-ribbon").getText(), sandBox);
  }

  @Test()
  public void checkLogOut() { //test loging in and out
    browser.reportLinkLog("checkLogOut");
    browser.logAction("*****Logout Test****");
    logInPages.lognInTest(adminUser, adminPass);
    logInPages.setLogOut();
    logInPages.lognIn(adminUser, adminPass);
  }

  @AfterMethod
  protected void cleanUp() {
    browser.close();
  }

}
