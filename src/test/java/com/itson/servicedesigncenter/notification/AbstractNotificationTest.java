/**
 * Checks the notification section
 *
 * @author Supriya Singh
 */
package com.itson.servicedesigncenter.notification;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itson.servicedesigncenter.AbstractTest;
import com.itson.servicedesigncenter.Browser;
import com.itson.servicedesigncenter.notification.NotificationList.Row;

public abstract class AbstractNotificationTest extends AbstractTest {

  private static final Logger LOGGER = LogManager.getFormatterLogger(AbstractNotificationTest.class);
  public static final Set<String> SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES;
  static {
    SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES = new HashSet<String>();
    SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES.add("Success");
    SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES.add("This notification body is finalizing. You cannot use it until it’s final. It may take a few minutes to become final. You do not have to stay on this page.");
    SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES.add("This notification body is finalized and ready to be used. It cannot be edited. Click the “Clone and Edit” button to create a separate version of this notification body.");
  }
  private final Class<? extends TitlePageSection> titlePageClass;
  private final Class<? extends RulePageSection<?>> rulePageClass;
  protected final List<String> todel;
  protected NotificationPage page;

  public AbstractNotificationTest(
      Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    this.titlePageClass = titlePageClass;
    this.rulePageClass = rulePageClass;
    this.todel = new ArrayList<>();
  }

  @BeforeClass(alwaysRun = true)
  protected void setup() throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException {
    topnav = "notification";
    portalPages.setTopNavLink(topnav);
    TitlePageSection titleSection = this.titlePageClass.getConstructor(Browser.class)
                                                       .newInstance(browser);
    RulePageSection<?> ruleSection = this.rulePageClass.getConstructor(Browser.class)
                                                       .newInstance(browser);
    this.page = new NotificationPage(browser, titleSection, ruleSection);
    this.page.switchToFrame().clickOnSection();
  }

  protected void create(String name, String description)
      throws InterruptedException, IOException {
    browser.logAction("Creating notififcation, name = " + name + " in draft.");
    String id = page.getCreateSection()
                    .clickAdd()
                    .setName(name)
                    .setOperatorDescription(description)
                    .create();
    Assert.assertNotNull(id);
  }

  protected void create(String name, String description, String savedName,
      String savedDescription) throws InterruptedException, IOException {
    create(name, description);
    assertListAndSearch(savedName, savedDescription, "DRAFT");
  }

  protected void delete(String name, boolean conform)
      throws InterruptedException, IOException {
    browser.logAction("Deleting notification with name = " + name);
    page.clickOnSection().open(name);
    page.getEditButtons().delete(conform);
    Assert.assertEquals(page.search(name).findByID(page.getID()).size(), 0);
  }

  protected void assertListAndSearch(String name, String description,
      String... states) {
    // JIRA SAAS-6743 - uncomment next line once the bug is resolved.
    // assertSingleRow(page.list(), name, description, state, page.getId());
    assertSingleRow(page.clickOnSection().getSearchSection().search(name),
                    name,
                    description,
                    states,
                    page.getID());
  }

  private void assertSingleRow(NotificationList result, String name,
      String description, String[] states, String expectedId) {
    List<Row> list = result.findByID(expectedId);
    Assert.assertEquals(list.size(), 1);
    Row row = list.get(0);
    assertEquals(row.id, expectedId);
    assertEquals(row.title, name.replaceAll("\\s+", " "));
    // Assert.assertEquals(row.description, description);
    Assert.assertTrue(Arrays.asList(states).contains(row.state),
                      String.valueOf(row.state));
    assertUniqueResults(result);
  }

  private void assertEquals(String actual, String expected) {
    if (actual != null && expected != null && actual.endsWith("...")) {
      actual = actual.substring(0, actual.length() - 3);
      if (expected.length() > actual.length()) {
        expected = expected.substring(0, actual.length());
      }
    }
    Assert.assertEquals(actual, expected);
  }

  private void assertUniqueResults(NotificationList result) {
    Map<String, Row> map = new HashMap<>();
    for (Row row : result.getRows()) {
      Row old = map.get(row.id);
      if (old == null) {
        map.put(row.id, old);
      } else {
        Assert.fail("Notification " + row.id + " is duplicated.");
      }
    }
  }

  protected void save(String name, String changedName, String changedDescription)
      throws InterruptedException, IOException {
    browser.logAction("Adding title and body.");

    // Change name and description
    page.clickOnSection()
        .open(name)
        .getDescriptionEditPageSection()
        .setEditName(changedName)
        .setEditOperatorDescription(changedDescription);

    // Edit content section
    page.getContentEditPageSection()
        .setContentTitle(browser.randomText(128))
        .setContentBody(browser.randomText(2048))
        .addLaunchURLContentAction(browser.randomName(),
                                   browser.randomName(),
                                   "English (United Kingdom)",
                                   browser.randomName());
    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(changedName, changedDescription, "DRAFT");
  }

  protected void saveAndFinalize(String name, String description)
      throws InterruptedException, IOException {
    browser.logAction("Clicking on save and finalize.");
    String conformationMessage = page.clickOnSection()
                                     .open(name)
                                     .getEditButtons()
                                     .saveAndFinalize();
    assertSuccessfulSaveAndFinalize(conformationMessage);
    assertListAndSearch(name, description, "PROCESSED", "PROCESSING");
  }

  protected void assertSuccessfulSaveAndFinalize(String conformationMessage) {
    Assert.assertTrue(SUCCESSFUL_SAVE_AND_FINALIZE_MESSAGES.contains(conformationMessage),
                      conformationMessage);
  }

  protected void clone(String original, String name, String description)
      throws InterruptedException, IOException {
    browser.logAction("Cloning notififcation, name = " + name
                      + " from "
                      + original);
    String id = page.clickOnSection()
                    .open(original)
                    .cloneNotification()
                    .setName(name)
                    .setOperatorDescription(description)
                    .create();
    Assert.assertNotNull(id);
    assertListAndSearch(name, description, "DRAFT");
  }

  @AfterClass(alwaysRun = true)
  public void cleanup() {
    delete(false);
  }

  protected void delete(boolean conform) {
    for (String name : todel) {
      try {
        page.clickOnSection().openWithInfinteScroll(name);
        page.getEditButtons().delete(conform);
      } catch (AssertionError e) {
        // JIRA SAAS-6743 - remove the try - catch once the bug is resolved.
      } catch (WebDriverException e) {
        LOGGER.error("Unable to delete notification " + name, e);
        try {
          browser.takeScreenshot(String.format("%s.%s-cleanup.png",
                                               getClass().getSimpleName(),
                                               name));
        } catch (IOException e1) {
          LOGGER.error("Unable to take screenshot", e1);
        }
      }
    }
    todel.clear();
  }

  @AfterMethod(alwaysRun = true)
  public void takeScreenshotOnError(ITestResult result) throws IOException {
    if (browser != null && !result.isSuccess()) {
      try {
        browser.takeScreenshot(getClass().getSimpleName() + "."
                               + result.getMethod().getMethodName()
                               + "-"
                               + System.currentTimeMillis()
                               + ".png");
      } catch (WebDriverException e) {
        e.printStackTrace();
        browser.logAction("Couldnot take the screenshot.");
      }
    }
  }
}
