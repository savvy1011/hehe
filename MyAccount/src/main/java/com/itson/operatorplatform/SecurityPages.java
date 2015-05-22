/**
 * locators and methods used in all Operator Platform
 *
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.itson.servicedesigncenter.Browser;


public class SecurityPages {

  protected Browser browser;
  protected String view;


  public SecurityPages(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
  }
//login
  @FindBy(how = How.XPATH, using = "//nav[@id='sideMenu']/ul/li[contains(.,'Security')]/div/h5")
  private WebElement sideNavSecurity;
  @FindBy(how = How.LINK_TEXT, using = "Users")
  private WebElement sideNavSecurityUsers;
  @FindBy(how = How.XPATH, using = "//div[@id='main']/div/div[2]/div/button")
  private WebElement addAdminUserBtn;
  @FindBy(how = How.ID, using = "fields'name'.value")
  private WebElement nameField;
  @FindBy(how = How.ID, using = "fields'login'.value")
  private WebElement loginField;
  @FindBy(how = How.ID, using = "fields'email'.value")
  private WebElement emailField;
  @FindBy(how = How.ID, using = "fields'phoneNumber'.value")
  private WebElement phoneNumberField;
  @FindBy(how = How.ID, using = "fields'password'.value")
  private WebElement passwordField;
  @FindBy(how = How.ID, using = "fields'passwordConfirm'.value")
  private WebElement confimPasswordField;
  @FindBy(how = How.ID, using = "activeStatusFlag-true")
  private WebElement activeStatusYesRadiobtn;
  @FindBy(how = How.CSS, using = ".submit-button")
  private WebElement saveBtn;
  @FindBy(how = How.XPATH, using = "//div[@id='allRoles']/div/ul/li/button")
  private WebElement addRoleBtn;
  @FindBy(how = How.CSS, using = "input[type='search']")
  private WebElement searchField;
  @FindBy(how = How.XPATH, using = "//div[2]/a")
  private WebElement searchBtn;
  @FindBy(how = How.XPATH, using = "//button[@type='button']")
  private WebElement deleteBtn;
  @FindBy(how = How.CSS, using = ".list-grid-no-results")
  private WebElement noResults;

  public SecurityPages setUserSection() {
    browser.logAction("Clicking On Security");
    browser.click(sideNavSecurity);
    browser.waitForVisibilityOfElement(By.linkText("Users"));
    browser.logAction("Clicking Users Section");
    browser.click(sideNavSecurityUsers);
    return this;
  }

  public SecurityPages setAddAdminUserBtn() {
    browser.logAction("Clicking addAdminUserBtn");
    browser.click(addAdminUserBtn);
    return this;
  }

  public SecurityPages setPasswordFileds(String password) {
    browser.logAction("Inputing password : " + password);
    browser.waitForClickableElement(passwordField);
    passwordField.clear();
    passwordField.sendKeys(password);

    browser.logAction("Inputing confim Password : " + password);
    confimPasswordField.clear();
    confimPasswordField.sendKeys(password);

    return this;
  }

  public SecurityPages setAddNewAdminUser(String name, String login, String email, String password) {
    browser.logAction("<<<< In Add New Admin User dialogbox >>>>");
    browser.logAction("Inputing Name : " + name);
    browser.retryingElement(nameField);
    nameField.clear();
    nameField.sendKeys(name);

    browser.logAction("Inputing login : " + login);
    loginField.clear();
    loginField.sendKeys(login);

    browser.logAction("Inputing email : " + email);
    emailField.clear();
    emailField.sendKeys(email);

    browser.logAction("Clicking YES  active status btn");
    browser.click(activeStatusYesRadiobtn);

    setPasswordFileds(password);

    browser.logAction("Clicking Save btn");
    browser.click(saveBtn);
    browser.logAction("Waiting For Save to Finish");
    return this;
  }

  public SecurityPages addAdmin(String password) {
    browser.logAction("Inputing password : " + password);
    browser.waitForClickableElement(passwordField);
    passwordField.clear();
    passwordField.sendKeys(password);

    browser.logAction("Inputing confim Password : " + password);
    confimPasswordField.clear();
    confimPasswordField.sendKeys(password);

    return this;
  }

  public SecurityPages setAdminRole() {
    browser.waitForClickableElement(addRoleBtn);

    browser.logAction("Clicking Add role Btn");
    browser.click(addRoleBtn);
    browser.logAction("Adding role");
    browser.findElement(By.id("myModalLabel")).click();
    //pick admin role

    int rowCount = 1;
    String rowName;
    while (rowCount < 20) {

      rowName = browser.findElement(By.xpath("//tr[" + rowCount + "]/td")).getText();
      browser.logAction("Got This " + rowName);
      if (rowName.equals("Admin")) {
        browser.logAction("Name Matched");
        browser.findElement(By.xpath("//tr[" + rowCount + "]/td")).click();
        rowCount = 10;
        break;
      }
      else {
        browser.logAction("Name Not Matched");
        rowCount++;
      }
    }
    browser.waitForClickableElement(By.cssSelector(".clickable > td:nth-child(1)")); //check admin added
    return this;
  }

  public SecurityPages createUser(String accountName, String accountPassword) {
    //goto user section
    setUserSection()
            //click add admin user btn
            .setAddAdminUserBtn()
            //Fill Add New Admin User dialog
            .setAddNewAdminUser(accountName, accountName, accountName + "@itsoninc.net", accountPassword);
    return this;
  }

  public SecurityPages deleteUser(String accountName) {
    browser.logAction("Deleting User");
    findUser(accountName);
    browser.logAction("Clicicking Delete Btn");
    browser.click(deleteBtn);
    browser.waitForClickableElement(searchField);
    return this;
  }

  public SecurityPages findUser(String accountName) {
    browser.logAction("find User");
    //goto user section
    setUserSection();
    browser.sleep(1500);
    //search user
    setSearchField(accountName);
    //wait for list to disappear 
    String adminName = "automation";
    if (!accountName.equals(adminName)) {
      browser.waitForInvisibilityOfElement(By.linkText(adminName));
    }
    browser.sleep(500);
    sdcViewRefresh();
    browser.waitForPageLoaded();
    browser.pollElementIsClickAble(By.linkText(accountName));
    browser.logAction("Clicking User: " + accountName);
    browser.retryingElement(By.cssSelector("td > a"));
    //check right user
    browser.verifyTextField(nameField, accountName);
    return this;
  }

  public SecurityPages verifyUserDeleted(String accountName) {
    browser.logAction("Verifying deleted User" + accountName);
    //search user
    setSearchField(accountName);
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
    return this;
  }

  public SecurityPages setSearchField(String accountName) {
    browser.logAction("Searching for : " + accountName);
    browser.click(searchField);
    searchField.clear();
    searchField.sendKeys(accountName);
    browser.logAction("Clicking searchBtn");
    browser.actionsEnter();
    //  searchBtn.click();
    return this;
  }

  public String changePassword(String accountName) {

    findUser(accountName);
    //browser.sleep(1500);
    //browser.findElement(By.xpath("//td/a")).click();
    //change password
    String newPassword = browser.randomCreateUser();
    // browser.sleep(1000);
    setPasswordFileds(newPassword);
    //click save button
    // browser.findElementByXPath("//button[@type='submit']").click();
    browser.sleep(6000); // get error
    browser.logAction("Clicking Savebtn *1");
    browser.click(saveBtn);
    browser.sleep(3000); // get error
    browser.waitForClickableElement(By.cssSelector(".alert-box-message"));

    return newPassword;
  }

  public SecurityPages sdcViewRefresh() {
    view = browser.getSdcView();
    if (view.equals("sdc")) {
    }
    else {
    browser.refresh();
    }
    return this;
  }
}
