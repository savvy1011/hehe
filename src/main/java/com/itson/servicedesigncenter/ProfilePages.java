/**
 * locators and methods that are that covered in Policy Page
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePages {

  private RemoteWebDriver driver;
  protected Browser browser;

  public ProfilePages(Browser browser) {
    this.driver = browser.getDriver();
    this.browser = browser;
    browser.initElements(this);
  }

  @FindBy(how = How.ID, using = "input_First_Name")
  public WebElement firstNameField;
  @FindBy(how = How.ID, using = "input_Last_Name")
  private WebElement lastNameField;
  @FindBy(how = How.ID, using = "btn_ChangeProfile")
  private WebElement changeProfileBtn;
  @FindBy(how = How.ID, using = "input_Old_Password")
  private WebElement oldPasswordField;
  @FindBy(how = How.ID, using = "input_New_Password")
  private WebElement newPasswordField;
  @FindBy(how = How.ID, using = "input_Verify_Password")
  private WebElement newPasswordConfirmationField;
  @FindBy(how = How.ID, using = "btn_ChangePassword")
  private WebElement changePasswordBtn;

  public ProfilePages setFirstNameField(String text) {
    firstNameField.clear();
    firstNameField.sendKeys(text);
    return this;
  }

  public ProfilePages setLastNameField(String text) {
    lastNameField.clear();
    lastNameField.sendKeys(text);
    return this;
  }

  public ProfilePages setOldPasswordField(String text) {
    oldPasswordField.clear();
    oldPasswordField.sendKeys(text);
    return this;
  }

  public ProfilePages setNewPasswordField(String text) {
    newPasswordField.clear();
    newPasswordField.sendKeys(text);
    return this;
  }

  public ProfilePages setNewPasswordConfirmationField(String text) {
    newPasswordConfirmationField.clear();
    newPasswordConfirmationField.sendKeys(text);
    return this;
  }

  public ProfilePages setChangeProfileBtn() {
    browser.logAction("Clicking Change Profile Button Button");
    browser.click(changeProfileBtn);
    return this;
  }

  public ProfilePages setChangePasswordBtn() {
    browser.logAction("Clicking Change Password Button Button");
    browser.click(changePasswordBtn);
    return this;
  }

  public ProfilePages verifyNameFields(String givenFirstName, String givenLastName) {
    browser.logAction("Verifying Name Fields");
    browser.waitForClickableElement(firstNameField);
    String getFirstName = firstNameField.getAttribute("value");
    browser.logAction("Got first Name: "+getFirstName);
    browser.compareText(getFirstName, givenFirstName);

    String getLastName = lastNameField.getAttribute("value");
    browser.compareText(getLastName, givenLastName);

    return this;
  }

  public ProfilePages inputNameFields(String firstName, String lastName) {
    setFirstNameField(firstName);
    setLastNameField(lastName);
    setChangeProfileBtn();
    return this;
  }

  public ProfilePages setCheckDisableChangePasswordButton() {
    browser.logAction("Checking if Change Passowrd Button Is Disabled");
    String attribute = changePasswordBtn.getAttribute("disabled");
    browser.logAction("attribute is " + attribute);
    //save button is not active
    if (changePasswordBtn.getAttribute("disabled").equals("true")) {
      browser.logAction("Button is disabled");

    }
    else {
      throw new RuntimeException();
    }
    return this;
  }

  public ProfilePages changePassword(String oldPassword, String newPassword) {
    browser.logAction("Changing Pasword, old password: " + oldPassword + ". New password" + newPassword);
    setOldPasswordField(oldPassword)
            .setNewPasswordField(newPassword)
            .setNewPasswordConfirmationField(newPassword).setChangePasswordBtn();

    return this;
  }
}
