package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.itson.servicedesigncenter.Browser;

public class CreatePageSection {

  private final Browser browser;
  private final NotificationPage top;

  @FindBy(how = How.XPATH, using = "//button[@class='button small radius dark right']")
  private WebElement addNotificationBodyButton;
  @FindBy(how = How.XPATH, using = "//div[@class='modal-dialog']//input[@ng-model='form.name']")
  private WebElement modelWindowAddNameField;
  @FindBy(how = How.XPATH, using = "//div[@class='modal-dialog']//input[@ng-model='form.description']")
  private WebElement modelWindowAddDescriptionField;
  @FindBy(how = How.ID, using = "confirmAction_btn")
  private WebElement modelWindowCreateButton;

  public CreatePageSection(Browser browser, NotificationPage top) {
    this.browser = browser;
    this.browser.initElements(this);
    this.top = top;
  }

  public CreatePageSection clickAdd() {
    browser.click(addNotificationBodyButton);
    return this;
  }

  public CreatePageSection setName(String name) {
    browser.sendKeys(modelWindowAddNameField, name, true);
    return this;
  }

  public CreatePageSection setOperatorDescription(String description) {
    browser.sendKeys(modelWindowAddDescriptionField, description, true);
    return this;
  }

  public String create() {
    browser.click(modelWindowCreateButton);
    browser.waitForElementToDisappear(By.xpath("//div[@class='modal-dialog']"));
    top.setID(Utils.findIDFromURL(browser.getCurrentUrl()));
    return top.getID();
  }

}
