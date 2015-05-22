package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import com.itson.servicedesigncenter.Browser;

@SuppressWarnings("unchecked")
public abstract class RulePageSection<E extends RulePageSection<E>> {

  protected final Browser browser;

  // Match
  @FindBy(how = How.XPATH, using = "//select[@ng-model='data.ruleSetOperator']")
  private WebElement match;
  @FindBy(how = How.LINK_TEXT, using = "Rule")
  protected WebElement ruleLink;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='data.subject']")
  private List<WebElement> ruleTypeDropdowns;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='data.operator']")
  private List<WebElement> conditionDropdowns;
  @FindBy(how = How.XPATH, using = "//select[@ng-model='data.value']")
  private List<WebElement> conditionDropdownValues;
  @FindBy(how = How.XPATH, using = "//input[@ng-model='data.value']")
  private List<WebElement> conditionInputValues;
  @FindBy(how = How.XPATH, using = "//div[@class='rule']//div[@class='foreign-key-value-container']/span")
  private List<WebElement> allRules;

  public RulePageSection(Browser browser) {
    this.browser = browser;
    browser.initElements(this);
  }

  public E addServicePolicyIdRule(int index) {
    browser.click(ruleLink);
    browser.selectDropdown(Utils.last(ruleTypeDropdowns), "Service Policy ID");
    browser.selectDropdown(Utils.last(conditionDropdowns), "is equal to");
    List<WebElement> all = browser.findElements(By.xpath("//button[@ng-click=\"chooseFromLookup('SERVICE_POLICY_ID')\"]"));
    WebElement last = all.get(all.size() - 1);
    browser.click(last);
    try {
      browser.click(browser.findElement(By.xpath("//table[@id='lookup-table']/tbody/tr[" + index
                                                 + "]/td[1]/a")));
    } catch (TimeoutException e) {
      // The table may be empty, check for it
      WebElement marker = browser.findElement(By.xpath("//table[@id='lookup-table']//td[@class='dataTables_empty']"));
      if (marker.isDisplayed()) {
        throw new IllegalStateException("No service policy defined.");
      }
    }
    return (E) this;
  }

  public E addReasonRule(String value) {
    browser.click(ruleLink);
    browser.selectDropdown(Utils.last(ruleTypeDropdowns), "Reason");
    browser.selectDropdown(Utils.last(conditionDropdowns), "is equal to");
    browser.selectDropdown(Utils.last(conditionDropdownValues), value);
    return (E) this;
  }

  public E addLimitRule(String limitType, String condition, String value) {
    browser.click(ruleLink);
    browser.selectDropdown(Utils.last(ruleTypeDropdowns), limitType);
    browser.selectDropdown(Utils.last(conditionDropdowns), condition);
    browser.sendKeys(Utils.last(conditionInputValues), value, true);
    return (E) this;
  }

  public E setMatch(String selectMatch) {
    browser.selectDropdown(match, selectMatch);
    return (E) this;
  }

  public String getSelectedMatchValue() {
    return new Select(match).getFirstSelectedOption().getText();
  }

  public String getSelectedRuleTypeValue(int index) {
    return new Select(ruleTypeDropdowns.get(index)).getFirstSelectedOption()
                                                   .getText();
  }

  public String getSelectedRuleOperation(int index) {
    return new Select(conditionDropdowns.get(index)).getFirstSelectedOption()
                                                    .getText();
  }

  public List<String> getRuleValues() {
    List<String> values = new ArrayList<>();
    for (WebElement element : allRules) {
      if (element.isDisplayed()) {
        values.add(element.getText());
      }
    }
    return values;
  }
}
