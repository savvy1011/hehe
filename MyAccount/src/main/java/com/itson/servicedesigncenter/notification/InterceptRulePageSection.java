package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.itson.servicedesigncenter.Browser;

public final class InterceptRulePageSection extends
    RulePageSection<InterceptRulePageSection> {

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
  private WebElement addInterCeptRules;

  public InterceptRulePageSection(Browser browser) {
    super(browser);
    browser.initElements(this);
  }

  public InterceptRulePageSection addProductUpsell() {
    browser.click(addProductUpsellYes);
    browser.click(addProduct);
    browser.click(lookUpProduct, 2 * 60);
    browser.click(saveProduct);
    return this;
  }

  public InterceptRulePageSection addMoreInterceptRules() {
    browser.click(addInterCeptRules);
    return this;
  }
}