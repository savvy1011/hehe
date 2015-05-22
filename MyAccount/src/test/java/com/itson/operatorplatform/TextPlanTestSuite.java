/*
 /**BroadleafTestSuite Testing creating and deleting plans
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextPlanTestSuite extends AbstractMethod {

  protected String section = "text";

  @BeforeMethod
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/textPlan.csv")
  public void createTextPlan(String givendata) throws Exception {
    //Get Data
    setData(givendata, productType);
    browser.logAction("*****Starting Text Create Plan****");
    //set random name for product
    productName = browser.randomName() + "textPlan";
    browser.reportLinkLog(productName);
    browser.logAction("Creating Plan with " + productName);
    productPages.createNewPlan(productName, negative, update);

  }

  @AfterMethod
  protected void planCleanUp() {
    blCleanUp();
  }
}
