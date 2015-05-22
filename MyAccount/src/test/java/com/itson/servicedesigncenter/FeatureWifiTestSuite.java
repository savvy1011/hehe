package com.itson.servicedesigncenter;

/**
 * Test suite for wifi flow in features Not done MIP
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeatureWifiTestSuite extends AbstractTest {

  public String savedName;

  @Test(dataProvider = "wifi", groups = {"Feature", "Wifi", "SDC", "Zact", "Sprint"})
  public void createWifipolicy(String time, String retry, String networkinout, String addCriteria, String testGroup, String publish) {
    browser.logAction("***Starting WIFI Policy test");
    groupName = this.browser.randomName() + "_wifipolicy";
    browser.reportLinkLog(groupName);
    topnav = "features";
    savedNetworkinout = networkinout;
    portalPages.setTopNavLink(topnav);
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    featurePages.addpolicy("wifi");

    browser.sleep(1500);//wait for overlay to disappear

    featurePages.wifiNotConnectIn(time)
            .retryLater(retry);

    featurePages.setAddPoilcyEvent("new");

    featurePages.dataSessionDefaultPolicyEventBlockNetworkGroupsIN(networkinout, groupName);
    browser.logAction("Network done");
    featurePages.dataPolicyDefaultPolicyEventAddCriteria(addCriteria);

    // test button
    featurePages.testGroupSubscribers(testGroup, "no");
    //publish
    featurePages.publishFeature(publish);

    //verfify
    portalPages.setTopNavLink(topnav).verifyGroupsAdded(groupName);

    featurePages.verifyPublishFeature(publish, testGroup);
    if (publish.toLowerCase().equals("no")) {
      //stop testing
      featurePages.setStopTestPolicyBtn();
    }
    if (!networkinout.equals("none")) {
      featurePages.verifyNetworkFilterAdded("networkremove");

    }
    browser.sleep(2000); //StaleElementReference
    // check action dropdown
    featurePages.verifyWifiNotConnectIn(time);
    browser.sleep(1000); //StaleElementReference
    featurePages.verifyretryLater(retry);

  }

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

  @DataProvider(name = "wifi")
  public Object[][] createData11() {
    return new Object[][]{
      //time, retry , networkinout, addCriteria, testGroup, publish
      {"2 m", "15 m", "none", "networktypeapplication", "libary", "yes"},
      {"2 m", "15 m", "in_libary", "networktypeapplication", "textfield", "yes"},
      {"5 m", "45 m", "out_new", "appvisibility", "new", "no"},
      {"10 m", "90 m", "in_libary", "time", "textfield", "no"},};
  }
}
