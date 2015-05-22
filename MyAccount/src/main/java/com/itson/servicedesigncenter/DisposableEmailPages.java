/*
 **
 * locators and methods to check email from gmail
 *
 * @author Gurtej Phangureh
 */
package com.itson.servicedesigncenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

public class DisposableEmailPages {

  private Browser browser;
  protected String randomName;

  public DisposableEmailPages(Browser brw) {
    browser = brw;
    browser.initElements(this);
  }

  @FindBy(how = How.CSS, using = "div.subject.ng-binding")
  private WebElement selectFirstEmail;
  @FindBy(how = How.CSS, using = "button.btn:nth-child(3)")
  private WebElement deleteBtn;
  public String createRandomEmail() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    String reportDate = df.format(today);
    String remove = reportDate.replaceAll("[/:a-zA-Z]", "");
    String removespace = remove.replaceAll("\\s", "");
    String allowedChars = "abcdefghijklmnopqrstuvwxyz";
    String temp = RandomStringUtils.random(6, allowedChars);
    randomName = "autoitsoninc" + temp;
    Reporter.log("Created random name :" + randomName);
    String randomNameEmail = randomName + "@mailinator.com";
    browser.logAction("Random  email :" + randomNameEmail);
    return randomNameEmail;
  }

  public DisposableEmailPages gotoMailinator() {
    browser.logAction("Got random name " + randomName);
    browser.get("https://www.mailinator.com/inbox.jsp?to=" + randomName);
    return this;
  }

  public DisposableEmailPages checkIndex(String test) {
    String linkText;
    gotoMailinator();
    browser.logAction("Checking Inbox ");
    int refreshCount = 1;
    while (browser.findElementById("noemailmsg").isDisplayed() && refreshCount < 50) {
      browser.logAction("Refreshing Count : " + refreshCount);
      browser.refresh();
      refreshCount++;

    }
    browser.logAction("Waiting for  " + selectFirstEmail);

    browser.waitForClickableElement(selectFirstEmail);
    browser.logAction("Clicking First Email ");
    selectFirstEmail.click();
    browser.waitForClickableElement(deleteBtn);
    browser.logAction("Switching Frame");
    browser.switchTo_frameName("rendermail");
    browser.logAction("Getting Link");

    if (test.toLowerCase().equals("setup")) {
      linkText = "setup";

    }
    else {
      linkText = "password";
    }

    WebElement link = browser.findElement(By.partialLinkText(linkText));
    String linkLocatin = link.getAttribute("href");
    browser.switchTo_deafult();
    browser.logAction("Clicking Delete Btn");
    browser.click(deleteBtn);
    browser.sleep(1000);
    browser.logAction("Going too " + linkLocatin);
    browser.get(linkLocatin);
    return this;
  }
}
