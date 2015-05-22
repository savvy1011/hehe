package com.itson.servicedesigncenter.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.IOException;

import com.itson.servicedesigncenter.Browser;

public class ContentEditPageSection {

  private static final Logger LOGGER = LogManager.getFormatterLogger(ContentEditPageSection.class);
  private final Browser browser;

  // Title and its translations
  @FindBy(how = How.XPATH, using = "//input[@ng-model='primaryTexts.en.title']")
  private WebElement modifyWindowTitleField;
  @FindBy(how = How.XPATH, using = "//translate-button[@property='title']/a")
  private WebElement clickTranslation;
  @FindBy(how = How.XPATH, using = "//button[@ng-click='options.add()']")
  private WebElement translationAddButton;
  @FindBy(how = How.ID, using = "select_localeCode")
  private WebElement selectLocaleCode;
  @FindBy(how = How.XPATH, using = "//input[@ng-show='options.whenInputTypeText()']")
  private WebElement translatedValue;
  @FindBy(how = How.XPATH, using = "//button[@ng-click='ok()']")
  private WebElement saveButton;
  @FindBy(how = How.XPATH, using = "//h4[contains(.,'Field Translations')]/../button[@class='close'][@ng-click='cancel()']")
  private WebElement xButton;
  @FindBy(how = How.CLASS_NAME, using = "tiny radius dark row-action button translation-grid-remove ng-binding")
  private WebElement deleteButton;
  @FindBy(how = How.CLASS_NAME, using = "tiny radius dark row-action button translation-grid-update ng-binding")
  private WebElement editButton;

  // Body
  @FindBy(how = How.ID, using = "notificationContentBody")
  private WebElement modifyWindowBodyField;
  @FindBy(how = How.CLASS_NAME, using = "re-icon re-bold")
  private WebElement boldField;
  @FindBy(how = How.CLASS_NAME, using = "re-icon re-italic")
  private WebElement italicField;
  @FindBy(how = How.CLASS_NAME, using = "re-icon re-deleted")
  private WebElement deletedField;
  @FindBy(how = How.CLASS_NAME, using = "re-icon re-itson redactor-btn-image")
  private WebElement insertVariable;

  // Content Button and Actions
  @FindBy(how = How.XPATH, using = "//select[@ng-model='primaryButtons.default.buttonAction']")
  private WebElement contentButton;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='primaryButtons.default.label']")
  private WebElement contentLabelField;
  @FindBy(how = How.XPATH, using = "//translate-button[@property='label']/a")
  private WebElement contentLabelAddTranslationLink;
  @FindBy(how = How.XPATH, using = "//notification-buttons/div[@id='upSaleProducts']//button[contains(.,'Add')]")
  private WebElement contentAddActionButton;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='$parent.form.action']")
  private WebElement contentActionLabelDropdown;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='$parent.form.page']")
  private WebElement contentActionLabelField;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='$parent.form.label']")
  private WebElement contentActionLabel;
  @FindBy(how = How.XPATH, using = "//div[contains(.,'Label')]/../translate-button/a")
  private WebElement contentLabelActionAddTranslationLink;
  @FindBy(how = How.XPATH, using = "//button[@id='confirmAction_btn']")
  private WebElement contentAddSaveButton;

  public ContentEditPageSection(Browser browser) {
    this.browser = browser;
    this.browser.initElements(this);
  }

  public ContentEditPageSection addTranslation(String locale,
      String translatedValue) throws IOException {
    return addTranslation(clickTranslation, locale, translatedValue);
  }

  private ContentEditPageSection addTranslation(WebElement link, String locale,
      String translatedValue) {
    browser.click(link);
    browser.click(translationAddButton);
    browser.selectDropdown(selectLocaleCode, locale);
    browser.sendKeys(this.translatedValue, translatedValue, true);
    browser.click(saveButton);
    browser.click(xButton);
    return this;
  }

  public ContentEditPageSection setContentTitle(String name) {
    LOGGER.debug("Setting the title = %s", name);
    browser.sendKeys(modifyWindowTitleField, name, true);
    return this;
  }

  public ContentEditPageSection setContentBody(String body) {
    LOGGER.debug("Setting the body = %s", body);
    browser.sendKeys(modifyWindowBodyField, body, true);
    return this;
  }

  public ContentEditPageSection setContentButton(String label) {
    browser.selectDropdown(contentButton, label);
    return this;
  }

  public ContentEditPageSection setContentLabel(String label) {
    browser.sendKeys(contentLabelField, label, true);
    return this;
  }

  public ContentEditPageSection addContentActionTranslation(String locale,
      String value) {
    return addTranslation(contentLabelAddTranslationLink, locale, value);
  }

  public ContentEditPageSection addDisplayPageContentAction(String page,
      String label, String language, String translation) {
    return addContentAction("Display Page", page, label, language, translation);
  }

  public ContentEditPageSection addLaunchURLContentAction(String page,
      String label, String language, String translation) {
    return addContentAction("Launch Url", page, label, language, translation);
  }

  public ContentEditPageSection addContentAction(String dropdown, String value,
      String label, String locale, String translation) {
    browser.click(contentAddActionButton);
    browser.selectDropdown(contentActionLabelDropdown, dropdown);
    browser.sendKeys(contentActionLabelField, value, true);
    browser.sendKeys(contentActionLabel, label, true);
    if (locale != null) {
      addTranslation(contentLabelActionAddTranslationLink, locale, translation);
    }
    browser.click(contentAddSaveButton);
    return this;
  }

}
