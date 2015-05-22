package com.itson.servicedesigncenter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.logging.Level;
import org.openqa.selenium.By;

public class NetworkGroupsTestSuite extends AbstractTest {

  @BeforeMethod
  public void setUp() {
    browser.logAction("in before test method" + portalPages + "\n");
    topnav = "network";
    portalPages.setTopNavLink(topnav);
    groupName = browser.randomName() + "network";
    groupName2 = browser.randomName() + "network";
    groupName3 = browser.randomName() + "network";
  }

  private SdcPages createDeleteNetworkGroup(String groupName, String CDMANumber) {
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName) //enter name and check save not active
            .setNetworkCdmaNumber(CDMANumber)
            .setMccMnc("310/120")
            .setAddOkBtn();
    portalPages.verifyGroupsAdded(groupName)
            .verifyNetworkGroupColumn("1", "1")
            .setTableEdit()
            .verifyNetworkGroup(groupName);
    portalPages.setGroupDelete(groupName, topnav);
    return portalPages;
  }
  //SAAS-7820 add ability to add MNC starting with preceding zero's
   private SdcPages createDeleteNetworkGroupMncWithZero(String groupName, String CDMANumber) {
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName) //enter name and check save not active
            .setNetworkCdmaNumber(CDMANumber)
            .setMccMnc("310/00")
            .setAddOkBtn();
    portalPages.verifyGroupsAdded(groupName)
            .verifyNetworkGroupColumn("1", "1")
            .setTableEdit()
            .verifyNetworkGroup(groupName);
    portalPages.setGroupDelete(groupName, topnav);
    return portalPages;
  }


  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNetworkGroupMenu() {
    browser.reportLinkLog("checkNetworkGroupMenu");
    browser.log(Level.INFO, "In method checkNetworkGroupMenu");
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkCreateGroupDeleteGroup() {
    browser.reportLinkLog("checkCreateGroupDeleteGroup");
    browser.log(Level.INFO, "In method checkCreateGroupDeleteGroup with Group Name: " + groupName);
    createDeleteNetworkGroup(groupName, "123");
    browser.log(Level.INFO, "In method checkCreateGroupDeleteGroup with Group Name: " + groupName);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNameBlankFieldNotAbleToSave() {
    browser.reportLinkLog("checkNameBlankFiledNotAbleToSave");
    browser.log(Level.INFO, "In method checkNameBlankFiledNotAbleToSave");
    portalPages
            .setCreateNewGroupBtn()
            .setNetworkCdmaNumber("1234 ")
            .setNetworkCdmaNumber("12345 ")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkDecimalNotAcceptted() {
    browser.reportLinkLog("checkDecimalNotAcceptted");
    browser.log(Level.INFO, "In method checkDecimalNotAcceptted");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("1.8")
            .setNetworkCdmaNumber("2.5")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkSIDFieldRange() {
    browser.reportLinkLog("checkSIDFieledRange");
    browser.log(Level.INFO, "In method checkSIDFieledRange");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("1767697798698769876987698")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNoAlphabeticalCdma() {
    browser.reportLinkLog("checkNoAlphabeticalCdma");
    browser.log(Level.INFO, "In method checkNoAlphabeticalCdma");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("test")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkClickingCancelBtnNotSaved() {
    browser.reportLinkLog("checkClickCancelBtnNotSaved");
    browser.log(Level.INFO, "In method checkClickCancelBtnNotSaved");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("12345")
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNegativeSidNotAccepted() {
    browser.reportLinkLog("checkNegativeSidNotAccepted");
    browser.log(Level.INFO, "In method checkNegativeSidNotAccepted");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("-545")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNotSaveUntilErrorsCleared() {
    browser.reportLinkLog("checkNotSaveUntilErrorsCleared");
    browser.log(Level.INFO, "In method checkNotSaveUntilErrorsCleared");
    if (groupName == null) {
      System.out.println("NullPointerException being thrown");
      throw new NullPointerException("Group Name is null");
    }
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("-123")
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("123.5")
            .setCheckSaveCheckbtn()
            .setinputIgnoreErrors()
            .setNetworkCdmaNumber("123")
            .setAddOkBtn()
            .setGroupDelete(groupName, topnav);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void editNetworkGroup() {
    browser.reportLinkLog("editNetworkGroup");
    browser.log(Level.INFO, "In method C32017");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("123")
            .setAddOkBtn()
            .verifyGroupsAdded(groupName)
            .setTableEdit()
            .setUnlockNetwork()
            .setNetworkCdmaNumber("-234")
            .setNetworkCdmaNumber("0.9")
            .setCheckSaveCheckbtn()
            .setGroupCancelBtn()
            .setGroupDelete(groupName, topnav);
  }

  @Test(groups = {"NetworkGroup"})
  public void checkAmountNewEntriesInEdit() {
    browser.reportLinkLog("checkAmountNewEntriesInEdit");
    browser.log(Level.INFO, "In method checkAmountNewEntriesInEdit");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName) //enter name and check save not active
            .setCheckSaveCheckbtn()
            .setNetworkCdmaNumber("123")
            .setAddOkBtn()
            .setTopNavLink("network")
            .verifyGroupsAdded(groupName) //find this group in group table
            .setTableEdit()
            .setUnlockNetwork()
            .setNetworkCdmaNumber("1") //in view/edit mode add 5 more SIDs: 1 2 3 4 5
            .setNetworkCdmaNumber("2")
            .setNetworkCdmaNumber("3")
            .setNetworkCdmaNumber("4")
            .setNetworkCdmaNumber("5")
            .compareModalMessage("6")
            .setAddOkBtn()
            .setGroupDelete(groupName, topnav);
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkDeleteBtn() {
    browser.reportLinkLog("checkDeleteBtn");
    browser.log(Level.INFO, "In method C32013");
    portalPages
            .setTableDelete()
            .setNetworkDeleteCancel();

  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkEditBtn() {
    browser.reportLinkLog("checkEditBtn");
    browser.log(Level.INFO, "In method checkEditBtn");
    portalPages
            .setTableEdit()
            .setGroupCancelBtn();
  }

  @Test(groups = {"NetworkGroup"})
  public void createDeleteMutipleGroups() {
    browser.reportLinkLog("createDeleteMutipleGroups");
    browser.log(Level.INFO, "In method creatDeleteMutipleGroups");
    createDeleteNetworkGroup(groupName, "4445");
    createDeleteNetworkGroup(groupName2, "2323");
    // createDeleteNetworkGroup(groupName3, "5656");
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkNotAbleSaveDuplicateSid() {
    browser.reportLinkLog("checkNotAbleSaveDuplicateSid");
    browser.log(Level.INFO, "In method checkNotAbleSaveDuplicateSid");
    portalPages
            .setCreateNewGroupBtn()
            .setNetworkCdmaNumber("123")
            .setNetworkCdmaNumber("123")
            .setCheckSaveCheckbtn();
    //does not have no error for 2nd #
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkrow() {
    browser.reportLinkLog("checkrow");
    portalPages.checkrow();
  }

  @Test(groups = {"SDC", "Zact", "Sprint", "NetworkGroup"})
  public void checkDeleteConfirmationDialogDisappears() { //SAAS-6889
    browser.reportLinkLog("checkDeleteConfirmationDialogDisappears");
    portalPages
            .setCreateNewGroupBtn()
            .setGroupInputName(groupName)
            .setNetworkCdmaNumber("123")
            .setAddOkBtn();
    portalPages.setTopNavLink(topnav);
    browser.waitForPageLoaded();
    portalPages.verifyNetworkAdded(groupName).setTableDelete();
    portalPages.setConfirmOkBtn();
    browser.invisibilityOfElementLocated(By.cssSelector(".modal-header > h4:nth-child(2)"));
    browser.logAction("Done Deleting Network");
    portalPages.verifyGroupDeleted(groupName, topnav);
  }

}
