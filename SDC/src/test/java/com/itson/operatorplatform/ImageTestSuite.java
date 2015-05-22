/*
 /**BroadleafTestSuite Testing creating and deleting plans
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ImageTestSuite extends AbstractMethod {

  protected String savedImageName;
  protected String section = "data";

  @BeforeMethod
  protected void logInSetUp() {
    // adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/plans.csv")
  public void newCreatePlanImageFromLibary(String givendata) throws Exception {
    setData(givendata, productType);
    productName = browser.randomName();
    browser.logAction("got  " + productName);
    browser.reportLinkLog(productName);
    //Get Data
    productPages.setNewProduct("Plan");
    //set random name for product
    productPages.appearanceSection(productName, "libary");
    productPages.typeSection()
            .behaviorSection()
            .setBilling()
            .setAvailability()
            .setSaveBtn(negative)
            .verifyPlanInformation(productName)
            .checkImageName()
            .setPromotePlan(productName);
    productPages.setApproveProduct(productName, "Add");
    productPages
            .verifyingPlanCreated(productName);

    //check image after approved
    // browser.findElement(By.linkText(productName)).click();
    productPages.checkImageName();
  }

  @AfterMethod
  protected void voiceCleanUp() {
    blCleanUp();
  }

}
