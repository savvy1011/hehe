/**
 *Checks the manifest section
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SoftwareManifestTestSuite extends AbstractTest {

  private String savedName;
  private SoftwareManifestPages softwareManifestPages;

  @BeforeMethod
  public void setUp()  {
    browser.logAction("in before test method " + portalPages + "\n");
    browser.logAction("******Staring SoftwareManifestPages*****");

    softwareManifestPages = new SoftwareManifestPages(browser);
    topnav = "Manifests";
    portalPages.setTopNavLink(topnav);
  }

  @Test(groups = {"OTA", "Sprint","Zact"})
  public void createNewManifestWithWrongFormatVersion()  {
    browser.logAction("in createNewManifestWithWrongFormatVersion() test method\n");
    
    String version = browser.randomName();
    String minVersion = browser.randomName();
    String fingerPrint = browser.randomName();
    
    softwareManifestPages
      .setNewManifestBtn()
      .setNewVersion(version)
      .setNewMinVersion(minVersion)
      .setNewFingerPrint(fingerPrint)
      .setSavebtn();
    
    // expected: wrong format version
    browser.waitForVisibilityOfElement(By.id("helpText_Wrong_Format_Version"));
    String error = browser.findElement(By.id("helpText_Wrong_Format_Version")).getText();
    
    browser.logAction("got this " + error);
    browser.compareText(error, "Major Version is incorrect format");
  }
  
  @Test(groups = {"OTA", "Sprint","Zact"})
  public void createNewManifest()  {
    browser.logAction("in createNewManifest() test method\n");
    
    String version = "4.4.0";
    String minVersion = "4.4.2";
    String fingerPrint = "test_" + browser.randomName();
    
    softwareManifestPages
      .setNewManifestBtn()
      .setNewVersion(version)
      .setNewMinVersion(minVersion)
      .setNewFingerPrint(fingerPrint);
      //.setSavebtn();
    
    // find fingerPrint in list
  }
  
  @Test(groups = {"OTA", "Sprint","Zact"})
  public void checkrow() {
    portalPages.checkrow();
  }
  
  @Test(groups = {"OTA", "Sprint","Zact"})
  public void createNewPolicyForManifest()  {
    browser.logAction("in createNewPolicyForManifest() test method\n");
    softwareManifestPages.createNewPolicy();
  }
}