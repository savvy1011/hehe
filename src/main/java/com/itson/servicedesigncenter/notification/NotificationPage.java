/**
 * Base class containing all the notification functionality
 * Note: this class contains all the common functionality across Block, Intercept and Usage
 * 
 * @author Supriya Singh
 */
package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;

import com.itson.servicedesigncenter.Browser;

public class NotificationPage {

  private final Browser browser;
  private final TitlePageSection titleSection;
  private final ListPageSection listSection;
  private final SearchPageSection searchSection;
  private final CreatePageSection createSection;
  private final EditButtonsPageSection editButtonsSection;
  private final DescriptionEditPageSection descriptionEditPageSection;
  private final ContentEditPageSection contentEditPageSection;
  private final RulePageSection<?> rulePageSection;
  private String id;

  public NotificationPage(Browser browser, TitlePageSection titleSection,
      RulePageSection<?> rulePageSection) {
    this.browser = browser;
    browser.initElements(this);
    this.titleSection = titleSection;
    this.searchSection = new SearchPageSection(browser, this);
    this.createSection = new CreatePageSection(browser, this);
    this.listSection = new ListPageSection(browser, this);
    this.descriptionEditPageSection = new DescriptionEditPageSection(browser);
    this.editButtonsSection = new EditButtonsPageSection(browser,
                                                         this.descriptionEditPageSection);
    this.contentEditPageSection = new ContentEditPageSection(browser);
    this.rulePageSection = rulePageSection;
  }

  public CreatePageSection getCreateSection() {
    return createSection;
  }

  public EditButtonsPageSection getEditButtons() {
    return editButtonsSection;
  }

  public ContentEditPageSection getContentEditPageSection() {
    return contentEditPageSection;
  }

  public DescriptionEditPageSection getDescriptionEditPageSection() {
    return descriptionEditPageSection;
  }

  public ListPageSection getListSection() {
    return listSection;
  }

  public SearchPageSection getSearchSection() {
    return searchSection;
  }

  public NotificationPage switchToFrame() {
    browser.switchToFrame(By.id("operator-iframe-window"));
    return this;
  }

  public ListPageSection clickOnSection() {
    titleSection.openTitlePage();
    return listSection;
  }

  public String getID() {
    String x = id.toString();
    if (x.isEmpty()) {
      return null;
    }
    return x;
  }

  NotificationPage setID(String id) {
    this.id = id;
    return this;
  }

  public CreatePageSection cloneNotification() {
    editButtonsSection.cloneNotification();
    return createSection;
  }

  public NotificationList search(String name) {
    return this.searchSection.search(name);
  }

  public RulePageSection<?> getRulePageSection() {
    return rulePageSection;
  }

  public BlockRulePageSection getBlockRulePageSection() {
    return (BlockRulePageSection) this.rulePageSection;
  }

  public InterceptRulePageSection getInterceptRulePageSection() {
    return (InterceptRulePageSection) this.rulePageSection;
  }

  public UsageRulePageSection getUsageRulePageSection() {
    return (UsageRulePageSection) this.rulePageSection;
  }

  public String getTitleName() {
    return this.titleSection.getTitleName();
  }

}
