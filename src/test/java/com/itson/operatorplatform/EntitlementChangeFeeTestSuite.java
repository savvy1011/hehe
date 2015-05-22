/*
 /** Testing EntitlementChangeFee/myfav section of BL
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntitlementChangeFeeTestSuite extends AbstractMethod {

  protected String section = "fee";

  @BeforeMethod
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/entitlmentFee.csv")
  public void createFee(String givendata) throws Exception {
    //Get Data
    entitlementPages.setEntitlementData(givendata);
    productName = browser.randomName() + "FEE";
    browser.reportLinkLog(productName);
    browser.logAction("*****Starting Fee Create Plan****");
    //set random name for product
    browser.logAction("Creating Plan with " + productName);
    entitlementPages.createEntitlementFee(productName);
  }

  @AfterMethod
  protected void planCleanUp() {
    blCleanUp();
  }
}
