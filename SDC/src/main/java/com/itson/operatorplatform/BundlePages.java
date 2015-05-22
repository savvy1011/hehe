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

public class BundlePages extends ProductPages {

  private String description;
  private String retailPrice;
  private String salePrice;
  private String taxCode;
  private String priceModel;
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
  private String bundleType;
  private String shareableAssignableSelection;
  private String giftableSelection;
  protected String[] bundleData;

  public BundlePages(Browser browser) {
    super(browser);
  }

  @FindBy(how = How.CSS, using = "div.redactor-editor")
  private WebElement addBundleDescriptionField;
  @FindBy(how = How.ID, using = "fields'pricingModel'.value1")
  private WebElement priceModelBundleSelection;
  @FindBy(how = How.ID, using = "fields'pricingModel'.value2")
  private WebElement priceModelItemSumSelection;
  @FindBy(how = How.XPATH, using = "//button[@type='submit']")
  private WebElement addBundleSaveBtn;

  @FindBy(how = How.ID, using = "fields'bundleRenewalType'.value")
  private WebElement bundleDropDownRenewal;
  @FindBy(how = How.ID, using = "fields'bundleRenewalType'.value")
  private WebElement bunledMaximumRenewalTextField;

  @FindBy(how = How.ID, using = "isBundleBillingAligned-true")
  private WebElement bunledAlignedToTheAccountCycleYesSelection;
  @FindBy(how = How.ID, using = "isBundleBillingAligned-false")
  private WebElement bunledalignedToTheAccountCycleNoSelection;
  // Behavior Bundle cycle length*
  @FindBy(how = How.ID, using = "unlimitedBundleDuration-true")
  private WebElement unlimitedBundleDuration_true;
  @FindBy(how = How.ID, using = "unlimitedBundleDuration-false")
  private WebElement unlimitedBundleDuration_false;

  @FindBy(how = How.ID, using = "fields'bundleDuration'.value")
  private WebElement bundleDurationTextField;
  @FindBy(how = How.ID, using = "fields'bundleDurationPeriod'.value")
  private WebElement dropDownBundleCycleDuration;

  @FindBy(how = How.ID, using = "isBundleProratedOnFirstCycle-true")
  private WebElement proRatedOnFirstCycle_YesSelection;
  @FindBy(how = How.ID, using = "isBundleProratedOnFirstCycle-false")
  private WebElement proRatedOnFirstCycle_NoSelection;
  @FindBy(how = How.ID, using = "fields'bundleNonPaidCycleAction'.value")
  private WebElement nonPaidCycleActionDropDownMenu;

  @FindBy(how = How.ID, using = "fields'shareTypeCompoundBundle'.value")
  private WebElement shareableAssignableDropDownCompound;

  //sku bundle items
  @FindBy(how = How.XPATH, using = "//div[@id='skuBundleItems']/div/ul/li/button")
  private WebElement skuBundleItemsAddBtn;
  @FindBy(how = How.CSS, using = "button.to-one-lookup:nth-child(3)")
  private WebElement skuBundleItemsLookUpBtn;
  @FindBy(how = How.XPATH, using = "//div[2]/div/div/table/thead/tr/th/div/div/div/i[2]")
  private WebElement skuBundleSortBtn;
  @FindBy(how = How.CSS, using = ".show-dropdown > li:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > input:nth-child(1)")
  private WebElement skuBundleSortSearchField;
  @FindBy(how = How.CSS, using = ".show-dropdown > li:nth-child(1) > div:nth-child(1) > div:nth-child(4) > button:nth-child(1)")
  private WebElement skuBundleSortFilterBtn;
  @FindBy(how = How.ID, using = "fields'quantity'.value")
  private WebElement skuBundlequantityField;
  @FindBy(how = How.CSS, using = "button.submit-button:nth-child(1)")
  private WebElement skuBundleSaveBtn;

  @FindBy(how = How.ID, using = "isGiftableCompoundBundle-true")
  private WebElement giftableYesSelectionCompound;
  @FindBy(how = How.ID, using = "isGiftableCompoundBundle-false")
  private WebElement giftableNoSelectionCompound;

  @FindBy(how = How.ID, using = "isGiftableBasicBundle-true")
  private WebElement giftableYesSelectionBasic;
  @FindBy(how = How.ID, using = "isGiftableBasicBundle-false")
  private WebElement giftableNoSelectionBasic;

  protected String[] setPlanData(String givenbundleData) {  //call parameters to create a bundle excludeing  plan bundle, error mesg for neg test 
    bundleData = givenbundleData.split(",");
    bundleType = bundleData[1].toLowerCase();
    description = bundleData[2];
    shareableAssignableSelection = bundleData[3];
    renewal = bundleData[4];
    alignedToAccountCycle = bundleData[5].toLowerCase().trim();
    planDuration = bundleData[6];
    planDurationUnit = bundleData[7];
    proRated = bundleData[8].toLowerCase();
    priceModel = bundleData[9].toLowerCase();

    retailPrice = bundleData[10];
    salePrice = bundleData[11];
    taxCode = bundleData[12];
    productName1 = bundleData[13];
    productName2 = bundleData[14];
    productName3 = bundleData[15];
    try {
      giftableSelection = bundleData[16];
    }
    catch (Exception e) {
      giftableSelection = "no";
    }

    upDate = "no";
    return bundleData;
  }

  public BundlePages setUpdate(String updateSelection) {
    upDate = updateSelection;
    return this;

  }

  public BundlePages createBundle(String productName) {
    browser.logAction("Creating new Bundle  " + productName);
    browser.logAction("Creating new Bundle tywpw  " + bundleType);
    switch (bundleType) {
      case "basic":
        createBasicBundle(productName);
        break;
      case "compound":
        createCompoundBundle(productName);
        break;
      default:
        System.err.println("***Input for Bundle is not a vaild choice " + productName);
        break;
    }
    setBillingSection();
    setAvailability();

    browser.click(addBundleSaveBtn);

    browser.sleep(5000);
    addSkuBundleItemsSection();
    //need to click save btn
    browser.sleep(500);
    browser.waitForClickableElement(By.xpath("//button[@type='submit']")).click();
    browser.click(By.cssSelector("a.close"));

    //promote 
    setPromotePlan(productName);
    String approveType;
    if (upDate.equals("update")) {
      approveType = "Update";
    }
    else {
      approveType = "Add";
    }

    //aprove plan
    setApproveProduct(productName, approveType);
    browser.sleep(1000);
    if (upDate.equals("update")) {
      //verify bundle 
      verifyingBundleCreated(productName);
    }

    return this;
  }

  public BundlePages createBasicBundle(String productName) {
    browser.logAction("Creating new BasicBundle  " + productName);
    if (!upDate.equals("update")) {
      setNewProduct("Basic Bundle");
    }
    setAddNewProductNameTextField(productName);
    checkModalTitle("Add Basic Bundle");
    setDescriptionTextField(productName);
    setTypeSectionBasic();
    setPriceSection();
    return this;
  }

  public BundlePages createCompoundBundle(String productName) {
    browser.logAction("Creating new CompundBundle  " + productName);
    setNewProduct("Compound Bundle");
    setAddNewProductNameTextField(productName);
    checkModalTitle("Add Compound Bundle");
    setDescriptionTextField(productName);
    setTypeSectionCompound();
    setBundleBehaviorSection();
    setPriceSection();
    return this;
  }

  public BundlePages addSkuBundleItemsSection() {
    if (!productName1.equals("none") && !productName1.equals("")) {
      createSkuBundleItems(productName1);
    }
    if (!productName2.equals("none") && !productName2.equals("")) {
      createSkuBundleItems(productName2);
    }
    if (!productName3.equals("none") && !productName3.equals("")) {
      createSkuBundleItems(productName3);
    }

    return this;

  }

  public BundlePages createSkuBundleItems(String productName) {
    browser.logAction("clicking add btn");
    browser.click(skuBundleItemsAddBtn);
    //click lookup btn
    browser.logAction("lookup btn");
    browser.retryingElement(skuBundleItemsLookUpBtn);
    // click sort btn
    browser.sleep(9000);
    browser.logAction("sort btn");
    browser.click(skuBundleSortBtn);
    //search
    //clicking field
    browser.logAction("clicking search field");
    browser.click(skuBundleSortSearchField);
    browser.logAction("sending search field: " + productName);
    skuBundleSortSearchField.sendKeys(productName);
    //click filter btn
    browser.logAction("Clicking filter btn");
    browser.click(skuBundleSortFilterBtn);
    //click 1st item
    browser.sleep(5000);
    browser.logAction("Clicking 1st product");
    browser.waitForClickableElement(By.xpath("//div[2]/div/div/table/tbody/tr/td")).click();
    //add Quantity
    browser.click(skuBundlequantityField);
    skuBundlequantityField.sendKeys("1");
    //click save btn
    browser.click(skuBundleSaveBtn);
    return this;
  }

  public BundlePages setDescriptionTextField(String msg) {
    browser.click(addBundleDescriptionField);
    addBundleDescriptionField.sendKeys(msg);
    return this;
  }

  public BundlePages setPriceSection() {
    browser.logAction("Selecting price modal  " + priceModel);
    if (priceModel.contains("bundle")) {
      browser.click(priceModelBundleSelection);
    }
    else {
      browser.click(priceModelItemSumSelection);
    }
    return this;
  }

  public BundlePages setTypeSectionBasic() {
    if (giftableSelection.contains("yes")) {
      browser.click(giftableYesSelectionBasic);
    }
    else {
      browser.checkRadioBtnSelected(giftableNoSelectionBasic); // No is selected by default
    }
    return this;
  }

  public BundlePages setTypeSectionCompound() {
    browser.dropDownSelectorBy(shareableAssignableDropDownCompound, shareableAssignableSelection);
    browser.logAction("Type section selecting giftable " + giftableSelection);
    if (giftableSelection.contains("yes")) {
      browser.click(giftableYesSelectionCompound);
    }
    else {
      browser.logAction("Checking giftable no is selected");
      browser.checkRadioBtnSelected(giftableNoSelectionCompound); // No is selected by default
    }
    return this;
  }

  public BundlePages setBillingSection() {
    setRetailPriceTextField(retailPrice);
    browser.dropDownSelectorBy(dropDownTaxCode, taxCode);
    return this;
  }

  public BundlePages setBundleBehaviorSection() {
    if (!renewal.equals("")) {
      setBundleRenewal();
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

  public BundlePages setBundleRenewal() {
    browser.logAction("setBundleRenewal " + renewal);
    if (renewal.contains("Renew")) { //Maximum renewal will have a number 
      browser.dropDownSelectorBy(bundleDropDownRenewal, renewal);
    }
    else {
      //Maximum Renewal
      browser.dropDownSelectorBy(bundleDropDownRenewal, "Maximum Renewals");
      browser.waitForClickableElement(bunledMaximumRenewalTextField);
      bunledMaximumRenewalTextField.sendKeys(renewal);
    }
    return this;
  }

  public BundlePages setAlignedToAccountCycle() {
    browser.logAction("Clicking Aligned to account cycle " + alignedToAccountCycle);
    browser.waitForClickableElement(bunledAlignedToTheAccountCycleYesSelection);
    switch (alignedToAccountCycle) {
      case "yes":
        browser.click(bunledAlignedToTheAccountCycleYesSelection);
        break;
      case "no":
        browser.click(bunledalignedToTheAccountCycleNoSelection);
        break;
      default:
        System.err.println("***Input for alignedToAccountCycle not Found only yes or no " + alignedToAccountCycle);
        break;
    }
    return this;
  }

  public BundlePages setPlanDuration(String planDuration, String planDurationUnit) {
    browser.logAction("Setting Data Usage");
    browser.waitForVisibilityOfElement(By.id("unlimitedPlanDuration-true"));
    browser.logAction("Bundle Duration Selction");
    if (planDuration.equals("unlimited")) {
      browser.logAction("Bundle Duration Selction slecting unlimited");
      browser.click(unlimitedBundleDuration_true);
      planDurationRadioBtn = "unlimited";
    }
    else {
      browser.logAction("Bundle Duration Selction selecting limited to " + planDuration);
      unlimitedBundleDuration_false.click();
      planDurationRadioBtn = "lasts";
      if (!planDuration.equals("")) {
        setBundleDurationTextField(planDuration);
      }
      if (!planDurationUnit.equals("")) {
        if (!planDuration.equals("")) {
          browser.dropDownSelectorBy(dropDownBundleCycleDuration, planDurationUnit);
        }
      }
    }
    return this;
  }

  public BundlePages setBundleDurationTextField(String text) {
    browser.logAction("Inputing Bundle Cycle Duration " + text);
    browser.switchTo_frameIndex();
    browser.waitForClickableElement(bundleDurationTextField);
    bundleDurationTextField.clear();
    bundleDurationTextField.sendKeys(text);
    return this;
  }

  public BundlePages setProRated() {
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

  public BundlePages verifyingBundleCreated(String productName) {

    browser.logAction("Verifying Plan Bundle :" + productName);
    verifyingProductCreated(productName, "bundle");
    //  verifyApprovedLogIn();
    verifyBundleInformation(productName);
    return this;
  }

  public BundlePages verifyBundleInformation(String productName) {

    //Appearance section
    browser.logAction("Checking Product Name: " + productName);
    browser.verifyTextField(addNewProductNameTextField, productName);

    //Type
    if (bundleType.equals("basic")) {
      verifyTypeSectionBasic();
    }
    else {
      verifyTypeSectionCompound();
    }

    //Behavior
    browser.logAction("ServicePolicy Checking DropDowns");
    if (!renewal.contains("Renew")) {
      renewal = "Maximum Renewals";
    }
    if (bundleType.equals("compound")) {
      browser.logAction("Renewal Checking DropDowns");
      browser.verifyDropDownSelected(bundleDropDownRenewal, renewal);
    }
    //Billing
    browser.logAction("Checking Retail Price");
    browser.verifyTextField(addNewProductRetailPriceTextField, retailPrice);
    if (taxCode.equals("Data Plan")) {
      dropDownTaxCode.sendKeys("Data Plan");
      // browser.dropDownSelectorBy(dropDownTaxCode, "Data Plan");
    }
    else {
      browser.logAction("Tax Code Checking DropDowns: " + taxCode);
      browser.verifyDropDownSelected(dropDownTaxCode, taxCode);
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

  public BundlePages verifyTypeSectionCompound() {
    browser.verifyDropDownSelected(shareableAssignableDropDownCompound, shareableAssignableSelection);

    browser.logAction("verifyGiftableRadioBtn giftable is   " + giftableSelection);
    if (planDurationRadioBtn.equals("unlimited")) {
      browser.logAction("checking giftable is yes");
      browser.checkRadioBtnSelected(giftableYesSelectionCompound);
    }
    else {
      browser.logAction("checking giftable is no");
      browser.checkRadioBtnSelected(giftableNoSelectionCompound);
    }

    return this;
  }

  public BundlePages verifyTypeSectionBasic() {
    browser.logAction("verifyGiftableRadioBtn giftable is   " + giftableSelection);
    if (planDurationRadioBtn.equals("unlimited")) {
      browser.logAction("clicking giftable is yes");
      browser.checkRadioBtnSelected(giftableYesSelectionBasic);
    }
    else {
      browser.logAction("checking giftable is no");
      browser.checkRadioBtnSelected(giftableNoSelectionBasic);
    }

    return this;
  }

}
