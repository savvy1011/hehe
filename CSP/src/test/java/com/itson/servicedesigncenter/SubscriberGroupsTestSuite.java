package com.itson.servicedesigncenter;

import java.util.Collection;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriberGroupsTestSuite extends AbstractTest {

  @BeforeMethod
  public void setUp() {
    browser.logAction("in before test method" + this.portalPages + "\n");
    topnav = "subscriber";
    portalPages.setTopNavLink(topnav);
    groupName = browser.randomName();
    groupName2 = browser.randomName();
    groupName3 = browser.randomName();
    phoneNumber = browser.randomPhoneNumber();

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void createGroupVerifyDelete() {
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn() //input name save button is not active
            .setSubscriberGroupPhoneNumber(phoneNumber)
            .setAddOkBtn();
    browser.sleep(1000);
    //portalPages.setPortalLogoutLogin(adminUser, adminPass);
    portalPages.setTopNavLink(topnav).verifyGroupsAdded(groupName).verifySubscriberGroup(groupName);//Verify: group added
    portalPages.setGroupDelete(groupName, topnav);  //delete groups
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void addMutipleGroups() {
    createDeleteGroup(groupName, phoneNumber);
    createDeleteGroup(groupName2, phoneNumber);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkNextBtn() {
    portalPages.setSubscriberGroupNextBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkPrevBtn() {

    //browser.reportLinkLog("C32100",saucelabs);
    portalPages
            .setSubscriberGroupNextBtn()
            .setSubscriberGroupPrevBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkDecimalNumbersNotAccepted() {
    Reporter.log("validation not working for phonenumbers MDN input");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("1.2 ")//input phone number
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkPhoneNumberLength() {

    //browser.reportLinkLog("C32225",saucelabs);
    Reporter.log("validation not working for phonenumbers MDN input");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("408123456789")//input phone number
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkNoAlphabetical() {
    Reporter.log("validation not working for phonenumbers MDN input");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("jdfabmfsdbf ")//input phone number
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkNoNegNum() {

    //browser.reportLinkLog("C32227",saucelabs);
    Reporter.log("validation not working for phonenumbers MDN input");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("-23654725 ")//input phone number
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkIncorrectEntryNotSaved() {

    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("-23654725 9.0 -444 sfjdnf ")//input phone number
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkNoDuplicateNumber() {
    //browser.reportLinkLog("C32230",saucelabs);
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)//input name
            .setSubscriberGroupPhoneNumber("4085551212 ")//input phone number
            .setSubscriberGroupPhoneNumber("4085551212 ");//input phone number
    Collection col = browser.findElements(By.cssSelector(".tag-item ng-scope error"));
    if (browser.findElements(By.cssSelector(".alert")).size() == 0) {
      throw new RuntimeException("No Error");
    }

    portalPages
            .setCheckSaveCheckbtn()
            .setSubscriberGroupCancelBtn();//save button is not active
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkrow() {
    portalPages.checkrow();

  }

  public SdcPages createDeleteGroup(String groupName, String phoneNumber) {
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName) //input name
            .setSubscriberGroupPhoneNumber(phoneNumber)
            .setAddOkBtn() //add phone number
            .verifyGroupsAdded(groupName)
            .verifySubscriberGroup(groupName)
            .setGroupDelete(groupName, topnav);

    return portalPages;
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkDeleteBtn() {
    browser.reportLinkLog("checkDeleteBtn");
    portalPages
            .setTableDelete()
            .setNetworkDeleteCancel();

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void checkEditBtn() {
    browser.reportLinkLog("checkEditBtn");
    portalPages
            .setTableEdit()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "SubscriberGroups"})
  public void editSubscriberGroups() {
    browser.log(Level.INFO, "In method C32017");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName) //input name
            .setSubscriberGroupPhoneNumber(phoneNumber)
            .setAddOkBtn()
            .verifyGroupsAdded(groupName)
            .setTableEdit()
            .setUnlockSubscriber()
            .verifyGroupName(groupName)
            .setSubscriberGroupPhoneNumber("40853412 ")
            .setSubscriberGroupPhoneNumber("4085341212 ")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn()
            .setGroupDelete(groupName, topnav);
  }

}
