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

public class CustomerSupportProvisioningPages {

  protected Browser browser;

  @FindBy(how = How.ID, using = "subscriber_tenantCarrierAccountId")
  private WebElement tenantCarrierAccountIdField;
  @FindBy(how = How.ID, using = "subscriber_imsi")
  private WebElement imsiField;
  @FindBy(how = How.ID, using = "subscriber_min")
  private WebElement minField;
  @FindBy(how = How.ID, using = "subscriber_phoneNumber")
  private WebElement phoneNumberField;
  @FindBy(how = How.ID, using = "subscriber_hardware_id")
  private WebElement hardwareIdField;
  @FindBy(how = How.ID, using = "subscriber_clientType")
  private WebElement clientTypeDropDown;
  @FindBy(how = How.ID, using = "subscriber_nai")
  private WebElement naiField;
  @FindBy(how = How.ID, using = "subscriber_carrierSubscriberId")
  private WebElement carrierSubscriberField;
  @FindBy(how = How.ID, using = "confirmOkBtn")
  private WebElement submitBtn;
  @FindBy(how = How.XPATH, using = "//div/span")
  private WebElement actionProvision;
  @FindBy(how = How.XPATH, using = "//span[2]")
  private WebElement actionDeprovision;

  private String tenantCarrierAccountId;
  private String imsi;
  private String min;
  private String phoneNumber;
  private String hardwireId;
  private String clientType;
  private String nai;
  private String carrierSubscriber;

  public CustomerSupportProvisioningPages(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
  }

  public CustomerSupportProvisioningPages setData(String giventenantCarrierAccountId, String givenimsi, String givenmin,
          String givenPhoneNumber, String givenHardwireId, String givenClientType, String givenNai, String givenCarrierSubscriber) {
    tenantCarrierAccountId = giventenantCarrierAccountId;
    imsi = givenimsi;
    min = givenmin;
    phoneNumber = givenPhoneNumber;
    hardwireId = givenHardwireId;
    clientType = givenClientType;
    nai = givenNai;
    carrierSubscriber = givenCarrierSubscriber;
    return this;

  }

  public CustomerSupportProvisioningPages setProvisionDeprovision() {
    waitForModal();
    browser.sendKeys(tenantCarrierAccountIdField, tenantCarrierAccountId, true);
    browser.sendKeys(imsiField, imsi, true);
    browser.sendKeys(minField, min, true);
    browser.sendKeys(phoneNumberField, phoneNumber, true);
    browser.sendKeys(hardwareIdField, hardwireId, true);
    browser.dropDownSelectorBy(clientTypeDropDown, clientType);
    browser.sendKeys(naiField, nai, true);
    browser.sendKeys(carrierSubscriberField, carrierSubscriber, true);
    submitBtn.click();
    return this;
  }

  public CustomerSupportProvisioningPages setDeprovision() {
    waitForModal();
    browser.click(actionDeprovision);
    return this;
  }

  public CustomerSupportProvisioningPages waitForModal() {
    browser.waitForText(By.cssSelector("h4.ng-binding"), "Provisioning");
    return this;
  }

}
