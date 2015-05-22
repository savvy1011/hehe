package com.itson.servicedesigncenter;

/**
 * Test suite for the Voice feature with Myfav filter flow
 *
 * @author gurtejphangureh
 */
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.itson.operatorplatform.*;
import org.junit.BeforeClass;

public class MyFavVoiceSmokeTestSuite extends AbstractTest {


  protected String productName;


  @Test(groups = {"Feature", "Voice", "SDC", "Zact", "zactOnly"})
  public void createFeaturesVoicePurchasablePolicy() {
 
    groupName = "myfav" + browser.randomName();
      
    /* //setdata
    featurePages.setMyFavData(groupName, "", "2", "all",
            "",
            "", "", "new");

    browser.logAction("***Starting Features Voice Policy test");
    browser.reportLinkLog(groupName);
    savedNetworkinout = "in_libary";
    featurePages.featuresPolicy("purchasable", "voice", groupName, "behavior", "Cellular Network", "time", "Allow", "in_libary", "libary", "yes", "no", "yes", "yes", "no");
            */
    portalPages.setLogOutTopNavLink();

  }

  @Test(groups = {"Feature", "Voice", "SDC", "Zact", "zactOnly"}, dependsOnMethods = {"createFeaturesVoicePurchasablePolicy"})
  public void createEntitlementFeeAddPhone() {

    browser.logAction("*****Got " + broadleafUrl);
    //goto broadleaf
    browser.getSite(broadleafUrl);
    blAdminUser = config.getBlUsername();
    blAdminPass = config.getBlPassword();
    logInPages.lognIn(blAdminUser, blAdminPass);
    productName = groupName + "FEEAdd";
    String policySelection = groupName + " (PREFERRED_DESTINATIONS)";
    //Get Data
    entitlementPages.setEntitlementData("test", policySelection, "Add number", "1.11", "2.22", "Voice Plan");
    browser.logAction("Creating Plan with " + productName);
    entitlementPages.createEntitlementFee(productName);
  }

  @AfterMethod
  protected void featureCleanUp() {
    //featurePages.featureCleanUp(groupName, savedNetworkinout);
  }

}
