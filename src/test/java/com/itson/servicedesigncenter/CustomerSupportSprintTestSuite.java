/*
 /**Customer Support for Sprint testSuite
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerSupportSprintTestSuite extends SdcAbstractClass {

  public final String wrongPhoneNumber = "654686142";
  public String page = "customersupport";
  public String email = "customersupport";

  

  @BeforeClass
  protected void deviceSetup() throws Exception {
   
    //get sprint number
    phoneNumber = config.getMDN();
    portalPages.setTopNavLink(page);
  }

  @AfterClass
  protected void deviceCleanup() {
    restCalls.myAccountDelete();
  }

  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint"}, priority = 1)
  public void checkPhoneNumberFound() {
    browser.reportLinkLog("checkPhoneNumberFound");
    //check phone found and no error message is given
    cspPages
            .setSearch(phoneNumber);
    cspPages.checkNumber(phoneNumber);
    browser.sleep(500);
  }

  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint", "Zact"}, priority = -1)
  public void phoneNumberNotFound() {
    browser.reportLinkLog("phoneNumberNotFound");
    //check if  number not in system then eror message is given 
    cspPages.setSearch(wrongPhoneNumber);
    browser.waitForVisibilityOfElement(By.cssSelector(".close")).isDisplayed();
  }

  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint"}, dependsOnMethods = {"checkPhoneNumberFound"}, priority = 55)
  public void checkGetClientLog() {
    browser.reportLinkLog("checkGetClientLog");
    //click on get log 
    browser.waitForClickableElement(By.id("csp-device-name")).click();
    browser.waitForClickableElement(By.id("csp-device-actions")).click();
    browser.waitForClickableElement(By.id("csp-get-log")).click();
    browser.sleep(1000);
    browser.waitForClickableElement(By.cssSelector("button.close"));
  }

}
