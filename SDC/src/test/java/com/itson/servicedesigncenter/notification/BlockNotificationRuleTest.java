package com.itson.servicedesigncenter.notification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import com.itson.servicedesigncenter.notification.TitlePageSection.BlockTitlePageSection;

public class BlockNotificationRuleTest extends AbstractNotificationTest {

  private String name;
  private String description;

  public BlockNotificationRuleTest() {
    super(BlockTitlePageSection.class, BlockRulePageSection.class);
  }

  @BeforeClass(alwaysRun = true, dependsOnMethods = "setup")
  public void create() throws InterruptedException, IOException {
    name = browser.uniqueName();
    description = browser.randomText(512);
    create(name, description, name, description);
    todel.add(name);
    save(name, name, description);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testBlockAddRule() throws InterruptedException, IOException {
    page.clickOnSection()
        .open(name)
        .getBlockRulePageSection()
        .setMatch("Any")
        .addServicePolicyIdRule(1);

    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(name, description, "DRAFT");
    assertRule("None", "Service Policy ID", "is equal to");
  }

  private void assertRule(String selectMatch, String policyId, String equals) {
    BlockRulePageSection rules = page.clickOnSection()
                                     .open(name)
                                     .getBlockRulePageSection();
    // JIRA - SAAS-6847
    // Assert.assertEquals(rules.getSelectedMatchValue(), selectMatch);
    Assert.assertEquals(rules.getSelectedRuleTypeValue(0), policyId);
    Assert.assertEquals(rules.getSelectedRuleOperation(0), equals);
    List<String> list = rules.getRuleValues();
    Assert.assertEquals(new HashSet<String>(list).size(),
                        list.size(),
                        "There are duplicate rules: " + String.valueOf(list));
  }

  private void addRule(int index) {
    page.clickOnSection()
        .open(name)
        .getBlockRulePageSection()
        .addServicePolicyIdRule(index);

    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(name, description, "DRAFT");
    assertRule("None", "Service Policy ID", "is equal to");
  }

  @Test(enabled = true, dependsOnMethods = "testBlockAddRule", groups = { "Notification",
                                                                         "zactOnly",
                                                                         "zact" })
  public void testBlockChangeMatch() throws InterruptedException, IOException {
    page.clickOnSection().open(name).getBlockRulePageSection().setMatch("All");

    String conformationMessage = page.getEditButtons().save();
    // Assert if there is save failure
    Assert.assertEquals(conformationMessage, "Success");
    assertListAndSearch(name, description, "DRAFT");
    assertRule("All", "Service Policy ID", "is equal to");
  }

  @Test(enabled = true, dependsOnMethods = "testBlockChangeMatch", groups = { "Notification",
                                                                             "zactOnly",
                                                                             "zact" })
  public void testBlockAddMoreRuleDifferent() throws InterruptedException,
      IOException {
    addRule(2);
  }

}
