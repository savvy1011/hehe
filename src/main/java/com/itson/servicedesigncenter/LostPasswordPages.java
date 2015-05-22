/**
 * locators and methods that are that covered in Lost Password Section
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LostPasswordPages extends SdcPages{


  public LostPasswordPages(Browser browser) {
    super(browser);
  }

  //losst password section
  @FindBy(how = How.CSS, using = ".input")
  private WebElement forgotPasswordEmailField;
  @FindBy(how = How.XPATH, using = "//button[2]")
  private WebElement forgotPasswordSendEmailBtn;
  @FindBy(how = How.CSS, using = ".inline-body > h4:nth-child(2)")
  private WebElement forgotPasswordTitle;
  //Reset Password after email
  @FindBy(how = How.ID, using = "passwordhash")
  private WebElement newPasswordField;
  @FindBy(how = How.ID, using = "verifyPasswordhash")
  private WebElement newPasswordConfirmationField;
  @FindBy(how = How.CSS, using = ".btn")
  private WebElement saveBtn;

  public LostPasswordPages setEmailField(String username) {
    browser.logAction("Inputing email: " + username);
    forgotPasswordEmailField.clear();
    forgotPasswordEmailField.sendKeys(username);
    return this;
  }

  public LostPasswordPages checkForgotPasswordPage() {
    String getTitle = forgotPasswordTitle.getText();
    browser.compareText(getTitle, "Forgot");
    return this;
  }

  public LostPasswordPages setSendEmailBtn() {
    browser.click(forgotPasswordSendEmailBtn);
    return this;
  }

  public LostPasswordPages setForgotPassword(String email) {
    browser.logAction("Forgot Pasword ");
    setForgotPasswordLink();
    //check in Forgot Password section
    checkForgotPasswordPage();
    setEmailField(email);
    setSendEmailBtn();
    browser.waitForClickableElement(By.id("LoginForm_username"));
    return this;
  }

  public LostPasswordPages setNewPasswordField(String text) {
    newPasswordField.clear();
    newPasswordField.click();
    newPasswordField.sendKeys(text);
    return this;
  }

  public LostPasswordPages setNewPasswordConfirmationField(String text) {
    newPasswordConfirmationField.clear();
    newPasswordConfirmationField.click();
    newPasswordConfirmationField.sendKeys(text);
    return this;
  }

  public LostPasswordPages setSaveBtn() {
    browser.logAction("Clicking Save Button Button");
    browser.click(saveBtn);
    return this;
  }

  public LostPasswordPages changePassword(String oldPassword, String newPassword) {
    // LostPasswordPages profilePages = new LostPasswordPages(driver);
    browser.logAction("Changing Pasword, old password: " + oldPassword + ". New password: " + newPassword);
    browser.waitForClickableElement(newPasswordField);
    setNewPasswordField(newPassword).setNewPasswordConfirmationField(newPassword).setSaveBtn();
    return this;
  }

}
