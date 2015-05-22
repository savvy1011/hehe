package com.itson.servicedesigncenter;

/**
 * Test suite for the Voice feature with Myfav filter flow
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeaturesMyFavVoicePolicyTestSuite extends AbstractTest {

  @Test(dataProvider = "voicepolicy", groups = {"Feature", "Voice", "SDC", "Zact", "zactOnly"})
  public void createFeaturesVoicePurchasablePolicy(String favType, String maximumNumber,
          String addPhone, String changeExistingNumber, String replaceDeletedNumber,
          String deleteNumberSelection, String addNetworkType, String addCriteria, String action,
          String networkinout, String testGroup, String publish, String negativeTest,
          String addNewComponent, String dataPolicyAddPolicyEvent) {
    groupName = "myfav" + browser.randomName();
    //setdata
    featurePages.setMyFavData(groupName, "", maximumNumber, addPhone,
            changeExistingNumber,
            replaceDeletedNumber, deleteNumberSelection, favType);
    if (favType.equals("libary")) {
      portalPages.setTopNavLink("myfav");
      //new btn
      featurePages.setCreateNewGroupBtn();
      //create policy
      featurePages.createMyFavPolicy();
    }
    browser.logAction("***Starting Features Voice Policy test");
    browser.reportLinkLog(groupName);
    savedNetworkinout = networkinout;
    featurePages.featuresPolicy("purchasable", "voice", groupName, "behavior", addNetworkType, addCriteria, action, networkinout, testGroup, publish, negativeTest, addNewComponent, dataPolicyAddPolicyEvent, "yes");

  }

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

  @DataProvider(name = "voicepolicy")
  public Object[][] createData1() {
    return new Object[][]{
      //addpolicy, addfilter,addNetworkType, addCriteria , action,  networkinout, testGroup, publish ,String negativeTest, String addNewComponent ,String dataPolicyAddPolicyEvent
      {"new", "2", "all", "", "", "", "Cellular Network", "time", "Allow", "in_libary", "libary", "no", "no", "yes", "yes"},
      {"libary", "4", "", "yes", "", "", "Wifi", "time", "Block", "none", "new", "none", "new", "yes", "yes"},
      {"new", "7", "", "", "", "yes", "3G", "time", "Allow", "out_new", "textfield", "none", "libary", "yes", "yes"},};

  }
}
