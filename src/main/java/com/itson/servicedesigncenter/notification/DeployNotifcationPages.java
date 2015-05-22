package com.itson.servicedesigncenter.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import com.itson.operatorplatform.LogInPages;
import com.itson.servicedesigncenter.Browser;

public class DeployNotifcationPages {

  private static final Logger LOGGER = LogManager.getFormatterLogger(DeployNotifcationPages.class);
  private static final int TIMEOUT = 4 * 60 * 1000;
  private final LogInPages logInPages;
  private final Browser browser;
  private final String title;

  @FindBy(how = How.ID, using = "username")
  private WebElement userNameField;
  @FindBy(how = How.ID, using = "password")
  private WebElement passwordFiled;
  @FindBy(how = How.CSS, using = ".button")
  private WebElement signInBtn;

  @FindBy(how = How.XPATH, using = "//nav[@id='sideMenu']/ul/li[contains(.,'Notifications')]/div/h5")
  private WebElement leftMenuNotificationSection;
  @FindBy(how = How.XPATH, using = "//nav[@id='sideMenu']/ul/li[contains(.,'Site Updates')]/div/h5")
  private WebElement leftMenuSiteUpdatesSection;
  @FindBy(how = How.LINK_TEXT, using = "Approvals")
  private WebElement leftMenuApprovalsSection;

  private final CreateSection createSection;
  private final SiteUpdatesSection siteUpdatesSection;
  private final ApprovalSection approvalSection;
  private final DeleteSection deleteSection;

  public DeployNotifcationPages(Browser browser, String title) {
    this.browser = browser;
    this.title = title;
    browser.initElements(this);
    this.logInPages = new LogInPages(browser);

    this.createSection = new CreateSection(browser, this);
    this.siteUpdatesSection = new SiteUpdatesSection(browser, this);
    this.approvalSection = new ApprovalSection(browser, this);
    this.deleteSection = new DeleteSection(browser, this);
  }

  public LogInPages getLogInPages() {
    return logInPages;
  }

  public DeployNotifcationPages login(String username, String password) {
    browser.logAction("Inputing User " + username);
    userNameField.clear();
    userNameField.sendKeys(username);
    passwordFiled.clear();
    passwordFiled.sendKeys(password);
    browser.logAction("Clicking Signin Button");
    browser.click(signInBtn);
    return this;
  }

  public DeployNotifcationPages openSection() {
    switchToFrame();
    this.browser.click(leftMenuNotificationSection);
    this.browser.click(By.linkText(title));
    return this;
  }

  private void switchToFrame() {
    this.browser.switchToFrame(By.id("operator-iframe-window"));
  }

  private void waitForSpinnerToVanish() {
    this.browser.waitForElementToDisappear(By.xpath("//i[@class='icon-spin icon-spinner']"));
  }

  private void waitForAjaxLoaderToVanish() {
    this.browser.waitForInvisibilityOfElement(By.xpath("//img[@class='ajax-loader'][@src]"));
  }

  public CreateSection openCreateDialog(String name) {
    this.browser.click(By.xpath("//button[@data-url='/op-admin/notification-" + title.toLowerCase()
                                + "/add']"));
    return this.createSection.open(name);
  }

  public SiteUpdatesSection openSiteUpdates() {
    this.browser.click(leftMenuSiteUpdatesSection);
    return this.siteUpdatesSection;
  }

  public ApprovalSection openApprovals() {
    this.browser.click(leftMenuApprovalsSection);
    return this.approvalSection;
  }

  public DeleteSection openForDelete(String name) {
    switchToFrame();
    return this.deleteSection.open(name);
  }

  public static final class CreateSection {

    private final Browser browser;
    private final DeployNotifcationPages top;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Lookup')]")
    private WebElement lookupButton;
    @FindBy(how = How.XPATH, using = "//table[@id='listGrid-inline-null']/tbody/tr/td[1]")
    private List<WebElement> existingNotifications;
    @FindBy(how = How.XPATH, using = "//div[@id='field-displayName']/input")
    private WebElement nameField;
    @FindBy(how = How.XPATH, using = "//div[@id='field-description']/input")
    private WebElement descriptionField;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement submitButton;

    public CreateSection(Browser browser, DeployNotifcationPages top) {
      this.browser = browser;
      this.top = top;
      this.browser.initElements(this);
    }

    public CreateSection open(String name) {
      this.browser.click(lookupButton);
      this.top.waitForSpinnerToVanish();
      for (WebElement row : existingNotifications) {
        String rowName = row.getText();
        if (name.equals(rowName)) {
          this.browser.click(row);
          return this;
        }
      }
      throw new IllegalStateException("There is no notification with name " + name);
    }

    public CreateSection setName(String name) {
      this.browser.sendKeys(nameField, name, true);
      return this;
    }

    public CreateSection setDescription(String description) {
      this.browser.sendKeys(descriptionField, description, true);
      return this;
    }

    public DeployNotifcationPages create() {
      this.browser.click(submitButton);
      this.top.waitForAjaxLoaderToVanish();
      return this.top;
    }
  }

  public static final class SiteUpdatesSection {

    private final Browser browser;
    private final DeployNotifcationPages top;

    @FindBy(how = How.LINK_TEXT, using = "My Changes")
    private WebElement myChanges;
    @FindBy(how = How.XPATH, using = "//td[@data-fieldname='itemName'][@data-fieldvalue]")
    private List<WebElement> notificationNames;
    @FindBy(how = How.XPATH, using = "//button[@data-urlpostfix='/workflow/promote']")
    private WebElement promoteButton;
    @FindBy(how = How.XPATH, using = "//div[@class='actions']/button[@data-actiontype='promote']")
    private WebElement promoteConformButton;
    @FindBy(how = How.XPATH, using = "//button[@data-url='/op-admin/workflow']")
    private WebElement syncSandbox;

    public SiteUpdatesSection(Browser browser, DeployNotifcationPages top) {
      this.browser = browser;
      this.top = top;
      this.browser.initElements(this);
    }

    public SiteUpdatesSection openMyChanges() {
      this.browser.click(myChanges);
      return this;
    }

    public SiteUpdatesSection openNotification(String name) {
      this.top.switchToFrame();
      for (WebElement node : notificationNames) {
        if (Utils.equalsIgnoreEllipsis(node.getText(), name)) {
          this.top.switchToFrame();
          this.browser.click(node);
          return this;
        }
      }
      throw new IllegalStateException("Created notification " + name
                                      + " is not present in the list.");
    }

    public SiteUpdatesSection promote() {
      this.browser.click(promoteButton);
      return this;
    }

    public DeployNotifcationPages conform() {
      this.browser.click(promoteConformButton);
      this.top.waitForAjaxLoaderToVanish();
      this.browser.click(syncSandbox);
      return this.top;
    }
  }

  public static final class ApprovalSection {

    private final Browser browser;
    private final DeployNotifcationPages top;

    @FindBy(how = How.XPATH, using = "//div[@class='listgrid-container']/label[text()='Items pending action for Automation']/..//td[@data-fieldname='name'][@data-fieldvalue]")
    private List<WebElement> notifications;
    @FindBy(how = How.XPATH, using = "//button[@data-urlpostfix='/workflow/approve']")
    private WebElement approveButton;
    @FindBy(how = How.XPATH, using = "//div[@class='actions']/button[@data-actiontype='approve']")
    private WebElement conformButton;
    @FindBy(how = How.XPATH, using = "//div[@class='modal-header']/h3")
    private WebElement inProgressWarning;
    @FindBy(how = How.XPATH, using = "//button[@class='close']")
    private WebElement closeButton;

    public ApprovalSection(Browser browser, DeployNotifcationPages top) {
      this.browser = browser;
      this.top = top;
      this.browser.initElements(this);
    }

    public ApprovalSection openNotification(String name) {
      this.top.switchToFrame();
      long time = System.currentTimeMillis() + TIMEOUT;
      while (time > System.currentTimeMillis()) {
        for (WebElement element : notifications) {
          String title = element.getAttribute("data-fieldvalue");
          if (title.contains(name)) {
            this.browser.click(element);
            return this;
          }
        }

        LOGGER.warn("The notification is not found in the list - refreshing the page and retrying.");
        this.browser.refresh();
        this.top.switchToFrame();
      }
      throw new IllegalStateException("The notification " + name
                                      + " is not found in the list, even after wait of "
                                      + TIMEOUT
                                      + " millis.");
    }

    public ApprovalSection approve() {
      this.browser.click(approveButton);
      return this;
    }

    @SuppressWarnings("null")
    public DeployNotifcationPages conform() {
      long time = System.currentTimeMillis() + TIMEOUT;
      TimeoutException saved = null;
      while (time > System.currentTimeMillis()) {
        try {
          this.browser.click(conformButton);
          this.top.waitForAjaxLoaderToVanish();
          return this.top;
        } catch (TimeoutException e) {
          String text = this.inProgressWarning.getText();
          if (text.toLowerCase().contains("progress")) {
            LOGGER.warn("The notification is still in progress - retrying.");
            this.browser.click(closeButton);
            approve();
            continue;
          } else {
            throw new IllegalStateException(text);
          }
        }
      }
      throw saved;
    }
  }

  public static final class DeleteSection {

    private final Browser browser;
    private final DeployNotifcationPages top;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Delete')]")
    private WebElement deleteButton;

    public DeleteSection(Browser browser, DeployNotifcationPages top) {
      this.browser = browser;
      this.top = top;
      this.browser.initElements(this);
    }

    public DeleteSection open(String name) {
      this.browser.click(By.xpath("//td[@data-fieldname='displayName'][@data-fieldvalue='" + name
                                  + "']/../td[@data-fieldname='description']//a"));
      this.top.switchToFrame();
      return this;
    }

    public DeployNotifcationPages delete() {
      this.browser.click(deleteButton);
      return this.top;
    }
  }

}
