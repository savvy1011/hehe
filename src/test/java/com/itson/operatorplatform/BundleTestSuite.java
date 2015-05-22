/*
 /**BroadleafTestSuite Testing creating and deleting plans
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BundleTestSuite extends AbstractMethod {

  protected String section = "bundle";

  @BeforeMethod
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/bundles.csv")
  public void createBundle(String givendata) throws Exception {
    //Get Data
    setData(givendata, "bundle");
    productName = "cb" + browser.randomName();
    browser.reportLinkLog(productName);
    browser.logAction("*****Starting Bundle Create Plan****");
    //set random name for product
    browser.reportLinkLog(productName);
    browser.logAction("Creating Plan with " + productName);
    bundlePages.createBundle(productName);
  }

  @AfterMethod
  protected void planCleanUp() {
    browser.sleep(2000);
    blCleanUp();
  }
}
