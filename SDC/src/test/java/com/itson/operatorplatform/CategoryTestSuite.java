package com.itson.operatorplatform;

/*
 /**Update Plan
 *
 * @author gurtejphangureh
 */
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class CategoryTestSuite extends AbstractClass {

  protected String updatedProductName;
  protected String savedProductName;
  protected String categoryName = "bhjnjfdjkl";
  protected String categoryMandatoryName;

  protected String type;
  protected String assignWhen;
  protected String eligibility;
  protected String receive;

  protected String typeUpdate;
  protected String assignWhenUpdate;
  protected String eligibilityUpdate;
  protected String receiveUpdate;
  protected String savedData;
  protected String addProduct;
  protected String section = "category";


  @Factory(dataProvider = "category")
  public CategoryTestSuite(String giventype, String givenassignWhen, String giveneligibility, String givenreceive, String updategiventype, String updategivenassignWhen,
          String updategiveneligibility, String updategivenreceive, String updateproduct) {
    this.type = giventype;
    this.assignWhen = givenassignWhen;
    this.eligibility = giveneligibility;
    this.receive = givenreceive;
    this.typeUpdate = updategiventype;
    this.assignWhenUpdate = updategivenassignWhen;
    this.eligibilityUpdate = updategiveneligibility;
    this.receiveUpdate = updategivenreceive;
    this.addProduct = updateproduct;
  }

  @Test(priority = 1, groups = {"broadleaf", "CategoryTestSuite"})
  public void createCategory() throws Exception {
    browser.logAction("***Started createCategory");
    categoryName = "category" + browser.randomName();
    browser.reportLinkLog(type + assignWhen + eligibility + receive + typeUpdate + assignWhenUpdate + eligibilityUpdate + receiveUpdate + categoryName);
    adminUser = adminUser + "+" + section;
    logInPages.lognIn(adminUser, adminPass);
    browser.logAction("got this: " + categoryName + "createCategory");
    catalogPages.selectNewCategory(type);
    catalogPages.createCategory(type, categoryName, assignWhen, eligibility, receive, "no");
    catalogPages.setPromotePlan(categoryName);
    catalogPages.setApproveProduct(categoryName, "Add");
    browser.sleep(15500); //OP-1325

  }

  @Test(priority = 2, groups = {"broadleaf", "CategoryTestSuite"})
  public void verifyingCategoryCreated() throws Exception {
    browser.reportLinkLog(type + assignWhen + eligibility + receive + typeUpdate + assignWhenUpdate + eligibilityUpdate + receiveUpdate + categoryName);
    catalogPages.verifyingCategoryCreated()
            .verifyCatalogInformation();
  }

  @Test(priority = 3, groups = {"broadleaf", "CategoryTestSuite"})
  public void addPlanToCategory() throws Exception {
    browser.logAction("***Started addPlanToCategory");
    browser.reportLinkLog(type + assignWhen + eligibility + receive + typeUpdate + assignWhenUpdate + eligibilityUpdate + receiveUpdate + categoryName);
    //find catalog
    // findCatalog(categoryName);
    browser.reportLinkLog(categoryName);
    browser.logAction("got this: " + categoryName);
    productPages.addProductCategory(addProduct);
    productPages.setPromotePlan(categoryName);
    productPages.setApproveProduct(categoryName, "Update");
    browser.sleep(15500); //OP-1325
    catalogPages.verifyingCategoryCreated()
            .verifyCatalogInformation();
  }

  @Test(priority = 4, groups = {"broadleaf", "CategoryTestSuite"})
  public void updateCategory() throws Exception {
    browser.reportLinkLog(type + assignWhen + eligibility + receive + typeUpdate + assignWhenUpdate + eligibilityUpdate + receiveUpdate + categoryName);

    if (!typeUpdate.contains("none")) {

      catalogPages.setSideNavCatalogCategories();
      findCatalog(categoryName);
      String upDatecategoryName = "updatecategory" + browser.randomName();
      //Get Data
      browser.reportLinkLog(upDatecategoryName);
      browser.logAction("got this: " + upDatecategoryName + "createCategory");
      catalogPages.createCategory(typeUpdate, upDatecategoryName, assignWhenUpdate, eligibilityUpdate, receiveUpdate, "yes");
      catalogPages.setPromotePlan(upDatecategoryName);
      catalogPages.setApproveProduct(upDatecategoryName, "Update");
      browser.sleep(15500); //OP-1325
      catalogPages.verifyingCategoryCreated()
              .verifyCatalogInformation();
      categoryName = upDatecategoryName;
    }

  }

  @Test(priority = 5, groups = {"broadleaf", "CategoryTestSuite"})
  public void deleteCategory() throws Exception {
    browser.reportLinkLog(type + assignWhen + eligibility + receive + typeUpdate + assignWhenUpdate + eligibilityUpdate + receiveUpdate + categoryName);
    catalogPages.setSideNavCatalogCategories();
    catalogPages.deleteCategory(categoryName);

  }

  protected void findCatalog(String name) {
    //search for plan
    catalogPages.setSearch(name);
    //check plan
    browser.logAction("Searching  Categories " + name);
    browser.waitForClickableElement(By.linkText(name)).click();
  }

  @DataProvider(name = "category")
  public static Object[][] createData() {
    return new Object[][]{
      {"none", "none", "none", "none", "update", "none", "none", "none", "DoNotDelete"},
      //  {"none", "none", "none", "none", "none", "none", "none", "none", "DoNotDelete"},
      //{"mandatory", "Account is created or is resumed", "none", "all", "mandatory", "Subscribers join or resume", "indefinitely", "all"},
      //     {"mandatory", "Account is created or is resumed", "none", "one", "none", "none", "none", "none"},
      {"mandatory", "Only additional subscribers join or resume", "indefinitely", "one", "mandatory", "Subscribers join or resume", "once", "one", "DoNotDelete"}, //   {"mandatory", "Only additional subscribers join or resume", "once", "all", "none", "none", "none", "none", "DoNotDelete"}, //{"mandatory", "Only additional subscribers join or resume", "indefinitely", "one", "mandatory", "Subscribers join or resume", "indefinitely", "all"},
    //    {"mandatory", "Subscribers join or resume", "once", "one", "mandatory", "Account is created or is resumed", "none", "all"},
    //      {"mandatory", "Subscribers join or resume", "indefinitely", "all", "none", "none", "none", "none"},
    };
  }
}
