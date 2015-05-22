package com.itson.servicedesigncenter.notification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import com.itson.servicedesigncenter.notification.TitlePageSection.InterceptTitlePageSection;

public class InterceptNotificationRuleTest extends AbstractNotificationTest {

  private String name;
  private String description;

  public InterceptNotificationRuleTest() {
    super(InterceptTitlePageSection.class, InterceptRulePageSection.class);
  }

  @BeforeClass(alwaysRun = true, dependsOnMethods = "setup")
  public void create() throws InterruptedException, IOException {
    name = browser.uniqueName();
    description = browser.randomText(512);
    create(name, description, name, description);
    todel.add(name);
    save(name, name, description);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" }, description = "Adding test case for validating intercept rule")
  public void testInterceptAddRule() throws InterruptedException, IOException {
    page.clickOnSection()
        .open(name)
        .getInterceptRulePageSection()
        .setMatch("Any")
        .addServicePolicyIdRule(1)
        .addReasonRule("Cap No Match")
        .addProductUpsell()
        .addMoreInterceptRules()
        .addServicePolicyIdRule(2)
        .addReasonRule("No Capable Plan")
        .addProductUpsell();

    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(name, description, "DRAFT");
  }
}
