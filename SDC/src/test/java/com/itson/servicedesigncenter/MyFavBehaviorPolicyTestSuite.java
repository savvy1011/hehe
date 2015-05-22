package com.itson.servicedesigncenter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.DataProvider;

public class MyFavBehaviorPolicyTestSuite extends AbstractTest {

  public String savedMyFavName;

  @BeforeMethod
  public void setUp() {
    browser.logAction("in before test method" + portalPages + "\n");
    topnav = "myfav";
    portalPages.setTopNavLink(topnav);
    myFavSdcPages = new MyFavSdcPages(browser);
  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkMyFavMenu() {
    browser.reportLinkLog("checkMyFavMenu");
  }

  @Test(dataProvider = "myFav", groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkCreateDeleteMyFavPolicy(String myFavMaximumNumberGiven, String myFavAddPhoneSelectionGiven,
          String myFavChangeExistingNumberSelectionGiven, String myFavReplaceDeletedNumberSelectionGiven,
          String myFavDeleteNumberSelectionGiven) {
    browser.reportLinkLog("checkCreateMyFavPolicy");
    savedMyFavName = "myFav" + browser.randomText(4);
    //setdata
    myFavSdcPages.setMyFavData(savedMyFavName, "", myFavMaximumNumberGiven, myFavAddPhoneSelectionGiven,
            myFavChangeExistingNumberSelectionGiven,
            myFavReplaceDeletedNumberSelectionGiven, myFavDeleteNumberSelectionGiven, "");
    //new btn
    myFavSdcPages.setCreateNewGroupBtn();
    //create policy
    myFavSdcPages.createMyFavPolicy()
            //verify policy
            .verifyMyFavPolicy();
    myFavSdcPages.setGroupDelete(savedMyFavName, topnav);

  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkNameBlankFieldNotAbleToSave() {
    browser.reportLinkLog("checkNameBlankFiledNotAbleToSave");
    browser.log(Level.INFO, "In method checkNameBlankFiledNotAbleToSave");
    myFavSdcPages
            .setCreateNewGroupBtn();
    myFavSdcPages
            .setMyFavAllowablePhoneField("");
    browser.dropDownSelectorBy(myFavSdcPages.myFavMaximumNumberDropDown, "2");
    browser.click(myFavSdcPages.myFavAddPhoneCheckBox);
    myFavSdcPages
            .setCheckSaveCheckMyFavbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkAllowablePhoneBlankFieledNotAbleToSave() {
    browser.reportLinkLog("checkNameBlankFiledNotAbleToSave");
    browser.log(Level.INFO, "In method checkNameBlankFiledNotAbleToSave");
    myFavSdcPages
            .setCreateNewGroupBtn();
    myFavSdcPages
            .setMyFavNameTextField("Test");
    browser.dropDownSelectorBy(myFavSdcPages.myFavMaximumNumberDropDown, "2");
    browser.click(myFavSdcPages.myFavAddPhoneCheckBox);
    myFavSdcPages
            .setCheckSaveCheckMyFavbtn()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkrow() {
    browser.reportLinkLog("checkrow");
    myFavSdcPages.checkrow();
  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkEditBtn() {
    browser.reportLinkLog("checkEditBtn");
    browser.log(Level.INFO, "In method checkEditBtn");
    myFavSdcPages
            .setTableEdit()
            .setGroupCancelBtn();
  }

  @Test(groups = {"SDC", "Zact", "zactOnly", "MyFav"})
  public void checkDeleteBtn() {
    browser.reportLinkLog("checkEditBtn");
    browser.log(Level.INFO, "In method checkEditBtn");
    myFavSdcPages
            .setTableDelete();
    browser.waitForClickableElement(By.id("confirmCancelBtn"));
  }

  @Test(expectedExceptions = TimeoutException.class, groups = {"Feature", "SDC", "Sprint", "SprintOnly"})
  public void checkNoMyFavMenu() {
  }

  @DataProvider(name = "myFav")
  public Object[][] createMyFavData() {
    return new Object[][]{
      //myFavMaximumNumber,myFavAddPhoneSelection ,myFavChangeExistingNumberSelection ,myFavReplaceDeletedNumberSelection,myFavDeleteNumberSelection
      {"2", "all", "", "", ""},
      {"3", "yes", "", "", ""},
      {"1", "", "yes", "yes", ""},
      {"4", "", "yes", "", ""},
      {"5", "", "yes", "", "yes"},
      {"5", "yes", "yes", "", "yes"},
      {"6", "all", "", "yes", ""},
      {"7", "", "", "", "yes"},};
  }

}
