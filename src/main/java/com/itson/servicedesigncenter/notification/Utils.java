package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Utils {
  public static String findIDFromURL(String url) {
    int idx = url.indexOf("id=");
    if (idx > 0) {
      String temp = url.substring(idx + 3);
      idx = temp.indexOf("&");
      if (idx > 0) {
        temp = temp.substring(0, idx);
      }
      return temp;
    }

    return null;
  }

  public static boolean equalsIgnoreEllipsis(String in, String expected) {
    if (in.endsWith("...")) {
      in = in.substring(0, in.length() - 3);
      if (expected.length() > in.length()) {
        expected = expected.substring(0, in.length());
      }
    }
    return in.equals(expected);
  }

  public static WebElement last(List<WebElement> list) {
    return list.get(list.size() - 1);
  }
}
