/*
 /**Update Plan
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.Test;

public class UpDatePlanTestSuite extends AbstractClass {

  protected String savedProductName;

  protected String savedData;
  private final String createPlanInfo = "name,Description - 23e4ee2c7b,Open VOICE Policy - Home (VOICE),100,Customer Payment,Reassignable indefinitely,3,No,2,Minute(s),Yes,Custom,Display Both Unit and Cycle Data,Yes,1.11,1.23,Text Plan";
  protected String section = "update";

  @Test(priority = 1)
  public void createPlan() throws Exception {
    adminUser = adminUser + "+" + section;
    logIn();
    //Get Data
    setData(createPlanInfo, "plan");
    savedProductName = browser.randomName();
    browser.reportLinkLog(savedProductName + "updatecreateplan");
    browser.logAction("got this: " + savedProductName);
    productPages.createNewPlan(savedProductName, negative, update);
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile", dependsOnMethods = {"createPlan"}, priority = 2)
  @DataProviderArguments("filePath=../plans/updatePlans.csv")
  public void updatePlan(String givendata) throws Exception {
    productPages.checkLogin(adminUser, adminPass);
    browser.logAction("****Starting Update Plan Test****");
    //get info off old test
    setData(createPlanInfo, "plan");
    //find plan 
    productPages.setFindProduct(savedProductName);
    //productPages.verifyPlanInformation(savedProductName);
    browser.logAction("***Updating Plan****");
    //get data set for update plans
    setData(givendata, "plan");
    savedData = givendata;
    productName = savedProductName + "Updated";
    browser.reportLinkLog(productName + "updateplan");
    //update Plan
    productPages.planSelection(productName, negative, "yes", "none");
    //Promote Plan
    productPages.setPromotePlan(productName);
    //Approve plan
    productPages.setApproveProduct(productName, "Update");
    browser.sleep(12000);
  }
  @Test(dependsOnMethods = {"updatePlan"}, priority = 3)
  public void verifyUpdatedPlan() throws Exception {
    browser.logAction("******Starting verifyUpdatedPlan****");
    productPages. verifyApprovedLogIn();
    productPages.setFindProduct(productName);
    productPages.verifyPlanInformation(productName);
  }

  @Test(dependsOnMethods = {"updatePlan"}, priority = 4)
  public void deleteUpdatePlan() throws Exception {
    browser.reportLinkLog(productName + "deleteUpdatePlan");
    blCleanUp();
  }
}
