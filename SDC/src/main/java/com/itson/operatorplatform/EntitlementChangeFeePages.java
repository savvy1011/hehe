/**
 * locators and methods for plan creation section
 *
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.itson.servicedesigncenter.Browser;

public class EntitlementChangeFeePages extends ProductPages {

  private String description;
  private String retailPrice;
  private String entitlementPolicySelection;
  private String entitlementEventSelection;

  private String salePrice;
  private String taxCode;

  protected String giftableSelection;

  protected String[] entitlementData;

  public EntitlementChangeFeePages(Browser browser) {
    super(browser);
  }
  //Appearancem  section
  @FindBy(how = How.CSS, using = ".redactor-editor")
  private WebElement entitlementFeeDescriptionField;
  //Behavior section
  @FindBy(how = How.ID, using = "fields'entitlementPolicy'.value")
  private WebElement entitlementPolicyDropDown;
  @FindBy(how = How.ID, using = "fields'eventName'.value")
  private WebElement entitlementEventDropDown;

  protected String[] setEntitlementData(String givenentitlementData) {  //call parameters to create a fee excludeing  plan fee, error mesg for neg test 
    entitlementData = givenentitlementData.split(",");
    description = entitlementData[1];
    entitlementPolicySelection = entitlementData[2];
    entitlementEventSelection = entitlementData[3];
    retailPrice = entitlementData[4];
    salePrice = entitlementData[5];
    taxCode = entitlementData[6];

    upDate = "no";

    return entitlementData;
  }
    public EntitlementChangeFeePages setEntitlementData(String descriptionData,String entitlementPolicySelectionData,String entitlementEventSelectionData,String retailPriceData,
    String salePriceData, String taxCodeData) {  //call parameters to create a fee excludeing  plan fee, error mesg for neg test 
    description = descriptionData;
    entitlementPolicySelection = entitlementPolicySelectionData;
    entitlementEventSelection = entitlementEventSelectionData;
    retailPrice = retailPriceData;
    salePrice = salePriceData;
    taxCode = taxCodeData;

    upDate = "no";

    return this;
  }

  public EntitlementChangeFeePages createEntitlementFee(String productName) {
    browser.logAction("Creating new Entitlement Change Fee  " + productName);
    // start creating a new fee
    setNewProduct("Entitlement Change Fee");
    browser.sleep(1000);
    //appreance section
    checkModalTitle("Add Entitlement Change Fee");
    setEntitlementFeeApperanceSection(productName);

    //Behavior section
    setEntitlementFeeBehaviorSection();
    //Billing Section
    setBillingSection();
    //Availability Section
    setAvailability();
    //Save Fee
    browser.click(addSaveBtn);
    browser.sleep(5000);
    //promote 
    setPromotePlan(productName);
    //aprove plan
    setApproveProduct(productName, "Add");
    browser.sleep(1000);
    verifyingEntitlementCreated(productName);
    return this;
  }

  public EntitlementChangeFeePages setEntitlementFeeApperanceSection(String productName) {
    browser.logAction("Inputing Text in name field : " + productName);
    setAddNewProductNameTextField(productName);
    browser.sendKeys(entitlementFeeDescriptionField, productName, true);
    return this;
  }

  public EntitlementChangeFeePages setEntitlementFeeBehaviorSection() {
    browser.logAction("Starting Behavior Section");
    browser.waitForClickableElement(entitlementPolicyDropDown);
    browser.logAction("entitlementPolicyDropDown selecting " + entitlementPolicySelection);
    browser.dropDownSelectorBy(entitlementPolicyDropDown, entitlementPolicySelection);
    browser.logAction("entitlementEventDropDown selecting " + entitlementEventSelection);
    browser.dropDownSelectorBy(entitlementEventDropDown, entitlementEventSelection);
    return this;
  }

  public EntitlementChangeFeePages setBillingSection() {
    setRetailPriceTextField(retailPrice);
    browser.dropDownSelectorBy(dropDownTaxCode, taxCode);
    return this;
  }

  public EntitlementChangeFeePages verifyingEntitlementCreated(String productName) {

    browser.logAction("Verifying Plan Fee :" + productName);
    verifyingProductCreated(productName, "fee");
    //  verifyApprovedLogIn();
    verifyEntitlementInformation(productName);
    return this;
  }

  public EntitlementChangeFeePages verifyEntitlementInformation(String productName) {
    browser.logAction("Verifing  Entitlement Change Fee  " + productName);
    browser.verifyTextField(addNewProductNameTextField, productName);
    browser.verifyDropDownSelected(entitlementPolicyDropDown, entitlementPolicySelection);
    browser.verifyDropDownSelected(entitlementEventDropDown, entitlementEventSelection);
    browser.verifyTextField(addNewProductRetailPriceTextField, retailPrice);
    return this;

  }
}
