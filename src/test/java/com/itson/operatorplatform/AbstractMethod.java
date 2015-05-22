package com.itson.operatorplatform;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.itson.servicedesigncenter.Browser;
import com.itson.servicedesigncenter.Config;
import com.itson.servicedesigncenter.PortalPages;
import java.util.Locale;

/**
 * This sets up the @Before and @After Method.
 */
public class AbstractMethod {

  protected Browser browser;
  protected final String accountPassword = "Pass1234";
  protected final String negative = "no";
  protected final String update = "no";
  protected String adminUser;
  protected String adminPass;
  protected String partnerName;
  protected String accountNumber;
  protected String sandBox;
  protected String url;
  protected String productName;
  protected String accountName;
  protected String errorMessage;
  protected String[] data;

  protected String view;
  protected String sdcUserName;
  protected String sdcPassword;
  protected String menuParnterID;
  protected String productType = "plan";

  protected Config config = new Config();
  protected ProductPages productPages;
  protected PortalPages portalPages;
  protected GiftCardAccount giftCardAccount;
  protected SecurityPages securityPages;
  protected LogInPages logInPages;
  protected CatalogPages catalogPages;
  protected BundlePages bundlePages;
  protected FeePages feePages;
  protected EntitlementChangeFeePages entitlementPages;

  @BeforeMethod
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
    entitlementPages = new EntitlementChangeFeePages(browser);

    browser.setView(view);
    partnerName = config.getPartnerName().toLowerCase();

    if (view.equals("sdc")) {
      browser.logAction("****In SDC view");
      portalPages.sdcLogin(menuParnterID);
    }

  }

  @AfterMethod
  protected void browserClose() {
    browser.close();
  }

  protected void logIn() {

    logInPages.lognIn(adminUser, adminPass);
    logInPages.checkPartnerName(partnerName);
  }

  protected void spiltData(String givendata) {
    data = givendata.split(",");

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

  protected void blCleanUp() {
    browser.logAction("***Clean Up method " + productName);
    logInPages.setLogOut();
    logInPages.lognIn(adminUser, adminPass);
    productPages.deleteProduct(productName);
  }

}
