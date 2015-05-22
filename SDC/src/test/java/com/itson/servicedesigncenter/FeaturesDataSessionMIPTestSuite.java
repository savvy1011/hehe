/**
 * Test suite for Mip flow in features
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FeaturesDataSessionMIPTestSuite extends AbstractTest {

  public String savedName;

  @Test(dataProvider = "qa", groups = {"Feature", "Mip", "SDC", "Zact", "Sprint"})
  public void createMipPolicy(String trigger, String policyEvent, String addCriteria, String networkinout, String testGroup, String publish) {
    browser.logAction("***Starting MIP Policy test");
    topnav = "features";
    savedNetworkinout = networkinout;
    portalPages.setTopNavLink(topnav);
    groupName = this.browser.randomName() + "_mip";
    browser.reportLinkLog(groupName);
    //create new feature
    featurePages.setNewFeatureBtn("publishable").setNewFeatureName(groupName).setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1").setNewFeatureSavebtn();
    featurePages.addpolicy("mip");
    featurePages.dataSessionSettingsTriggerDropDownMenu(trigger);
    featurePages.dataSessionDefaultPolicyEventActionDropDownMenu(policyEvent);

    featurePages.dataSessionDefaultPolicyEventAddCriteria(addCriteria);

    featurePages.dataSessionDefaultPolicyEventBlockNetworkGroupsIN(networkinout, groupName);
    browser.sleep(500); //wait for NetworkGroups to disappear
    //Add Test group
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
    featurePages.verifyNetworkFilterAdded("networkRemove");

  }

  @AfterMethod
  protected void featureCleanUp() {
    featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

  @DataProvider(name = "qa")
  public Object[][] createData11() {
    return new Object[][]{
      // trigger, policyEvent, addCriteria , networkinout, testGroup,publish
      {"0", "Allow", "downloading", "none", "libary", "yes"},
      {"0", "None", "screenon", "out_new", "new", "no"},
      {"1", "Allow", "streamingmedia", "in_libary", "textfield", "no"},};
  }
}
