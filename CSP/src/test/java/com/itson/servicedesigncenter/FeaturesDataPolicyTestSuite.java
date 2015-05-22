/**
 * Test suite for the Data policy flow in features
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeaturesDataPolicyTestSuite extends AbstractTest {

  @Test(dataProvider = "datapolicy", groups = {"Feature", "DataPolicy", "SDC", "Zact", "Sprint"})
  public void createFeaturesDataPublishablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    savedNetworkinout = networkinout;
    browser.logAction("***Starting Features Data Policy test");
    groupName = browser.randomName() + addpolicy + "policy";
    browser.reportLinkLog(groupName);
    featurePages.featuresPolicy("publishable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");
  }
  @Test(dataProvider = "datapolicy", groups = {"Feature", "DataPolicy", "SDC", "Zact", "zactOnly"})
  public void createFeaturesDataPurchasablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    savedNetworkinout = networkinout;
    browser.logAction("***Starting Features Data Policy test");
    groupName = browser.randomName() + addpolicy + "policy";
    featurePages.featuresPolicy("purchasable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");
  }

  @Test(dataProvider = "changename", groups = {"Feature", "DataPolicy", "SDC", "Zact", "Sprint", "noDevBuild"})//IOQE-80
  public void changeFeatureNames(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    savedNetworkinout = networkinout;
    browser.logAction("***Starting Features Data Policy test");
    groupName = browser.randomName() + addpolicy;
    browser.reportLinkLog(groupName+"changename");
    String newName = "nameChanged" + groupName;
    //create feature
    featurePages.featuresPolicy("publishable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "no");
    //change and check name
    featurePages.nameChange("publishable", groupName, newName, publish, testGroup);
  }

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

  @DataProvider(name = "datapolicy")
  public Object[][] createData1() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"data", "app", "2G", "time", "Allow", "none", "libary", "yes", "no", "yes", "yes"},
      {"data", "domain", "3G", "time", "Block", "in_libary", "new", "no", "no", "yes", "yes"},
      {"data", "ipaddress", "4G", "appvisibilityBackGround", "Allow", "none", "textfield", "no", "no", "yes", "yes"},
      {"data", "protocol", "2G", "appvisibilityBackGround", "Allow", "out_new", "textfield", "no", "no", "yes", "yes"},};

  }
  @DataProvider(name = "changename")
  public Object[][]  changenamedata() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"data", "app", "3G", "time", "Allow", "none", "no", "no", "no", "yes", "yes"},
      {"data", "domain", "3G", "time", "Allow", "none", "libary", "yes", "no", "yes", "yes"},
      {"data", "protocol", "2G", "time", "Allow", "none", "no", "no", "no", "yes", "yes"},};
  }
}
