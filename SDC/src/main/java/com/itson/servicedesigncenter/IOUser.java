package com.itson.servicedesigncenter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by harryjackson on 7/23/14.
 */
public class IOUser implements PortalUser {
  private String username;
  private String password;
  private boolean validUserName;
  private boolean validPassword;

  public IOUser(String name, String pass) {
    username = name;
    password = pass;
    validUserName = true;
    validPassword = true;

    try {
      InternetAddress emailAddr = new InternetAddress(username);
      emailAddr.validate();
    }
    catch (AddressException ex) {
      validUserName = false;
    }
  }

  public boolean isValid() {
    return validUserName && validPassword;
  }

  public String getUserName() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
