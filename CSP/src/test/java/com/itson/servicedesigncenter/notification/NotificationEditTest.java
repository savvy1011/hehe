package com.itson.servicedesigncenter.notification;

import org.testng.annotations.Test;

import java.io.IOException;

public class NotificationEditTest extends AbstractNotificationTest {

  public NotificationEditTest(Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    super(titlePageClass, rulePageClass);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testNotificationEdit() throws InterruptedException, IOException {
    String name = browser.uniqueName();
    String description = browser.randomText(512);
    create(name, description, name, description);
    save(name, name, description);
    delete(name, false);
  }
}
