/*
 /**Customer Support testSuite
 *
 * @author gurtejphangureh
 */
package com.itson.servicedesigncenter;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerSupportProvisioningTestSuite extends AbstractTest {

  public final String wrongPhoneNumber = "654686142";
  public String page = "customersupportprovisioning";
  protected CustomerSupportProvisioningPages cspPagesProvisioning;

  @BeforeClass
  protected void setup() {
    cspPagesProvisioning = new CustomerSupportProvisioningPages(browser);
    portalPages.setTopNavLink(page);
  }
    @Test(groups = {"CustomerSupport", "SDC", "Sprint", "Zact"})
  public void checkImsi() {
    browser.reportLinkLog("checkImsi");
  
  }

}
