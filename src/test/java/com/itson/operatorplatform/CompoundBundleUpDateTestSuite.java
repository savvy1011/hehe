/*
 /**Update Plan
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;
import org.testng.annotations.Test;

public class CompoundBundleUpDateTestSuite extends AbstractClass {

  protected String savedProductName;

  protected String savedData;
  private final String createBundleInfo = "BundleCompound123,compound,Description-compound,Renews Indefinitely,yes,none,none,no,bundle,1.11,2.22,Data Plan,DoNotDelete,none,none\n"
          + "";
  protected String section = "update";
  protected String productType = "bundle";

  @Test(priority = 1)
  public void createBundle() throws Exception {
    adminUser = adminUser + "+" + section;
    logIn();
    //Get createBundleInfo
    setData(createBundleInfo, "bundle");
    productName = browser.randomName() + "bundle";
    browser.reportLinkLog(productName);
    browser.logAction("*****Starting Bundle Create Plan****");
    //set random name for product
    browser.reportLinkLog(productName);
    browser.logAction("Creating Plan with " + productName);
    bundlePages.createBundle(productName);
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile", dependsOnMethods = {"createBundle"}, priority = 2)
  @DataProviderArguments("filePath=../plans/updateBundles.csv")
  public void updateBundle(String givendata) throws Exception {
    productPages.checkLogin(adminUser, adminPass);
    browser.logAction("****Starting Update Compound Bundle Test****");
    //get info off old test
    setData(givendata, "bundle");
    //find plan 
    bundlePages.setFindProduct(productName);
    //productPages.verifyPlanInformation(savedProductName);
    browser.logAction("***Updating Plan****");
    //get data set for update plans
    //setData(givendata);
    savedData = givendata;
    productName = savedProductName + "Updated";
    browser.reportLinkLog(productName + "updateplan");
    //update Plan
    bundlePages.setUpdate("update");
    bundlePages.createBundle(productName);
    browser.sleep(10000);
  }

  @Test(dependsOnMethods = {"updateBundle"}, priority = 3)
  public void verifyUpdatedBundle() throws Exception {
    browser.logAction("******Starting verifyUpdatedPlan****");
    bundlePages.setFindProduct(productName);
    bundlePages.verifyingBundleCreated(productName);
  }

  @Test(dependsOnMethods = {"updateBundle"}, priority = 4)
  protected void deleteUpdatedPlan() {
    bundlePages.setLogOut();
    bundlePages.lognIn(adminUser, adminPass);
    productPages.deleteProduct(productName);
  }
}
