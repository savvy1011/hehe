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

public class ProductPages extends OperatorPlatformPagesAbstract {

  protected String div = "2";
  protected String givenSandbox;
  protected String categoryType;
  protected String categoryName;
  protected String assignWhen;
  protected String categoryEligibility;
  protected String planDurationRadioBtn = "none";

  public ProductPages(Browser browser) {
    super(browser);
  }

  @FindBy(how = How.XPATH, using = "//div[@id='field-defaultCategory']/div/div[2]/button[2]")
  private WebElement addNewProductNameCategoryLookUpBtn;
  @FindBy(how = How.XPATH, using = "//div[@id='field-defaultSku--longDescription']/div[2]/div/div")
  private WebElement addNewProductDescriptionTextField;
  @FindBy(how = How.ID, using = "fields'planDuration'.value")
  private WebElement addNewProductPlanDurationtextField;
  @FindBy(how = How.XPATH, using = "//input[@id='isRecurring-true']")
  private WebElement addNewProductPlanDurationIsReccurringYesSelection;
  @FindBy(how = How.XPATH, using = "//input[@id='isRecurring-false']")
  private WebElement addNewProductPlanDurationIsReccurringNoSelection;
  @FindBy(how = How.ID, using = "customDisplay-true")
  private WebElement customDisplay_true;

  @FindBy(how = How.ID, using = "fields'defaultSku__salePrice'.value")
  private WebElement addNewProductSalePriceTextField;
  @FindBy(how = How.CSS, using = ".submit-button")
  private WebElement addNewSaveBtn;
  @FindBy(how = How.ID, using = "isBillingAligned-true")
  private WebElement alignedToTheAccountCycleYesSelection;
  @FindBy(how = How.XPATH, using = "//input[@id='isBillingAligned-false']")
  private WebElement alignedToTheAccountCycleNoSelection;
  @FindBy(how = How.ID, using = "unlimitedPlanDuration-true")
  private WebElement unlimitedPlanDuration_true;
  @FindBy(how = How.ID, using = "unlimitedPlanDuration-false")
  private WebElement unlimitedPlanDuration_false;
  @FindBy(how = How.ID, using = "isProratedOnFirstCycle-true")
  private WebElement addNewProductBillingProratedOnFirstCycleYesSelection;
  @FindBy(how = How.ID, using = "isProratedOnFirstCycle-false")
  private WebElement addNewProductBillingProratedOnFirstCycleNoSelection;
  @FindBy(how = How.ID, using = "isUnlimited-false")
  private WebElement addNewProductDataUsageLimitedToSelection;
  @FindBy(how = How.ID, using = "isUnlimited-true")
  private WebElement addNewProductDataUsageUnLimitedToSelection;
  @FindBy(how = How.NAME, using = "fields['usageLimit'].value")
  private WebElement addNewProductDataUsageLimitedToSelectionTextField;
  @FindBy(how = How.CSS, using = ".ui-datepicker-current")
  private WebElement addNewProductAvailabilityNowSelection;
  @FindBy(how = How.CSS, using = ".ui-datepicker-close")
  private WebElement addNewProductAvailabilityDoneBtn;

  @FindBy(how = How.ID, using = "myModalLabel")
  private WebElement myModalLabel;

  @FindBy(how = How.XPATH, using = "//input[@id='displayDetailedUsage-true']")
  private WebElement displayDetailedUsageDataYes;
  @FindBy(how = How.XPATH, using = "//input[@id='displayDetailedUsage-false']")
  private WebElement displayDetailedUsageDataNo;

  @FindBy(how = How.ID, using = "fields'renewalCount'.value")
  private WebElement maximumRenewalTextField;

  //dropdown menus
  @FindBy(how = How.ID, using = "fields'servicePolicy'.value")
  private WebElement dropDownServicePolicy;
  @FindBy(how = How.ID, using = "fields'shareType'.value")
  private WebElement dropDownShareType;
  @FindBy(how = How.ID, using = "fields'renewalType'.value")
  private WebElement dropDownRenewal;
  @FindBy(how = How.ID, using = "fields'planDurationPeriod'.value")
  private WebElement dropDownPlanDuration;
  @FindBy(how = How.ID, using = "fields'usageDisplayType'.value")
  private WebElement dropDownUsageDisplayType;
  @FindBy(how = How.ID, using = "fields'defaultSku__fulfillmentType'.value")
  private WebElement dropDownFulfillmentType;

  @FindBy(how = How.XPATH, using = "//div[@id='field-defaultSku--skuMedia---primary']/div/button")
  private WebElement selectUploadImagebtn;
  @FindBy(how = How.NAME, using = "file")
  private WebElement uploadNewAsset;
  @FindBy(how = How.LINK_TEXT, using = "Marketing")
  private WebElement marketingTab;
  @FindBy(how = How.XPATH, using = "//div[@id='allParentCategoryXrefs']/div/ul/li/button")
  private WebElement addParentCategoriesBtn;

  //Availability Fee
  @FindBy(how = How.XPATH, using = "//button[@type='submit']")
  protected WebElement addSaveBtn;
  @FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']/div[3]/button")
  protected WebElement availabilityNowBtn;
  @FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']/div[3]/button[2]")
  protected WebElement availabilityDoneBtn;

  // Categories
  @FindBy(how = How.XPATH, using = "//button[@type='button']")
  private WebElement addCategoryBtn;
  @FindBy(how = How.LINK_TEXT, using = "Category")
  private WebElement addNewCategorySelectionCategory;
  @FindBy(how = How.LINK_TEXT, using = "Mandatory Category")
  private WebElement addNewCategorySelectionMandatoryCategory;
  @FindBy(how = How.ID, using = "fields'name'.value")
  private WebElement addNewCategoryDescriptionTextField;
  @FindBy(how = How.CSS, using = ".icon-calendar")
  private WebElement addNewCategoryCalendarBtn;
  @FindBy(how = How.XPATH, using = "//div[3]/button")
  private WebElement addNewCategoryCalendarnowBtn;
  @FindBy(how = How.XPATH, using = "//div[3]/button[2]")
  private WebElement addNewCategoryCalendarDoneBtn;
  @FindBy(how = How.ID, using = "fields'assignWhen'.value")
  private WebElement addNewCategoryAssignWhenDropDownMenu;
  @FindBy(how = How.ID, using = "fields'subscriberEligibility'.value1")
  private WebElement addNewCategorySubscriberEligibilityIndefinitely;
  @FindBy(how = How.ID, using = "fields'subscriberEligibility'.value2")
  private WebElement addNewCategorySubscriberEligibilityOncepersubscriber;
  @FindBy(how = How.ID, using = "required-true")
  private WebElement addNewCategorySubscriberReceivesAllProducts;
  @FindBy(how = How.ID, using = "required-false")
  private WebElement addNewCategorySubscriberChoosesOneProductOnly;

  @FindBy(how = How.ID, using = "servicePolicyLookup")
  private WebElement servicePolicyLookupBtn;
  @FindBy(how = How.XPATH, using = "//div[2]/div/div/table/thead/tr/th/div/div/div/i[2]")
  private WebElement servicePolicyFilterBtn;
  @FindBy(how = How.XPATH, using = "//table[@id='listGrid-inline-null-header']/thead/tr/th/div/ul/li/div/div/div/input")
  private WebElement servicePolicyFilterTextField;
  @FindBy(how = How.CSS, using = ".show-dropdown > li:nth-child(1) > div:nth-child(1) > div:nth-child(4) > button:nth-child(1)")
  private WebElement servicePolicyFilterSearchBtn;

  @FindBy(how = How.ID, using = "isGiftablePlan-true")
  private WebElement giftableYesSelection;
  @FindBy(how = How.ID, using = "isGiftablePlan-false")
  private WebElement giftableNoSelection;

  public ProductPages selectNewCategory(String type) {
    browser.logAction("Creating new Category  *selectNewCategory " + type);
    browser.click(addCategoryBtn);
    if (!type.toLowerCase().contains("mandatory")) {
      browser.logAction("Selecting   Category");
      browser.click(addNewCategorySelectionCategory);
    }
    else {
      browser.logAction("Selecting  Mandatory Category");
      browser.waitForClickableElement(By.linkText("Mandatory Category")).click();
    }
    return this;
  }

  public ProductPages categoryActiveStartDate() {
    browser.logAction("Adding Active Start Now Date");
    browser.click(addNewCategoryCalendarBtn);
    browser.click(addNewCategoryCalendarnowBtn);
    browser.click(addNewCategoryCalendarDoneBtn);

    return this;
  }

  public ProductPages categoryMandatorySection(String assignWhen, String eligibility, String product) {
    browser.logAction("Selecting Mandatory Selection: " + assignWhen);
    browser.dropDownSelectorBy(addNewCategoryAssignWhenDropDownMenu, assignWhen);
    //Subscriber Eligibility
    if (!assignWhen.toLowerCase().contains("account")) {
      if (eligibility.toLowerCase().contains("indefinitely")) {
        browser.logAction("Selecting Indefinitely ");
        browser.click(addNewCategorySubscriberEligibilityIndefinitely);
      }
      else {
        browser.logAction("Selecting once for subscriber");
        browser.click(addNewCategorySubscriberEligibilityOncepersubscriber);
      }
    }
    browser.click(addNewCategoryCalendarBtn);
    browser.click(addNewCategoryCalendarnowBtn);
    browser.click(addNewCategoryCalendarDoneBtn);
    //The account or device user
    if (product.toLowerCase().contains("all")) {
      browser.logAction("Selecting Receives all products");
      browser.click(addNewCategorySubscriberReceivesAllProducts);
    }
    else {
      browser.logAction("Chooses one product only");
      browser.click(addNewCategorySubscriberChoosesOneProductOnly);
    }
    return this;
  }

  public ProductPages createCategory(String type, String name, String givenAssignWhen, String givenEligibility, String product) {
    categoryType = type.toLowerCase();
    categoryName = name;
    assignWhen = givenAssignWhen;
    categoryEligibility = givenEligibility;

    //Description
    browser.sendKeys(addNewCategoryDescriptionTextField, name, true);
    //Active Date Range pick now
    categoryActiveStartDate();
    if (categoryType.contains("mandatory")) {
      categoryMandatorySection(assignWhen, categoryEligibility, product);
    }
    //save btn
//    if(categoryName.contains("update")){
//      browser.waitForClickableElement(By.xpath("//button[3]")).click();
//      
//    }else{
    browser.waitForClickableElement(By.xpath("//button[@type='submit']")).click();
    // browser.waitForClickableElement(By.linkText("Categories"));
    return this;
  }

  public ProductPages addProductCategory(String product) {
    browser.waitForClickableElement(By.linkText("Facets"));
    //products tab
    browser.waitForClickableElement(By.linkText("Products")).click();
    //add product btn
    browser.waitForClickableElement(By.xpath("//div[@id='allProductXrefs']/div/ul/li/button")).click();
    browser.sleep(2000);
    findAddProductCategory(product);

    return this;
  }

  public ProductPages verifyCatalogInformation() {
    browser.logAction("verifyCatalogInformation: " + categoryName);
    browser.verifyTextField(addNewCategoryDescriptionTextField, categoryName);

    if (categoryType.contains("mandatory")) {
      //check Assign When dropdown
      browser.verifyDropDownSelected(addNewCategoryAssignWhenDropDownMenu, assignWhen);
    }
    return this;
  }

  public ProductPages findAddProductCategory(String product) {
    //filter results 
    browser.waitForClickableElement(By.cssSelector("i.filter-icon.icon-filter")).click();
    browser.waitForClickableElement(By.cssSelector("input.listgrid-criteria-input")).click();
    browser.findElement(By.cssSelector("input.listgrid-criteria-input")).sendKeys(product);
    browser.sleep(5000);
    //click 1st
    browser.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td")).click();
    
   /* 
    browser.logAction("Selecting product " + product);
    String getRow = "fall";
    int rowCount = 1; //starts at 2

    while (!getRow.equals(product)) {

      getRow = browser.findElement(By.cssSelector("tr.clickable:nth-child(" + rowCount + ") > td:nth-child(1)")).getText();
      browser.logAction("Got Text " + getRow);
      if (getRow.equals(product)) {
        browser.logAction("Text Matched Clicking On Category " + product);
        browser.waitForClickableElement(By.cssSelector("tr.clickable:nth-child(" + rowCount + ") > td:nth-child(1)")).click();
        break;
      }
      else {
        browser.logAction("No Text Matched got " + getRow);
        rowCount++;
      }

    }
*/
    return this;
  }

  protected String description;
  protected String servicePolicy;
  protected String usage;
  protected String catalogSelection;
  protected String shareType;
  protected String renewal;
  protected String alignedToAccountCycle;
  protected String planDuration;
  protected String planDurationUnit;
  protected String prorated;
  protected String usageDisplayProperties;
  protected String usageDisplayPropertiesDropDown;
  protected String displayDetailedUsageData;
  protected String retailPrice;
  protected String salePrice;
  protected String taxCode;
  protected String dropDownName;
  protected String planDataUsageAmount;
  protected String accountName;
  protected String errorMessage;
  protected String upDate;
  protected String giftableSelection;

  protected String savedImageName = "none";
  protected String[] planData;

  protected String[] setPlanData(String givenplanData) {  //call parameters to create a plan excludeing  plan name, error mesg for neg test 
    planData = givenplanData.split(",");
    description = planData[1];
    servicePolicy = planData[2];
    usage = planData[3];
    catalogSelection = planData[4]; //selects catatgory
    shareType = planData[5];
    renewal = planData[6];
    alignedToAccountCycle = planData[7].toLowerCase();
    planDuration = planData[8];
    planDurationUnit = planData[9];
    prorated = planData[10].toLowerCase();
    usageDisplayProperties = planData[11].toLowerCase();
    usageDisplayPropertiesDropDown = planData[12];
    displayDetailedUsageData = planData[13].toLowerCase();
    retailPrice = planData[14];
    salePrice = planData[15];
    taxCode = planData[16];
    try {
      giftableSelection = planData[17];
    }
    catch (Exception e) {
      giftableSelection = "no";
    }

    upDate = "no";
    return planData;
  }

  public ProductPages setAddNewProductSelectionItsOnProduct() {
    browser.logAction("Clicking addNewProductSelectionItsOnProduct");
    browser.waitForClickableElement(By.partialLinkText("Plan")).click();
    browser.sleep(3000);
    // browser.waitForTextToAppear(myModalLabel, "Add Plan");     //wait for plan create modal
    return this;
  }

  public ProductPages setAddNewProductCategorySelection(String category) {
    /*
     //goto Marketing tab
     browser.findElement(By.linkText("Marketing")).click();
     browser.logAction("Clicking addNewProductNameCategoryLookUpBtn");
     browser.switchTo_frameIndex();
     browser.click(addNewProductNameCategoryLookUpBtn);
     //find category add click
     selectDefaultCategory(category);
     // browser.waitForVisibilityOfElement(By.cssSelector("button.to-one-lookup:nth-child(3)"), 1000);
     //go back to general tab
     browser.logAction("Clicking General Tab");
     browser.switchTo_frameIndex();
     */
    browser.waitForClickableElement(By.linkText("General")).click();

    return this;
  }

  public ProductPages setAddNewProductShareType(String type) {
    browser.logAction("Selecting Share types " + type);
    browser.switchTo_frameIndex();
    browser.dropDownSelectorBy(dropDownShareType, type);
    return this;
  }

  public ProductPages setAddNewProductPlanDurationtextField(String text) {
    browser.logAction("Inputing Plan Duration " + text);
    browser.switchTo_frameIndex();
    browser.waitForClickableElement(addNewProductPlanDurationtextField);
    addNewProductPlanDurationtextField.clear();
    browser.sendKeys(addNewProductPlanDurationtextField, text, true);
    return this;
  }

  public ProductPages setRenewal(String text) {
    if (text.contains("Renew")) { //Maximum renewal will have a number 
      browser.dropDownSelectorBy(dropDownRenewal, text);
    }
    else {
      //Maximum Renewal
      browser.dropDownSelectorBy(dropDownRenewal, "Maximum Renewals");
      browser.waitForClickableElement(maximumRenewalTextField);
      maximumRenewalTextField.sendKeys(text);
    }
    return this;
  }

  public ProductPages setPlanDuration(String planDuration, String planDurationUnit) {
    browser.logAction("Setting Data Usage");
    browser.waitForVisibilityOfElement(By.id("unlimitedPlanDuration-true"));
    browser.logAction("Plan Duration Selction");
    if (planDuration.equals("unlimited")) {
      browser.logAction("Plan Duration Selction slecting unlimited");
      browser.click(unlimitedPlanDuration_true);
      planDurationRadioBtn = "unlimited";
    }
    else {
      browser.logAction("Plan Duration Selction selecting limited to " + planDuration);
      unlimitedPlanDuration_false.click();
      planDurationRadioBtn = "lasts";
      if (!planDuration.equals("")) {
        setAddNewProductPlanDurationtextField(planDuration);
      }
      if (!planDurationUnit.equals("")) {
        if (!planDuration.equals("")) {
          browser.dropDownSelectorBy(dropDownPlanDuration, planDurationUnit);
        }
      }
    }
    return this;
  }

  public ProductPages setDisplayDetailedUsageData(String usageDisplayProperties, String usageDisplayPropertiesDropDown, String displayDetailedUsageData) {

    if (!usageDisplayProperties.equals("custom")) {
      browser.logAction("Selected Default");
      //  no action nedded default is already selected
    }
    else {
      browser.logAction("Selected Custom");
      customDisplay_true.click();
      //Usage Display Properties selection
      browser.logAction("Selecting Dropdown");
      browser.dropDownSelectorBy(dropDownUsageDisplayType, usageDisplayPropertiesDropDown);

      if (displayDetailedUsageData.equals("yes")) {
        browser.logAction("Display Detailed Usage Data Clicking Yes");
        browser.click(displayDetailedUsageDataYes);
      }
      else {
        browser.logAction("Display Detailed Usage Data Clicking No");
        browser.click(displayDetailedUsageDataNo);
      }
    }
    return this;
  }

  public ProductPages setBilling() {
    //Retail Price
    setRetailPriceTextField(retailPrice);
    //Sale Price
    if (!salePrice.equals("")) {
      browser.logAction("Inputing sale Price  " + salePrice);
      browser.sendKeys(addNewProductSalePriceTextField, salePrice, true);
    }
    if (!salePrice.equals("")) {
      setTaxcode(taxCode);
    }
    return this;
  }

  public ProductPages setAvailability() {
    browser.actionsArrowDown();
    browser.logAction("Selecting Availability");
    browser.click(iconCalendar);
    browser.logAction("Clicking Now");
    browser.waitForVisibilityOfElement(By.cssSelector(".ui-datepicker-current"));
    browser.click(addNewProductAvailabilityNowSelection);
    browser.logAction("Clicking Done");
    browser.click(addNewProductAvailabilityDoneBtn);
    return this;
  }

  public ProductPages setSaveBtn(String negative) {
    browser.logAction("Clicking Save Button setSaveBtn");
    browser.click(addNewSaveBtn);
    if (!negative.toLowerCase().equals("no")) {
      browser.logAction("Checking error message **1");
      String live = browser.findElementByCssSelector(".fieldError").getText();
      browser.logAction("Got Error Msg : " + live);
      if (live.contains(negative.trim())) {
        browser.compareText(live, negative);
      }
      else {
        try {
          browser.logAction("Checking error message **2");   //needed for OP-1293 Error Messages in Add New Product modal should be order has they appear
          browser.compareText(browser.findElement(By.cssSelector("span.fieldError:nth-child(3)")).getText(), negative);
        }
        catch (Exception e) {
          browser.logAction("Checking error message **3");   //needed for OP-1293 Error Messages in Add New Product modal should be order has they appear
          browser.compareText(browser.findElement(By.cssSelector("span.fieldError:nth-child(4)")).getText(), negative);
        }
      }

    }
    else {
      browser.waitForInvisibilityOfElement(By.id("myModalLabel"));
      browser.sleep(5000); // to make sure modal is gone
      browser.waitForVisibilityOfElement(By.cssSelector("button.small:nth-child(1)"));
    }
    return this;
  }

  public ProductPages setUsage(String usage) {
    browser.logAction("Setting  usage *1");
    browser.waitForVisibilityOfElement(By.id("isUnlimited-false"));
    browser.logAction("Setting  Usage *2");
    browser.waitForVisibilityOfElement(By.xpath("//div[4]/input"));
    //set Data Usage Unlimited  Limited To
    if (usage.toLowerCase().equals("unlimited")) {
      browser.logAction("Selecting UnLimited");
      browser.click(addNewProductDataUsageUnLimitedToSelection);
    }
    else {
      browser.logAction("Selecting Limited");
      browser.click(addNewProductDataUsageLimitedToSelection);
      addNewProductDataUsageLimitedToSelectionTextField.sendKeys(usage);
    }
    return this;
  }

  public ProductPages planSelection(String productName, String negative, String update, String image) throws Exception {
    upDate = update;
    browser.waitForClickableElement(addNewProductNameTextField);
    //Appearance section
    appearanceSection(productName, image);
    //check fullment type 
    browser.verifyDropDownSelected(dropDownFulfillmentType, "ItsOn Digital Delivery");

    if (!upDate.equals("yes")) {
      checkModalTitle("Add Plan");
    }
    typeSection();
    behaviorSection();
    setBilling();

    //Availability
    if (!upDate.equals("yes")) {
      setAvailability();
    }
    //Set Save Button
    setSaveBtn(negative);
    browser.retryingWaitElement(By.cssSelector(".actions > a:nth-child(2)"));
    if (negative.toLowerCase().equals("no")) {
      addParentCategories();
      verifyPlanInformation(productName);
    }
    return this;
  }

  public ProductPages appearanceSection(String productName) {

    //name
    if (!productName.equals("")) {
      setAddNewProductNameTextField(productName);
    }
    //Default Category
    if (!upDate.equals("yes")) {
      setAddNewProductCategorySelection("Plans");
    }
    browser.switchTo_frameIndex();
    //inputing description
    browser.logAction("Inputing description " + description);
    addNewProductDescriptionTextField.sendKeys(description);
    return this;
  }

  public ProductPages appearanceSection(String productName, String image) {
    image = image.toLowerCase();

    //name
    if (!productName.equals("")) {
      setAddNewProductNameTextField(productName);
    }
    /*
     //Default Category
     if (!upDate.equals("yes")) {
     setAddNewProductCategorySelection("Plans");
     }
     */
    if (image.equals("libary")) {
      setSelectUploadImagebtn(image);
    }
    //inputing description
    browser.logAction("Inputing description " + description);
    addNewProductDescriptionTextField.sendKeys(description);
    return this;
  }

  public ProductPages typeSection() {
    //Plan Configuration
    //plan type selection
    browser.logAction("Scolling down");
    browser.scrollDown();
    if (!servicePolicy.equals("")) {
      servicePoilcySelection();
      //Data Usage
      if (!usage.equals("")) {
        //Data Usage
        setUsage(usage);
      }
    }
    //Share Type
    if (!shareType.equals("")) {
      setAddNewProductShareType(shareType);
    }
    try {
      //Per Call Limit
      browser.waitForClickableElement(By.xpath("//div[6]/div[2]/label/input")).click();
      //OP-1874
      //browser.findElement(By.id("fields'perCallLimit'.value")).sendKeys("1");
    }
    catch (Exception e) {

    }
    if (giftableSelection.contains("yes")) {
      browser.click(giftableYesSelection);
    }
    else {
      browser.click(giftableNoSelection);
    }
    return this;
  }

  public ProductPages servicePoilcySelection() {
    browser.logAction("Selecting Policy from selection: " + servicePolicy);
    browser.click(servicePolicyLookupBtn);
    findServicePolicy();

    return this;
  }

  public ProductPages findServicePolicy() {
    browser.waitForClickableElement(By.id("myModalLabel"));
    browser.logAction("Selecting Serive Policy " + servicePolicy);
    browser.click(servicePolicyFilterBtn);
    servicePolicyFilterTextField.sendKeys(servicePolicy);
    browser.click(servicePolicyFilterSearchBtn);
    browser.sleep(2000);
    String getLive = browser.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td")).getText();
    browser.compareText(getLive, getLive);
    //browser.waitForText(By.xpath("//div[2]/div/div/table/tbody/tr/td"), servicePolicy);
    browser.waitForClickableElement(By.xpath("//div[2]/div/div/table/tbody/tr/td")).click();
    return this;
  }

  public ProductPages behaviorSection() {

    //Is Recurring yes or no
    if (!renewal.equals("")) {
      setRenewal(renewal);
      //Aligned to account cycle  //Is Recurring must be yes
      if (!renewal.equals("Does Not Renew")) {
        setAlignedToAccountCycle();
      }

      //Plan Duration only option if Aligned to account cycle is no
      if (!alignedToAccountCycle.equals("yes") && !alignedToAccountCycle.equals("")) {
        setPlanDuration(planDuration, planDurationUnit);
      }
      //Prorated on first cycle Aligned to account cycle must be no
      if (alignedToAccountCycle.equals("yes") && !alignedToAccountCycle.equals("")) {
        setProRated();
      }
      //Usage Display Properties
      setDisplayDetailedUsageData(usageDisplayProperties, usageDisplayPropertiesDropDown, displayDetailedUsageData);
    }
    return this;
  }

  public ProductPages setAlignedToAccountCycle() {
    browser.logAction("Clicking Aligned to account cycle " + alignedToAccountCycle);
    browser.waitForClickableElement(alignedToTheAccountCycleYesSelection);
    switch (alignedToAccountCycle) {
      case "yes":
        browser.click(alignedToTheAccountCycleYesSelection);
        break;
      case "no":
        browser.click(alignedToTheAccountCycleNoSelection);
        break;
      default:
        System.err.println("***Input for alignedToAccountCycle not Found only yes or no" + alignedToAccountCycle);
        break;
    }
    return this;
  }

  public ProductPages setProRated() {
    browser.logAction("Clicking proated  " + prorated);
    browser.waitForClickableElement(addNewProductBillingProratedOnFirstCycleYesSelection);
    switch (prorated) {
      case "yes":
        browser.click(addNewProductBillingProratedOnFirstCycleYesSelection);
        break;
      case "no":
        browser.click(addNewProductBillingProratedOnFirstCycleNoSelection);
        break;
      default:
        System.err.println("***Input for alignedToAccountCycle not Found only yes or no" + prorated);
        break;
    }
    return this;
  }

  public ProductPages verifyPlanInformation(String productName) {

    //Appearance section
    browser.logAction("Checking Product Name: " + productName);
    browser.verifyTextField(addNewProductNameTextField, productName);
    checkImageName();

    verifyPlanRadioBtn();

    //Behavior
    //Type
    verifyGiftableRadioBtn();
    browser.logAction("ServicePolicy Checking DropDowns");
    String livesp = browser.findElementById("servicePolicySelected").getText();
    browser.compareText(livesp, servicePolicy);
    //browser.verifyDropDownSelected(dropDownServicePolicy, servicePolicy);
    if (!renewal.contains("Renew")) {
      renewal = "Maximum Renewals";
    }
    browser.logAction("Renewal Checking DropDowns");
    browser.verifyDropDownSelected(dropDownRenewal, renewal);
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
    return this;
  }

  public ProductPages verifyPlanRadioBtn() {
    browser.logAction("verifyPlanRadioBtn planDurationRadioBtn is   " + planDurationRadioBtn);
    if (!planDurationRadioBtn.equals("none")) {
      if (planDurationRadioBtn.equals("unlimited")) {
        browser.logAction("verifyPlanRadioBtn planDurationRadioBtn is true  ");
        browser.checkRadioBtnSelected(unlimitedPlanDuration_true);
      }
      else {
        browser.logAction("verifyPlanRadioBtn is false");
        browser.checkRadioBtnSelected(unlimitedPlanDuration_false);
      }
    }

    return this;
  }

  public ProductPages verifyGiftableRadioBtn() {
    browser.logAction("verifyGiftableRadioBtn giftable is   " + giftableSelection);
    if (planDurationRadioBtn.equals("unlimited")) {
      browser.logAction("checking giftable is yes");
      browser.checkRadioBtnSelected(giftableYesSelection);
    }
    else {
      browser.logAction("checking giftable is no");
      browser.checkRadioBtnSelected(giftableNoSelection);
    }

    return this;
  }

  public boolean findSandBox() {
    browser.logAction("Check if other sandbox is there");
    String sandBoxTitle = "fall";
    boolean result = false;
    int attempts = 0;
    givenSandbox = "Automation";
    int row = 2; //starts at 2
    while (!sandBoxTitle.contains(givenSandbox) && !sandBoxTitle.contains("Previously") && attempts < 4) {
      try {
        browser.logAction("sandBox checking row: " + row);
        browser.logAction("**1 Attempts: " + attempts);

        sandBoxTitle = browser.findElement(By.xpath("//html/body/div[1]/div/div/section/div[" + row + "]/div/div[2]/label")).getText();
        browser.logAction("Got sandBox title: " + sandBoxTitle);
        if (!sandBoxTitle.contains(givenSandbox)) {
          if (sandBoxTitle.contains(div)) {
            browser.logAction("No More tables table");
            setSyncSandBoxBtn();
            row = 2;
          }
          else {
            if (sandBoxTitle.contains("Previously")) { //if Previously reached last table
              setSyncSandBoxBtn();
              row = 2;
            }
            else {
              browser.logAction("Moving next table");
              row++;
            }
          }
        }

      }
      catch (Exception e) {
        setSyncSandBoxBtn();
        row = 2;
      }
      attempts++;
    }
    if (sandBoxTitle.contains(givenSandbox)) {
      div = Integer.toString(row);
      result = true;
    }

    return result;
  }

  public ProductPages selectDefaultCategory(String category) {
    browser.logAction("Selecting Category " + category);
    String getRow = "fall";
    int rowCount = 1; //starts at 2
    //browser.findElementByXPath("//table[@id='listGrid-to-one-defaultCategory-header']/thead/tr/th/div").click();
    // browser.findElementByXPath("//div[2]/div/div/table/tbody/tr[7]/td").click();

    while (!getRow.equals(category)) {

      getRow = browser.findElement(By.xpath("//div[2]/div/div/table/tbody/tr[" + rowCount + "]/td")).getText();
      browser.logAction("Got Text " + getRow);
      if (getRow.equals(category)) {
        browser.logAction("Text Matched Clicking On Category " + category);
        browser.waitForClickableElement(By.xpath("//div[2]/div/div/table/tbody/tr[" + rowCount + "]/td")).click();
        break;
      }
      else {
        browser.logAction("No Text Matched got " + getRow);
        rowCount++;
      }

    }

    return this;
  }

  public ProductPages findParentCategory(String category) {
    browser.logAction("Selecting Category " + category);
    String getRow = "fall";
    int rowCount = 1; //starts at 2

    while (!getRow.contains(category)) {

      getRow = browser.findElement(By.xpath("//tr[" + rowCount + "]/td")).getText();
      if (getRow.contains(category)) {
        browser.logAction("Text Matched Clicking On Category " + category);
        browser.waitForClickableElement(By.xpath("//tr[" + rowCount + "]/td")).click();
        break;
      }
      else {
        browser.logAction("No Text Matched got " + getRow);
        rowCount++;
      }

    }
    return this;
  }

  public ProductPages createNewPlan(String productName, String negative, String update) throws Exception {

    //goto new plan 
    //Create Plan
    String promoteType;
    setNewProduct("Plan");
    planSelection(productName, negative, update, "none");

    if (negative.toLowerCase().equals("no") || negative.equals("")) {

      if (update.equals("yes")) {
        promoteType = "Update";
      }
      else {
        promoteType = "Add";
      }
      //Promote Plan
      setPromotePlan(productName);
      //Approve plan
      setApproveProduct(productName, promoteType);
      //check plan created
      verifyingPlanCreated(productName);
    }
    return this;
  }

  public ProductPages addParentCategories() {
    if (catalogSelection.equals("Plans")) {
      browser.logAction("Adding Parent Categories");
      //click marketing tab
      browser.click(marketingTab);
      browser.click(addParentCategoriesBtn);
      //find plans
      findParentCategory(catalogSelection);
      browser.sleep(500);
      //click save btn 
      browser.waitForClickableElement(By.xpath("//button[@type='submit']")).click();
      //wait for x
      browser.waitForInvisibilityOfElement(By.linkText("x"));
      // click genral tab
      browser.waitForClickableElement(By.linkText("General")).click();
    }
    return this;
  }

  public ProductPages deleteProduct(String productName) {
    browser.logAction("*****Starting Deleting Plan****");
    browser.logAction("Deleting: " + productName);
    //find plan 
    setFindProduct(productName);
    //Delete Plan
    setDeletePlan(productName);
    verifyingPlanDeleted(productName);
    return this;
  }

  public ProductPages verifyingPlanCreated(String productName) {

    browser.logAction("Verifying Plan Created :" + productName);
    verifyingProductCreated(productName, "plans");
    verifyApprovedLogIn();
    setFindProduct(productName);
    verifyPlanInformation(productName);

    return this;
  }

  public ProductPages verifyingPlanDeleted(String productName) {

    browser.logAction("Verifying Plan Deleted :" + productName);
    //Goto to Catalog PLAN SECTION
    //click catalog
    setSideNavCatalog();
    //pick products
    setCatalogProductSelection();
    //search for plan
    setSearch(productName);
    //check plan deletd
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
    browser.logAction("Verifying Plan Deleted With Differnt User :" + productName);
    //check with differnt user
    verifyApprovedLogIn();
    //pick products
    setCatalogProductSelection();
    //search for plan
    setSearch(productName);
    //check plan deletd
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
    return this;
  }

  public ProductPages verifyingPlanNotShowing(String productName) { //verfiey with different user plan is not showing
    //check with differnt user
    verifyApprovedLogIn();
    //pick products
    setCatalogProductSelection();
    //search for plan
    setSearch(productName);
    //check plan deletd
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
    return this;
  }

  public ProductPages setSelectUploadImagebtn(String type) {

    browser.logAction("Clicking selectUploadImagebtn");
    browser.click(selectUploadImagebtn);
    //wait for  Select Asset 
    browser.waitfortext("myModalLabel", "Select Asset");

    if (type.equalsIgnoreCase("libary")) { //from libary 
      browser.logAction("**1 Picking new image");
      WebElement firstRow = browser.findElementByXPath("//div[2]/div/div/table/tbody/tr/td[2]");
      savedImageName = firstRow.getText();
      browser.logAction("<<<<>><>>1  got Image Link " + savedImageName);
      firstRow.click();
    }
    else { //upload image //WIP
      browser.logAction("new");
      browser.waitForClickableElement(By.xpath("(//button[@type='button'])[9]")).click();
      browser.findElement(By.name("file")).click();
      browser.findElement(By.name("file")).sendKeys(".../Automation/jmeter/functional_operatorPlatform/WebOperatorPlatform/test.jpg");

    }
    checkImageName();
    return this;
  }

  public ProductPages checkImageName() {
    browser.logAction("******+-+-+-+-  savedImageName is " + savedImageName);
    if (!savedImageName.equals("none")) {
      browser.logAction("Checking Image Name : " + savedImageName);
      String getSrc = browser.findElement(By.className("thumbnail")).getAttribute("src");
      browser.compareText(getSrc, savedImageName);
    }
    return this;
  }

  public ProductPages planCleanUp(String adminUser, String adminPass, String productName) {
    setLogOut();
    lognIn(adminUser, adminPass);
    deleteProduct(productName);
    return this;
  }

}
