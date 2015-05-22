

/**
 * This sets up the used for myaccount testsuite to start and end test.
 * @author Gurtej Phangureh
 */

package com.itson.myaccount;

import com.itson.servicedesigncenter.Browser;
import com.itson.servicedesigncenter.Config;
import com.itson.servicedesigncenter.RestCalls;
import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public abstract class MyAccountAbstract {

  protected String email;
  protected String password;
  protected String phoneNumber;
  protected String url;

  protected Browser browser;
  protected Util util;
  protected Util.Compare compare;
  protected MyAccountPages myAccountPages;
  protected RestCalls restCalls = new RestCalls();
  protected Config config = new Config();



  @BeforeClass
  protected void classSetUp() throws Exception {
    email = restCalls.createAccount(); //creates the account used for test suite
    password = restCalls.getPassword();
    url = config.getHostUrl("myaccount"); //gets url from env file used for test
    this.browser = new Browser.Builder()
            .build();
    browser.logAction("Got this "+url);
    browser.getSite(url);
    Util util = new Util(browser);
    compare = util.new Compare();
    myAccountPages = new MyAccountPages(browser);
    // phoneNumber = browser.testMDN();
    myAccountPages.setLogIn(email, password);
  }
  
  @BeforeMethod
//  @Parameters({"method"})
  protected void methodSetUp(Method method) throws Exception {
    compare.begin(method.getName());
  }
  
  @AfterMethod
//  @Parameters({"method"})
  protected void methodCleanUp(Method method) throws Exception {
    compare.conclusion(method.getName());
  }
  
  @AfterClass
  protected void classCleanUp() {
   // restCalls.myAccountDelete();//deletes the account that was used for test suite
    browser.close();
  }
}
