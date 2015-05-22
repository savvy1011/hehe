package com.itson.servicedesigncenter;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Config {

  private HashMap map;
  private String hostUrl;
  private String hostUrlSdc;
  private String hostUrlBl;
  private String hostUrlMyAccount;

  private String configFile;
  private URL hub;
  private String browser_type;
  private String browser_version;
  private String browser_platform;
  private String browser_platform_version;
  private String testMDN;
  private String cookieName;
  private String site_view;

  private String tenantID;
  private String parnterID;
  private String grant_type;
  private String username;
  private String password;
  private String adminName;
  private String adminPassword;
  private String client_secret;
  private String oAuthUrl;
  private String adminUrl;
  private String adapterUrl;

  private String blUsername;
  private String blPassword;
  private String sandbox;
  private String verify_username;
  private String verify_password;
  private String partnerName;

  public Config() {
    System.setProperty("envFile", "/Users/ha/_WORK/_PROJECT/Evizi_AT_SAAS/Code/SDC/web_testing/ServiceDesignCenter/testng/env/qaZactMobile.json");
    System.setProperty("hub", "qahub");
    
    String envFile = System.getProperty("envFile");
    if (envFile == null) {
      throw new IllegalStateException("No -DenvFile=[filename] specified on command line\n");
    }
    if (envFile.isEmpty()) {
      throw new IllegalStateException("Invalid -DenvFile=[" + envFile
              + " ] specified on command line\n");
    }
    this.init(envFile);
  }

  public Config(String filename) {
    this.init(filename);
  }

  private void init(String filename) {
    configFile = filename;
    //browser.log("Working Directory = " + System.getProperty("user.dir"));
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(configFile));
    }
    catch (FileNotFoundException e) {
      String pwd = "Unable to find file" + filename + " from " + " working directory = " + System.getProperty("user.dir");
      e.printStackTrace();
      throw new IllegalArgumentException(pwd);
    }
    Gson gson = new Gson();
    map = new HashMap<String, String>();
    map = (HashMap<String, String>) gson.fromJson(br, map.getClass());
    this.hostUrlSdc = (String) map.get("hostUrlSdc");
    testMDN = (String) map.get("testMDN");
    this.tenantID = (String) map.get("tenantID");
    this.parnterID = (String) map.get("parnterID");
    this.grant_type = (String) map.get("browser_platform");
    username = (String) map.get("username");
    password = (String) map.get("password");
    adminName = (String) map.get("adminuser");
    adminPassword = (String) map.get("adminpassword");
    client_secret = (String) map.get("client_secret");
    oAuthUrl = (String) map.get("oAuthUrl");
    adminUrl = (String) map.get("adminUrl");
    adapterUrl = (String) map.get("adapterUrl");

    hostUrlBl = (String) map.get("hostUrlBl");
    hostUrlMyAccount = (String) map.get("hostUrlMyAccount");
    blUsername = (String) map.get("blUsername");
    blPassword = (String) map.get("blPassword");
    sandbox = (String) map.get("sandbox");
    partnerName = (String) map.get("partnerName");
    cookieName = null;//(String) map.get("cookieName");

  }

  private static void checkString(String name, String arg) {
    String err = "Bad argument in Config file: " + name;
    if (arg == null) {
      throw new NullPointerException(err + " is NULL");
    }
    if (arg.isEmpty()) {
      throw new NullPointerException(err + " is EMPTY");
    }
  }

  public String getHostUrl(String site) {
    site = site.toLowerCase();
    String checkUrl = System.getProperty("hostUrl");
    if (checkUrl == null || checkUrl.equals("")) {
      switch (site) {
        case "bl":
          hostUrl = hostUrlBl;
          break;
        case "broadleaf":
          hostUrl = hostUrlBl;
          break;
        case "myaccount":
          hostUrl = hostUrlMyAccount;
          break;
        default:
          hostUrl = hostUrlSdc;//default value
          break;
      }

    }
    else {
      hostUrl = checkUrl;
    }
    return hostUrl;
  }

  public String getConfigFile() {
    return configFile;
  }

  public String toString() {
    String str = new StringBuilder()
            .append("configFile:" + configFile + "\n")
            .append("hostUrl:" + hostUrl + "\n")
            .toString();
    return str;
  }

  public String getMDN() {
    return testMDN;
  }

  public String getCookieName() {
    if (cookieName == null) {
      return "PHPSESSID";
    }
    return cookieName;
  }

  public String getTenantID() {
    return tenantID;
  }

  public String getParnterID() {
    return parnterID;
  }

  public String getGrant_type() {
    return grant_type;
  }

  public String getClientSecret() {
    return client_secret;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAdminUsername() {
    return adminName;
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public String getoAuthUrl() {
    return oAuthUrl;
  }

  public String getAdminUrl() {
    return adminUrl;
  }

  public String getAdapterUrl() {
    return adapterUrl;
  }
    public String getHostBlUrl() {
    return hostUrlBl;
  }

// parmters come from command line if not called or left blank deafult value is given
  public String getHub() {
    String hubUrl = System.getProperty("hub");
    if (hubUrl == null || hubUrl.equals("")) {
      hubUrl = "qahub";//default value
    }
    return hubUrl;
  }

  public String getBrowserPlatform() {
    browser_platform = System.getProperty("bPlatform");
    if (browser_platform == null || browser_platform.equals("")) {
      browser_platform = "ANY";//default value
    }
    if (browser_platform.equals("windows7")) { //needed for saucelabs
      browser_platform = "VISTA";
    }
    return browser_platform;
  }

  public String getBrowserPlatformVersion() {
    browser_platform_version = System.getProperty("bPlatformVersion");
    if (browser_platform_version == null || browser_platform.equals("")) {
      browser_platform_version = "ANY";//default value
    }
    return browser_platform_version;
  }

  public String getBrowserType() {
    browser_type = System.getProperty("bType");
    if (browser_type == null || browser_type.equals("")) {
      browser_type = "firefox"; //default value
    }
    if (browser_type.equals("ie")) { //needed for saucelabs
      browser_type = "internet explorer";
    }
    return browser_type;
  }

  public String getBrowserVersion() {
    browser_version = System.getProperty("bVersion");
    if (browser_version == null || browser_version.equals("")) {
      browser_version = "ANY";//default value
    }
    return browser_version;
  }

  public String getView() {
    site_view = System.getProperty("view");
    if (site_view == null || site_view.equals("")) {
      site_view = "broadleaf";//default value
    }
    return site_view;
  }

  public String getTestName() {
    String testName = System.getProperty("testName");
    if (testName == null) {
      testName = "webauto1";
    }
    return testName;
  }

  public String getBlUsername() {
    return blUsername;
  }

  public String getBlPassword() {
    return blPassword;
  }

  public String getSandbox() {
    return sandbox;
  }
   public String getPartnerName() {
    return partnerName;
  }

  public String getVerifyUsername() {
    verify_username = "verify";
    return verify_username;
  }

  public String getVerifyPassword() {
    verify_password = "SOASTA650";
    return verify_password;
  }
}
