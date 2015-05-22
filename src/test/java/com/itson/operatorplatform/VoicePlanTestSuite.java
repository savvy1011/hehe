/*
 /**BroadleafTestSuite Testing creating and deleting plans
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VoicePlanTestSuite extends AbstractMethod {

  protected String section = "voice";

  @BeforeMethod
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/voicePlan.csv")
  public void createVoicePlan(String givendata) throws Exception {
    //Get Data
    setData(givendata,productType); 
    browser.logAction("*****Starting Voice Create Plan****");
    //set random name for product
    productName = browser.randomName() + "voicePlan";
    browser.reportLinkLog(productName);
    browser.logAction("Creating Plan with " + productName);
    productPages.createNewPlan(productName, negative, update);
  }

  @AfterMethod
  protected void voiceCleanUp() {
    blCleanUp();
  }

}
