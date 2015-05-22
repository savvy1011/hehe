package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.itson.servicedesigncenter.Browser;

public abstract class TitlePageSection {

  private Browser browser;

  public TitlePageSection(Browser browser) {
    this.browser = browser;
  }

  public TitlePageSection openTitlePage() {
    browser.click(getTitleLink());
    browser.waitForPageLoaded();
    browser.waitForSpinnerToVanish();
    return this;
  }

  protected abstract WebElement getTitleLink();

  public abstract String getTitleName();

  public static final class BlockTitlePageSection extends TitlePageSection {

    @FindBy(how = How.XPATH, using = "//section[@id='sidebar']//a[contains(.,'Block')]")
    private WebElement link;

    public BlockTitlePageSection(Browser browser) {
      super(browser);
      browser.initElements(this);
    }

    @Override
    protected WebElement getTitleLink() {
      return link;
    }

    @Override
    public String getTitleName() {
      return "Block";
    }
  }

  public static final class InterceptTitlePageSection extends TitlePageSection {

    @FindBy(how = How.XPATH, using = "//section[@id='sidebar']//a[contains(.,'Intercept')]")
    private WebElement link;

    public InterceptTitlePageSection(Browser browser) {
      super(browser);
      browser.initElements(this);
    }

    @Override
    protected WebElement getTitleLink() {
      return link;
    }

    @Override
    public String getTitleName() {
      return "Intercept";
    }
  }

  public static final class UsageTitlePageSection extends TitlePageSection {

    @FindBy(how = How.XPATH, using = "//section[@id='sidebar']//a[contains(.,'Usage')]")
    private WebElement link;

    public UsageTitlePageSection(Browser browser) {
      super(browser);
      browser.initElements(this);
    }

    @Override
    protected WebElement getTitleLink() {
      return link;
    }

    @Override
    public String getTitleName() {
      return "Usage";
    }
  }

}
