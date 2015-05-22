package com.itson.servicedesigncenter.notification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import com.itson.servicedesigncenter.notification.TitlePageSection.UsageTitlePageSection;

public class UsageNotificationRuleTest extends AbstractNotificationTest {

  public UsageNotificationRuleTest() {
    super(UsageTitlePageSection.class, UsageRulePageSection.class);
  }

  private String name;
  private String description;

  @BeforeClass(alwaysRun = true, dependsOnMethods = "setup")
  public void create() throws InterruptedException, IOException {
    name = browser.uniqueName();
    description = browser.randomText(512);
    create(name, description, name, description);
    todel.add(name);
    save(name, name, description);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testUsageAddRule() throws InterruptedException, IOException {
    page.clickOnSection()
        .open(name)
        .getUsageRulePageSection()
        .setMatch("All")
        .addLimitRule("Product - Text Limit", "is equal to", "100")
        .addThresholdPercentage("Any", "Percentage used", "is equal to", "40")
        .addProductUpsell()
        .addMoreRules()
        .addThresholdUnit("Any", "Minutes Remaining", "is not equal to", "1290");

    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(name, description, "DRAFT");
  }
}
