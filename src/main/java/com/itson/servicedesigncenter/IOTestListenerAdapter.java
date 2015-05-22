package com.itson.servicedesigncenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IOTestListenerAdapter extends TestListenerAdapter {

  private static final Logger LOGGER = LogManager.getFormatterLogger(Browser.class);
  private ConcurrentMap<String, Long> testTimes = new ConcurrentHashMap<String, Long>();

  static class ValueComparator implements Comparator<String> {
    Map<String, Long> base;

    ValueComparator(Map<String, Long> base) {
      this.base = base;
    }

    @Override
    public int compare(String a, String b) {
      if (base.get(a) >= base.get(b)) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  @Override
  public void onTestStart(ITestResult tr) {
    LOGGER.info("****Starting  test %s.%s%s",
                tr.getMethod().getTestClass().getRealClass().getSimpleName(),
                tr.getMethod().getMethodName(),
                tr.getParameters() != null ? " with parameters " + Arrays.toString(tr.getParameters())
                                          : "");
  }

  @Override
  public void onFinish(ITestContext testContext) {
    ValueComparator times = new ValueComparator(testTimes);
    TreeMap<String, Long> sorted_map = new TreeMap<String, Long>(times);
    // System.out.println("Test Times:");
    for (Map.Entry<String, Long> entry : sorted_map.entrySet()) {
      String key = entry.getKey();
      Long value = entry.getValue();
      System.out.println("  " + key + " => " + value);
    }
  }

  private void log(String status, ITestResult tr) {
    long time = tr.getEndMillis() - tr.getStartMillis();
    String className = tr.getMethod()
                         .getTestClass()
                         .getRealClass()
                         .getSimpleName();
    String str = status + ": " + className + "." + tr.getName() + "();" + time;
    testTimes.put(str, time);
    LOGGER.info(str);
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    log("ok  ", tr);
  }

  @Override
  public void onTestFailure(ITestResult tr) {
    log("nok ", tr);
  }

  @Override
  public void onTestSkipped(ITestResult tr) {
    log("skip", tr);
  }

}