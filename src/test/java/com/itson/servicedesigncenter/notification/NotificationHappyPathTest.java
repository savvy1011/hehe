package com.itson.servicedesigncenter.notification;

import org.testng.annotations.Test;

import java.io.IOException;

public class NotificationHappyPathTest extends AbstractNotificationTest {

  public NotificationHappyPathTest(
      Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    super(titlePageClass, rulePageClass);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testEndToEnd() throws InterruptedException, IOException {
    String name = browser.uniqueName();
    String description = browser.randomText(512);
    create(name, description, name, description);
    save(name, name, description);
    saveAndFinalize(name, description);
    String clonedName = browser.uniqueName();
    String clonedDescripton = browser.randomText(512);
    clone(name, clonedName, clonedDescripton);
    delete(name, true);
    delete(clonedName, false);
  }

}
