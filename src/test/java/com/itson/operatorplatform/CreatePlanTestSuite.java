/*
 /**Create Plan from csv in steps
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatePlanTestSuite extends AbstractClass {

  protected String section = "createplan";

  @BeforeClass
  protected void logInSetUp() {
    adminUser = adminUser + "+" + section;
    logIn();
  }

  @Test()
  public void logInTest() {
    browser.reportLinkLog("CreatePlanTestSuite");
  }

  @Test(dependsOnMethods = {"logInTest"})
  public void addNewPlan() {
    productPages.setNewProduct("Plan");

  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile", dependsOnMethods = {"logInTest"})
  @DataProviderArguments("filePath=../plans/plans.csv")
  public void appearanceSection(String givendata) {
    setData(givendata, "plan");
    //set random name for product
    productName = browser.randomName();
    productPages.appearanceSection(productName);

  }

  @Test(dependsOnMethods = {"appearanceSection"})
  public void typeSection() {
    productPages.typeSection();

  }

  @Test(dependsOnMethods = {"typeSection"})
  public void behaviorSection() {
    productPages.behaviorSection();
  }

  @Test(dependsOnMethods = {"behaviorSection"})
  public void billing() {
    productPages.setBilling();

  }

  @Test(dependsOnMethods = {"billing"})
  public void availability() {
    productPages.setAvailability();

  }

  @Test(dependsOnMethods = {"availability"})
  public void saveBtn() {
    productPages.setSaveBtn(negative);

  }

  @Test(dependsOnMethods = {"saveBtn"})
  public void checkDropDowns() {
    productPages.verifyPlanInformation(productName);

  }

  @Test(dependsOnMethods = {"saveBtn"})
  public void promotePlan() {
    productPages.setPromotePlan(productName);

  }

  @Test(dependsOnMethods = {"promotePlan"})
  public void approvePlan() {
    productPages.setApproveProduct(productName, "Add");
  }

  @Test(dependsOnMethods = {"approvePlan"})
  public void verifyPlanInformation() {
    productPages.verifyingPlanCreated(productName);
  }

  @Test(dependsOnMethods = {"verifyPlanInformation"})
  public void deletePlan() {
    browser.sleep(15500); //OP-1325
    productPages.setLogOut();
    productPages.lognIn(adminUser, adminPass);
    productPages.deleteProduct(productName);

  }

}
