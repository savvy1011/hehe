/**
 * locators and methods used in all Operator Platform such ha login and menu
 *
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.itson.servicedesigncenter.Browser;
import org.testng.Assert;

public abstract class OperatorPlatformPagesAbstract {

  protected Browser browser;
  protected String view;
  protected String partnerName;

  public OperatorPlatformPagesAbstract(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
  }
//login
  @FindBy(how = How.ID, using = "username")
  private WebElement userNameField;
  @FindBy(how = How.ID, using = "password")
  private WebElement passwordFiled;
  @FindBy(how = How.CSS, using = ".button")
  private WebElement signInBtn;
  @FindBy(how = How.XPATH, using = "//nav[@id='sideMenu']/ul/li[contains(.,'Customer Care')]/div/h5")
  private WebElement sideNavCustomerCare;
  @FindBy(how = How.LINK_TEXT, using = "Gift Card Account")
  private WebElement sideNavCustomerCareGiftCardAccount;
  @FindBy(how = How.XPATH, using = "//nav[@id='sideMenu']/ul/li[contains(.,'Site Updates')]/div/h5")
  private WebElement sideNavSiteUpdate;
  @FindBy(how = How.LINK_TEXT, using = "My Changes")
  private WebElement sideNavSiteUpdateMyChanges;
  @FindBy(how = How.CSS, using = "h5")
  private WebElement sideNavCatalog;
  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Categories')]")
  private WebElement sideNavCatalogCategories;
  @FindBy(how = How.LINK_TEXT, using = "Approvals")
  private WebElement sideNavApproval;

  //@FindBy(how = How.LINK_TEXT, using = "Logout")
  //private WebElement logOutBtn;
  @FindBy(how = How.CSS, using = ".alert-box")
  public WebElement errorMessageWrongUserNamePassword;

  @FindBy(how = How.CSS, using = "input[type=\"search\"]")
  private WebElement productsSearch;
  @FindBy(how = How.XPATH, using = "//form/div/div[2]/a")
  private WebElement productsSearchBtn;
  @FindBy(how = How.XPATH, using = "//button[@type='button']")
  private WebElement syncSandBoxBtn;
  @FindBy(how = How.CSS, using = ".workflow-promote")
  private WebElement promoteBtn;
  @FindBy(how = How.CSS, using = ".workflow-confirm-approve")
  private WebElement promoteConfirmBtn;
  @FindBy(how = How.XPATH, using = "//button[2]")
  private WebElement productsCatalogDeleteBtn;
  @FindBy(how = How.CSS, using = ".workflow-approve")
  private WebElement approvalsApproveBtn;
  @FindBy(how = How.CSS, using = ".workflow-confirm-approve")
  private WebElement approveBtnModal;
  @FindBy(how = How.CSS, using = ".list-grid-no-results")
  protected WebElement noResults;
  @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Products")
  private WebElement catalogProductSelection;

  //createproduct 
  @FindBy(how = How.ID, using = "fields'defaultSku__name'.value")
  protected WebElement addNewProductNameTextField;
  @FindBy(how = How.ID, using = "fields'defaultSku__retailPrice'.value")
  protected WebElement addNewProductRetailPriceTextField;
  @FindBy(how = How.ID, using = "fields'defaultSku__taxCode'.value")
  protected WebElement dropDownTaxCode;
  @FindBy(how = How.CLASS_NAME, using = "icon-calendar")
  protected WebElement iconCalendar;
  @FindBy(how = How.XPATH, using = "//button[@type='button']")
  private WebElement addProductBtn;
  @FindBy(how = How.ID, using = "myModalLabel")
  protected WebElement myModalLabel;

  public OperatorPlatformPagesAbstract setSideNavCatalog() {
    browser.logAction("Clicking Catalog On Side Nav");
    browser.scrollUp();
    browser.click(sideNavCatalog);
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavCatalogCategories() {
    browser.logAction("Clicking Catalog On sideNavCatalogCategories *setSideNavCatalogCategories");
    browser.click(sideNavCatalogCategories);
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavApproval() {
    browser.logAction("Clicking Approvals On Side Nav");
    browser.switchTo_frameIndex();
    browser.click(sideNavApproval);
    return this;
  }

  public OperatorPlatformPagesAbstract lognIn(String username, String password) {
    browser.logAction("Inputing User " + username);
    userNameField.clear();
    userNameField.sendKeys(username);
    browser.logAction("Inputing Password " + password);
    passwordFiled.clear();
    passwordFiled.sendKeys(password);
    browser.logAction("Clicking Signin Button");
    browser.click(signInBtn);
    //check if logged in 
    browser.waitForClickableElement(sideNavCustomerCare);
    return this;
  }
  public OperatorPlatformPagesAbstract checkPartnerName(String givenPartnerName) {
    partnerName = givenPartnerName;
    //check partner
    String getPartnerName = browser.findElementByCssSelector("a > span").getText();
    browser.logAction("Got PartnerName: "+getPartnerName);
    browser.compareText(getPartnerName, givenPartnerName);
    return this;
  }

  public OperatorPlatformPagesAbstract lognInTest(String username, String password) { //for log in test
    browser.logAction("Inputing User " + username);
    userNameField.clear();
    userNameField.sendKeys(username);
    browser.logAction("Inputing Password " + password);
    passwordFiled.clear();
    passwordFiled.sendKeys(password);
    browser.logAction("Clicking Signin Button");
    browser.click(signInBtn);
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavCustomerCare() {
    browser.logAction("Clicking CustomerCare On Side Nav");
    browser.waitForClickableElement(sideNavCustomerCare);
    browser.click(sideNavCustomerCare);
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavCustomerCareGiftCardAccount() {
    browser.logAction("Clicking sideNavCustomerCareGiftCardAccount");
    browser.click(sideNavCustomerCareGiftCardAccount);
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavSiteUpdate() {
    browser.logAction("Clicking sideNavSiteUpdate");
    try {
      browser.click(sideNavSiteUpdate);
    }
    catch (Exception e) {
      browser.logAction("*catch Clicking sideNavSiteUpdate");

      browser.waitForClickableElement(By.xpath("//nav[@id='sideMenu']/ul/li[6]/div/h5")).click();

    }
    return this;
  }

  public OperatorPlatformPagesAbstract setSideNavSiteUpdateMyChanges() {
    browser.logAction("Clicking SideNavSiteUpdateMyChanges");
    browser.waitForVisibilityOfElement(By.linkText("My Changes"));
    browser.click(sideNavSiteUpdateMyChanges);
    return this;
  }

  public OperatorPlatformPagesAbstract setLogOut() {
    browser.switchTo_frameIndex();
    browser.logAction("Logging Out");
    browser.logOutReplacement();
    browser.waitForVisibilityOfElement(By.id("username"));
    return this;
  }

  public OperatorPlatformPagesAbstract checkErrorMsg() {
    browser.logAction("Checking Error Message");
    browser.waitForTextToAppear(errorMessageWrongUserNamePassword, "Invalid username / password combination.");
    return this;
  }

  public OperatorPlatformPagesAbstract checkLogin(String accountName, String password) {
    //check log out
    setLogOut();
    //log back in
    lognIn(accountName, password);
    browser.waitForClickableElement(By.cssSelector("li.blc-module:nth-child(1) > div:nth-child(1) > h5:nth-child(1)"));
    return this;
  }

  public OperatorPlatformPagesAbstract verifyApprovedLogIn() {
//    Config config = new Config();
//    String accountName = config.getVerifyUsername();
//    String password = config.getVerifyPassword();
    checkLogin("verify", "SOASTA650");
    return this;
  }

  public OperatorPlatformPagesAbstract setSearch(String productName) {
    browser.waitForVisibilityOfElement(By.cssSelector("input[type=\"search\"]"));
    browser.logAction("Searching  " + productName);
    productsSearch.sendKeys(productName);
    browser.logAction("Clicking search btn");
    browser.click(productsSearchBtn);
    return this;
  }

  public Boolean verifyingProductApproved() {
    boolean result = true;
    view = browser.getSdcView();
    browser.sleep(3500);
    if (!view.equals("sdc")) {
      browser.logAction("Checking Product Is Approved with no tool tip");
      result = false;
      int attempts = 1;
      while (attempts < 53) {
        try {
          browser.waitForClickableElement(By.xpath("//span/i"));
          browser.sleep(1080);
          browser.refresh();
          attempts++;
        }
        catch (Exception e) {
          browser.logAction("Checked No tool tip ***Done Product Approved");
          result = true;
          break;
        }
      }
      Assert.assertEquals(result, true);
    }
    return result;
  }

  public OperatorPlatformPagesAbstract setDeletePlan(String productName) {
    //click delete
    browser.waitForVisibilityOfElement(By.linkText("Marketing"));
    browser.logAction("Clicking delete btn");
    browser.click(productsCatalogDeleteBtn);
    browser.waitForClickableElement(By.xpath("//a[contains(@href, '/op-admin/product')]")); //wait for porducts section
    //promote delete
    setPromotePlan(productName);
    //goto approvals
    setApproveProduct(productName, "Delete");
    return this;
  }

  public boolean setPromotePlan(String productName) {
    browser.sleep(2000); //need to wait to be in sync
    browser.switchTo_frameIndex();
    browser.scrollUp();
    boolean result = false;
    setSideNavSiteUpdate();
    setSideNavSiteUpdateMyChanges();
    browser.waitForVisibilityOfElement(By.cssSelector(".workflow-promote"));
    browser.logAction("Selecting  " + productName);
    // pick row
    int rowCount = 1;
    int attempts = 1;
    String rowName;
    while (attempts < 25) {
      try {
        rowName = browser.findElement(By.xpath("/html/body/div[1]/div/div/section/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[" + rowCount + "]/td[2]")).getText();
        browser.logAction("Got This " + rowName);
        if (rowName.equals(productName)) {
          browser.logAction("Name Matched");
          browser.sleep(8000);
          browser.findElement(By.xpath("/html/body/div[1]/div/div/section/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[" + rowCount + "]/td[2]")).click();
          result = true;
          break;
        }
        else {
          browser.logAction("Name Not Matched");
          rowCount++;
        }
      }
      catch (Exception e) {
        setSyncSandBoxBtn();
        rowCount = 1;
      }
      attempts++;
    }
    //click promote
    browser.logAction("Clicking Promote Btn");
    String getSelectedProductName = browser.findElementByCssSelector(".selected > td:nth-child(2)").getText();
    browser.logAction("Got Selected Name: " + getSelectedProductName);
    browser.compareText(getSelectedProductName, productName);
    browser.click(promoteBtn);
    browser.waitForClickableElement(By.id("workflow-action-all"));
    browser.logAction("Clicking Confirm Promote Btn");
    browser.waitForVisibilityOfElement(By.cssSelector(".workflow-promote"));
    promoteConfirmBtn.click();
    browser.waitForInvisibilityOfElement(By.id("workflow-action-all")); //wait for modal to disappear
    //browser.sleep(15000); //need to wait to be in sync
    return result;
  }

  public OperatorPlatformPagesAbstract setSyncSandBoxBtn() {
    browser.logAction("Clicking syncSandBoxBtn");
    browser.switchTo_frameIndex();
    browser.click(syncSandBoxBtn);
    browser.waitForClickableElement(By.cssSelector("a.close")).click();
    return this;
  }

  public OperatorPlatformPagesAbstract setApproveProduct(String productName, String type) {
    browser.sleep(18000); //need to wait to be in sync
    //approve
    setSideNavApproval();
    //sync sandbox
    setSyncSandBoxBtn();
    findProductApproval(productName, type);
    browser.scrollDown();
    String getSelectedProductName = browser.findElementByCssSelector(".selected > td:nth-child(1)").getText();
    browser.logAction("Got Selected Name: " + getSelectedProductName);
    browser.compareText(getSelectedProductName, productName);
    setApprovalsApproveBtn();
    browser.waitForClickableElement(By.id("deployTime-future")); //wait for Items to Approve modal
    browser.logAction("Clicking Approvals btn in pop up ");
    browser.click(approveBtnModal);
    browser.waitForInvisibilityOfElement(By.id("deployTime-future"));

    return this;
  }

  public boolean findProductApproval(String productName, String type) {
    browser.switchTo_frameIndex();
    browser.scrollUp();
    browser.logAction("Finding Plan " + productName);
    boolean result = false;
    //findSandBox();
    int rowCount = 1;
    int attempts = 1;
    String rowName;
    String rowMessage;
    String rowLocator;

    while (attempts < 35) {
      try {

        rowLocator = "//tr[" + rowCount + "]/td";
        if (rowCount == 1) {
          rowName = browser.findElement(By.xpath("//td")).getText();

        }
        else {
          rowName = browser.findElement(By.xpath(rowLocator)).getText();
        }
        rowMessage = browser.findElement(By.xpath("/html/body/div[1]/div/div/section/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[" + rowCount + "]/td[2]")).getText();

        browser.logAction("Got This name " + rowName);
        browser.logAction("Got This  Message  " + rowMessage);

        if (rowName.contains(productName) && rowName.contains(type)) {
          if (!rowMessage.toLowerCase().contains("progress")) {

            browser.logAction("Name Matched");
            if (rowCount == 1) {
              browser.waitForClickableElement(By.xpath("//td")).click();
            }
            else {
              browser.waitForClickableElement(By.xpath(rowLocator)).click();
            }
            browser.sleep(1500);
            result = true;
            break;
          }
          else {
            browser.logAction("*** Promote still in in progress ***");
            //setSyncSandBoxBtn();
            sdcViewRefreash();
            rowCount = 1;
            break;
          }
        }
        else {
          browser.logAction("Name Not Matched in row");
          rowCount++;
        }

      }
      catch (Exception e) {
        browser.logAction("In catch attempts : " + attempts);
        sdcViewRefreash();
        rowCount = 1;
      }
      attempts++;
    }
    Assert.assertEquals(result, true);
    return result;
  }

  public OperatorPlatformPagesAbstract setApprovalsApproveBtn() {
    browser.logAction("Clicking Approval btn 1st one");
    browser.click(approvalsApproveBtn);
    return this;
  }

  public OperatorPlatformPagesAbstract sdcViewRefreash() {
    view = browser.getSdcView();
    if (view.equals("sdc")) {
      setSyncSandBoxBtn();
    }
    else {
      browser.refresh();
    }

    return this;
  }

  public OperatorPlatformPagesAbstract verifyingProductCreated(String productName, String productType) {

    browser.sleep(16000);
    browser.logAction("Verifying Product Created :" + productName);
    //Goto to Catalog PLAN SECTION
    //click catalog
    setSideNavCatalog();
    //pick section
    switch (productType) {
      case "plans":
        setCatalogProductSelection();
        break;
      case "category":
        setSideNavCatalogCategories();
        break;
      case "bundle":
        setCatalogProductSelection();
        break;
      case "fee":
        setCatalogProductSelection();
        break;
      default:
        System.err.println("***Input not Found, given:   " + productType);
        break;
    }
    //search for plan
    setSearch(productName);
    //check plan
    browser.logAction("Checking " + productType + " " + productName);
    browser.sleep(5500);
    browser.waitForClickableElement(By.linkText(productName));
    verifyingProductApproved();
//    browser.logAction("Verifying Product Created With Differnt User :" + productName);
//    //check with differnt user
//    verifyApprovedLogIn();
//    if (productType.equals("plans") || productType.equals("bundles")) {
//      browser.sleep(8000);
//      //pick products
//      setCatalogProductSelection();
//    }
//    //search for product
//    setSearch(productName);
//    verifyingProductApproved();
//    browser.sleep(500);
    browser.waitForClickableElement(By.linkText(productName)).click();
    return this;
  }

  public OperatorPlatformPagesAbstract setCatalogProductSelection() {
    browser.logAction("Clicking sideNav For Products");
    browser.click(catalogProductSelection);
    browser.switchTo_frameIndex();
    browser.waitForClickableElement(By.xpath("//a[contains(@href, '/op-admin/product')]")); //wait for porducts section
    return this;
  }

  public OperatorPlatformPagesAbstract setAddNewProductNameTextField(String text) {
    browser.logAction("Inputing in add new product name text field " + text);
    addNewProductNameTextField.clear();
    addNewProductNameTextField.sendKeys(text);
    return this;
  }

  public OperatorPlatformPagesAbstract setRetailPriceTextField(String retailPrice) {
    browser.logAction("Inputing Retail Price  " + retailPrice);
    browser.sendKeys(addNewProductRetailPriceTextField, retailPrice, true);
    return this;
  }

  public OperatorPlatformPagesAbstract setTaxcode(String taxCode) {
    int value = 0;
    if (!taxCode.equals("")) {
      switch (taxCode) {
        case "Data Plan":
          value = 8; //planData plan is not be selected
          break;
        case "Voice Plan":
          value = 0;
          break;
        case "Text Plan":
          value = 1;
          break;
        case "Line Based Fees":
          value = 2;
          break;
        case "Equipment":
          value = 3;
          break;
        case "Not Applicable":
          value = 4;
          break;
        case "Tax Category 1":
          value = 5;
          break;

      }
      browser.logAction("Inputing Tax Code with index " + value);
      browser.dropDownSelectorValueBy(dropDownTaxCode, value, taxCode);
    }

    return this;
  }

  public OperatorPlatformPagesAbstract setNewProduct(String productType) {
    browser.logAction("Clicking new product " + productType);
    //browser.switchTo_frameIndex(view);
    //Goto to create plan
    setCatalogProductSelection();
    setAddProductBtn();
    browser.waitForTextToAppear(myModalLabel, "Add Product");
    browser.waitForClickableElement(By.linkText(productType)).click();
    return this;
  }

  public OperatorPlatformPagesAbstract checkModalTitle(String title) {
    browser.logAction("Checking Title " + title);
    browser.waitForClickableElement(myModalLabel);
    //check title
    String getProductTitle = myModalLabel.getText();
    browser.compareText(getProductTitle, title);
    return this;
  }

  public OperatorPlatformPagesAbstract setAddProductBtn() {
    browser.logAction("Clicking addProductBtn");
    browser.click(addProductBtn);
    return this;
  }

  public OperatorPlatformPagesAbstract sdcViewWait() {
    if (view.equals("sdc")) {
      browser.sleep(20000);
    }
    return this;
  }
  public OperatorPlatformPagesAbstract setFindProduct(String productName) {
    //goto catalog section
    setCatalogProductSelection();
    setSearch(productName);
    //find plan
    browser.logAction("Selecting " + productName);
    // pick product
    browser.retryingElement(By.linkText(productName));

    return this;
  }
}
