package com.itson.servicedesigncenter.notification;

import org.testng.annotations.Factory;

import java.util.ArrayList;
import java.util.List;

import com.itson.servicedesigncenter.notification.TitlePageSection.BlockTitlePageSection;
import com.itson.servicedesigncenter.notification.TitlePageSection.InterceptTitlePageSection;
import com.itson.servicedesigncenter.notification.TitlePageSection.UsageTitlePageSection;

public class TestFactory {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Factory
  public AbstractNotificationTest[] tests() {
    List<AbstractNotificationTest> list = new ArrayList<>();

    for (Class[] args : new Class[][] { new Class[] { BlockTitlePageSection.class,
                                                     BlockRulePageSection.class },
                                       new Class[] { UsageTitlePageSection.class,
                                                    UsageRulePageSection.class },
                                       new Class[] { InterceptTitlePageSection.class,
                                                    InterceptRulePageSection.class } }) {
      Class<? extends TitlePageSection> titlePageClass = args[0];
      Class<? extends RulePageSection<?>> rulePageClass = args[1];
      list.add(new NotificationHappyPathTest(titlePageClass, rulePageClass));
      list.add(new NotificationCreateTest(titlePageClass, rulePageClass));
      list.add(new NotificationEditTest(titlePageClass, rulePageClass));
      list.add(new NotificationSearchTest(titlePageClass, rulePageClass));
      list.add(new NotificationPromoteTest(titlePageClass, rulePageClass));
    }
    list.add(new BlockNotificationRuleTest());
    list.add(new InterceptNotificationRuleTest());
    list.add(new UsageNotificationRuleTest());

    return list.toArray(new AbstractNotificationTest[list.size()]);
  }
}
