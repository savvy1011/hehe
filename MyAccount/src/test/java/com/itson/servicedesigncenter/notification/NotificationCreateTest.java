package com.itson.servicedesigncenter.notification;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.itson.servicedesigncenter.notification.NotificationList.Row;

public class NotificationCreateTest extends AbstractNotificationTest {

  public static final String POSITIVE = "positive";

  public NotificationCreateTest(
      Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    super(titlePageClass, rulePageClass);
  }

  @DataProvider(name = POSITIVE)
  public Object[][] positive() {
    return new Object[][] { new Object[] { "通知標題",
                                          "我不能改變風的方向，但我可以調整我的風帆永遠到達我的目的地。",
                                          null,
                                          null },
                           new Object[] { browser.uniqueName(),
                                         browser.randomText(512),
                                         null,
                                         null },
                           new Object[] { "<b>Name</b>", "", "Name", "" },
                           new Object[] { "x   x", "", "x   x", "" },
                           new Object[] { "Name with javascript injection <script>alert('Injected!');</script>",
                                         "",
                                         "Name with javascript injection alert&#40;'Injected!'&#41;;",
                                         "" } };
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" }, dataProvider = POSITIVE, description = "Tests the create functionality of nofitication")
  public void testCreatePositiveTest(String name, String description,
      String savedName, String savedDescription) throws InterruptedException,
      IOException {
    create(name, description);
    if (savedName == null) {
      savedName = name;
    }
    if (savedDescription == null) {
      savedDescription = description;
    }
    assertListAndSearch(savedName, savedDescription, "DRAFT");
    delete(savedName, false);
  }

  @Test(enabled = false, groups = { "Notification", "zactOnly", "zact" })
  public void testDeleteTestData() throws InterruptedException, IOException {
    NotificationList list = page.clickOnSection()
                                .getSearchSection()
                                .search("webqe");
    SortedMap<String, String> all = new TreeMap<>();
    for (Row r : list.getRows()) {
      all.put(r.title, r.state);
    }
    for (Entry<String, String> entry : all.entrySet()) {
      String x = entry.getKey();
      String state = entry.getValue();
      try {
        page.clickOnSection().open(x);
        page.getEditButtons().delete("PROCESSED".equals(state));
      } catch (AssertionError e) {
      }
    }
  }

}
