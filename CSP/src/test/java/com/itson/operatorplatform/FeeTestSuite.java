/*
 /**BroadleafTestSuite Testing creating and deleting plans
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeeTestSuite extends AbstractMethod {

  protected String section = "fee";

  @BeforeMethod
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/fee.csv")
  public void createFee(String givendata) throws Exception {
    //Get Data
    setData(givendata, "fee");
    productName = browser.randomName() + "FEE";
    browser.reportLinkLog(productName);
    browser.logAction("*****Starting Fee Create Plan****");
    //set random name for product
    browser.logAction("Creating Plan with " + productName);
    feePages.createFee(productName);
  }

  @AfterMethod
  protected void planCleanUp() {
    blCleanUp();
  }
}
