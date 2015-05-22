package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import com.itson.servicedesigncenter.Browser;

public class ListPageSection {

  private final Browser browser;
  private final NotificationPage top;

  public ListPageSection(Browser browser, NotificationPage top) {
    this.browser = browser;
    this.browser.initElements(this);
    this.top = top;
  }

  public NotificationList list() {
    By by = By.xpath("//table[@id='notification-table']//tbody/tr/td/a");
    browser.waitForVisibilityOfElement(by);
    NotificationList result = new NotificationList();
    List<WebElement> list = browser.findElements(by);
    while (true) {
      collectNames(list, result);
      browser.waitForSpinnerToVanish();
      // The list will change because of infinite scrolling.
      List<WebElement> changed = browser.findElements(by);
      if (changed.size() != list.size()) {
        // The list has changed.
        list = changed;
      } else {
        // Reached the end of scroll.
        break;
      }
    }
    return result;
  }

  private void collectNames(List<WebElement> list, NotificationList result) {
    for (WebElement x : list) {
      browser.moveMouse(x);
      String id = Utils.findIDFromURL(x.getAttribute("href"));
      if (!result.containsByID(id)) {
        String title = x.getText();
        String description = x.findElement(By.xpath("../../td[2]")).getText();
        String state = x.findElement(By.xpath("../../td[3]")).getText();
        result.add(id, title, description, state, x);
      }
    }
  }

  public NotificationPage open(String name) {
    return openWithSearch(name);
  }

  private NotificationPage openWithSearch(String name) {
    return this.top.getSearchSection().open(name);
  }

  public NotificationPage openWithInfinteScroll(String name) {
    By by = By.xpath("//table[@id='notification-table']//tbody/tr/td/a");
    browser.waitForVisibilityOfElement(by);
    List<WebElement> list = browser.findElements(by);
    while (true) {
      for (WebElement x : list) {
        browser.waitForClickableElement(x);
        browser.moveMouse(x);
        if (Utils.equalsIgnoreEllipsis(x.getText(), name)) {
          x.click();
          this.top.setID(Utils.findIDFromURL(browser.getUrl()));
          browser.waitForSpinnerToVanish();
          browser.waitForDropDownValue(By.xpath("//select[@ng-model='data.ruleSetOperator']"));
          return top;
        }
      }

      browser.waitForSpinnerToVanish();
      // The list will change because of infinite scrolling.
      List<WebElement> changed = browser.findElements(by);
      if (changed.size() != list.size()) {
        // The list has changed.
        list = changed;
      } else {
        // Reached the end of scroll.
        // Could not find the element
        Assert.fail("Couldn't find the notification with name: " + name);
        break;
      }
    }
    return top;
  }

  public SearchPageSection getSearchSection() {
    return top.getSearchSection();
  }

  public CreatePageSection getCreatePageSection() {
    return top.getCreateSection();
  }
}
