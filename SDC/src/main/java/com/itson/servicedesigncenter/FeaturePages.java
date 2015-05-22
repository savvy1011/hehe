/**
 * locators and methods for Feature sections
 *
 * @author Gurtej Phangureh
 * @author Eugene Voznesensky
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Level;

public class FeaturePages extends MyFavSdcPages {

  protected String savedGroupName;
  protected String networkGroupType = "none";
  protected String policyType;
  protected String featureId;
  protected String dataFilterType;

  public FeaturePages(Browser browser) {
    super(browser);
  }

  //New Feature
  @FindBy(how = How.CSS, using = ".btn-primary")
  private WebElement createNewFeatureBtn;
  @FindBy(how = How.LINK_TEXT, using = "Publishable Feature")
  private WebElement newPublishableFeatureSelection;
  @FindBy(how = How.LINK_TEXT, using = "Purchasable Feature")
  private WebElement newPurchasableFeatureSelection;
  @FindBy(how = How.ID, using = "input-Feature_Name")
  private WebElement newFeatureNameField;
  @FindBy(how = How.ID, using = "input_featureCode")
  private WebElement newFeatureCodeField;
  @FindBy(how = How.ID, using = "input-Feature_Description")
  private WebElement newFeatureDescriptionField;
  @FindBy(how = How.ID, using = "addCancelBtn")
  private WebElement newFeatureCancelBtn;
  @FindBy(how = How.ID, using = "confirmAction_btn")
  private WebElement newFeatureSaveBtn;
  @FindBy(how = How.ID, using = "confirmOkBtn")
  public WebElement searchDeleteConfirmBtn;
  //draft view
  @FindBy(how = How.ID, using = "add-policy-btn")
  private WebElement addPolicyBtn;
  @FindBy(how = How.CSS, using = ".m-btn-add-component :nth-of-type(1)")
  private WebElement addNewComponentBtn;
  @FindBy(how = How.ID, using = "name")
  private WebElement nameField;
  @FindBy(how = How.CSS, using = ".btn-primary")
  private WebElement addNewComponenSaveBtn;
  @FindBy(how = How.CLASS_NAME, using = "m-btn-add-policy-event")
  private WebElement addPolicyEvent;
  @FindBy(how = How.ID, using = "add-test-group-btn")
  private WebElement addTestGroupBtn;
  @FindBy(how = How.ID, using = "btn_testSubLib")
  private WebElement addTestGroupBtnSelectionFromLibary;
  @FindBy(how = How.ID, using = "libraryOkBtn")
  private WebElement addTestGroupLibarySavebtn;
  @FindBy(how = How.ID, using = "testFeatureBtn")
  public WebElement testFeatureBtn;

  //data policy
  @FindBy(how = How.CSS, using = ".m-btn-add-criteria :nth-of-type(1)")
  private WebElement addCriteriaBtn;
  @FindBy(how = How.CLASS_NAME, using = "m-dropdown-menu-option-activeNetwork")
  private WebElement addCriteriaActiveNetworkSelection;
  @FindBy(how = How.CLASS_NAME, using = "m-dropdown-menu-option-applicationVisibility")
  private WebElement addCriteriaAppvisibilityBackgroundSelection;
  @FindBy(how = How.CLASS_NAME, using = "m-dropdown-menu-option-activeNetwork")
  private WebElement addCriteriaActiveAppvisibilityforegroundSelection;
  @FindBy(how = How.CSS, using = ".m-btn-add-filter")
  private WebElement addFilterBtn;
  @FindBy(how = How.ID, using = "btn-filter-add-criteria")
  private WebElement addCriteriaFilterBtn;
  @FindBy(how = How.ID, using = "appId-selection")
  private WebElement appIdSelection;
  @FindBy(how = How.ID, using = "application")
  private WebElement application;
  @FindBy(how = How.CSS, using = "service-component-item .m-btn-add-policyEvent")
  private WebElement addPolicyEventBtn;
  @FindBy(how = How.ID, using = "app-visibility-selection")
  private WebElement appVisibilitySelection;
  @FindBy(how = How.ID, using = "applicationVisibilityAllowSelect")
  private WebElement applicationVisibilityAllowSelect;
  @FindBy(how = How.ID, using = "add-policy-new-selection")
  private WebElement addPolicyNewSelection;
  @FindBy(how = How.CSS, using = ".m-select-actionClass")
  public WebElement actionClassSelect;
  @FindBy(how = How.CSS, using = "service-component-item .m-btn-add-policyEvent")
  private WebElement dataPolicyAddComponentAddPolicyEventBtn;
  @FindBy(how = How.CLASS_NAME, using = "m-dropdown-menu-option-new-policyEvent")
  private WebElement dataPolicyAddComponentAddPolicyNewSelection;
  @FindBy(how = How.CLASS_NAME, using = "priority-widget")
  private WebElement dataPolicyAddComponentAddPolicyPriorityWidget;
  @FindBy(how = How.ID, using = "stopTestPolicyBtn")
  private WebElement stopTestPolicyBtn;
  @FindBy(how = How.ID, using = "unlock-btn")
  private WebElement unlockFeatures;
  @FindBy(how = How.ID, using = "filter_lock-btn")
  private WebElement unlockFilters;
  @FindBy(how = How.ID, using = "libraryOkBtn-btn")
  private WebElement libaryOkBtn;
  @FindBy(how = How.XPATH, using = "//div[3]/button[2]")
  private WebElement libaryOkBtnXpath;
  @FindBy(how = How.CSS, using = "#row_0")
  private WebElement libaryFirstRow;
  @FindBy(how = How.ID, using = "publish-btn")
  private WebElement publishBtn;
  @FindBy(how = How.NAME, using = "NetworkGroup_notin_btn")
  private WebElement policyEventNetworkGroupNotInBtn;
  @FindBy(how = How.NAME, using = "NetworkGroup_in_btn")
  private WebElement policyEventNetworkGroupInBtn;
  @FindBy(how = How.LINK_TEXT, using = "New")
  private WebElement policyEventNetworkGroupNewSelection;
  @FindBy(how = How.CSS, using = "a[id*='btn_libraryNetworkGroup']")
  private WebElement policyEventNetworkGroupLibarySelection;

  @FindBy(how = How.CSS, using = ".entity-title")
  private WebElement servicePolicyTitleName;
  @FindBy(how = How.CSS, using = "input.entity-title")
  private WebElement servicePolicyTitleNameTextField;

  public FeaturePages setLibaryFirstRow() {
    browser.logAction("Selecting 1st row");
    browser.retryingElement(libaryFirstRow);
    return this;
  }

  public FeaturePages setUnlockFeatures() {
    browser.logAction("Clicking Unlock in Features");
    browser.click(unlockFeatures);

    return this;
  }

  public FeaturePages setUnlockFilters() {
    browser.logAction("Clicking Unlock in Filters");
    browser.click(unlockFilters);
    return this;
  }

  public FeaturePages setNewFeatureName(String name) {
    browser.logAction("Inputing Feature Name: " + name);
    savedGroupName = name;
    newFeatureNameField.clear();
    newFeatureNameField.sendKeys(name);
    savedGroupName = name;
    return this;
  }

  public FeaturePages setNewFeatureCode(String name) {
    browser.logAction("Inputing FeatureCode: " + name);
    newFeatureCodeField.clear();
    newFeatureCodeField.sendKeys(name);

    return this;
  }

  public FeaturePages setNewFeatureDescription(String name) {
    browser.logAction("Inputing name: " + name);
    newFeatureDescriptionField.clear();
    newFeatureDescriptionField.sendKeys(name);

    return this;
  }

  public FeaturePages setNewFeatureBtn(String type) {
    browser.logAction("Click New Feature Btn");
    browser.click(createNewFeatureBtn);

    browser.waitForVisibilityOfElement(By.xpath("//div/div[2]/div/div/div/ul/li/a"));
    if (type.toLowerCase().equals("publishable")) {
      browser.logAction("Click create New Feature First Selection publishable");
      browser.click(newPublishableFeatureSelection);
    }
    else {
      browser.logAction("Click create New Feature First Selection Purchasable");
      browser.click(newPurchasableFeatureSelection);
    }

    browser.invisibilityOfElementLocated(By.xpath("//div/div[2]/div/div/div/ul/li/a"));
    return this;
  }

  public FeaturePages setAddTestGroupBtn() {
    try {
      browser.click(addTestGroupBtn);
    }
    catch (Exception ignored) { //needed for chrome
      WebElement element = addTestGroupBtn;
      browser.logAction("Using JavascriptExecutor to click Add test Button");
      browser.executeScript("arguments[0].click();", element);
    }
    return this;
  }

  public FeaturePages setLibaryOkBtn() {
    browser.logAction("Clicking Libary save button");
    try {
      browser.click(libaryOkBtnXpath);
    }
    catch (Exception ignored) { //needed for chrome libary btn is cut off JIRA SAAS-4426 filed in August 
      browser.logAction("Clicking Save btn by javascript");
      WebElement element = libaryOkBtnXpath;
      browser.executeScript("arguments[0].click();", element);
    }

    return this;
  }

  public FeaturePages setSearchDeleteConfirmBtn() {
    try {
      browser.click(searchDeleteConfirmBtn);
    }
    catch (Exception ignored) { //for chrome
      browser.waitForClickableElement(By.xpath("//div[3]/button[2]")).click();
    }
    return this;
  }

  public FeaturePages setAddTestGroupBtnSelectionFromLibary() {
    browser.logAction("Clicking AddTestGroupBtnSelectionFromLibary");
    browser.click(addTestGroupBtnSelectionFromLibary);
    return this;
  }

  public FeaturePages setNewFeatureCancelbtn() {
    browser.logAction("Clicking Cancel Button ");
    browser.click(newFeatureCancelBtn);
    return this;
  }

  public FeaturePages setNewFeatureSavebtn() {
    browser.logAction("Clicking Save Button ");
    browser.click(newFeatureSaveBtn);
    return this;
  }

  public FeaturePages setNameField(String Text) {
    browser.logAction("Entering Text in Name  field : " + Text);
    nameField.sendKeys(Text);
    return this;
  }

  public FeaturePages setAddTestGroupLibarySavebtn() {
    browser.logAction("Clicking Save Button in add test Group libary");
    browser.click(addTestGroupLibarySavebtn);

    return this;
  }

  public FeaturePages setStopTestPolicyBtn() {

    browser.logAction("Clicking Stop Testing Button");
    browser.click(stopTestPolicyBtn);
    // browser.invisibilityOfElementLocated("stopTestPolicyBtn", "id");
    browser.logAction("Clicking Confirm Button");
    browser.click(confirmOkBtn);
    //browser.invisibilityOfElementLocated("confirmOkBtn", "id");
    browser.sleep(2800); //needed stale  element for filter
    return this;
  }

  public FeaturePages verifyFeatureAdded(String groupName) {
    setTopNavLink("features").setSearchfield(groupName);
    browser.sleep(9000);
    String getgroup = tableRow.getText();
    browser.compareText(getgroup, groupName);

    return this;
  }

  public FeaturePages addpolicy(String type) {
    browser.logAction("*1 Clicking Add Policy button");
    browser.sleep(1000);
    browser.click(addPolicyBtn);
    type = type.toLowerCase();
    policyType = type;

    switch (type) {
      case "data":
        browser.waitForClickableElement(By.id("add-new-data-selection")).click();
        break;
      case "mip":
        browser.waitForClickableElement(By.id("add-new-data-session-selection")).click();
        break;
      case "voice":
        browser.waitForClickableElement(By.id("add-new-voice-selection")).click();
        break;
      case "message":
        browser.waitForClickableElement(By.id("add-new-message-selection")).click();
        break;
      case "wifi":
        browser.waitForClickableElement(By.id("add-new-data-wifi_auto_on-selection")).click();
        break;
    }
    browser.logAction("Selected :" + type);
    browser.sleep(1000);// neded or error Element is no longer attached to the DOM

    getFeatureId();
    return this;
  }

  public void getFeatureId() {
    //get  feature id fromurl
    featureId = browser.getUrl();
    String[] getId = featureId.split("=");
    getId = getId[2].split("&");
    featureId = getId[0];
    browser.logAction("got FeatureId: " + featureId);
  }

  public String returnFeatureId() {
    return featureId;
  }

  public FeaturePages dataSessionSettingsTriggerDropDownMenu(String select) {
    Select select1 = new Select(browser.findElement(By.className("m-select-mip-trigger")));
    //can choice unlock , Internet Application , Screen On
    select1.selectByValue(select);
    return this;
  }

  public FeaturePages dataSessionDefaultPolicyEventActionDropDownMenu(String select) {
    Select select1 = new Select(browser.findElement(By.cssSelector(".m-default-policy-action")));
    //Allow or one
    select1.selectByVisibleText(select);

    return this;
  }

  public FeaturePages dataSessionDefaultPolicyEventAddCriteria(String type) {

    if (type.equals("none")) {
      browser.logAction("No Criteria Added");

    }
    else {

      browser.logAction("* Adding Criteria: " + type);
      browser.waitForClickableElement(By.className("m-btn-mip-defaultPolicyAddCriteria")).click();
      //browser.findElement(By.cssSelector("div.status-item:nth-child(3) > div:nth-child(1) > button:nth-child(1)")).click();

      switch (type.toLowerCase()) {
        case "downloading":
          browser.waitForClickableElement(By.linkText("Downloading")).click();
          break;
        case "streamingmedia":
          browser.waitForClickableElement(By.partialLinkText("Streaming Media")).click();
          break;
        case "screenon":
          browser.waitForClickableElement(By.linkText("Screen On")).click();
          break;
        case "screenonapp":
          browser.waitForClickableElement(By.linkText("Screen On & Internet App in Foreground")).click();
          break;
      }
    }
    return this;
  }

  public FeaturePages setAddPoilcyEvent(String type) {
    browser.logAction("++Clicking addPoilcyEvent btn/menu");
    browser.click(addPolicyEvent);
    switch (type.toLowerCase()) {
      case "new":
        Actions actions = new Actions(browser.getDriver());
        WebElement menuHoverLink = browser.findElement(By.linkText("New"));
        actions.moveToElement(menuHoverLink);
        // driver.findElement(By.linkText("New")).
        browser.waitForClickableElement(By.xpath("(//a[contains(text(),'New')])[2]")).click();
        break;
      case "libary":
        browser.waitForClickableElement(By.linkText("From Libary")).click();
        break;
    }

    return this;
  }

  public FeaturePages dataPolicyAddPolicyEvent(String groupName) {

    browser.logAction("*1 Clicking on Add Policy Event dataPolicyAddComponentAddPolicyEventBtn ");
    browser.click(dataPolicyAddComponentAddPolicyEventBtn);

    browser.logAction("Clicking on New in Add Policy Event");
    browser.click(dataPolicyAddComponentAddPolicyNewSelection);
    return this;
  }

  public FeaturePages dataSessionDefaultPolicyEventBlockNetworkGroupsIN(String networkinout, String networkName) {

    browser.executeScript("window.scrollBy(0,800)", "");
    networkinout = networkinout.toLowerCase();
    browser.logAction("Parameter Given: " + networkinout);

    WebElement networkbtn = null;
    browser.sleep(1500); //many time does not click in/out without this sleep
    if (networkinout.equals("none")) {
      browser.logAction("No Network Group Added");
      return this;

    }
    if (networkinout.contains("in_")) {
      browser.logAction("Clicking In Network Group");
      browser.waitForClickableElement(policyEventNetworkGroupInBtn);
      networkbtn = policyEventNetworkGroupInBtn;
    }
    else {
      //out
      browser.logAction("Clicking NotIn");
      browser.waitForClickableElement(policyEventNetworkGroupNotInBtn);
      networkbtn = policyEventNetworkGroupNotInBtn;
    }

    browser.executeScript("window.scrollBy(0,800)", "");

    if (networkinout.contains("new")) {
      networkGroupType = "new";
      browser.logAction("Clicking New **networkinout");
      browser.actionsSelection(networkbtn, policyEventNetworkGroupNewSelection);
      setGroupInputName(networkName);
      browser.findElement(By.xpath("//div[@id='inputCdmaSid_group']/div/tags-input/div/div/input")).sendKeys("1 ");
      networkCdmaNumber = "1";
      setAddOkBtn();
      browser.invisibilityOfElementLocated(By.xpath("//div[@id='inputCdmaSid_group']/div/tags-input/div/div/input"));
    }
    else {
      networkGroupType = "libary";
      browser.logAction("Clicking Libary **networkinout");
      browser.actionsSelection(networkbtn, policyEventNetworkGroupLibarySelection);
      //select first from libary
      setLibaryFirstRow();
      //Click ok button
      setLibaryOkBtn();
    }

    //check group added
    if (!networkinout.equals("none")) {
      if (policyType.equals("mip")) {
        verifyNetworkFilterAdded("mip");
      }
      else {
        verifyNetworkFilterAdded("network");
      }
      browser.sleep(500);
    }
    return this;
  }

  public FeaturePages addNewComponent(String type, String name) {
    browser.logAction("adding New Component :" + type);
    browser.sleep(2500); //org.openqa.selenium.TimeoutException
    browser.click(addNewComponentBtn);
    browser.waitForVisibilityOfElement(By.linkText("New"));

    switch (type) {
      case "new":
        browser.findElement(By.linkText("New")).click();
        setNameField(name);
        browser.click(addNewComponenSaveBtn);
        break;
      case "libary":
        browser.waitForClickableElement(By.linkText("From Libary")).click();
        break;
    }

    return this;
  }

  public FeaturePages dataPolicyDefaultPolicyEventAddCriteria(String type) {

    if (type.toLowerCase().equals("none")) {
      browser.logAction("No Criteria Added");

      return this;
    }
    /*
     browser.sleep(500);
     */
    browser.logAction("* Adding Criteria: " + type);
    browser.logAction("Scrolling to add criteria ");
    browser.executeScript("window.scrollBy(0,350)", "");
    browser.waitForVisibilityOfElement(By.className("m-btn-add-criteria"));
    browser.sleep(500);
    browser.logAction("Selecting  add criteria:  " + type);

    switch (type.toLowerCase()) {
      case "appvisibilitybackground":
        browser.logAction("Selecting  appvisibilitybackground from  add criteria ");
        browser.executeScript("window.scrollBy(0,550)", "");
        browser.waitForClickableElement(addCriteriaBtn);
        browser.actionsSelection(addCriteriaBtn, addCriteriaAppvisibilityBackgroundSelection);
        browser.waitForVisibilityOfElement(By.className("m-btn-remove-activeNetwork"));
        browser.executeScript("window.scrollBy(0,550)", "");
        browser.logAction("Selecting  Background from appvisibilityforeground from add dropdown ");
        //browser.waitForClickableElement(By.className("m-select-applicationVisibilityAllow"));
        /// browser.dropDownSelectorBy("m-select-applicationVisibilityAllow", "Background", "ClassName");
        break;
      case "appvisibilityforeground":
        browser.waitForClickableElement(addCriteriaBtn);
        browser.logAction("Selecting  appvisibilitybackground from  add criteria ");
        browser.actionsSelection(addCriteriaBtn, addCriteriaAppvisibilityBackgroundSelection);
        browser.waitForVisibilityOfElement(By.className("m-btn-remove-activeNetwork"));
        browser.executeScript("window.scrollBy(0,550)", "");
        browser.executeScript("window.scrollBy(0,550)", "");
        browser.waitForClickableElement(By.cssSelector(".m-btn-removeApplicationVisibility"));
        browser.logAction("Selecting  Background from appvisibilityforeground from add dropdown ");
        browser.waitForDropDownValue(". m-select-applicationVisibilityAllow > option[value='Background']");

        browser.waitForClickableElement(By.className("m-select-applicationVisibilityAllow")).click();
        browser.logAction("Selecting  *****1 ");

        // browser.actionsSelection(dropDown, dropDownSelection);
        browser.dropDownSelectorBy("m-select-applicationVisibilityAllow", "Foreground or Streaming", "ClassName");
        break;
      case "time":    /*                    //select time option
       browser.waitForVisibilityOfElement(By.className("m-dropdown-menu-option-timeOfDay"));
       browser.findElement(By.className("m-dropdown-menu-option-timeOfDay")).click();
       ExpectedConditions.invisibilityOfElementLocated(By.className("m-dropdown-menu-option-timeOfDay"));
       browser.executeScript("window.scrollBy(0,480)", "");
       browser.findElement(By.className("m-select-cronTimeStartHours")).click();
       Select dropdown = new Select(browser.findElement(By.className("m-select-cronTimeStartHours")));
       dropdown.selectByVisibleText("3");

        
       //browser.findElement(By.className("m-select-cronTimeStartHours")).click();
       //ebElement element1 = browser.findElement(By.className("m-select-cronTimeStartHours")); 
       //WebElement element2 = browser.findElement(By.linkText("3"));
        
       //dropdown start
       //  browser.dropDownSelectorBy("m-select-cronTimeStartHours", "3", "ClassName");
       browser.dropDownSelectorBy("m-select-cronTimeStartMinutes", "8", "ClassName");
       browser.dropDownSelectorBy("m-select-cronTimeStartWeekdays", "Friday", "ClassName");
       //dropdown start
       browser.dropDownSelectorBy("m-select-cronTimeStopHours", "9", "ClassName");
       browser.dropDownSelectorBy("m-select-cronTimeStopMinutes", "13", "ClassName");
       browser.dropDownSelectorBy("m-select-cronTimeStopWeekdays", "Sunday", "ClassName");
       */

    }
    browser.logAction("After add criteria: " + type);
    return this;
  }

  public FeaturePages dataPolicyAddFilter(String groupName, String type) {
    dataFilterType = type;
    if (type.toLowerCase().equals("none") || type.toLowerCase().equals("no")) {
      browser.logAction("No Criteria Added");

      return this;
    }

    browser.logAction("Clicking on Add Filter btn");
    browser.click(addFilterBtn);
    browser.logAction("Clicking and Selecting New");

    browser.waitForClickableElement(By.linkText("New")).click();
    browser.invisibilityOfElementLocated(By.linkText("New"));

    setNameField(groupName);
    browser.click(addCriteriaFilterBtn);

    browser.logAction("Clicking " + type);
    switch (type.toLowerCase()) {
      //data
      case "app":
        browser.click(appIdSelection);
        browser.logAction("Inputing com.itsonqa.com");
        application.sendKeys("com.itsonqa.com");
        setAddOkBtn();
        //press continue button
        browser.logAction("Waiting for No Certificate Found for This App ID");
        browser.sleep(500);
        browser.waitForClickableElement(By.cssSelector("h4.ng-binding"));
        browser.waitForTextToAppear(browser.findElement(By.cssSelector("h4.ng-binding")), "No Certificate Found for This App ID", 45);
        browser.click(confirmOkBtn);
        break;
      case "domain":
        browser.waitForClickableElement(By.id("domain-selection")).click();
        browser.findElement(By.id("remoteHost")).sendKeys("10.10.33.202");
        setAddOkBtn();
        break;
      case "ipaddress":
        browser.waitForClickableElement(By.id("ipAddress-selection")).click();
        browser.findElement(By.id("ipAddress")).sendKeys("10.10.33.202");
        setAddOkBtn();
        break;
      case "protocol":
        browser.waitForClickableElement(By.id("protocol-selection")).click();
        WebElement dropDownListBox12 = browser.findElement(By.id("layer4Protocol"));
        Select clickThis12 = new Select(dropDownListBox12);
        clickThis12.selectByVisibleText("UDP");
        setAddOkBtn();
        break;
      case "port":
        browser.waitForClickableElement(By.id("port-selection")).click();
        browser.findElement(By.id("remotePort")).sendKeys("101");
        setAddOkBtn();
        break;
      case "remotephone":
        browser.waitForClickableElement(By.id("domain-selection")).click();
        browser.findElement(By.id("directoryNumberRegex")).sendKeys("658213567");
        setAddOkBtn();
        break;
      case "direction":
        browser.waitForClickableElement(By.id("ipAddress-selection")).click();
        WebElement dropDownListBox2 = browser.findElement(By.id("direction"));
        Select clickThis2 = new Select(dropDownListBox2);
        clickThis2.selectByVisibleText("Inbound (MT)");
        setAddOkBtn();
        break;
      case "sms":
        browser.waitForClickableElement(By.partialLinkText("SMS")).click();
        browser.findElement(By.id("messageBodyRegex")).sendKeys("test");
        setAddOkBtn();
        break;
      case "behavior":
        browser.waitForClickableElement(By.id("behavior-policy")).click();
        browser.click(By.id("btn-add-policy-dropdown"));
        browser.sleep(1000);
        if (myFavCreationType.equals("new")) {

          browser.click(By.cssSelector("a.option-new_behavior_policy.ng-binding"));
          createMyFavPolicy();
          browser.waitForClickableElement(By.cssSelector(".badge"));
          WebElement badge = browser.findElement(By.cssSelector(".badge"));
          browser.waitForTextToAppear(badge, myFavName, 30);
        }
        if (myFavCreationType.equals("libary")) {
          browser.click(By.cssSelector("a.option-from_library_behavior_policy.ng-binding"));
          browser.waitForClickableElement(By.xpath("//div[@id='library-table_filter']/label/input"));
          browser.findElement(By.xpath("//div[@id='library-table_filter']/label/input")).sendKeys(myFavName);
          browser.sleep(3000);
          String getrowOneName = browser.findElement(By.cssSelector("tr.odd > td:nth-child(2)")).getText();
          browser.click(By.id("row_0"));
          browser.sleep(500);
          browser.click(By.id("libraryOkBtn"));
        }
        setAddOkBtn();
        break;
      default:
        browser.hardExit("dataPolicyAddFilter: Bad switch option", -1);
        setAddOkBtn();
        break;

    }

    verifyNetworkFilterAdded("filter");
    return this;
  }

  public FeaturePages verifyAddedFilter(String type) {
    String getText;
    browser.sleep(1000); // needed ie
    browser.logAction("Verify on Added Filter ");
    //click added filter
    browser.waitForClickableElement(By.xpath("//service-component-filters/div/div[3]")).click();
    //click unlock
    setUnlockFilters();

    browser.logAction("Verifying " + type);
    switch (type.toLowerCase()) {
      //data
      case "app":
        getText = application.getAttribute("value");
        browser.compareText(getText, "com.itsonqa.com");
        break;
      case "domain":
        getText = browser.findElement(By.id("remoteHost")).getAttribute("value");
        browser.compareText(getText, "10.10.33.202");
        break;
      case "ipaddress":
        getText = browser.findElement(By.id("ipAddress")).getAttribute("value");
        browser.compareText(getText, "10.10.33.202");
        break;
      case "protocol":
        browser.waitForClickableElement(By.id("layer4Protocol")).click();
        WebElement dropDownListBox12 = browser.findElement(By.id("layer4Protocol"));
        Select selected = new Select(dropDownListBox12);
        getText = selected.getFirstSelectedOption().getText();
        browser.compareText(getText, "UDP");
        break;
      case "port":
        getText = browser.findElement(By.id("remotePort")).getAttribute("value");
        browser.compareText(getText, "101");
        break;
      case "remotephone":
        getText = browser.findElement(By.id("directoryNumberRegex")).getAttribute("value");
        browser.compareText(getText, "658213567");
        break;
      case "direction":
        browser.waitForClickableElement(By.id("direction")).click();
        WebElement dropDownListBox2 = browser.findElement(By.id("direction"));
        Select selected1 = new Select(dropDownListBox2);
        getText = selected1.getFirstSelectedOption().getText();
        browser.compareText(getText, "Inbound (MT)");
        break;
      case "sms":
        getText = browser.findElement(By.id("messageBodyRegex")).getAttribute("value");
        browser.compareText(getText, "test");
        break;
      case "behavior":
        browser.logAction("checking myfav filter");
        //verifyMyFavPolicy();
        break;
      default:
        browser.hardExit("dataPolicyAddFilter: bad case option", -1);
        break;

    }
    //click cancel
    browser.click(newFeatureCancelBtn);
    return this;
  }

  public FeaturePages checkDraftMode() {
    //Verify you are in the draft mode
    browser.logAction("Checking if in Draft Mode");
    String id = "label_featureState";
    browser.textToBePresentInElementValue(By.id(id), "DRAFT");
    String mipTable2 = browser.findElementById(id).getText();
    browser.log(Level.INFO, "mipTable2 == " + mipTable2);
    browser.compareText(mipTable2, "DRAFT");
    return this;
  }

  public FeaturePages addTestGroup() {
    browser.executeScript("window.scrollBy(0,800)", "");
    browser.logAction("Clicking Addtest Button");
    browser.click(addTestGroupBtn);
    /* I removed the ignored exceptions because we should never ignore exceptions.
     browser.findElement(By.cssSelector("#add-test-group-btn")).click();
     browser.findElement(By.xpath("//feature-subscribers/div/div[2]/div/button")).click();
     */
    return this;
  }

  public FeaturePages actionallowblock(String action) {
    browser.logAction("Selecting Action " + action);
    browser.waitForClickableElement(actionClassSelect);
    browser.waitForDropDownValue(".m-select-actionClass > option[value='Allow']");

    WebElement dropDownListBox2 = actionClassSelect;
    browser.logAction("Selecting actionallowblock  " + action);
    Select clickThis2 = new Select(dropDownListBox2);
    clickThis2.selectByVisibleText(action);
    return this;
  }

  public FeaturePages wifiNotConnectIn(String time) {
    browser.logAction("Setting Turn Off for : " + time);

    WebElement dropDownListBox = browser.findElement(By.cssSelector("select.ng-valid[data-id='wifiNotConnectIn']"));
    browser.waitForClickableElement(By.cssSelector("select.ng-valid[data-id='wifiNotConnectIn']")).click();
    Select clickThis = new Select(dropDownListBox);
    clickThis.selectByVisibleText(time);
    Keyboard kb = browser.getKeyboard();
    kb.pressKey(Keys.RETURN);

    return this;
  }

  public FeaturePages retryLater(String time) {
    browser.logAction("Setting retryLater for : " + time);
    WebElement dropDownListBox2 = browser.findElement(By.cssSelector("select.ng-valid[data-id='retryLater']"));
    browser.waitForClickableElement(By.cssSelector("select.ng-valid[data-id='retryLater']")).click();
    Select clickThis2 = new Select(dropDownListBox2);
    clickThis2.selectByVisibleText(time);
    Keyboard kb = browser.getKeyboard();
    kb.pressKey(Keys.RETURN);

    return this;
  }

  public FeaturePages verifyWifiNotConnectIn(String time) {
    //Browser browser = new BrowserBuilder().setDriver(driver).createBrowser();
    browser.logAction("Checking DropDown");
    browser.waitForClickableElement(By.cssSelector("select.ng-valid[data-id='wifiNotConnectIn']")).click();
    WebElement dropDownCheck = browser.findElement(By.cssSelector("select.ng-valid[data-id='wifiNotConnectIn']"));
    Select selected = new Select(dropDownCheck);
    String getText = selected.getFirstSelectedOption().getText();
    browser.compareText(getText, time);

    return this;
  }

  public FeaturePages verifyretryLater(String time) {
    browser.logAction("Checking DropDown");
    browser.waitForClickableElement(By.cssSelector("select.ng-valid[data-id='retryLater']")).click();
    WebElement dropDownListBox2 = browser.findElement(By.cssSelector("select.ng-valid[data-id='retryLater']"));
    Select selected = new Select(dropDownListBox2);
    String getText = selected.getFirstSelectedOption().getText();
    browser.compareText(getText, time);

    return this;
  }

  public FeaturePages verifyNetworkFilterAdded(String type) {

    browser.logAction("**verifyNetworkFilterAdded  Checking " + type + " Added");
    browser.sleep(1500); //org.openqa.selenium.StaleElementReferenceException
    String input = type.toLowerCase();
    switch (input) {
      case "network":
        type = ".tag-label";
        if (networkGroupType.equals("new")) {
          browser.waitForClickableElement(By.cssSelector(type)).click();
          verifyNetworkGroup(savedGroupName);
        }
        break;
      case "mip":
        type = ".networkLabel";
        if (networkGroupType.equals("new")) {
          browser.waitForClickableElement(By.cssSelector(type)).click();
          verifyNetworkGroup(savedGroupName);
        }
        break;
      case "networkremove":
        type = ".tag-label";
        browser.logAction("Removing Network Group");
        //remove ntwork group
        browser.waitForVisibilityOfElement(By.cssSelector(type));
        browser.waitForClickableElement(By.cssSelector(".tag-btn > i:nth-child(1)")).click();
        browser.invisibilityOfElementLocated(By.cssSelector(".tag-label"));
        break;
      case "filter":
        type = ".ffb-label";
        browser.retryingWaitElement(By.cssSelector(type));
        //browser.waitForVisibilityOfElement(By.cssSelector(type));
        break;
    }
    browser.logAction("Out switch Removing Network Group");
    return this;
  }

  public FeaturePages verifyActionClassSelect(String action) {
    browser.logAction("Checking DropDown");
    browser.click(actionClassSelect);
    WebElement dropDownCheck = actionClassSelect;
    Select selected = new Select(dropDownCheck);
    String getText = selected.getFirstSelectedOption().getText();
    browser.compareText(getText, action);

    return this;
  }

  public FeaturePages testGroupSubscribers(String type, String negativeTest) {
    String phoneNumber = browser.randomPhoneNumber();

    switch (type.toLowerCase()) {
      case "textfield":
        browser.logAction("Adding Subscribers By text filed");
        browser.findElement(By.cssSelector("div.tags input.tag-input")).sendKeys(phoneNumber + " ");
        browser.sleep(1000);//wait test sub to be added in backend
        browser.waitForClickableElement(By.cssSelector(".tag-item > button:nth-child(2)")); //wait for number added
        break;
      case "new":
        browser.logAction("Adding TEST Subscribers By New");
        setAddTestGroupBtn();
        browser.waitForClickableElement(By.id("btn_testSubNew")).click();
        setGroupInputName("webqe");
        //input number
        browser.findElement(By.id("inputSubscriberGroup")).sendKeys(phoneNumber + " ");

        setAddOkBtn();
        browser.invisibilityOfElementLocated(By.id("addOkBtn"));
        browser.sleep(1500);
        break;
      case "libary":
        browser.logAction("Adding Subscribers By Libary");
        setAddTestGroupBtn();
        browser.logAction("Adding Subscribers By Libary clicking button");
        setAddTestGroupBtnSelectionFromLibary();
        //catch (Exception e) {
        //browser.logAction("Catch Adding Subscribers By Libary");
        //featurePages.setAddTestGroupBtn();
        //browser.findElement(By.xpath("//a[2]")).click();
        //select first libary
        setLibaryFirstRow();

        //click libary ok button
        setLibaryOkBtn();

        break;
    }
    //check group added
    browser.logAction("Checking Network group added");
    // browser.waitForClickableElement(By.cssSelector(".tag-item > span:nth-child(1)")).click();  //networkLabel
    browser.sleep(3500);
    testFeatureBtn.click();
    browser.sleep(1500);
    if (!negativeTest.equals("yes")) {
      //click confirm
      setConfirmOkBtn();
    }
    return this;
  }

  public FeaturePages delete(String groupName, String Page) {//needs to be moved to SdcPages
    browser.logAction("Features CleanUp " + groupName);
    setTopNavLink(Page);
    verifyFeatureAdded(groupName);
    setTableDelete();
    setSearchDeleteConfirmBtn();
    // Safari Exception was ignored browser.logAction("Was Not Able to Delete. Error Given: " + ignored2);
    return null;

  }

  public FeaturePages publishFeature(String publish) {

    if (publish.toLowerCase().contains("yes")) {
      browser.logAction("Clicking Publish Button");
      browser.click(publishBtn);

      //for none deafult option
      if (publish.equals("yes_notdefault")) {

        browser.sleep(3000); //need to wait or site gives error not in testing mode

        browser.waitForClickableElement(By.xpath("//label/div/div/input")).click();
      }
      setConfirmOkBtn();
    }
    return this;
  }

  public FeaturePages verifyPublishFeature(String publish, String testing) {
    String getText = browser.findElement(By.cssSelector(".label")).getText();

    if (publish.toLowerCase().equals("yes")) {
      browser.logAction("Checking Publish " + getText);
      browser.compareText(getText, "PUBLISHED");
    }
    else {

      if (testing.toLowerCase().equals("no")) {
        browser.logAction("Checking DRAFT " + getText);
        browser.compareText(getText, "DRAFT");
      }
      else {

        browser.logAction("Checking Testing " + getText);
        browser.compareText(getText, "TESTING");
      }
    }
    verifyView();
    return this;
  }

  public FeaturePages verifyView() {
    setTableEdit();
    browser.sleep(4000);
    setUnlockFeatures();
    return this;
  }

  public String createFeature() {
    browser.logAction("Creating Feature");
    String groupName = browser.randomName();
    setNewFeatureBtn("publishable").setNewFeatureName(groupName).setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1").setNewFeatureSavebtn();
    addpolicy("data")
            .addNewComponent("new", groupName);
    dataPolicyAddPolicyEvent(groupName);
    dataSessionDefaultPolicyEventBlockNetworkGroupsIN("in_new", groupName);
    browser.logAction("Network done");
    return groupName;
  }

  public FeaturePages addNetworkType(String action) {
    if (action.toLowerCase().equals("none") || action.toLowerCase().equals("")) {
      browser.logAction("No NetworkType Added");

      return this;
    }
    browser.logAction("Scrolling to add NetworkType ");
    browser.executeScript("window.scrollBy(0,550)", "");

    browser.sleep(1500);//needed StaleElementReference Element
    browser.waitForClickableElement(addCriteriaBtn);
    browser.logAction("Clicking addCriteriaBtn");
    browser.sleep(500);//needed StaleElementReference Element
    browser.actionsSelection(addCriteriaBtn, addCriteriaActiveNetworkSelection);

    browser.logAction("Clicking activeNetwork");

    browser.invisibilityOfElementLocated(By.cssSelector(".m-dropdown-menu-option-activeNetwork"));
    browser.waitForVisibilityOfElement(By.className("m-btn-remove-activeNetwork"));

    browser.waitForDropDownValue(".m-select-networkType > option[value='2G']");
    browser.sleep(500);//needed StaleElementReference Element
    browser.logAction("**1 Clicking Dropdown " + action);
    browser.executeScript("window.scrollBy(0,200)", "");
    browser.waitForVisibilityOfElement(By.cssSelector(".m-select-networkType"));

    browser.sleep(500);//needed StaleElementReference Element
    WebElement dropDownListBox2 = browser.findElement(By.cssSelector(".m-select-networkType"));

    Select clickThis2 = new Select(dropDownListBox2);
    dropDownListBox2.click();
    browser.logAction("**2 Clicking Dropdown " + action);
    browser.sleep(2500); //needed for realiable dropdown selection 
    clickThis2.selectByVisibleText(action);
    browser.actionsEnter();

    return this;
  }

  public FeaturePages verifyAddNetworkType(String action) {
    browser.logAction("Checking DropDown");
    WebElement dropDownCheck = browser.findElement(By.className("m-select-networkType"));
    dropDownCheck.click();
    Select selected = new Select(dropDownCheck);
    String getText = selected.getFirstSelectedOption().getText();
    browser.compareText(getText, action);
    return this;
  }

  public FeaturePages verfiyAddCriteria(String type) {
    if (type.toLowerCase().equals("none")) {
      browser.logAction("No Criteria Added");
      return this;
    }
    browser.logAction("* Verifying Criteria: " + type);
    browser.logAction("Scrolling to verfiy criteria ");
    browser.executeScript("window.scrollBy(0,370)", "");

    browser.logAction("Verfiy  add criteria:  " + type);

    switch (type.toLowerCase()) {
      case "networktypeapplication":
        browser.waitForClickableElement(By.className("m-dropdown-menu-option-activeNetwork")).click();
        break;
      case "appvisibilitybackground":
        browser.verifyDropDownSelectorBy("m-select-applicationVisibilityAllow", "Background", "ClassName");
        break;
      case "appvisibilityforeground":
        browser.verifyDropDownSelectorBy("m-select-applicationVisibilityAllow", "Foreground or Streaming", "ClassName");
        break;
      //start
      case "time":
        /*
         browser.verifyDropDownSelectorBy("m-select-cronTimeStartHours", "3", "ClassName"); //will add back after bug is fixed
         browser.verifyDropDownSelectorBy("m-select-cronTimeStartMinutes", "8", "ClassName");
         browser.verifyDropDownSelectorBy("m-select-cronTimeStartWeekdays", "Friday", "ClassName");
         //stop
         browser.verifyDropDownSelectorBy("m-select-cronTimeStopHours", "9", "ClassName");
         browser.verifyDropDownSelectorBy("m-select-cronTimeStopMinutes", "13", "ClassName");
         browser.verifyDropDownSelectorBy("m-select-cronTimeStopWeekdays", "Sunday", "ClassName");*/
        break;
    }
    return this;
  }

  public FeaturePages featuresPolicy(String featureType, String addpolicy, String groupName, String addFilter, String addNetworkType, String addCriteria, String action, String networkinout, String testGroup, String publish, String negativeTest, String addNewComponent, String dataPolicyAddPolicyEvent, String verfify) {
    savedGroupName = groupName;

    String topnav = "features";

    setTopNavLink(topnav);

    //create new feature
    setNewFeatureBtn(featureType).setNewFeatureName(groupName).setNewFeatureCode(groupName)
            .setNewFeatureDescription("Test 1").setNewFeatureSavebtn();
    //select policy
    addpolicy(addpolicy);
    //change policy name
    changeServicePolicyName(groupName);
    //add Component
    if (!addNewComponent.equals("none")) {
      addNewComponent("new", groupName);
    }
    ///Component Data Filter from dataProvider
    dataPolicyAddFilter(groupName, addFilter);
    if (!dataPolicyAddPolicyEvent.equals("none")) {
      //data polcy event

      dataPolicyAddPolicyEvent(groupName);
      actionallowblock(action);

    }
    //add network group
    dataSessionDefaultPolicyEventBlockNetworkGroupsIN(networkinout, groupName);
    //add network type
    if (addpolicy.toLowerCase().equals("data")) {
      addNetworkType(addNetworkType);

    }
    dataPolicyDefaultPolicyEventAddCriteria(addCriteria);
    // test button
    if (!testGroup.toLowerCase().equals("no")) {
      testGroupSubscribers(testGroup, negativeTest);
    }
    if (negativeTest.equals("yes")) {
      browser.logAction("Neagtive Test Case Checking for error");
      browser.waitForVisibilityOfElement(By.cssSelector(".message"));
      return this;
    }
    else {
      //publish
      publishFeature(publish);

      if (verfify.toLowerCase().equals("yes")) {
        //verfify
        setTopNavLink(topnav).verifyDataGroupsAdded(groupName, featureType);
        verifyPublishFeature(publish, testGroup);
        if (!publish.toLowerCase().equals("yes")) {
          //stop testing
          setStopTestPolicyBtn();
        }
        if (!networkinout.equals("none")) {
          verifyNetworkFilterAdded("networkRemove");

        }

        verifyAddedFilter(addFilter);

        // check action dropdown
        verifyActionClassSelect(action);
        if (addpolicy.toLowerCase().equals("data")) {
          verifyAddNetworkType(addNetworkType);
        }
        verfiyAddCriteria(addCriteria);
      }
    }
    return this;
  }

  public FeaturePages featureCleanUp(String groupName, String savedNetworkinout) {
    RestCalls restCalls = new RestCalls();
    restCalls.deleteFeature(featureId);
//    browser.logAction("Disabling :"+groupName);
//    setTopNavLink("features").setSearchfield(groupName);
//    browser.sleep(12500);
//    String getText = browser.findElement(By.cssSelector(".label")).getText();
//    browser.sleep(2000);
//    verifyView();
//    browser.sleep(2000);
//    browser.waitForClickableElement(By.id("label_featureName"));
//    String checkName = browser.findElement(By.id("label_featureName")).getText();
//    browser.sleep(2000);
//    if (!getText.toLowerCase().equals("testing") && !getText.toLowerCase().equals("published")) {//stop testing mode
//      testFeatureBtn.click();
//      browser.sleep(1500);
//      //click confirm
//      setConfirmOkBtn();
//    }
//    if (checkName.contains(groupName)) {
//      restCalls.unPublishFeature(featureId);
//    }
    if (savedNetworkinout.toLowerCase().contains("_new")) {
      tryNetworkDelete(groupName, "network");
    }
    return this;
  }

  public String getPoilcySwitchAttribute(String groupName, String onOff) {
    int rowCount = 0;
    String rowName;
    String span;
    String gotAttribute = null;
    //set span on or off
    if (onOff.toLowerCase().equals("on")) {
      span = "span";
    }
    else {
      span = "span[2]";
    }

    while (rowCount < 10) {

      rowName = browser.findElement(By.id("row_" + rowCount)).getText();
      browser.logAction("Got This " + rowName);
      if (rowName.equals(groupName)) {
        browser.logAction("Name Matched");
        if (rowCount == 0) {
          browser.logAction("1st row ");
          gotAttribute = browser.findElement(By.xpath("//ng-button-switch/div/div/" + span)).getAttribute("outerHTML");

        }
        else {
          int addOne = rowCount + 1;
          gotAttribute = browser.findElement(By.xpath("//tr[" + rowCount + "]/td[3]/ng-button-switch/div/div/" + span)).getAttribute("outerHTML");

        }

        rowCount = 10;
        break;
      }
      else {
        browser.logAction("Name Not Matched");
        rowCount++;
      }
    }
    return gotAttribute;
  }

  public FeaturePages nameChange(String featureType, String groupName, String newName, String publish, String testGroup) {
    String topnav = "features";
    //find feature
    setTopNavLink(topnav).verifyDataGroupsAdded(groupName, featureType);
    verifyPublishFeature(publish, testGroup);
    //CHANGE policy name
    changeServicePolicyName(newName);
    //change component name
    browser.waitForClickableElement(By.xpath("//service-component-item/component/div/div/h5/span[2]")).click();
    browser.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
    browser.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(newName);
    //change filter name
    browser.waitForClickableElement(By.cssSelector(".filterLabel")).click();
    setUnlockFilters();
    browser.findElementById("name").clear();
    browser.findElementById("name").sendKeys(newName);
    setAddOkBtn();
    if (dataFilterType.equals("app")) {
      browser.sleep(500);
      browser.click(By.id("confirmOkBtn"));
    }

    //find feature
    setTopNavLink(topnav).verifyDataGroupsAdded(groupName, featureType);
    verifyPublishFeature(publish, testGroup);
    if (!publish.toLowerCase().equals("yes") && testGroup.toLowerCase().equals("yes")) {
      //stop testing
      setStopTestPolicyBtn();
    }
    //check name
    String gotText = browser.findElementByCssSelector(".entity-title").getText();
    browser.logAction("Got Policy Name " + gotText);
    browser.compareText(gotText, "nameChanged");
    gotText = browser.findElementByCssSelector(".filterLabel").getText();
    browser.logAction("Got Filter Name " + gotText);
    browser.compareText(gotText, "nameChanged");
    gotText = browser.findElementByXPath("//service-component-item/component/div/div/h5/span[2]").getText();
    browser.logAction("Got component Name " + gotText);
    browser.compareText(gotText, "nameChanged");
    return this;
  }

  public FeaturePages changeServicePolicyName(String name) {
    browser.click(servicePolicyTitleName);
    browser.waitForClickableElement(servicePolicyTitleNameTextField);
    servicePolicyTitleNameTextField.clear();
    servicePolicyTitleNameTextField.sendKeys(name);
    return this;
  }

}
