/*
 /**Create Plan from csv in steps
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.Test;

public class SandBoxTestSuite extends AbstractClass {

  protected String section = "createplan";

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../plans/plans.csv")
  public void createPlanCheckWithOtherUser(String givendata) {
    //Create a plan with one user Connect with the other one, check the plan is not visible
    browser.reportLinkLog("CreatePlanTestSuite");
    setData(givendata, "plan");
    logIn();
    productPages.setNewProduct("Plan");
    //set random name for product
    productName = browser.randomName();
    productPages.appearanceSection(productName);
    productPages.typeSection();
    productPages.behaviorSection();
    productPages.setBilling();
    productPages.setAvailability();
    productPages.setSaveBtn(negative);
    productPages.verifyingPlanNotShowing(productName);
  }

  @Test(priority = 2, dependsOnMethods = {"createPlanCheckWithOtherUser"})
  public void checkAfterPromotePlan() {
    //Promote the plan Connect with the other one, check the plan is not visible
    productPages.checkLogin(adminUser, adminPass);
    productPages.setPromotePlan(productName);
    productPages.verifyingPlanNotShowing(productName);

  }

  @Test(priority = 3, dependsOnMethods = {"createPlanCheckWithOtherUser"})
  public void deletePlanThatsNotApproved() {
    //check be able delete plan that is not approved
    browser.sleep(15500); //OP-1325
    productPages.setLogOut();
    productPages.lognIn(adminUser, adminPass);
    productPages.deleteProduct(productName);
  }

}
