
 /**Customer Support zact testSuite
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class CustomerSupportTestSuite extends SdcAbstractClass {

  public final String wrongPhoneNumber = "654686142";
  public String page = "customersupport";
  public String email;
  protected String subId ;
  protected String imsi;
  protected String searchType;
  protected String givenSearchType;

  @Factory(dataProvider = "cs")
  public CustomerSupportTestSuite(String type) {
    givenSearchType = type;
  }

  @AfterClass
  protected void deviceCleanup() {
    //restCalls.myAccountDelete();
  }

  @BeforeClass
  protected void deviceSetup() throws Exception {
    email = restCalls.createAccount();
    phoneNumber = restCalls.getPhoneNumber();
    browser.sleep(4000);
    portalPages.setTopNavLink(page);
  }

  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint"}, priority = 1)
  public void checkPhoneNumberFound() throws Exception {

    browser.reportLinkLog("checkPhoneNumberFound");
    searchType = setSearchType();
    browser.logAction("****Got " + searchType);
    String number = restCalls.getPhoneNumber();
    browser.logAction("****Got " + number);

    //check phone found and no error message is given
    cspPages
            .setSearch(searchType);
    if (givenSearchType.contains("imsi") ) {
      browser.waitForClickableElement(By.id("csp-search-imsi")).click();
      browser.sleep(800);
    }
    browser.waitForClickableElement(By.id("authenticateBtn")).click();
    if (givenSearchType.contains("email")) {
      browser.waitForClickableElement(By.id("csp-sub-0")).click();
    }

    cspPages.checkNumber(phoneNumber);
    browser.sleep(500);
  }
  
   @Test(groups = {"CustomerSupport", "SDC", "Sprint", "Zact"}, dependsOnMethods = {"checkPhoneNumberFound"}, priority = 2)
   public void checkImsi() {
   browser.reportLinkLog("checkImsi");
   imsi = restCalls.getImsi();
    
   cspPages
   .checkImsi(imsi);
   }
  
   @Test(groups = {"CustomerSupport", "SDC", "zactOnly", "Zact"}, dependsOnMethods = {"checkPhoneNumberFound"}, priority = 3)
   public void checkMin() {
   browser.reportLinkLog("checkMin");
   cspPages
   .checkMin(phoneNumber); //phonenumber and min are same
   }
  
   @Test(groups = {"CustomerSupport", "SDC", "Sprint", "Zact"}, dependsOnMethods = {"checkPhoneNumberFound"}, priority = 4)
   public void checkSubscriberNetworkId() throws Exception {
   browser.reportLinkLog("checkImsi");
   if (url.contains("zact")) {
   subId = restCalls.getSubscriberNetworkId();
   }
   cspPages
   .checkSubcriberNetworkId(subId);
   }
  
   @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint", "Zact"}, priority = -1)
   public void phoneSearchNotFound() {
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
   

  @DataProvider(name = "cs")
  public static Object[][] createData() {
    return new Object[][]{
      {"email"},
      {"phone"},
      {"imsi"},
      {"subid"},
    };
  }

  protected String setSearchType() {
    switch (givenSearchType) {
      case "email":
        searchType = email;
        break;
      case "phone":
        searchType = restCalls.getPhoneNumber();
        ;
        break;
      case "imsi":
        searchType = restCalls.getImsi();
        break;
      case "subid":
        searchType = restCalls.getSubscriberNetworkId();
        break;
      default:
        System.err.println("***Input  is not a vaild choice " + searchType);
        break;
    }
    return searchType;
  }

}