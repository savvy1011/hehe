/**
 * locators and methods that are that covered. ex: login, network
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.SkipException;

import java.util.Collection;
import java.util.logging.Level;

public abstract class SdcPages {
  
  private long start = 0;
  private long end = 0;
  private long duration = 0;
  public Browser browser;
  public String networkCdmaNumber;
  public String networkMccMnc;
  public String subscriberGroupPhoneNumber;
  
  public SdcPages(Browser b) {
    browser = b;
    browser.initElements(this);
  }

  //Log In Page
  @FindBy(how = How.ID, using = "LoginForm_username")
  public WebElement saasUserNameTextField;
  @FindBy(how = How.ID, using = "LoginForm_password")
  private WebElement saasUserPasswordTextField;
  @FindBy(how = How.ID, using = "UserLoginSubmitBtn")
  private WebElement logInBTTN;
  @FindBy(how = How.ID, using = "SaasLoginForm_rememberMe")
  private WebElement remeberMeCheckBox;
  @FindBy(how = How.CSS, using = "#UserLoginForgotPasswordBtn")
  private WebElement forgotPasswordLink;

//TOP LEVEL MENUS
  @FindBy(how = How.ID, using = "navigLinkToPolicy")
  private WebElement policyTopNav;
  @FindBy(how = How.ID, using = "navigLinkToMyFavBehaviorPolicy")
  private WebElement myFavTopNav;
  @FindBy(how = How.ID, using = "navigLinkToCommerce")
  private WebElement commerceTopNav;
  @FindBy(how = How.ID, using = "navigLinkToCSP")
  private WebElement customerSupportTopNav;
  @FindBy(how = How.ID, using = "navigLinkToDevTools")
  private WebElement devToolsTopNav;
  
  @FindBy(how = How.ID, using = "navigLinkToNetworkGroups")
  private WebElement networkGroupsTopNav;
  @FindBy(how = How.ID, using = "navigLinkToSubscriberGroups")
  private WebElement subscriberGroupsTopNav;
  //POLICY SUBMENUS
  @FindBy(how = How.ID, using = "navigLinkToServicePolicies")
  private WebElement serviceTopNav;

  //COMMERCE SUBMENUS
  @FindBy(how = How.ID, using = "navigLinkToOperators")
  private WebElement operatorsTopNav;
  @FindBy(how = How.ID, using = "navigLinkToNotifications")
  private WebElement notificationsTopNav;
  
  @FindBy(how = How.ID, using = "navigLinkToAccounts")
  private WebElement accountsTopNav;

  //CSP SUBMENUS
  @FindBy(how = How.ID, using = "navigLinkToSubscriber")
  private WebElement customerSupportSubscriberSelectionTopNav;
  @FindBy(how = How.XPATH, using = "//section[@id='nav-cog']/article/button")
  private WebElement userTopNav;
  @FindBy(how = How.LINK_TEXT, using = "Roles & Permissions")
  private WebElement rolesPermissionLink;

  //Roles & Permissions
  @FindBy(how = How.ID, using = "navigLinkToFeatures")
  private WebElement featuresTopNav;
  
  @FindBy(how = How.ID, using = "navigLinkToProvisioning")
  private WebElement provisioningTopNav;
  @FindBy(how = How.ID, using = "logoutmenu")
  public WebElement logOutTopMenu;
  @FindBy(how = How.ID, using = "btn_logout")
  private WebElement logOutTopNav;
  @FindBy(how = How.ID, using = "btn_myProfile")
  private WebElement profileTopNav;

//table
  @FindBy(how = How.ID, using = "delete_row_0")
  public WebElement tableDelete;
  @FindBy(how = How.ID, using = "edit_row_0")
  public WebElement tableEdit;
  @FindBy(how = How.ID, using = "row_0")
  public WebElement tableRow;
  @FindBy(how = How.ID, using = "row_10")
  public WebElement tableRow10;
  
  @FindBy(how = How.CSS, using = "input[type=\"text\"]")
  private WebElement searchField;
  //forgot password
  @FindBy(how = How.ID, using = "email")
  private WebElement forgotPasswdEmailField;
  @FindBy(how = How.ID, using = "ForgotPasswdSubmitBtn")
  private WebElement forgotPasswdSubmitBtn;
  @FindBy(how = How.ID, using = "topAlertMessage")
  private WebElement errorMessage;
  //Groups
  @FindBy(how = How.ID, using = "createNewGroupBtn")
  public WebElement createNewGroupBtn;
  //End User License Agreement
  @FindBy(how = How.ID, using = "EulaAcceptCheckbox")
  private WebElement iAgreeEulaAcceptCheckbox;
  @FindBy(how = How.ID, using = "EulaSubmitBtn")
  private WebElement eulaSubmitBtn;
  @FindBy(how = How.ID, using = "btn_returnToLoginScreen")
  private WebElement eulaReturnToLoginLink;
  // Add New Subscriber Group
  @FindBy(how = How.ID, using = "name")
  public WebElement groupInputName;
  @FindBy(how = How.ID, using = "inputDescription")
  public WebElement subscriberGroupInputDescription;
  @FindBy(how = How.ID, using = "selectMatcherParamType")
  public WebElement subscriberGroupSelectMatcherParamType;
  @FindBy(how = How.CSS, using = "input.tag-input")
  public WebElement subscriberGroupMatcherPhoneNumber;
  @FindBy(how = How.ID, using = "inputMccMncValue")
  public WebElement networkGroupMccMncfield;
  @FindBy(how = How.ID, using = "addOkBtn")
  public WebElement addOkBtn;
  @FindBy(how = How.CSS, using = "div.modal-footer > button.btn")
  public WebElement subscriberGroupCancelBtn;
  @FindBy(how = How.CSS, using = ".next > a:nth-child(1)")
  public WebElement subscriberGroupNextBtn;
  @FindBy(how = How.CSS, using = ".prev > a:nth-child(1)")
  public WebElement subscriberGroupPrevBtn;
  @FindBy(how = How.ID, using = "addCancelBtn")
  public WebElement groupCancelBtn;
  @FindBy(how = How.ID, using = "inputIgnoreErrors_1")
  public WebElement inputIgnoreErrors;
  //network
  @FindBy(how = How.CSS, using = ".alert alert-success ng-hide")
  public WebElement networkMessage;
  @FindBy(how = How.ID, using = "confirmCancelBtn")
  public WebElement networkDeleteCancel;
  @FindBy(how = How.ID, using = "newGroupAlertMessageUI-message")
  public WebElement modalMessage;
  @FindBy(how = How.ID, using = "confirmOkBtn")
  public WebElement confirmOkBtn;
  @FindBy(how = How.ID, using = "network_lock-btn")
  private WebElement unlockNetworkGroups;
  @FindBy(how = How.ID, using = "subscriber_lock-btn")
  private WebElement unlockSubscriberGroups;

  //otaTopNav
  @FindBy(how = How.ID, using = "navigLinkToOTA")
  private WebElement otaTopNav;
  @FindBy(how = How.ID, using = "navigLinkToOTAManifest")
  private WebElement manifestsTopNav;
  
  public SdcPages setUnlockNetwork() {
    browser.logAction("Clicking Unlock in Network");
    browser.click(unlockNetworkGroups);
    return this;
  }
  
  public SdcPages setUnlockSubscriber() {
    browser.logAction("Clicking Unlock in Subscriber");
    browser.click(unlockSubscriberGroups);
    
    return this;
  }

  /**
   * Clear a specific cookie
   *
   * @param cookie
   * @return SdcPages
   * @author Aditya Mittal
   */
  public SdcPages clearCookieNamed(String cookie) {
    browser.deleteCookieNamed(cookie);
    return this;
  }

  /**
   * Get the message that shows up inside the SDC Portal Modals
   *
   * @return String modalMessage
   */
  private String getModalMessage() {
    return modalMessage.getText();
  }

  /**
   * Compare a message with the message that shows up inside the SDC Portal
   * Modals
   *
   * @param expected_message - what string should the modal message contain in
   * it?
   * @return SdcPages
   */
  public SdcPages compareModalMessage(String expected_message) {
    String getEntries = this.getModalMessage();
    browser.logAction(getEntries);
    browser.compareText(getEntries, expected_message);
    return this;
  }
  
  public SdcPages setinputIgnoreErrors() {
    browser.click(inputIgnoreErrors);
    return this;
  }
  
  public SdcPages setTopNavLink(String page) {
    browser.refresh();
    browser.waitForPageLoaded();
    page = page.toLowerCase();
    String given = null;
    String title = "SDC SAAS Portal - ";
    browser.logAction("Click Top Nav Link: " + page);
    browser.waitForPageLoaded();
    switch (page) {
      case "network":
        browser.click(policyTopNav);
        browser.click(networkGroupsTopNav);
        given = "Network Groups";
        break;
      case "subscriber":
        browser.click(policyTopNav);
        browser.click(subscriberGroupsTopNav);
        given = "Subscriber Groups";
        break;
      case "myfav":
        browser.click(policyTopNav);
        browser.click(myFavTopNav);
        given = "myfav behavior policy";
        break;
      case "customersupport":
        browser.click(customerSupportTopNav);
        browser.waitForVisibilityOfElement(By.id("navigLinkToAccounts"));
        browser.click(accountsTopNav);
        browser.invisibilityOfElementLocated(By.id("navigLinkToAccounts"));
        given = "Customer Support";
        break;
      case "customersupportprovisioning":
        browser.click(customerSupportTopNav);
        browser.waitForVisibilityOfElement(By.id("navigLinkToProvision"));
        browser.click(accountsTopNav);
        browser.invisibilityOfElementLocated(By.id("navigLinkToProvision"));
        given = "Customer Support";
        break;
      case "users":
        setUser();
        given = "Admin";
        break;
      case "features":
        browser.click(policyTopNav);
        browser.click(featuresTopNav);
        given = "Features";
        break;
      case "provisioning":
        browser.click(policyTopNav);
        browser.click(provisioningTopNav);
        given = "Provisioning";
        break;
      case "manifests":
        browser.click(otaTopNav);
        browser.click(manifestsTopNav);
        given = "OTA";
        break;
      case "operator":
        browser.click(commerceTopNav);
        browser.click(operatorsTopNav);
        given = "SDC SAAS Portal - Operators";
        break;
      case "notification":
        browser.click(commerceTopNav);
        browser.click(notificationsTopNav);
        given = "SDC SAAS Portal - Operators";
        break;
      case "operators":
        browser.click(commerceTopNav);
        browser.click(operatorsTopNav);
        given = "SDC SAAS Portal - Operators";
        break;
      default:
        browser.hardExit("Input for nav link not Found " + page, -1);
        break;
    }
    //title += given;
    browser.waitForPageLoaded();
    String page_title = browser.getPageTitle();
    String page_url = browser.getUrl();
    //System.err.println("comparing:" + page_title + " with " + given + " on page " + page_url);
    browser.logAction("Got this Page Title " + page_title);
    browser.compareText(page_title, given);
    browser.sleep(3000);//overlay causing issue
    return this;
  }
  
  public SdcPages setLogOutTopNavLink() {
    browser.logAction("Logging Out");
    browser.click(logOutTopMenu);
    browser.click(logOutTopNav);
    browser.waitForClickableElement(saasUserNameTextField);
    return this;
  }
  
  public SdcPages setProfileTopNavLink() {
    //  browser.sleep(500); //needed for chrome invalid element state
    browser.logAction("Clicking Profile  logOutTopMenu");
    browser.click(logOutTopMenu);
    browser.logAction("Clicking Profile  setProfileTopNavLink");
    browser.click(profileTopNav);
    browser.sleep(1000); //ie10 Element is no longer valid 
    return this;
  }
  
  public SdcPages setUser() {
    browser.logAction("Clicking Profile");
    browser.click(userTopNav);
    browser.waitForClickableElement(rolesPermissionLink);
    browser.click(rolesPermissionLink);
    return this;
  }
  
  public SdcPages setPortalLogin(String email, String Password) {
    this.setSaasUserNameTextField(email)
            .setSaasUserPasswordTextField(Password).setLogInBTTN();
    try {
      browser.waitForClickableElement(By.id("EulaAcceptCheckbox"));
      WebElement eula = browser.findElementFast(By.id("EulaSubmitBtn"));
      this.setIAgreeEulaAcceptCheckbox()
              .setEulaSubmitBtn();
    }
    catch (Exception e) {
      /*
       * At this point we may have skipped past the EULA because we have logged in before
       * If this is the case we get a SkipException from the browser and we will test to make sure we
       * can see the Home Page, if not we raise an illegal state ie something bad happened.
       */
      System.out.println("Eula not present, trying home page");
      browser.log(Level.WARNING, "Eula not present, trying home page");
    }
    WebElement home = browser.findElementFast(By.id("navigLinkToHome"));
    if (!home.isDisplayed()) {
      throw new IllegalStateException("Unable to get past Login Eula page");
    }
    return this;
  }
  
  public SdcPages setPortalLogoutLogin(String email, String Password) {
    setLogOutTopNavLink();
    setSaasUserNameTextField(email);
    setSaasUserPasswordTextField(Password);
    setLogInBTTN();
    Config config = new Config();
    String url = config.getHostUrl("sdc");
    String partnerId = config.getParnterID();
    if (url.contains("zact")) { //select zact mobile
      browser.click(logOutTopMenu);
      browser.waitForClickableElement(By.id(partnerId)).click();
    }
    return this;
  }
  
  public SdcPages setPolicySearchfield(String text) {
    browser.logAction("setSearchFieldTo: " + text);
    browser.waitForClickableElement(searchField);
    searchField.clear();
    browser.sendTextExecuteScript(text, searchField);
    browser.actionsEnter();
    browser.logAction("Searching for: " + text);
    browser.sleep(1000);
    browser.textToBePresentInElement(tableRow, text);
    return this;
  }
  
  public SdcPages setSearchfield(String text) {
    browser.logAction("setSearchFieldTo: " + text);
    searchField.clear();
    searchField.sendKeys(text);
    browser.logAction("Searching for: " + text);
    browser.textToBePresentInElement(tableRow, text);
    return this;
  }

  //forgot password
  public SdcPages setSaasUserNameTextField(String username) {
    browser.logAction("Inputing email: " + username);
    browser.waitForClickableElement(saasUserNameTextField);
    saasUserNameTextField.clear();
    saasUserNameTextField.sendKeys(username);
    String uname = saasUserNameTextField.getAttribute("value");
    if (!uname.equals(username)) {
      throw new IllegalStateException("Error: Unable to set username to: " + uname + " :on page: " + browser.getUrl());
    }
    return this;
  }
  
  public SdcPages setSaasUserPasswordTextField(String password) {
    browser.logAction("Inputing password: " + password);
    saasUserPasswordTextField.clear();
    saasUserPasswordTextField.sendKeys(password);
    return this;
  }
  
  public SdcPages setLogInBTTN() {
    browser.logAction("Clicking Login Button");
    // browser.waitForVisibilityOfElement(By.id(logInBTTN.getAttribute("id")));
    browser.click(logInBTTN);
    //browser.waitForClickableElement(By.id("navigLinkToHome"));
    return this;
  }
  
  public SdcPages setRemeberMeCheckBox() {
    browser.logAction("Checking  Remeber Me CheckBox");
    browser.click(remeberMeCheckBox);
    return this;
  }
  
  public SdcPages setForgotPasswordLink() {
    browser.logAction("Clicking forgot Password Link");
    browser.click(forgotPasswordLink);
    return this;
  }
  
  public SdcPages setForgotPasswdEmailField(String email) {
    browser.logAction("typing " + email + " in to forgot Email Field");
    forgotPasswdEmailField.sendKeys(email);
    return this;
  }
  
  public SdcPages setForgotPasswdSubmitBtn() {
    browser.logAction("Clicking forgot Password Link");
    forgotPasswdSubmitBtn.click();
    return this;
  }
  
  public SdcPages errorMessage() {
    String getmessage = errorMessage.getText();
    browser.logAction("Checking message text" + getmessage);
    return this;
  }

  //Eula
  public SdcPages setIAgreeEulaAcceptCheckbox() {
    browser.executeScript("window.scrollBy(0,600)", "");
    browser.click(iAgreeEulaAcceptCheckbox);
    return this;
  }
  
  public SdcPages setEulaSubmitBtn() {
    browser.executeScript("window.scrollBy(0,450)", "");
    browser.logAction("Clicking eulaSubmitBtn");
    browser.click(eulaSubmitBtn);
    return this;
  }
  
  public SdcPages setEulaReturnToLoginLink() {
    browser.executeScript("window.scrollBy(0,450)", "");//scroll down to eulaReturnToLoginLink
    browser.logAction("Clicking setEulaReturnToLoginLink");
    browser.click(eulaReturnToLoginLink);
    return this;
  }
  
  public SdcPages setCreateNewGroupBtn() {
    browser.logAction("Clicking New Group Button  setCreateNewGroupBtn");
    
    try {
      browser.retryingElement(createNewGroupBtn);
    }
    catch (Exception ignored) {//for chrome
      browser.logAction("Catch Clicking New Group Button  setCreateNewGroupBtn");
      browser.findElement(By.xpath("//div[2]/button")).click();
    }
    
    return this;
  }

  // Add New Subscriber Group
  public SdcPages setGroupCancelBtn() {
    browser.logAction("Clicking New Group Cancel Button");
    browser.click(groupCancelBtn);
    return this;
  }
  
  public SdcPages setSubscriberGroupNextBtn() {
    browser.click(subscriberGroupNextBtn);
    return this;
  }
  
  public SdcPages setSubscriberGroupPrevBtn() {
    browser.click(subscriberGroupPrevBtn);
    return this;
  }
  
  public SdcPages setGroupInputName(String name) {
    browser.logAction("Inputing name: " + name);
    browser.waitForClickableElement(groupInputName);
    groupInputName.clear();
    groupInputName.sendKeys(name);
    return this;
  }
  
  public SdcPages setSubscriberGroupPhoneNumber(String value) {
    browser.logAction("Inputing PhoneNumber: " + value);
    browser.click(subscriberGroupMatcherPhoneNumber);
    subscriberGroupPhoneNumber = value;
    subscriberGroupMatcherPhoneNumber.sendKeys(subscriberGroupPhoneNumber, Keys.RETURN);
    return this;
  }
  
  public SdcPages setNetworkCdmaNumber(String value) {
    browser.logAction("Inputing description: " + value);
    networkCdmaNumber = value;
    browser.logAction("got setNetworkCdmaNumber " + networkCdmaNumber);
    subscriberGroupMatcherPhoneNumber.sendKeys(networkCdmaNumber, Keys.RETURN);
    return this;
  }
  
  public SdcPages setMccMnc(String value) {
    browser.logAction("Inputing description: " + value);
    networkMccMnc = value;
    networkGroupMccMncfield.sendKeys(value, Keys.RETURN);
    return this;
  }
  
  public SdcPages setAddOkBtn() {
    browser.waitForVisibilityOfElement(By.id("addOkBtn"));
    
    try {
      browser.logAction("Using Id to click Save setAddOkBtn");
      browser.click(addOkBtn);
    }
    catch (Exception ignored) { //needed for ie9
      WebElement element = addOkBtn;
      browser.logAction("Using JavascriptExecutor to click Save");
      browser.executeScript("arguments[0].click();", element);
    }
    browser.invisibilityOfElementLocated(By.id("addOkBtn"));
    
    return this;
  }
  
  public SdcPages setSubscriberGroupCancelBtn() {
    browser.logAction("Clicking Cancelbutton");
    browser.click(subscriberGroupCancelBtn);
    return this;
  }
  
  public SdcPages setCheckSaveCheckbtn() {
    if (addOkBtn.getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");
    }
    else {
      throw new RuntimeException("Button is enabled");
    }
    return this;
  }

  public SdcPages setTableEdit() {
    try { //needed for IE
      browser.logAction("Clicking Edit on 1st row trying by javascript");
      WebElement element = tableEdit;
      browser.executeScript("arguments[0].click();", element);
      
    }
    catch (Exception ignored1) {
      browser.logAction("Clicking tableEdit");
      browser.click(tableRow);
      browser.retryingElement(tableEdit);
    }
    return this;
  }
  
  public SdcPages setNetworkDeleteCancel() {
    browser.logAction("Clicking networkDeleteCancel");
    browser.click(networkDeleteCancel);
    return this;
  }
  
  public SdcPages setTableDelete() {
    try {
      browser.logAction("Clicking Delete on 1st row trying by javascript");
      WebElement element = tableDelete;
      browser.executeScript("arguments[0].click();", element);
      
    }
    catch (Exception ignored1) {//needed for IE
      browser.logAction("catch Clicking Delete on 1st row");
      browser.click(tableDelete);
    }
    browser.invisibilityOfElementLocated(By.id("delete_row_0"));
    return this;
  }
  
  public SdcPages setConfirmOkBtn() {
    browser.logAction("Clicking ConfirmOkBtn");
    browser.sleep(1500); //causing test failures 
    try {
      browser.logAction("Clicking ConfirmOkBtn By Javascript");
      browser.javaSciptClick(confirmOkBtn);
    }
    catch (Exception ignored1) {
      browser.logAction("Catch Clicking ConfirmOkBtn");
      browser.click(confirmOkBtn);
    }
    
    browser.sleep(5500); //causing test failures 

    return this;
  }
  
  public SdcPages tryGroupDelete(String groupName, String page) {
    try {
      
      browser.logAction("Trying to Delete " + groupName);
      setTopNavLink(page);
      browser.waitForPageLoaded();
      verifyGroupsAdded(groupName).setTableDelete();
      setConfirmOkBtn();
    }
    catch (Exception ignored1) {
      browser.logAction("Was Not able to finish Delete");
    }
    
    return this;
  }
  
  public SdcPages tryNetworkDelete(String groupName, String page) {
    try {
      browser.logAction("Trying to Delete Network " + groupName);
      setTopNavLink(page);
      browser.waitForPageLoaded();
      verifyNetworkAdded(groupName).setTableDelete();
      setConfirmOkBtn();
      browser.logAction("Done Deleting Network");
      
    }
    catch (Exception ignored1) {
      browser.logAction("Was Not able to finish Delete");
    }
    return this;
  }
  
  public boolean networkGroupExists(String groupName, String page) {
    long start = browser.timer_restart();
    setTopNavLink(page);
    setSearchfield(groupName);
    Collection c = browser.findElements(By.className("dataTables_empty"));
    return c.size() > 0;
  }
  
  public SdcPages setGroupDelete(String groupName, String page) {
    long start = browser.timer_restart();
    setTopNavLink(page);
    browser.waitForPageLoaded();
    verifyNetworkAdded(groupName).setTableDelete();
    setConfirmOkBtn();
    browser.logAction("Done Deleting Network");
    verifyGroupDeleted(groupName, page);
    return this;
  }
  
  public SdcPages verifyGroupDeleted(String groupName, String page) {
    browser.logAction("Verify Deleted " + page + " Named: " + groupName);
    setTopNavLink(page);
    setSearchfield(groupName);
    browser.sleep(5000);
    //browser.visibilityOfElementLocated(By.className("dataTables_empty"));
    browser.retryingElement(By.className("dataTables_empty"));
    return this;
  }
  
  public SdcPages verifyNetworkGroupColumn(String sidCount, String mccCount) {
    //check sid in table column 
    String getTableSid = browser.findElement(By.cssSelector(".odd > td:nth-child(2)")).getText();
    browser.logAction("****got column Sid  " + getTableSid);
    browser.compareText(getTableSid, sidCount);
    String getTableMcc = browser.findElement(By.cssSelector(".odd > td:nth-child(3)")).getText();
    browser.logAction("****got column MCC  " + getTableMcc);
    browser.compareText(getTableMcc, mccCount);
    
    return this;
  }
  
  public SdcPages verifyNetworkGroup(String groupName) {
    setUnlockNetwork();
    verifyGroupName(groupName);
    String liveTagName = browser.findElement(By.className("tag-item")).getText();
    browser.logAction("****got tag  " + liveTagName);
    browser.compareText(liveTagName, networkCdmaNumber);
    // String liveMccName = browser.findElement(By.cssSelector("div.control-group:nth-child(4) > div:nth-child(2) > tags-input:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)")).getText();
    // browser.logAction("****got mcc  " + liveMccName);
    //browser.compareText(liveMccName, networkMccMnc);

    setGroupCancelBtn();
    return this;
  }
  
  public String getNetworkCdmaNumber() {
    browser.logAction("got getNetworkCdmaNumber " + networkCdmaNumber);
    return networkCdmaNumber;
  }
  
  public SdcPages verifySubscriberGroup(String groupName) {
    browser.logAction("Verifying  " + groupName);
    setTableEdit();
    setUnlockSubscriber();
    verifyGroupName(groupName);
    String liveTagName = browser.findElement(By.className("tag-item")).getText();
    browser.logAction("****got tag  " + liveTagName);
    browser.compareText(liveTagName, subscriberGroupPhoneNumber);
    setGroupCancelBtn();
    return this;
  }
  
  public SdcPages verifyGroupName(String groupName) {
    browser.logAction("Verifying Name " + groupName);
    
    String liveName = groupInputName.getAttribute("value");
    browser.logAction("****verifyGroupName got name  " + liveName);
    browser.compareText(liveName, groupName);
    return this;
  }
  
  public SdcPages verifyGroupsAdded(String groupName) {
    if (groupName == null) {
      throw new IllegalArgumentException("verifyGroupsAdded(groupName == is null)");
    }
    String row_id = "row_0";
    setPolicySearchfield(groupName);
    //String str = browser.findElementById(row_id).getText();
    if (!browser.textToBePresentInElementValue(By.id(row_id), groupName)) {
      throw new IllegalStateException("Error: Search failed for group:" + groupName);
    }
    String getgroup = browser.findElementById(row_id).getText();
    browser.compareText(getgroup, groupName);
    return this;
  }
  
  public SdcPages verifyDataGroupsAdded(String groupName, String type) {
    if (groupName == null) {
      throw new IllegalArgumentException("verifyGroupsAdded(groupName == is null)");
    }

    //switch to  Purchasable Features 
    if (type.toLowerCase().equals("purchasable")) {
      browser.waitForClickableElement(By.linkText("Purchasable Features")).click();
    }
    //slowing down for data policy test
    String row_id = "row_0";
    browser.waitForPageLoaded();
    browser.waitForVisibilityOfElement(By.id("row_0"));
    browser.waitForVisibilityOfElement(By.id("row_1"));
    browser.waitForClickableElement(By.id("row_4"));
    
    setPolicySearchfield(groupName);
    
    if (!browser.textToBePresentInElementValue(By.id(row_id), groupName)) {
      throw new IllegalStateException("Error: Search failed for group:" + groupName);
    }
    String getgroup = browser.findElementById(row_id).getText();
    browser.compareText(getgroup, groupName);
    return this;
  }
  
  public SdcPages verifyNetworkAdded(String groupName) {
    if (groupName == null) {
      throw new IllegalArgumentException("verifyGroupsAdded(groupName == is null)");
    }
    //slowing down for data policy test
    String row_id = "row_0";
    browser.waitForPageLoaded();
    browser.waitForVisibilityOfElement(By.id("row_0"));
    browser.waitForVisibilityOfElement(By.id("row_1"));
    browser.waitForClickableElement(By.id("row_4"));
    
    setPolicySearchfield(groupName);

    //String str = browser.findElementById(row_id).getText();
    if (!browser.textToBePresentInElementValue(By.id(row_id), groupName)) {
      throw new IllegalStateException("Error: Search failed for group:" + groupName);
    }
    String getgroup = browser.findElementById(row_id).getText();
    browser.compareText(getgroup, groupName);
    return this;
  }
  
  public SdcPages checkSaveBtn() {
    browser.logAction("Checking Save Button");
    if (browser.findElements(By.cssSelector("#addOkBtn")).size() != 0) {
      throw new RuntimeException("Save button should not be displayed");
    }
    return this;
  }
  
  public SdcPages checkrow() {
    browser.logAction("Checking Rows No more then 10 row per table");
    String results;
    try {
      tableRow10.click();
      results = "fail";
    }
    catch (Exception ignored) {
      results = "pass";
      browser.logAction("Test Pass");
    }
    browser.compareText(results, "pass");
    return this;
  }
  
  public SdcPages sdcLogin(String partnerid) {
    browser.logAction("Logging into SDC Portal");
    //log in into sdc
    setPortalLogin("gurtej+zact@itsoninc.com", "Pass1234");
    //select partner from menu
    logOutTopMenu.click();
    browser.findElementById(partnerid).click();
    //goto to operators
    setTopNavLink("operators");
    //switch frame
    browser.switchTo_frameIndex(0);
    
    return this;
  }
  //

}
