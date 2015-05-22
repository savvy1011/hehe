package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class NotificationPromoteTest extends AbstractNotificationTest {

  private DeployNotifcationPages deployPage;
  private String created;

  public NotificationPromoteTest(
      Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    super(titlePageClass, rulePageClass);
  }

  @BeforeClass(alwaysRun = true)
  protected void setup() throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException {
    super.setup();
    this.deployPage = new DeployNotifcationPages(this.browser,
                                                 this.page.getTitleName());
  }

  @AfterClass(alwaysRun = true)
  public void cleanup() {
    portalPages.setTopNavLink(topnav);
    this.page.switchToFrame().clickOnSection();
    delete(true);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testCreateAndPromote() throws InterruptedException, IOException {
    String name = browser.uniqueName();
    String description = browser.randomText(512);
    create(name, description);
    this.todel.add(name);
    saveAndFinalize(name);
    created = createInOperator(name);
    promote(created);
    approve(created);
  }

  @Test(enabled = true, dependsOnMethods = "testCreateAndPromote", groups = { "Notification",
                                                                             "zactOnly",
                                                                             "zact" })
  public void testDeleteAndPromote() throws InterruptedException, IOException {
    browser.logAction("Deleting the operator notification " + created);
    switchToFrame();
    this.deployPage.openSection().openForDelete(created).delete();
    promote(created);
    approve(created);
    browser.logAction("Deleted the operator notification " + created);
  }

  private void saveAndFinalize(String name) {
    page.getContentEditPageSection()
        .setContentTitle(browser.randomText(128))
        .setContentBody(browser.randomText(128));
    String conformationMessage = page.getEditButtons().saveAndFinalize();
    assertSuccessfulSaveAndFinalize(conformationMessage);
    page.getEditButtons().waitForFinalization();
  }

  private String createInOperator(String name) {
    browser.logAction("Logging in to operator.");
    portalPages.setTopNavLink("operator");
    switchToFrame();
    this.deployPage.login(config.getBlUsername(), config.getBlPassword());

    browser.logAction("Creating operator notification for " + name);
    this.deployPage.openSection();

    String createdName = this.browser.uniqueName();
    this.deployPage.openCreateDialog(name)
                   .setName(createdName)
                   .setDescription(browser.randomText(128))
                   .create();
    browser.logAction("Created operator notification with name = " + createdName
                      + " for notification "
                      + name);
    return createdName;
  }

  private void promote(String name) {
    // Site updates
    browser.logAction("Promoting the operator notification " + name);
    switchToFrame();
    this.deployPage.openSiteUpdates()
                   .openMyChanges()
                   .openNotification(name)
                   .promote()
                   .conform();
    browser.logAction("Promoted the operator notification " + name);
  }

  private void approve(String name) {
    browser.logAction("Approving the operator notification " + name);
    this.deployPage.openApprovals().openNotification(name).approve().conform();
    browser.logAction("Approved the operator notification " + name);
  }

  private void switchToFrame() {
    this.browser.switchToFrame(By.id("operator-iframe-window"));
  }

}
