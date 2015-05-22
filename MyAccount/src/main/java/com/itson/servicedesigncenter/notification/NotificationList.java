package com.itson.servicedesigncenter.notification;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationList {

  private final List<Row> list;

  public NotificationList() {
    this.list = new ArrayList<Row>();
  }

  public void add(String id, String title, String description, String state,
      WebElement link) {
    this.list.add(new Row(id, title, description, state, link));
  }

  public List<Row> findByID(String id) {
    List<Row> result = new ArrayList<Row>();
    for (Row x : list) {
      if (x.id.equals(id)) {
        result.add(x);
      }
    }
    return result;
  }

  public boolean containsByID(String id) {
    return !findByID(id).isEmpty();
  }

  public List<Row> getRows() {
    return Collections.unmodifiableList(list);
  }

  public static final class Row {
    public final String id;
    public final String title;
    public final String description;
    public final String state;
    public final WebElement link;

    public Row(String id, String title, String description, String state,
        WebElement link) {
      super();
      this.id = id;
      this.title = title;
      this.description = description;
      this.state = state;
      this.link = link;
    }
  }

}
