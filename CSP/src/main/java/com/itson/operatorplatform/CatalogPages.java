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

public class CatalogPages extends OperatorPlatformPagesAbstract {

  protected String div = "2";
  protected String givenSandbox;
  protected String categoryType;
  protected String categoryName;
  protected String assignWhen;
  protected String categoryEligibility;
  protected String planDurationRadioBtn = "none";

  public CatalogPages(Browser browser) {
    super(browser);
  }

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

  public CatalogPages selectNewCategory(String type) {
    browser.logAction("Creating new Category  *selectNewCategory " + type);
    browser.click(addCategoryBtn);
    browser.waitForTextToAppear(myModalLabel, "Add Category");
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

  public CatalogPages categoryActiveStartDate() {
    browser.logAction("Adding Active Start Now Date");
    browser.click(addNewCategoryCalendarBtn);
    browser.click(addNewCategoryCalendarnowBtn);
    browser.click(addNewCategoryCalendarDoneBtn);

    return this;
  }

  public CatalogPages categoryMandatorySection(String assignWhen, String eligibility, String product) {
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

  public CatalogPages createCategory(String type, String name, String givenAssignWhen, String givenEligibility, String product, String update) {
    categoryType = type.toLowerCase();
    categoryName = name;
    assignWhen = givenAssignWhen;
    categoryEligibility = givenEligibility;
    String title = "Add Category";
    if (categoryType.contains("mandatory")) {
      title = "Add Mandatory Category";
    }
    if (!update.toLowerCase().equals("yes")) {
      checkModalTitle(title);
    }

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

  public CatalogPages verifyingCategoryCreated() {
    browser.logAction("Verifying Category Created :" + categoryName);
    //Goto to Catalog PLAN SECTION
    //click catalog
    verifyingProductCreated(categoryName, "category");
    return this;
  }

  public CatalogPages addProductCategory() {
    browser.waitForClickableElement(By.linkText("Facets"));
    //products tab
    browser.waitForClickableElement(By.linkText("Products")).click();
    //add product btn
    browser.waitForClickableElement(By.xpath("//div[@id='allProductXrefs']/div/ul/li/button")).click();
    findAddProductCategory();

    return this;
  }

  public CatalogPages deleteCategory(String name) {
    browser.logAction("*****Starting Deleting Category****");
    browser.logAction("Deleting: " + name);
    //find plan 
    setFindCategory(name);
    //Delete Plan
    setDeletePlan(name);

    verifyingCategoryDeleted(name);
    return this;
  }

  public CatalogPages setFindCategory(String categoryName) {
    setSearch(categoryName);
    //find plan
    browser.logAction("Selecting " + categoryName);
    // pick product
    browser.retryingElement(By.linkText(categoryName));
    return this;
  }

  public CatalogPages verifyCatalogInformation() {
    browser.logAction("verifyCatalogInformation: " + categoryName);
    browser.verifyTextField(addNewCategoryDescriptionTextField, categoryName);

    if (categoryType.contains("mandatory")) {
      //check Assign When dropdown
      browser.verifyDropDownSelected(addNewCategoryAssignWhenDropDownMenu, assignWhen);
    }
    return this;
  }

  public CatalogPages findAddProductCategory() {
    String product = "DoNotDelete";
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

    return this;
  }

  public CatalogPages verifyingCategoryDeleted(String categoryName) {

    browser.logAction("Verifying Category Deleted :" + categoryName);
    browser.waitForClickableElement(By.cssSelector("h5")).click();
    browser.waitForClickableElement(By.linkText("Categories")).click();

    //search for plan
    setSearch(categoryName);
    //check plan deletd
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
    browser.logAction("Verifying Category Deleted With Differnt User :" + categoryName);
    //check with differnt user
    verifyApprovedLogIn();
    //search for plan
    setSearch(categoryName);
    //check plan deletd
    browser.logAction("Check that No records found");
    browser.retryingElement(noResults);
            
    return this;
  }

}
