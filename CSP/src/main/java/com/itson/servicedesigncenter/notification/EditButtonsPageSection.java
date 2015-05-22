package com.itson.servicedesigncenter.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import com.itson.servicedesigncenter.Browser;

public class EditButtonsPageSection {

  private static final Logger LOGGER = LogManager.getFormatterLogger(EditButtonsPageSection.class);
  private final Browser browser;
  private final DescriptionEditPageSection descriptionSection;

  @FindBy(how = How.XPATH, using = "//button[@ng-click='removeNotification()']")
  private WebElement deleteButton;
  @FindBy(how = How.XPATH, using = "//button[@ng-click='updateNotification()']")
  private WebElement saveButton;
  @FindBy(how = How.XPATH, using = "//button[@ng-click='finalizeNotification()']")
  private WebElement saveAndFinalizeButton;
  @FindBy(how = How.XPATH, using = "//button[@ng-click='showModalForCloningNotification()']")
  private WebElement cloneAndEditButton;

  public EditButtonsPageSection(Browser browser,
      DescriptionEditPageSection descriptionSection) {
    this.browser = browser;
    this.browser.initElements(this);
    this.descriptionSection = descriptionSection;
  }

  public String save() {
    browser.click(saveButton);
    browser.waitForClickableElement(saveButton);
    return findMessageInAlert();
  }

  public EditButtonsPageSection delete() {
    browser.click(deleteButton);
    return this;
  }

  public String saveAndFinalize() {
    browser.click(saveAndFinalizeButton);
    browser.waitForClickableElement(deleteButton);
    return findMessageInAlert();
  }

  private String findMessageInAlert() {
    try {
      List<WebElement> list = browser.findElements(By.xpath("//div[@id='notification-issues']/strong/p"));
      if (list != null && !list.isEmpty()) {
        StringBuilder buffer = new StringBuilder(1024);
        for (WebElement x : list) {
          if (buffer.length() != 0) {
            buffer.append("\n");
          }
          buffer.append(x.getText());
        }
        return buffer.toString();
      }
      return "Success";
    } catch (TimeoutException e) {
      // There is no message in alertbox - check if elements are read-only
      if (!descriptionSection.isNameFieldEnabled()) {
        return "Success";
      }
      throw e;
    }
  }

  public EditButtonsPageSection delete(boolean conform) {
    browser.click(deleteButton);
    if (conform) {
      By deleteConformation = By.xpath("//div[@class='modal-dialog']//button[@ng-click='ok()']");
      browser.click(browser.findElement(deleteConformation));
    }

    browser.waitForVisibilityOfElement(By.xpath("//a[@ng-click='search()']"));
    return this;
  }

  public EditButtonsPageSection cloneNotification() {
    waitForFinalization();
    browser.click(cloneAndEditButton);
    return this;
  }

  public EditButtonsPageSection waitForFinalization() {
    long start = System.currentTimeMillis();
    long time = start + 2 * 60 * 1000l;
    WebDriverException saved = null;
    while (time > System.currentTimeMillis()) {
      try {
        browser.waitForClickableElement(cloneAndEditButton);
        return this;
      } catch (WebDriverException e) {
        LOGGER.warn("No clone button is found for cloning, refreshing the page and trying again.",
                    e);
        saved = e;
        browser.refresh();
      }
    }
    throw new IllegalStateException(String.format("The notification is in still in processing state, waited for %d mins.",
                                                  (System.currentTimeMillis() - start) / (60 * 1000)),
                                    saved);
  }

}
