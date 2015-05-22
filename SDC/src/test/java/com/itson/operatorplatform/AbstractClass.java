package com.itson.operatorplatform;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.itson.servicedesigncenter.Browser;
import com.itson.servicedesigncenter.Config;
import com.itson.servicedesigncenter.PortalPages;

/**
 * This sets up the @Before and @After Class. It should not do anything with
 *
 * @BeforeMethod or @AfterMethod
 */
public abstract class AbstractClass {

  protected Browser browser;
  protected final String accountPassword = "Pass1234";
  protected final String negative = "no";
  protected final String update = "no";
  protected String adminUser;
  protected String adminPass;
  protected String accountNumber;
  protected String sandBox;

  protected String productName;
  protected String errorMessage;
  protected String[] data;

  protected String view;
  protected String sdcUserName;
  protected String sdcPassword;
  protected String menuParnterID;
  protected String url;
  protected Config config = new Config();
  protected ProductPages productPages;
  protected PortalPages portalPages;
  protected LogInPages logInPages;
  protected CatalogPages catalogPages;

  protected GiftCardAccount giftCardAccount;
  protected SecurityPages securityPages;
  protected BundlePages bundlePages;
  protected FeePages feePages;
  protected String partnerName;


  @BeforeClass
  protected void beforeSetUp() {
    adminUser = config.getBlUsername();
    adminPass = config.getBlPassword();
    sandBox = config.getSandbox();
    view = config.getView().toLowerCase();
    menuParnterID = config.getParnterID();
    url = config.getHostUrl("bl");
    this.browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    productPages = new ProductPages(browser);
    giftCardAccount = new GiftCardAccount(browser);
    securityPages = new SecurityPages(browser);
    portalPages = new PortalPages(browser);
    logInPages = new LogInPages(browser);
    catalogPages = new CatalogPages(browser);
    bundlePages = new BundlePages(browser);
    feePages = new FeePages(browser);
    partnerName = config.getPartnerName().toLowerCase();

    browser.setView(view);
    if (view.equals("sdc")) {
      portalPages.sdcLogin(menuParnterID);
    }
  }

  @AfterClass
  protected void afterSetUp() {
    browser.close();
  }

  protected void setData(String givendata, String productType) {

    switch (productType) {
      case "plan":
        data = productPages.setPlanData(givendata);
        break;
      case "bundle":
        data = bundlePages.setPlanData(givendata);
        break;
      case "fee":
        data = feePages.setPlanData(givendata);
        break;
      default:
        System.err.println("***Input  is not a vaild choice " + productType);
        break;
    }
    productName = data[0];

  }

  protected void setErrorMessage() {
    errorMessage = data[17];
  }

  protected void logIn() {
    logInPages.lognIn(adminUser, adminPass);
    logInPages.checkPartnerName(partnerName);
  }

  protected void blCleanUp() {
    try {
      browser.logAction("***Clean Up method " + productName);
      logInPages.setLogOut();
      logInPages.lognIn(adminUser, adminPass);
      productPages.deleteProduct(productName);
    }
    catch (Exception ignored1) {
      browser.reporterLogger("###Was Not Able to CleanUp#### " + productName);
    }
  }

}
