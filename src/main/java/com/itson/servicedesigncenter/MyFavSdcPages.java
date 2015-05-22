/**
 * locators and methods for my sdc section
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyFavSdcPages extends SdcPages {

  public MyFavSdcPages(Browser browser) {
    super(browser);
  }

  protected String behaviorAllowedCheckbox;

  //modal to create myfav policy
  @FindBy(how = How.ID, using = "input-MyFav_Name")
  private WebElement myFavNameTextField;
  @FindBy(how = How.ID, using = "allowable_Phone_Number")
  private WebElement myFavAllowablePhoneField;
  @FindBy(how = How.ID, using = "maximum_Number_of_Favs")
  public WebElement myFavMaximumNumberDropDown;
  //Add behavior allowed checkbox section
  @FindBy(how = How.ID, using = "input-Checkbox_Behavior_Add")
  public WebElement myFavAddPhoneCheckBox;
  @FindBy(how = How.ID, using = "input-Checkbox_Behavior_Change")
  private WebElement myFavChangeExistingNumberCheckBox;
  @FindBy(how = How.ID, using = "input-Checkbox_Behavior_Replace")
  private WebElement myFavReplaceDeletedNumberCheckBox;
  @FindBy(how = How.ID, using = "input-Checkbox_Behavior_Remove")
  private WebElement myFavDeleteNumberCheckBox;

  @FindBy(how = How.ID, using = "confirmAction_btn")
  private WebElement myFavSaveBtn;
  @FindBy(how = How.ID, using = "addCancelBtn")
  private WebElement myFavCancelBtn;

  public MyFavSdcPages createMyFavPolicy() {
    browser.logAction("creating MyFavPolicy");
    browser.waitForClickableElement(myFavNameTextField);
    //browser.waitForText(By.cssSelector("h4.ng-binding"), "Add MyFav Behavior Policy");
    setMyFavNameTextField(myFavName);
    setMyFavAllowablePhoneField(myFavAllowablePhone);
    browser.logAction("Selecting Dropdown myFavMaximumNumberDropDown" + myFavMaximumNumber);
    browser.dropDownSelectorBy(myFavMaximumNumberDropDown, myFavMaximumNumber);
    setBehaviorAllowedCheckbox();
    browser.logAction("Clcicking Save Btn");
    browser.click(myFavSaveBtn);
    browser.invisibilityOfElementLocated(By.id("input-MyFav_Name"));
    return this;
  }

  public MyFavSdcPages setMyFavNameTextField(String text) {
    browser.logAction("Inputing Text Name Field " + text);
    browser.sendKeys(myFavNameTextField, text, true);
    return this;
  }

  public MyFavSdcPages setMyFavAllowablePhoneField(String text) {
    if (text.equals("none") || text.equals("")) {
      text = "^(?:\\+?1)?(800|866)[0-9]{7}$";
    }
    browser.logAction("Inputing allowablePhoneField " + text);
    browser.sendKeys(myFavAllowablePhoneField, text, true);
    return this;
  }

  public MyFavSdcPages setBehaviorAllowedCheckbox() {
    browser.logAction("Selecting Behavior Allowed Checkboxes");
    if (myFavAddPhoneSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Selecting addPhoneCheckBox");
      browser.click(myFavAddPhoneCheckBox);
    }
    if (myFavChangeExistingNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Selecting changeExistingNumberCheckBox");
      browser.click(myFavChangeExistingNumberCheckBox);
    }
    if (myFavReplaceDeletedNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Selecting replaceDeletedNumberCheckBox");
      browser.click(myFavReplaceDeletedNumberCheckBox);
    }
    if (myFavDeleteNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Selecting replaceDeletedNumberCheckBox");
      browser.click(myFavDeleteNumberCheckBox);
    }

    return this;
  }

  public MyFavSdcPages verifyMyFavPolicy() {
    verifyGroupsAdded(myFavName);
    // click edit btn
    setTableEdit();
    //click unlock
    browser.logAction("Clicking Unclock Btn");
    browser.waitForClickableElement(By.id("toggleBtn_locked")).click();
    verifyMyFavPolicyInfomation();
    return this;
  }

  public MyFavSdcPages verifyMyFavPolicyInfomation() {
    browser.logAction("verifyMyFavPolicyInfomation Started");
    //check name field
    // browser.waitForText(By.cssSelector("h4.ng-binding"), "View Add MyFav Behavior Policy");
    browser.verifyTextField(myFavNameTextField, myFavName);
    browser.verifyTextField(myFavAllowablePhoneField, myFavAllowablePhone);
    browser.verifyDropDownSelected(myFavMaximumNumberDropDown, myFavMaximumNumber);
    verifyBehaviorAllowedCheckbox();
    return this;
  }

  public MyFavSdcPages verifyBehaviorAllowedCheckbox() {
    browser.logAction("Verifying Behavior Allowed Checkboxes");
    if (myFavAddPhoneSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Verifying addPhoneCheckBox");
      browser.checkRadioBtnSelected(myFavAddPhoneCheckBox);
    }
    if (myFavChangeExistingNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Verifying changeExistingNumberCheckBox");
      browser.checkRadioBtnSelected(myFavChangeExistingNumberCheckBox);
    }
    if (myFavReplaceDeletedNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Verifying replaceDeletedNumberCheckBox");
      browser.checkRadioBtnSelected(myFavReplaceDeletedNumberCheckBox);
    }
    if (myFavDeleteNumberSelection.equals("yes") || myFavAddPhoneSelection.equals("all")) {
      browser.logAction("Verifying replaceDeletedNumberCheckBox");
      browser.checkRadioBtnSelected(myFavDeleteNumberCheckBox);
    }
    return this;
  }

  public SdcPages setCheckSaveCheckMyFavbtn() {
    //confirmAction_btn
    if (myFavSaveBtn.getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");
    }
    else {
      throw new RuntimeException("Button is enabled");
    }
    return this;
  }

  //Setting Data
  protected String myFavCreationType;
  protected String myFavName;
  protected String myFavDescription;
  protected String myFavAllowablePhone;
  protected String myFavMaximumNumber;
  protected String myFavAddPhoneSelection;
  protected String myFavChangeExistingNumberSelection;
  protected String myFavReplaceDeletedNumberSelection;
  protected String myFavDeleteNumberSelection;

  protected String[] myFavData;

  protected String[] setMyFavData(String givenData) {
    myFavData = givenData.split(",");
    myFavName = myFavData[0];
    myFavAllowablePhone = myFavData[1];
    myFavMaximumNumber = myFavData[2];
    myFavAddPhoneSelection = myFavData[3];
    myFavChangeExistingNumberSelection = myFavData[4];
    myFavReplaceDeletedNumberSelection = myFavData[5];
    myFavDeleteNumberSelection = myFavData[6];

    try {
      myFavCreationType = myFavData[7];
    }
    catch (Exception e) {
      myFavCreationType = "new";
    }
    return myFavData;
  }

  //Used set data for Test not using CSV
  protected void setMyFavData(String myFavNameGiven,
          String myFavAllowablePhoneGiven, String myFavMaximumNumberGiven, String myFavAddPhoneSelectionGiven,
          String myFavChangeExistingNumberSelectionGiven, String myFavReplaceDeletedNumberSelectionGiven,
          String myFavDeleteNumberSelectionGiven,String type) {
    myFavName = myFavNameGiven;
    myFavAllowablePhone = myFavAllowablePhoneGiven;
    if (myFavAllowablePhone.equals("none") || myFavAllowablePhone.equals("")) {
      myFavAllowablePhone = "^(?:\\+?1)?(800|866)[0-9]{7}$";
    }
    myFavMaximumNumber = myFavMaximumNumberGiven;
    myFavAddPhoneSelection = myFavAddPhoneSelectionGiven;
    myFavChangeExistingNumberSelection = myFavChangeExistingNumberSelectionGiven;
    myFavReplaceDeletedNumberSelection = myFavReplaceDeletedNumberSelectionGiven;
    myFavDeleteNumberSelection = myFavDeleteNumberSelectionGiven;
    myFavCreationType = type;
  }

}
