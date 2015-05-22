/**
 * Checks the feature section
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeatureTestSuite extends SdcAbstractClass {

  @BeforeMethod
  public void setUpFeature() {
    browser.logAction("in before test method" + portalPages + "\n");
    topnav = "features";
    portalPages.setTopNavLink(topnav);
    groupName = this.browser.randomName();
    groupName2 = this.browser.randomName();
    groupName3 = this.browser.randomName();
  }

  @Test(groups = {"Feature", "SDC", "Zact", "Sprint"})
  public void checkPage() {
    browser.reportLinkLog("checkPage");
  }

  @Test(groups = {"Feature", "SDC", "Zact", "Sprint"})
  public void createGroup() {
    browser.reportLinkLog("createGroup");
    browser.logAction("in createGroup() test method\n");
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    //cleanup
    portalPages.tryGroupDelete(groupName, topnav);
    browser.logAction("in createGroup() test method\n");
  }

  @Test(groups = "noDevBuild")
  public void checkFeatureCode() {   //Add a new feature and save it; then go back to the feature topnav and try adding the same feature code again; check the error
    browser.reportLinkLog("checkFeatureCode");
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    portalPages
            .setTopNavLink(topnav);
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    browser.waitForId("helpText_duplicate_featureCode");
  }

  @Test(groups = {"Feature", "SDC", "Zact", "Sprint"})
  public void createFeature() {
    browser.reportLinkLog("createFeature");
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    //confirm taken to draft view
    browser.logAction("@@ " + groupName + " >> Draft Mode");
    portalPages.tryGroupDelete(groupName, topnav);
    /* TODO-harry: commented out due to RaceCondition
     featurePages.checkDraftMode();
     */
  }

  @Test(groups = "Feature")//not working with new build
  public void createFeatureCheckSaved() {
    browser.reportLinkLog("createFeatureCheckSaved");
    //create new feature
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    //confirm taken to draft view
    featurePages
            .checkDraftMode();
    portalPages
            .setTopNavLink(topnav)
            .verifyGroupsAdded(groupName)
            .setTableEdit();
    featurePages.setUnlockFeatures();

  }

  @Test(groups = "noDevBuild")
  public void createFeatureDelete() throws InterruptedException {
    browser.reportLinkLog("createFeatureDelete");
    //create new feature
    featurePages
            .setNewFeatureBtn("publishable")
            .setNewFeatureName(groupName)
            .setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1")
            .setNewFeatureSavebtn();
    browser.waitForVisibilityOfElement(By.id("add-policy-btn"));
    browser.sleep(60000);
    featurePages
            .verifyFeatureAdded(groupName);
    portalPages
            .setTableDelete();
    featurePages
            .setSearchDeleteConfirmBtn();
  }

  @Test(groups = {"Feature", "SDC", "Zact", "Sprint"})
  public void checkrow() {
    browser.reportLinkLog("checkrow");
    portalPages.checkrow();
    //check table change in topnav
   /*     
     //get feature code from 1st row
     String getFeatureCode1  = driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2)")).getText();
     driver.findElement(By.cssSelector(".dataTables_paginate > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")).click();
     String getFeatureCode2  = driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2)")).getText();
     if(getFeatureCode1.equalsIgnoreCase(getFeatureCode2)){
     System.err.println("Fail: code match on both topnavs");
     throw new RuntimeException();
     }
     */
  }

  @Test(groups = {"Feature", "SDC", "Zact", "zactOnly"})
  public void checkrowPurchasable() {
    browser.reportLinkLog("checkrowPurchasable");
    browser.waitForClickableElement(By.partialLinkText("Purchasable Features")).click();
    browser.sleep(1000); //wait for table to switch
    portalPages.checkrow();
  }

  @Test(expectedExceptions = TimeoutException.class, groups = {"Feature", "SDC", "Sprint", "SprintOnly"})
  public void checkNoPurchasableTab() { //IOQE-153
    browser.reportLinkLog("checkNoPurchasableTab");
    browser.findElementFast(By.xpath("//a[contains(text(),'Purchasable Features')]")).click();
  }

  @Test(groups = {"Feature", "SDC", "Zact","Sprint"})
  public void noResults() {
    browser.reportLinkLog("noResults");
    featurePages.setPolicySearchfield(browser.randomText(10));
    browser.sleep(3000); //wait for table to switch
    browser.waitForClickableElement(By.id("dataTables_empty"));
  }

}
