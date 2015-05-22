package com.itson.operatorplatform;

/*
 /**testing error message is given when required fields are left blank in plan creation 
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequiredFieldTestSuite extends AbstractMethod {

  @BeforeMethod
  protected void logInSetUp() {
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/negative.csv")
  public void negativeTest(String givendata) throws Exception {
    //Get Data
    setData(givendata,productType); 
    setErrorMessage(); //set error meassge
    browser.logAction("*****Start negativeTest****");
    //set random name for product
    if (!productName.equals("")) {
      productName = browser.randomName();
    }
    browser.reportLinkLog("negativeTest" + productName);
    browser.logAction("Creating Plan with " + productName);
    browser.reporterLogger("Testing For Messsage: " + errorMessage);
    productPages.createNewPlan(productName, errorMessage, update);
  }

  @AfterClass
  protected void cleanUp() {
    browserClose();
  }

}
