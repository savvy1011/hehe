package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

import com.itson.servicedesigncenter.Browser;

public class SearchPageSection {

  private final Browser browser;
  private final NotificationPage top;

  @FindBy(how = How.XPATH, using = "//input[@placeholder='Search for Notification Bodies']")
  private WebElement searchField;
  @FindBy(how = How.XPATH, using = "//a[@ng-click='search()']")
  private WebElement searchButton;
  @FindBy(how = How.LINK_TEXT, using = "Clear")
  private WebElement clearButton;

  public SearchPageSection(Browser browser, NotificationPage top) {
    this.browser = browser;
    this.browser.initElements(this);
    this.top = top;
  }

  public NotificationList search(String notification) {
    browser.sendKeys(searchField, notification, true);
    browser.click(searchButton);
    browser.waitForPageLoaded();
    By by = By.xpath("//table[@id='notification-table']//tbody/tr/td/a");
    browser.waitForSpinnerToVanish();
    List<WebElement> list = browser.findElements(by);
    NotificationList result = new NotificationList();
    for (WebElement x : list) {
      String id = Utils.findIDFromURL(x.getAttribute("href"));
      String title = x.getText();
      String description = x.findElement(By.xpath("../../td[2]")).getText();
      String state = x.findElement(By.xpath("../../td[3]")).getText();
      result.add(id, title, description, state, x);
    }
    return result;
  }

  public NotificationPage open(String name) {
    browser.sendKeys(searchField, name, true);
    browser.click(searchButton);
    browser.waitForPageLoaded();
    By by = By.xpath("//table[@id='notification-table']//tbody/tr/td/a");
    browser.waitForSpinnerToVanish();
    List<WebElement> list = browser.findElements(by);
    for (WebElement x : list) {
      if (Utils.equalsIgnoreEllipsis(x.getText(), name.replaceAll("\\s+", " "))) {
        x.click();
        this.top.setID(Utils.findIDFromURL(browser.getUrl()));
        browser.waitForSpinnerToVanish();
        browser.waitForDropDownValue(By.xpath("//select[@ng-model='data.ruleSetOperator']"));
        return top;
      }
    }
    Assert.fail("Couldn't find the notification with name: " + name);
    return null;
  }
}
