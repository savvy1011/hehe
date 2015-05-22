package com.itson.operatorplatform;

/*
 /**Create Gift card test
 *
 * @author gurtejphangureh
 */



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateGiftCardTestSuite extends AbstractMethod{
  
  @BeforeMethod
  protected void logInSetUp() {
    logIn();
  }

  @Test()
  public void CreateGiftCard()  {
    String email = browser.randomName()+"@itsoninc.net";
    String amount = browser.randomNumber(2);
    browser.reportLinkLog("GiftCard"+email);
    browser.logAction("Got This For Amount:" + amount + " Got This For email: " + email);
    accountNumber = giftCardAccount.CreateGiftCardAccount(amount, email);
    browser.reporterLogger("Account Number : "+accountNumber);
    browser.close();
  }
 
}
