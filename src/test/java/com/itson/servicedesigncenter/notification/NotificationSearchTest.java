package com.itson.servicedesigncenter.notification;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import com.itson.servicedesigncenter.notification.NotificationList.Row;

public class NotificationSearchTest extends AbstractNotificationTest {

  public NotificationSearchTest(
      Class<? extends TitlePageSection> titlePageClass,
      Class<? extends RulePageSection<?>> rulePageClass) {
    super(titlePageClass, rulePageClass);
  }

  @Test(enabled = true, groups = { "Notification", "zactOnly", "zact" })
  public void testSearch() {
    String one = String.valueOf(UUID.randomUUID()).replace("-", "").trim();
    String two = String.valueOf(UUID.randomUUID()).replace("-", "").trim();
    String three = String.valueOf(UUID.randomUUID()).replace("-", "").trim();
    String four = String.valueOf(UUID.randomUUID()).replace("-", "").trim();

    Map<String, List<String>> expected = new TreeMap<>();
    String a = page.getCreateSection().clickAdd().setName(one).create();
    browser.logAction(String.format("Created notififcation, id = %s, name = %s in draft.",
                                    a,
                                    one));
    todel.add(one);
    addToExpected(expected, one, a);
    String b = page.clickOnSection()
                   .getCreatePageSection()
                   .clickAdd()
                   .setName(one + " " + two)
                   .create();
    browser.logAction(String.format("Created notififcation, id = %s, name = %s in draft.",
                                    b,
                                    one + " " + two));
    todel.add(one + " " + two);
    // JIRA - file bug
    addToExpected(expected, one + " " + two, b);
    addToExpected(expected, one, b);
    addToExpected(expected, two, b);
    String bDup = page.clickOnSection()
                      .getCreatePageSection()
                      .clickAdd()
                      .setName(one + " " + two)
                      .create();
    browser.logAction(String.format("Created notififcation, id = %s, name = %s in draft.",
                                    bDup,
                                    one + " " + two));
    todel.add(one + " " + two);
    addToExpected(expected, one + " " + two, bDup);
    addToExpected(expected, one, bDup);
    addToExpected(expected, two, bDup);

    String c = page.clickOnSection()
                   .getCreatePageSection()
                   .clickAdd()
                   .setName(two + " " + three + " " + four)
                   .create();
    browser.logAction(String.format("Created notififcation, id = %s, name = %s in draft.",
                                    c,
                                    two + " " + three + " " + four));
    todel.add(two + " " + three + " " + four);
    addToExpected(expected, two + " " + three + " " + four, c);
    addToExpected(expected, two + " " + three, c);
    addToExpected(expected, three + " " + four, c);
    addToExpected(expected, two, c);
    addToExpected(expected, three, c);
    addToExpected(expected, four, c);

    for (Entry<String, List<String>> entry : expected.entrySet()) {
      String keyword = entry.getKey();
      List<String> expectedResults = entry.getValue();
      NotificationList searchResult = page.clickOnSection()
                                          .getSearchSection()
                                          .search(keyword);
      List<String> found = new ArrayList<>();
      for (Row r : searchResult.getRows()) {
        found.add(r.id);
      }

      Collections.sort(found);
      Collections.sort(expectedResults);
      Assert.assertEquals(found, expectedResults, keyword);
    }
  }

  private void addToExpected(Map<String, List<String>> expected,
      String keyword, String id) {
    List<String> old = expected.get(keyword);
    if (old == null) {
      expected.put(keyword, new ArrayList<String>());
      old = expected.get(keyword);
    }
    old.add(id);
  }
}
