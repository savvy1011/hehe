/**
 * locators and methods for Feature sections
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerSupportPages {

  protected Browser browser;

  @FindBy(how = How.ID, using = "csp-search-input")
  private WebElement phoneSearchField;
  @FindBy(how = How.ID, using = "csp-search-btn")
  private WebElement searchBtn;
  @FindBy(how = How.ID, using = "input_featureCode")
  private WebElement newFeatureCodeField;
  @FindBy(how = How.ID, using = "input-Feature_Description")
  private WebElement newFeatureDescriptionField;
  @FindBy(how = How.ID, using = "phone-number")
  private WebElement phoneNumberField;
  @FindBy(how = How.ID, using = "subscriber_imsi")
  private WebElement imsiField;
  @FindBy(how = How.ID, using = "device_subscriberNetworkId")
  private WebElement subscriberNetworkIdField;
  @FindBy(how = How.ID, using = "subscriber_min")
  private WebElement minField;
  @FindBy(how = How.ID, using = "m-btn-subscriberMenuList")
  private WebElement subscriberMenuList;
  @FindBy(how = How.ID, using = "getClientLogFile_btn")
  private WebElement getClientLogFileBtn;

  public CustomerSupportPages(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
  }

  public CustomerSupportPages setSearch(String number) {
    browser.logAction("CSP Inputing in Search " + number);
    phoneSearchField.clear();
    phoneSearchField.sendKeys(number);
    browser.click(searchBtn);
    return this;
  }

  public CustomerSupportPages checkForErrorMessage() {
    browser.sleep(5000); //need to wait to make sure error is not shown

    if (browser.findElement(By.cssSelector("div.ng-binding")).isDisplayed()) {
      browser.logAction("Error message Given");
      throw new RuntimeException();
    }
    return this;

  }

  public CustomerSupportPages checkNumber(String rightPhoneNumber) {
    ExpectedConditions.textToBePresentInElement(phoneNumberField, rightPhoneNumber);
    String getNumber = browser.findElementByCssSelector("article.br:nth-child(1) > p:nth-child(2) > b:nth-child(2)").getText();
    browser.compareText(getNumber, rightPhoneNumber);
    return this;
  }

  public CustomerSupportPages checkGetClientLogFile() {
    browser.logAction("Clicking subscriberMenuList");
    //click menu
    browser.click(subscriberMenuList);
    browser.logAction("Clicking getClientLogFileBtn");
    browser.click(getClientLogFileBtn);
    //check message is given
    browser.waitForClickableElement(By.className("alert"));
    return this;
  }

  public CustomerSupportPages checkImsi(String imsi) {
    String getLive = browser.findElement(By.id("csp-subscriber-imsi")).getText();
    browser.compareText(getLive, imsi);
    return this;
  }

  public CustomerSupportPages checkSubcriberNetworkId(String subid) {
    browser.logAction("Checking for subid " + subid);
    String getLive = browser.findElement(By.id("csp-subscriber-id")).getText();
    browser.logAction("Got for subid " + getLive);

    browser.compareText(getLive, subid);
    return this;
  }

  public CustomerSupportPages checkMin(String min) {
    String getLive = browser.findElement(By.id("csp-subscriber-min")).getText();
    browser.compareText(getLive, min);
    return this;
  }
}
