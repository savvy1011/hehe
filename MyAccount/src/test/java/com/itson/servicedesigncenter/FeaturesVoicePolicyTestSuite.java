package com.itson.servicedesigncenter;

/**
 * Test suite for the Voice policy flow in features
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeaturesVoicePolicyTestSuite extends AbstractTest {

  @Test(dataProvider = "voicepolicy", groups = {"Feature", "Voice", "SDC", "Zact", "Sprint"})
  public void createFeaturesVoicePublishablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    browser.logAction("***Starting Features Voice Policy test");
     browser.reportLinkLog(groupName);
    savedNetworkinout = networkinout;
    groupName = browser.randomName() + addpolicy + "policy";
    featurePages.featuresPolicy("publishable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");

  }
  
  @Test(dataProvider = "voicepolicy", groups = {"Feature", "Voice", "SDC", "Zact","zactOnly"})
  public void createFeaturesVoicePurchasablePolicy(String addpolicy, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent) {
    browser.logAction("***Starting Features Voice Policy test");
    browser.reportLinkLog(groupName);
    savedNetworkinout = networkinout;
    groupName = browser.randomName() + addpolicy + "policy";
    featurePages.featuresPolicy("purchasable", addpolicy, groupName, addFilter, addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");

  }

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

  @DataProvider(name = "voicepolicy")
  public Object[][] createData1() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"voice", "remotephone", "Cellular Network", "time", "Allow", "in_libary", "libary", "no", "no", "yes", "yes"},
      {"voice", "direction", "Wifi", "time", "Block", "none", "new", "none", "new", "yes", "yes"},
      {"voice", "remotephone", "3G", "time", "Allow", "out_new", "textfield", "none", "libary", "yes", "yes"},
    };

  }
}
