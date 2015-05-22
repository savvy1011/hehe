package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.itson.servicedesigncenter.Browser;

public class DescriptionEditPageSection {

  private final Browser browser;

  @FindBy(how = How.XPATH, using = "//input[@ng-model='form.name']")
  private WebElement nameField;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='form.description']")
  private WebElement descriptionField;

  public DescriptionEditPageSection(Browser browser) {
    this.browser = browser;
    this.browser.initElements(this);
  }

  public DescriptionEditPageSection setEditName(String name) {
    browser.sendKeys(nameField, name, true);
    return this;
  }

  public DescriptionEditPageSection setEditOperatorDescription(
      String description) {
    browser.sendKeys(descriptionField, description, true);
    return this;
  }

  public boolean isNameFieldEnabled() {
    return nameField.isEnabled();
  }
}
