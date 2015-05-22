/**
 * locators and methods for GiftCardAccount section
 *
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.itson.servicedesigncenter.Browser;


public class GiftCardAccount extends OperatorPlatformPagesAbstract {

  public GiftCardAccount(Browser browser) {
    super(browser);
  }
// Add New Gift Card Account 
  @FindBy(how = How.CSS, using = "button.small")
  private WebElement addGiftCardAccountBtn;
  @FindBy(how = How.ID, using = "fields'balance'.value")
  private WebElement addGiftCardAccountBalanceField;
  @FindBy(how = How.ID, using = "fields'recipientEmailAddress'.value")
  private WebElement addGiftCardAccountRecipientEmailAddressField;
  @FindBy(how = How.CSS, using = ".submit-button")
  private WebElement addGiftCardAccountSaveBtn;
  @FindBy(how = How.CSS, using = ".to-one-lookup")
  private WebElement addGiftCardAccountCurrencyLookUpBtn;
  @FindBy(how = How.XPATH, using = "//div[2]/div/div/table/tbody/tr[16]/td")
  private WebElement addGiftCardAccountCurrencyLookUpUsDollarSelection;
  @FindBy(how = How.CSS, using = "#field-accountNumber > div:nth-child(2) > span:nth-child(1)")
  private WebElement addGiftCardAccountAccountNumber;



  public String CreateGiftCardAccount(String balance, String email) {
    setSideNavCustomerCare().setSideNavCustomerCareGiftCardAccount();

    browser.logAction("Clicking Add Gift Card Account");
    browser.click(addGiftCardAccountBtn);
    browser.logAction("Inputing Balance");
    addGiftCardAccountBalanceField.clear();
    addGiftCardAccountBalanceField.sendKeys(balance);
    browser.logAction("Selecting Currency, Clicking Currency Lookup Button");
    browser.click(addGiftCardAccountCurrencyLookUpBtn);
    browser.waitForVisibilityOfElement(By.cssSelector("tr.clickable:nth-child(4) > td:nth-child(1)"));

    browser.logAction("Selecting Us dollar");

    browser.click(addGiftCardAccountCurrencyLookUpUsDollarSelection);
    browser.waitForVisibilityOfElement(By.id("fields'recipientEmailAddress'.value"));
    browser.logAction("Inputing Recipient Email Address");
    addGiftCardAccountRecipientEmailAddressField.clear();
    addGiftCardAccountRecipientEmailAddressField.sendKeys(email);

    browser.logAction("Clicking Save Button");
    browser.waitForVisibilityOfElement(By.cssSelector(".submit-button"));
    browser.click(addGiftCardAccountSaveBtn);
    browser.waitForInvisibilityOfElement(By.id("myModalLabel"));
    browser.waitForVisibilityOfElement(By.id("field-accountNumber"));
    String accountNumber = addGiftCardAccountAccountNumber.getText();
    browser.logAction("Got Account Number " + accountNumber);

    return accountNumber;
  }

  public GiftCardAccount verifyGiftCardAccount(String accountNumber, String balance, String email) {

    setSideNavCustomerCare().setSideNavCustomerCareGiftCardAccount();
    //find account
    browser.findElementByXPath("//input").sendKeys(accountNumber);
    browser.waitForClickableElement(By.linkText("Search")).click();
    browser.waitForClickableElement(By.linkText(accountNumber)).click();
    String gotBalance = browser.findElement(By.cssSelector("#field-balance > div:nth-child(2) > span:nth-child(1)")).getText();
    browser.compareText(gotBalance, balance);
    //String gotEmail = browser.findElement(null)
    return this;
  }
}
