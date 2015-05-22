/*
 /**Create Plan from csv for jmeter test
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProductUtilities extends AbstractMethod {
  
  @BeforeMethod
  protected void logInSetUp() {
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans.csv")
  public void CreatePlan(String givendata) throws Exception{
    //Get Data
    setData(givendata,productType); 
    browser.logAction("got this: " + productName);
    productPages.createNewPlan(productName, negative,update);

  }
  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../bundle.csv")
  public void CreateBundle(String givendata) throws Exception{
    //Get Data
    setData(givendata,productType); 
    browser.logAction("got this: " + productName);
    bundlePages.createBundle(productName);

  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans.csv")
  public void DeletePlan(String givendata)  {
    setData(givendata,"bundle"); 
    productPages.deleteProduct(productName);

  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans.csv")
  public void UpdatePlan(String givendata)  throws Exception{
    //get data set
    setData(givendata,productType); 
    browser.logAction("productName: " + productName);
    //Goto to create plan
    productPages.setCatalogProductSelection();
    //find plan 
    productPages.setFindProduct(productName);
    //Create Plan
    productPages.planSelection(productName,negative,update,"none");
    //Promote Plan
    productPages.setPromotePlan(productName);
    //Approve plan
    productPages.setApproveProduct(productName,"Add");

  }
  

}
