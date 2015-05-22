package com.itson.servicedesigncenter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProvisioningTestSuite extends SdcAbstractClass {

  String deafultGroup;
  String notDeafultGroup;
  String gotAttribute;

  @BeforeMethod
  public void setUp() {
    browser.logAction("in before test method" + portalPages + "\n");
    topnav = "provisioning";
    portalPages.setTopNavLink(topnav);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Provisioning"})
  public void checkPage() {

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Provisioning"})
  public void checkrow() {
    portalPages.checkrow();

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Provisioning", "noDevBuild"})
  public void checkOnOffDefault() {
    deafultGroup = "00001aaaaaa" + browser.randomName();
    groupName = deafultGroup;
    //deafultGroup
    featurePages.featuresPolicy("publishable", "data", deafultGroup, "app", "3G", "time", "Allow", "none", "libary", "yes", "no", "yes", "yes", "no");
    browser.sleep(1000);
    portalPages.setTopNavLink(topnav);
    gotAttribute = featurePages.getPoilcySwitchAttribute(deafultGroup, "off");
    browser.logAction("Got This " + gotAttribute);
    browser.compareText(gotAttribute, "Off");
    featurePages.featureCleanUp(deafultGroup, "no");

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "Provisioning", "unstable"})
  public void checkOnOffNotDefault() {
    notDeafultGroup = "01aaaaa" + browser.randomName();
    groupName = notDeafultGroup;
    //notDeafultGroup
    featurePages.featuresPolicy("publishable", "data", notDeafultGroup, "app", "3G", "time", "Allow", "none", "libary", "yes_notdefault", "no", "yes", "yes", "no");
    portalPages.setTopNavLink(topnav);
    gotAttribute = featurePages.getPoilcySwitchAttribute(notDeafultGroup, "on");
    browser.logAction("*****Got This " + gotAttribute);
    browser.compareText(gotAttribute, "On");
    featurePages.featureCleanUp(notDeafultGroup, "no");
  }

  @AfterMethod
  protected void featureCleanUp() {
    // featurePages.featureCleanUp(groupName, "libary");
    restCalls.unPublishFeature(featurePages.returnFeatureId());

  }

}
