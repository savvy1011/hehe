package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.itson.servicedesigncenter.Browser;

public final class UsageRulePageSection extends
    RulePageSection<UsageRulePageSection> {

  @FindBy(how = How.LINK_TEXT, using = "Sub-Condition")
  private WebElement subConditionLink;

  // Upsell
  @FindBy(how = How.XPATH, using = "//input[@ng-model='upsell'][@value='1']")
  private WebElement addProductUpsellYes;
  @FindBy(how = How.XPATH, using = "//button[@class='tiny radius dark button sub-list-grid-add ng-binding'][@ng-click='add()']")
  private WebElement addProduct;
  @FindBy(how = How.XPATH, using = "//table[@id='lookup-table']/tbody/tr[1]")
  private WebElement lookUpProduct;
  @FindBy(how = How.XPATH, using = "//button[@ng-disabled='!selectedPlanIds.length']")
  private WebElement saveProduct;

  // Adding more intercept rules
  @FindBy(how = How.XPATH, using = "//button[@ng-click='addUsageConfigurations()']")
  private WebElement addUsageRules;

  // Threshold
  @FindBy(how = How.XPATH, using = "//input[@value='PERCENTAGE_USED']")
  private WebElement percentageUsed;
  @FindBy(how = How.XPATH, using = "//input[@value='UNITS_REMAINING']")
  private WebElement unitsUsed;
  @FindBy(how = How.XPATH, using = "//div[@class='conditional-rules']//select[@class='all-any-none']")
  private WebElement matchThreshold;
  // Percentage used
  @FindBy(how = How.XPATH, using = "//select[@ng-model='percentage.data[$index].unit']")
  private WebElement selectPercentage;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='percentage.data[$index].operator']")
  private WebElement equal;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='percentage.data[$index].value']")
  private WebElement value;
  // Unit used
  @FindBy(how = How.XPATH, using = "//select[@ng-model='unit.data[$index].unit']")
  private WebElement selectPercentageinUnit;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='unit.data[$index].operator']")
  private WebElement equalinUnit;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='unit.data[$index].value']")
  private WebElement valueinUnit;

  public UsageRulePageSection(Browser browser) {
    super(browser);
    browser.initElements(this);
  }

  public UsageRulePageSection addProductUpsell() {
    browser.click(addProductUpsellYes);
    browser.click(addProduct);
    browser.waitForClickableElement(lookUpProduct);
    browser.click(lookUpProduct);
    browser.click(saveProduct);
    return this;
  }

  public UsageRulePageSection addMoreRules() {
    browser.click(addUsageRules);
    return this;
  }

  public UsageRulePageSection addThresholdPercentage(String matchValue,
      String percentageValue, String equalValue, String valuePercentage) {
    browser.click(percentageUsed);
    browser.selectDropdown(matchThreshold, matchValue);
    browser.click(browser.findElement(By.xpath("//div[@ng-show=\"whenType('PERCENTAGE_USED')\"]//a[text()='Rule']")));
    // JIRA - file bug
    // browser.selectDropdown(selectPercentage, percentageValue);
    // browser.selectDropdown(equal, equalValue);
    // browser.selectDropdown(value, valuePercentage);
    return this;
  }

  public UsageRulePageSection addThresholdUnit(String matchValue,
      String percentageValue, String equalValue, String valuePercentage) {
    browser.click(unitsUsed);
    browser.selectDropdown(matchThreshold, matchValue);
    browser.click(browser.findElement(By.xpath("//div[@ng-show=\"whenType('UNITS_REMAINING')\"]//a[text()='Rule']")));
    // JIRA - file bug
    // browser.selectDropdown(selectPercentageinUnit, percentageValue);
    // browser.selectDropdown(equalinUnit, equalValue);
    // browser.sendKeys(valueinUnit, valuePercentage, true);
    return this;
  }
}