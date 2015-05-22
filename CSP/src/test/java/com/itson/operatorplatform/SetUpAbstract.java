package com.itson.operatorplatform;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itson.servicedesigncenter.Browser;
import com.itson.servicedesigncenter.Config;
import com.itson.servicedesigncenter.PortalPages;

/**
 * setup for other Abstract classes
 *
 *
 */
public abstract class SetUpAbstract {

  protected Browser browser;
  protected final String accountPassword = "Pass1234";
  protected final String negative = "no";
  protected final String update = "no";
  protected String adminUser;
  protected String adminPass;
  protected String accountNumber;
  protected String sandBox;
  protected String wrongPassword;
  protected String wrongUserName;

  protected String productName;
  protected String description;
  protected String servicePolicy;
  protected String usage;
  protected String extraData;
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
  protected String dataUsageAmount;
  protected String accountName;
  protected String errorMessage;
  protected String[] data;
  protected String url;
  protected String view;
  protected String sdcUserName;
  protected String sdcPassword;
  protected String menuParnterID;
  protected String partnerName;

  protected Config config = new Config();
  protected ProductPages productPages;
  protected PortalPages portalPages;
  protected GiftCardAccount giftCardAccount;
  protected SecurityPages securityPages;
  protected LogInPages logInPages;

  protected void browserSetUp() {
    adminUser = config.getBlUsername();
    adminPass = config.getBlPassword();
    sandBox = config.getSandbox();
    url = config.getHostUrl("bl");
    this.browser = new Browser.Builder()
            .build();
    browser.getSite(url);
    productPages = new ProductPages(browser);
    giftCardAccount = new GiftCardAccount(browser);
    securityPages = new SecurityPages(browser);
    portalPages = new PortalPages(browser);
    logInPages = new LogInPages(browser);
    view = config.getView().toLowerCase();
    menuParnterID = config.getParnterID();
    logInPages.view(view);
    browser.setView(view);
    browser.logAction("*** View is " + view);
    partnerName = config.getPartnerName().toLowerCase();

    if (view.equals("sdc")) {
      browser.logAction("******In sdc view");
      portalPages.sdcLogin(menuParnterID);
    }

  }

  protected void spiltData(String givendata) {
    data = givendata.split(",");

  }

  protected void writeCSV(String file, String data) {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
      writer.append(data);
      writer.append('\n');
      writer.flush();
    }
    catch (IOException ex) {
      Logger.getLogger(SetUpAbstract.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        writer.close();
      }
      catch (IOException ex) {
        Logger.getLogger(SetUpAbstract.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  protected void setData(String givendata) {
    spiltData(givendata);
    productName = data[0];
    description = data[1];
    servicePolicy = data[2];
    usage = data[3];
    extraData = data[4]; //not used but still in csv
    shareType = data[5];
    renewal = data[6];
    alignedToAccountCycle = data[7].toLowerCase();
    planDuration = data[8];
    planDurationUnit = data[9];
    prorated = data[10].toLowerCase();
    usageDisplayProperties = data[11].toLowerCase();
    usageDisplayPropertiesDropDown = data[12];
    displayDetailedUsageData = data[13].toLowerCase();
    retailPrice = data[14];
    salePrice = data[15];
    taxCode = data[16];
  }

  protected void setErrorMessage() {
    errorMessage = data[17];
  }

  protected void checkPartnerName() {
    logInPages.checkPartnerName(partnerName);
  }

}
