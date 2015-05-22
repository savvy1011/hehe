/**
 * locators and methods that are that covered in Policy Page
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserPages extends SdcPages {

  public UserPages(Browser browser) {
    super(browser);
  }

  @FindBy(how = How.ID, using = "btn-Create_New_User")
  private WebElement createNewUserBtn;
  @FindBy(how = How.NAME, using = "email")
  private WebElement createNewUserEmailField;
  @FindBy(how = How.NAME, using = "verifyEmail")
  private WebElement createNewUserVerifyEmailField;
  @FindBy(how = How.ID, using = "btn-Send-Invite")
  private WebElement sendInviteBtn;
  @FindBy(how = How.CSS, using = "div.btn-spacing > button.btn")
  private WebElement addRoleBtn;
  @FindBy(how = How.CSS, using = ".form-vertical > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
  private WebElement removeRole;
  @FindBy(how = How.XPATH, using = "//table[2]/tbody/tr/td[2]/div/button[2]")
  private WebElement addAllPartnersBtn;

  //Account Setup
  @FindBy(how = How.ID, using = "accountSetUpFirstName")
  private WebElement accountSetupFirstNameField;
  @FindBy(how = How.ID, using = "accountSetUpLastName")
  private WebElement accountSetupLastNameField;
  @FindBy(how = How.ID, using = "accountSetUpPasswordHash")
  private WebElement accountSetupPasswordField;
  @FindBy(how = How.ID, using = "accountSetUpverifyPasswordhash")
  private WebElement accountSetupReEnterPasswordField;
  @FindBy(how = How.ID, using = "accountSetSaveBtn")
  private WebElement accountSetupSavebtn;
  @FindBy(how = How.CSS, using = "div.switch-off.ng-scope > span.ng-binding")
  private WebElement enableUserOnOff;
  @FindBy(how = How.CSS, using = "button.pull-right:nth-child(2)")
  private WebElement deleteUserBtn;
  @FindBy(how = How.ID, using = "btn-Save-User")
  private WebElement userInformationSaveBtn;

  public UserPages setCreateNewUserBtn() {
    browser.click(createNewUserBtn);
    return this;
  }

  public UserPages setUserInformationSaveBtn() {
    browser.click(userInformationSaveBtn);
    return this;
  }

  public UserPages setSendInviteBtn() {
    browser.retryingElement(sendInviteBtn);
    return this;
  }

  public UserPages setAddRoleBtn() {
    browser.logAction("Clicking Arrow to add role");
    browser.click(addRoleBtn);
    return this;
  }

  public UserPages setRemoveRoleBtn() {
    browser.logAction("Clicking Arrow to remove role");
    browser.click(removeRole);
    return this;
  }

  public UserPages setCreateNewUserEmailField(String text) {
    browser.logAction("Inputing text Email " + text);
    createNewUserEmailField.clear();
    createNewUserEmailField.sendKeys(text);
    return this;
  }

  public UserPages setCreateNewUserVerifyEmailField(String text) {
    browser.logAction("Inputing text verify email " + text);
    createNewUserVerifyEmailField.clear();
    createNewUserVerifyEmailField.sendKeys(text);
    return this;
  }

  public UserPages setAccountSetupFirstNameField(String text) {
    browser.logAction("Inputing text First Name " + text);
    accountSetupFirstNameField.clear();
    accountSetupFirstNameField.sendKeys(text);
    return this;
  }

  public UserPages setAccountSetUpLastName(String text) {
    browser.logAction("Inputing text LastName  " + text);
    accountSetupLastNameField.clear();
    accountSetupLastNameField.sendKeys(text);
    return this;
  }

  public UserPages setAccountSetupPasswordField(String text) {
    accountSetupPasswordField.clear();
    accountSetupPasswordField.sendKeys(text);
    return this;
  }

  public UserPages setAccountSetupReEnterPasswordField(String text) {
    browser.logAction("Inputing text reenter password " + text);
    accountSetupReEnterPasswordField.clear();
    accountSetupReEnterPasswordField.sendKeys(text);
    return this;
  }

  public UserPages setAccountSetupSavebtn() {
    browser.logAction("Clicking Account Save Button");
    browser.sleep(500);
    browser.click(accountSetupSavebtn);
    return this;
  }

  public UserPages setAddRole(String role) {

      browser.logAction("Clicking add all role Button");
      browser.retryingElement(By.xpath("//button[2]"));

    /*  browser.logAction("Adding Role " + role);
     role = role.toUpperCase();
     browser.waitForClickableElement(By.id("availableRoles")).click();
     WebElement dropDownListBox23 = browser.findElement(By.id("availableRoles"));

     Select clickThis23 = new Select(dropDownListBox23);
     clickThis23.selectByVisibleText(role);
    
    
     browser.waitForClickableElement(By.linkText(role)).click();
     browser.dropDownSelectorBy("availableRoles", role, "id");
     setAddRoleBtn(); */
    return this;
  }

  public UserPages setPartners() {
    browser.logAction("Adding Partner ");
    browser.click(addAllPartnersBtn);
    return this;
  }

  public UserPages setDeleteUser(String user) {
    browser.logAction("Deleting User " + user);
    setTopNavLink("users");
    browser.dropDownSelectorBy("select_Existing_Users", user, "id");
    browser.click(deleteUserBtn);
    setConfirmOkBtn();
    return this;
  }

  public UserPages setPendingUser(String user) {
    browser.logAction("Selecting Pending User " + user);
    browser.dropDownSelectorBy("select_Pending_Users", user, "id");
    //enable user
    browser.click(enableUserOnOff);
    return this;
  }

  public UserPages accountSetup(String firstName, String lastName, String password, String rePasword) {
    browser.logAction("Account Setup");
    browser.sleep(2000);
    browser.waitForClickableElement(By.cssSelector(".inline-header > h3:nth-child(1)"));
    setAccountSetupFirstNameField(firstName).setAccountSetUpLastName(lastName)
            .setAccountSetupPasswordField(password).setAccountSetupReEnterPasswordField(rePasword).setAccountSetupSavebtn();
    return this;
  }

  public UserPages setRemoveRole(String role) {
    role = role.toUpperCase();
    browser.dropDownSelectorBy("assignedRoles", role, "id");
    setAddRoleBtn();
    return this;
  }

  public UserPages checkRoles(String email, String password) {
    checkRolePages(email, password, "network");
    checkRolePages(email, password, "subscriber");

    return this;
  }

  public UserPages checkRolePages(String email, String password, String givenPage) {

    //goto correct page
    setTopNavLink(givenPage);

    if (browser.findElements(By.id("delete_row_0")).size() != 0) {
      throw new RuntimeException("Delete Should not be dispalyed");
    }
    //click on view
    setTableEdit();

    if (givenPage.equals("features")) {
      //lock button should not be present
      if (browser.findElements(By.cssSelector("button.btn:nth-child(3)")).size() != 0) {
        throw new RuntimeException("Lock button Should not be dispalyed, failed test");
      }
      //click on network group
      browser.waitForClickableElement(By.cssSelector(".tag-label")).click();
      //save button should not be present
      checkSaveBtn();

      //clean up delete feature
      setPortalLogoutLogin(email, password);

    }
    else {
      //lock button should not be present
      if (browser.findElements(By.id("lock-btn")).size() != 0) {
        throw new RuntimeException("Lock button Should not be dispalyed, failed test");
      }
      //save button should not be present
      checkSaveBtn().setGroupCancelBtn();
    }
    return this;
  }

}
