/**
 * locators and methods for MyAccount sections
 *
 * @author Gurtej Phangureh
 */
package com.itson.myaccount;

import com.itson.servicedesigncenter.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPages {

  protected Browser browser;
  protected Util util;
  protected Util.Compare compare;
  protected Util.JQuery jq;
  protected Util.JavaScript js;

  public MyAccountPages(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
    util = new Util(browser);
    compare = util.new Compare();
  }

  //Header
  @FindBy(how = How.CSS, using = ".container-fluid")
  public WebElement headerSection;
  //Header: Logout
  @FindBy(how = How.ID, using = "logoutmenu")
  private WebElement logOutMenu;
  @FindBy(how = How.ID, using = "btn_logout")
  private WebElement logoutBtn;
  @FindBy(how = How.XPATH, using = "//a[text()='Account Overview']")
  private WebElement accountOverViewSubTabBtn;
  
  //Login form
  @FindBy(how = How.ID, using = "LoginForm_username")
  private WebElement emailField;
  @FindBy(how = How.ID, using = "LoginForm_password")
  private WebElement passwordField;
  @FindBy(how = How.ID, using = "UserLoginSubmitBtn")
  private WebElement signInBtn;
  
  //Account Overview tab: View
  @FindBy(how = How.CSS, using = "div.ng-binding:nth-child(3)")
  private WebElement accountHolderName;
  @FindBy(how = How.CSS, using = ".content > div:nth-child(2) > div:nth-child(1) > div:nth-child(4)")
  private WebElement accountHolderEmail;
  @FindBy(how = How.ID, using = "account-holder-edit")
  private WebElement accountHolderEditBtn;
  @FindBy(how = How.ID, using = "profile-change-my-password")
  private WebElement changeMyPasswordLink;
  @FindBy(how = How.ID, using = "edit-firstname")
  private WebElement editNameFirstNameField;
  @FindBy(how = How.ID, using = "edit-lastname")
  private WebElement editNameLastNameField;
  @FindBy(how = How.ID, using = "edit-emailaddress")
  private WebElement editNameEmailField;
  @FindBy(how = How.ID, using = "edit-emailaddress-2")
  private WebElement editNameConfirmEmailField;
  @FindBy(how = How.CSS, using = ".span14 > a:nth-child(1)")
  private WebElement backToProfileLink;
  @FindBy(how = How.ID, using = "edit-submit")
  private WebElement saveChangesBtn;
  @FindBy(how = How.ID, using = "cancel-link-")
  private WebElement editNameCancelBtn;
  //Change my password
  @FindBy(how = How.ID, using = "edit-current-pass")
  private WebElement currentPassswordFieled;
  @FindBy(how = How.ID, using = "edit-pass-pass1")
  private WebElement newPassswordFieled;
  @FindBy(how = How.ID, using = "edit-pass-pass2")
  private WebElement confirmNewPasswordFieled;
  
  //Message
  public final String messageCSS = ".message";
  @FindBy(how = How.CSS, using = messageCSS)
  public WebElement message;
  public String passwordIncorrect = "Password is incorrect.";
  public String passwordNotMatched = "Password is not matched.";
  public String passwordSavedSuccessfully = "Password successfully saved.";

  public MyAccountPages setSaveChangesBtn() {
    browser.logAction("Clicking Save Changes btn");
    browser.sleep(Util.sleepTimeDegree2);
    browser.click(saveChangesBtn);
    return this;
  }

  public MyAccountPages setCancelBtn() {
    browser.logAction("Clicking Cancel btn");
    browser.click(editNameCancelBtn);
    return this;
  }

  public MyAccountPages setEditBtn() {
    browser.logAction("Clicking edit btn");
    browser.click(accountHolderEditBtn);
    return this;
  }

  public MyAccountPages setChangePasswordBtn() {
    browser.click(changeMyPasswordLink);
    return this;
  }

  public MyAccountPages setAccountOverViewSubTabBtn() {
    browser.click(accountOverViewSubTabBtn);
    return this;
  }

  public MyAccountPages setLogOut() {
    browser.logAction("Logging Out");
    browser.click(logOutMenu);
    logoutBtn.isDisplayed();
    browser.click(logoutBtn);
    return this;
  }

  public MyAccountPages setLogIn(String email, String password) {
    browser.logAction("Logging In");
    browser.logAction("Inputing Email " + email);
    browser.sendKeys(emailField, email, true);
    browser.logAction("Inputing Pasword " + password);
    browser.sendKeys(passwordField, password, true);
    browser.click(signInBtn);
    browser.logAction("Logging into to site");
    //make sure 
    browser.waitForInvisibilityOfElement(By.id("logoutmenu"));
    browser.logAction("Logged in");
    browser.waitForPageLoaded();
    return this;
  }
  
  public Boolean isLogined()
  {
    browser.logAction("Check an Account is Logined");
    if (headerSection.getText().toLowerCase().contains("logout"))
      return true;
    return false;
  }

  public MyAccountPages checkAccountHolder(String givenHolderName, String givenEmail) {
    browser.logAction("Checking Account Holder in Account Overview page");
    this.setAccountOverViewSubTabBtn();
    String liveAccountHolder = accountHolderName.getText();
    browser.logAction("Got Account Holder Name " + liveAccountHolder);
    compare.verify(liveAccountHolder, givenHolderName, "equals", "Verify: Live holder Name equals Given holder Name.");
    String liveHolderEmail = accountHolderEmail.getText();
    browser.logAction("Got Account Holder Email " + liveHolderEmail);
    compare.verify(liveHolderEmail, givenEmail, "equals", "Verify: Live holder Email equals Given holder Email.");
    return this;
  }

  public MyAccountPages checkEditNameAndEmail(String givenFirstName, String givenLastName, String givenEmail) {
    browser.logAction("Checking Information in Edit Name and Email Address");
    this.setAccountOverViewSubTabBtn();
    this.setEditBtn();
    //Check First Name Field
    String liveFirstName = editNameFirstNameField.getAttribute("value");
    browser.logAction("Got First  Name " + liveFirstName);
    compare.verify(liveFirstName, givenFirstName, "equals", "Verify: Live holder First Name equals Given holder First Name.");
    //Check last Name Field
    String liveLastName = editNameLastNameField.getAttribute("value");
    browser.logAction("Got Last  Name " + liveLastName);
    compare.verify(liveLastName, givenLastName, "equals", "Verify: Live holder Last Name equals Given holder Last Name.");
    //Check email
    String liveEmail = editNameEmailField.getAttribute("value");
    browser.logAction("Got Email " + liveEmail);
    compare.verify(liveEmail, givenEmail, "equals", "Verify: Live holder Email equals Given holder email.");
    return this;
  }

  public MyAccountPages editAccountHolderInformation(String newFirstName, String newLastName, String newEmail, String password, boolean... doCancel) {
    browser.logAction("Editing Information in Edit Name and Email Address");
    this.setAccountOverViewSubTabBtn();
    this.setEditBtn();
    browser.logAction("Changing 1st Name to " + newFirstName);
    browser.sendKeys(editNameFirstNameField, newFirstName, true);
    browser.logAction("Changing last Name to " + newLastName);
    browser.sendKeys(editNameLastNameField, newLastName, true);
    browser.logAction("Changing email to " + newEmail);
    browser.sendKeys(editNameEmailField, newEmail, true);
    browser.logAction("Changing confirm email to " + newEmail + "notmached");
    browser.sendKeys(editNameConfirmEmailField, newEmail + "notmached", true);
    setSaveChangesBtn();
    compare.verify(message.getText(), "Email addresses do not match.", "equals", "Verify: Message 'Email addresses do not match.' is displayed.");
    browser.logAction("Changing confirm email to " + newEmail);
    browser.sendKeys(editNameConfirmEmailField, newEmail, true);
    if(doCancel.length > 0 && doCancel[0])
      setCancelBtn();
    else
    {
      setSaveChangesBtn();
      browser.logAction("Relogin to check new email. (It's an known issue. So I add 2 below rows temporarily.)");
      browser.waitForClickableElement(logOutMenu);
      setLogOut();
      setLogIn(newEmail, password);
    }
//    browser.click(backToProfileLink); // [HaNT: There is no link as Back to Profile after clicking on Save changes button.]
    browser.waitForClickableElement(accountHolderEditBtn);
    return this;
  }

  public MyAccountPages changeMyPassword(String currentPassword, String newPassword, String email, String expectedMessage, boolean... doCancel) {
    browser.logAction("Changing Password");
    setAccountOverViewSubTabBtn();
    browser.logAction("Clicking Change Password btn");
    browser.click(changeMyPasswordLink);
    browser.logAction("Inputing Current Pasword " + currentPassword);
    browser.sendKeys(currentPassswordFieled, currentPassword, true);
    browser.logAction("Inputing New Pasword " + newPassword);
    browser.sendKeys(newPassswordFieled, newPassword, true);
    browser.logAction("Confirming New Pasword > Not matched: " + newPassword + "notmached");
    browser.sendKeys(confirmNewPasswordFieled, newPassword + "notmached", true);
    setSaveChangesBtn();
    compare.verify(message.getText(), "Password do not match.", "equals", "Verify: Message 'Password do not match.' is displayed.");
    browser.logAction("Confirming New Pasword > Matched: " + newPassword);
    browser.sendKeys(confirmNewPasswordFieled, newPassword, true);
    if(doCancel.length > 0 && doCancel[0])
      setCancelBtn();
    else
    {
      setSaveChangesBtn();
      String messageText = browser.findElementByCssSelector(messageCSS).getText();
      if(!expectedMessage.isEmpty())
        compare.verify(messageText, expectedMessage, "equals", "Verify: There is a message '"+ expectedMessage +"'.");
      if(messageText.equals(passwordSavedSuccessfully))
      {
        //logout
        browser.waitForClickableElement(logOutMenu);
        setLogOut();
        setLogIn(email, newPassword);
      }
    }
    return this;
  }

}
