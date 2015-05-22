package com.itson.servicedesigncenter;

/**
 * Test suite for the Voice policy flow in features
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeaturesTextPolicyTestSuite extends AbstractTest {

  @Test(dataProvider = "textpolicy", groups = {"Feature", "Text", "SDC", "Zact", "Sprint"})
  public void createFeaturesTextPublishablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    savedNetworkinout = networkinout;
    browser.logAction("***Starting Features Text Policy test");
    groupName = browser.randomName() + addpolicy + "policy";
    browser.reportLinkLog(groupName);
    featurePages.featuresPolicy("publishable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");

  }
  
   @Test(dataProvider = "textpolicy", groups = {"Feature", "Text", "SDC", "Zact", "zactOnly"})
   public void createFeaturesTextPurchasablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
   savedNetworkinout = networkinout;
   browser.logAction("***Starting Features Text Policy test");
   groupName = browser.randomName() + addpolicy + "policy";
   browser.reportLinkLog(groupName);
   featurePages.featuresPolicy("purchasable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");

   }
   

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);

  }

  @DataProvider(name = "textpolicy")
  public Object[][] createData1() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"message", "remotephone", "Cellular Network", "time", "Allow", "in_new", "libary", "no", "no", "yes", "yes"}, //   {"message", "direction", "2G", "time", "Block", "out_new", "new", "none", "no", "yes", "yes"},
      {"message", "sms", "4G", "time", "Allow", "none", "textfield", "none", "none", "yes", "yes"},
      {"message", "direction", "Cellular Network", "time", "Allow", "in_new", "libary", "no", "no", "yes", "yes"},};

  }
}
