/**
 * locators and methods for plan creation section
 *
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.itson.servicedesigncenter.Browser;

public class FeePages extends ProductPages {

  private String description;
  private String retailPrice;
  private String salePrice;
  private String taxCode;
  private String feeType;
  private String renewal;
  private String alignedToAccountCycle;
  private String planDuration;
  private String planDurationUnit;
  private String planDurationRadioBtn;
  private String proRated;
  private String nonPaidCycleAction;
  private String upDate;
  private String productName1;
  private String productName2;
  private String productName3;
  protected String giftableSelection;

  protected String[] feeData;

  public FeePages(Browser browser) {
    super(browser);
  }

  @FindBy(how = How.CSS, using = "fields'defaultSku__name'.value")
  private WebElement addFeeDescriptionField;
  //Behavior section

  @FindBy(how = How.ID, using = "fields'feeRenewalType'.value")
  private WebElement feeDropDownRenewal;
  @FindBy(how = How.ID, using = "fields'feeRenewalCount'.value")
  private WebElement feeMaximumRenewalTextField;
  @FindBy(how = How.ID, using = "isFeeBillingAligned-true")
  private WebElement feeAlignedToTheAccountCycleYesSelection;
  @FindBy(how = How.ID, using = "isFeeBillingAligned-false")
  private WebElement feeAlignedToTheAccountCycleNoSelection;
  @FindBy(how = How.ID, using = "unlimitedFeeDuration-true")
  private WebElement unlimitedFeeDuration_true;
  @FindBy(how = How.ID, using = "unlimitedFeeDuration-false")
  private WebElement unlimitedFeeDuration_false;
  @FindBy(how = How.ID, using = "fields'feeDuration'.value")
  private WebElement feeDurationTextField;
  @FindBy(how = How.ID, using = "fields'feeDurationPeriod'.value")
  private WebElement dropDownFeeCycleDuration;
  @FindBy(how = How.ID, using = "isFeeProratedOnFirstCycle-true")
  private WebElement proRatedOnFirstCycle_YesSelection;
  @FindBy(how = How.ID, using = "isFeeProratedOnFirstCycle-false")
  private WebElement proRatedOnFirstCycle_NoSelection;
  @FindBy(how = How.ID, using = "fields'feeNonPaidCycleAction'.value")
  private WebElement nonPaidCycleActionDropDownMenu;
  @FindBy(how = How.ID, using = "fields'feeType'.value1")
  private WebElement feeTypeAccountFeeSelection;
  @FindBy(how = How.ID, using = "fields'feeType'.value2")
  private WebElement feeTypeSubcriberFeeSelection;

  @FindBy(how = How.ID, using = "isGiftableFee-true")
  private WebElement giftableYesSelection;
  @FindBy(how = How.ID, using = "isGiftableFee-false")
  private WebElement giftableNoSelection;

  // Behavior Fee cycle length*


  protected String[] setPlanData(String givenfeeData) {  //call parameters to create a fee excludeing  plan fee, error mesg for neg test 
    feeData = givenfeeData.split(",");
    description = feeData[1];
    renewal = feeData[2];
    alignedToAccountCycle = feeData[3].toLowerCase().trim();
    planDuration = feeData[4];
    planDurationUnit = feeData[5];
    proRated = feeData[6].toLowerCase();
    feeType = feeData[7].toLowerCase();

    retailPrice = feeData[8];
    salePrice = feeData[9];
    taxCode = feeData[10];
    upDate = "no";

    try {
      giftableSelection = feeData[11];
    }
    catch (Exception e) {
      giftableSelection = "no";
    }

    return feeData;
  }

  public FeePages createFee(String productName) {
    browser.logAction("Creating new fee  " + productName);

    setNewProduct("Fee");
    setAddNewProductNameTextField(productName);
    checkModalTitle("Add Fee");
    setFeeTypeSection();

    setFeeBehaviorSection();
    setBillingSection();
    setAvailability();
    browser.click(addSaveBtn);
    browser.sleep(5000);
    //promote 
    setPromotePlan(productName);
    //aprove plan
    setApproveProduct(productName, "Add");
    browser.sleep(1000);
    //verify fee 
    verifyingFeeCreated(productName);

    return this;
  }

  public FeePages setFeeTypeSection() {
    browser.logAction("Selecting price modal  " + feeType);
    if (feeType.contains("account")) {
      browser.logAction("Clicking feeTypeAccountFeeSelection");
      browser.click(feeTypeAccountFeeSelection);
    }
    else {
      browser.logAction("Clicking feeTypeSubcriberFeeSelection");
      browser.click(feeTypeSubcriberFeeSelection);
    }
    if (giftableSelection.contains("yes")) {
      browser.click(giftableYesSelection);
    }
    else {//no is deafult
      browser.checkRadioBtnSelected(giftableNoSelection);
    }
    return this;
  }

  public FeePages setBillingSection() {
    setRetailPriceTextField(retailPrice);
    browser.dropDownSelectorBy(dropDownTaxCode, taxCode);
    //setTaxcode(taxCode);
    return this;
  }



  public FeePages setFeeBehaviorSection() {
    if (!renewal.equals("")) {
      setFeeRenewal(renewal);
      //Aligned to account cycle  //Is Recurring must be yes
      if (!renewal.equals("Does Not Renew")) {
        setAlignedToAccountCycle();
      }
      if (!alignedToAccountCycle.equals("yes") && !alignedToAccountCycle.equals("")) {
        setPlanDuration(planDuration, planDurationUnit);
      }
      if (alignedToAccountCycle.equals("yes") && !alignedToAccountCycle.equals("")) {
        setProRated();
      }
      browser.dropDownSelectorBy(nonPaidCycleActionDropDownMenu, "No Action");
    }
    return this;
  }

  public FeePages setFeeRenewal(String text) {
    if (text.contains("Renew")) { //Maximum renewal will have a number 
      browser.dropDownSelectorBy(feeDropDownRenewal, text);
    }
    else {
      //Maximum Renewal
      browser.dropDownSelectorBy(feeDropDownRenewal, "Maximum Renewals");
      //  browser.waitForClickableElement(feeMaximumRenewalTextField);
      feeMaximumRenewalTextField.sendKeys(text);
    }
    return this;
  }

  public FeePages setAlignedToAccountCycle() {
    browser.logAction("Clicking Aligned to account cycle " + alignedToAccountCycle);
    browser.waitForClickableElement(feeAlignedToTheAccountCycleYesSelection);
    switch (alignedToAccountCycle) {
      case "yes":
        browser.click(feeAlignedToTheAccountCycleYesSelection);
        break;
      case "no":
        browser.click(feeAlignedToTheAccountCycleNoSelection);
        break;
      default:
        System.err.println("***Input for alignedToAccountCycle not Found only yes or no " + alignedToAccountCycle);
        break;
    }
    return this;
  }

  public FeePages setPlanDuration(String planDuration, String planDurationUnit) {
    browser.logAction("Setting Data Usage");
    browser.waitForVisibilityOfElement(By.id("unlimitedPlanDuration-true"));
    browser.logAction("Fee Duration Selction");
    if (planDuration.equals("unlimited")) {
      browser.logAction("Fee Duration Selction slecting unlimited");
      browser.click(unlimitedFeeDuration_true);
      planDurationRadioBtn = "unlimited";
    }
    else {
      browser.logAction("Fee Duration Selction selecting limited to " + planDuration);
      unlimitedFeeDuration_false.click();
      planDurationRadioBtn = "lasts";
      if (!planDuration.equals("")) {
        setFeeDurationTextField(planDuration);
      }
      if (!planDurationUnit.equals("")) {
        if (!planDuration.equals("")) {
          browser.dropDownSelectorBy(dropDownFeeCycleDuration, planDurationUnit);
        }
      }
    }
    return this;
  }

  public FeePages setFeeDurationTextField(String text) {
    browser.logAction("Inputing Fee Cycle Duration " + text);
    browser.switchTo_frameIndex();
    browser.waitForClickableElement(feeDurationTextField);
    feeDurationTextField.clear();
    feeDurationTextField.sendKeys(text);
    return this;
  }

  public FeePages setProRated() {
    browser.logAction("Clicking proated  " + proRated);
    browser.waitForClickableElement(proRatedOnFirstCycle_YesSelection);
    switch (proRated) {
      case "yes":
        browser.click(proRatedOnFirstCycle_YesSelection);
        break;
      case "no":
        browser.click(proRatedOnFirstCycle_NoSelection);
        break;
      default:
        System.err.println("***Input for alignedToAccountCycle not Found only yes or no" + proRated);
        break;
    }
    return this;
  }

  public FeePages verifyingFeeCreated(String productName) {

    browser.logAction("Verifying Plan Fee :" + productName);
    verifyingProductCreated(productName, "fee");
    //  verifyApprovedLogIn();
    //verifyFeeInformation(productName);
    return this;
  }
  /*
   public FeePages verifyFeeInformation(String productName) {

   //Appearance section
   browser.logAction("Checking Product Name: " + productName);
   browser.verifyTextField(addNewProductNameTextField, productName);
   // verifyPlanRadioBtn();

   //Behavior
   //Type
   browser.logAction("ServicePolicy Checking DropDowns");
   if (!renewal.contains("Renew")) {
   renewal = "Maximum Renewals";
   }
   if (feeType.equals("compound")) {
   browser.logAction("Renewal Checking DropDowns");
   browser.verifyDropDownSelected(feeDropDownRenewal, renewal);
   }
   //Billing
   browser.logAction("Checking Retail Price");
   browser.verifyTextField(addNewProductRetailPriceTextField, retailPrice);
   if (taxCode.equals("Data Plan")) {
   feeTaxCode.sendKeys("Data Plan");
   // browser.dropDownSelectorBy(dropDownTaxCode, "Data Plan");
   }
   else {
   browser.logAction("Tax Code Checking DropDowns: " + taxCode);
   browser.verifyDropDownSelected(feeTaxCode, taxCode);
   }
   if (!productName1.equals("none") && !productName1.equals("")) {
   //check product
   String getPlanName = browser.findElement(By.cssSelector(".clickable > td:nth-child(1)")).getText();
   browser.compareText(getPlanName, productName1);
   }
   if (!productName2.equals("none") && !productName1.equals("")) {
   //check product
   String getPlanName = browser.findElement(By.cssSelector(".clickable > td:nth-child(2)")).getText();
   browser.compareText(getPlanName, productName1);
   }
   if (!productName3.equals("none") && !productName1.equals("")) {
   //check product
   String getPlanName = browser.findElement(By.cssSelector(".clickable > td:nth-child(3)")).getText();
   browser.compareText(getPlanName, productName1);
   }

   return this;
   }
   */
}
