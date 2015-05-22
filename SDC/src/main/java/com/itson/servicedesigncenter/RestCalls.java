/*
 * Create fixtures form API 
 *  @author gurtejphangureh
 * 
 */
package com.itson.servicedesigncenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Reporter;

public class RestCalls {

  protected int responseCode;
  public String access_token;
  public String savedOAuthUrl;
  public Map<String, String> savedMapOfHeader;
  protected String adminAccountId;
  protected String email;
  protected String accountPassword = "1Newpass!";
  protected String phoneNumber;
  protected String subscriberId;
  protected String savedIMSI;
  protected String hardwareId;
  protected String idSaved;
  protected String accountId;
  protected String zactEmail;
  protected String myAccountEmail;
  protected String saasAccountId;
  protected String savedSaasAccountId;

  protected String customerId;

  protected Config config = new Config();

  protected String tenantID = config.getTenantID();
  protected String parnterID = config.getParnterID();
  protected String grant_type = config.getGrant_type();
  protected String usernameApi = config.getUsername();
  protected String passwordApi = config.getPassword();
  protected String client_id;
  protected String client_secret = config.getClientSecret();
  protected String oAuthUrl = config.getoAuthUrl();
  protected String adminUrl = config.getAdminUrl();
  protected String roleIdAdmin = "d94abe80-9383-11e3-baa8-0800200c9a66";
  protected String roleIdViewer = "d94abe83-9383-11e3-baa8-0800200c9a66";
  protected String roleIdCspAdmin = "9ed6fcc8-ac69-47c6-a0bf-ec1a55f15860";
  protected String roleIdCspViewer = "adc58a36-5c4f-4ed3-82e3-0c3710f469d6";
  protected String roleId = null;
  protected String adapterUrl = config.getAdapterUrl();

  public void sendPost(String url, Map<String, String> headerMap) {
    try {
      HttpClient client = HttpClientBuilder.create().build();
      HttpPost post = new HttpPost(url);

      for (String each : headerMap.keySet()) {
        post.setHeader(each, headerMap.get(each));
      }

      HttpResponse response = client.execute(post);

      responseCode = response.getStatusLine().getStatusCode();

      logAction("\nSending 'POST' request to URL : " + url);
      logAction("Response Code : " + responseCode);

      BufferedReader rd = new BufferedReader(
              new InputStreamReader(response.getEntity().getContent()));

      StringBuffer result = new StringBuffer();
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      access_token = result.toString();
      String[] data = access_token.split("\"");
      access_token = data[3];
      saveAccess_token(access_token);
      logAction("Saved access_token " + access_token);
      logAction(result.toString());
    }
    catch (IOException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void getOauth() {
    logAction("Getting oauth");
    saveOAuthUrl(oAuthUrl);
    //Setting header for the POST call.
    Map<String, String> mapOfHeader = new HashMap();
    mapOfHeader.put("User-Agent", "Mozilla/5.0");
    mapOfHeader.put("X-IO-Tenant-Id", tenantID);
    mapOfHeader.put("X-IO-Partner-Id", parnterID);
    mapOfHeader.put("Content-Type", "application/x-www-form-urlencoded");

    //Setting up path params.
    List<NameValuePair> listOfPathParams = new LinkedList();
    listOfPathParams.add(new BasicNameValuePair("grant_type", grant_type));
    listOfPathParams.add(new BasicNameValuePair("username", usernameApi));
    listOfPathParams.add(new BasicNameValuePair("password", passwordApi));
    listOfPathParams.add(new BasicNameValuePair("client_id", client_id));
    listOfPathParams.add(new BasicNameValuePair("client_secret", client_secret));
    saveMapOfHeader(mapOfHeader);
    sendPost(oAuthUrl, mapOfHeader);

  }

  public void logAction(String msg) {
    System.out.println(msg);

  }

  public void saveOAuthUrl(String given) {
    savedOAuthUrl = given;
  }

  public void saveAccess_token(String given) {
    access_token = given;
  }

  public void saveMapOfHeader(Map m) {

    savedMapOfHeader = m;
  }

  public String getPassword() {
    return accountPassword;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void createAdminAccount(String role, String givenEmail) {
    HttpClient client = new DefaultHttpClient();

    //get oauth 
    getOauth();
    logAction("Creating Account");
    adminAccountId = createRandomNumber(9);
    email = givenEmail;

    logAction("Creating Account  " + givenEmail);
    HttpPost httppost = new HttpPost(adminUrl + "adminaccount/");
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      JSONObject a = new JSONObject();
      a.append("id", adminAccountId); //adminAccountId
      a.append("partnerId", parnterID);
      a.append("email", email);
      a.append("passwordhash", accountPassword);
      a.append("verificationcode", "nowtestgdj");
      a.append("firstname", "Automated");
      a.append("lastname", "Policy");
      a.append("secretquestion", "DoesNoTMATTER");
      a.append("secretanswer", "LIFE");
      a.append("inactive", false);

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
    accountStatus();
    //add role
    accountRole(role);
    //return email;
  }

  public void accountRole(String role) {
    logAction("Adding Role " + role);
    HttpClient client = new DefaultHttpClient();
    idSaved = createRandomNumber(9);

    //get role
    switch (role.toLowerCase()) {
      case "admin":
        roleId = roleIdAdmin;
        break;
      case "cspadmin":
        roleId = roleIdCspAdmin;
        break;
      case "cspviewer":
        roleId = roleIdCspViewer;
        break;
      case "viewer":
        roleId = roleIdViewer;
        break;
    }
    HttpPost httppost = new HttpPost(adminUrl + "adminaccountrole");
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      JSONObject a = new JSONObject();
      a.append("id", idSaved);
      a.append("adminAccountId", adminAccountId);
      a.append("roleId", roleId);
      a.append("lastUpdateBy", "WebAuto");

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String provisioningDeviceSprint() {
    logAction("Device Provisioning");
    HttpClient client = new DefaultHttpClient();
    phoneNumber = createRandomNumber(10);
    savedIMSI = "310120" + createRandomNumber(9);//random but beginning should be 310120
    hardwareId = "3582" + createRandomNumber(10);
    logAction("provisioningDeviceSprint Got This Hardware id " + hardwareId);
    subscriberId = "sprint_" + phoneNumber;//random append “sprint_"
    String line = null;

    HttpPost httppost = new HttpPost(adapterUrl);
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");
      httppost.addHeader("X-IO-Hardware-Id", hardwareId);

      JSONObject a = new JSONObject();
      a.put("IMSI", savedIMSI);
      a.put("MSID", phoneNumber);
      a.put("MDN", phoneNumber);
      a.put("ESN_HEX", hardwareId);
      a.put("SPRINT_ACCOUNT_ID", "Sprint_12345");
      a.put("SPRINT_SUBSCRIBER_ID", subscriberId);
      a.put("BILLING_ZIP", "94680");
      a.put("LAST_TRX_DATE", System.currentTimeMillis());
      a.put("LAST_TRX_CODE", "NAC");
      a.put("PRIMARY_NAI", "");
      a.put("DEVICE_SKU", "ITSON");
      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      line = "";
      while ((line = rd.readLine()) != null) {
        logAction("from here" + line);

      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
    logAction("Phonenumber Created: " + phoneNumber);
    return phoneNumber;
  }

  public String provisioningDeviceZact() {
    logAction("Device Provisioning Zact");
    HttpClient client = new DefaultHttpClient();
    phoneNumber = createRandomNumber(10);
    savedIMSI = "310120" + createRandomNumber(9);//random but beginning should be 310120
    hardwareId = "3582" + createRandomNumber(10);
    logAction("Got hardwareid " + hardwareId);
    String line = null;

    HttpPost httppost = new HttpPost("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/partner/" + parnterID + "/subscriber/provision");
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");
      httppost.addHeader("X-IO-Hardware-Id", hardwareId);

      JSONObject a = new JSONObject();
      a.put("min", phoneNumber);
      a.put("imsi", savedIMSI);
      a.put("deviceIdType", "MEID");
      a.put("phoneNumber", phoneNumber);
      a.put("provisionTimestamp", System.currentTimeMillis());
      a.put("receivedTimestamp", System.currentTimeMillis());
      a.put("hardwareIdType", 1);
      a.put("hardwareId", hardwareId);

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      line = "";
      while ((line = rd.readLine()) != null) {
        logAction("from here" + line);
        String[] saveData = line.split("\"");
        subscriberId = saveData[5];
        logAction("Got subscriberId " + subscriberId);

      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
    logAction("Phonenumber Created: " + phoneNumber);
    return phoneNumber;
  }

  public void deprovisioningDevice() {
    logAction("Device DeProvisioning");
    HttpClient client = new DefaultHttpClient();
    String line = null;
    if (!tenantID.toLowerCase().equals("zact")) {
      try {
        getSubScriberNetworkId();
      }
      catch (Exception ex) {
        Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    HttpPost httppost = new HttpPost("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/partner/" + parnterID + "/subscriber/deprovision");
    try {
      httppost.addHeader("Connection", "close");
      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("Content-Type", "application/json");
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("X-IO-Hardware-Id", hardwareId);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("User-Agent", "Apache-HttpClient/4.2.6 (java 1.5)");

      JSONObject a = new JSONObject();

      a.put("subscriberNetworkId", subscriberId);
      a.put("provisionTimestamp", System.currentTimeMillis());
      a.put("receivedTimestamp", System.currentTimeMillis());
      String now = a.toString();
      logAction(now);
      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void accountStatus() {
    logAction("Acoount Status");
    HttpClient client = new DefaultHttpClient();

    HttpPut httpPut = new HttpPut(adminUrl + "adminaccount/" + adminAccountId + "/verificationstatus/true");
    try {

      httpPut.addHeader("Authorization", "Bearer " + access_token);
      httpPut.addHeader("X-IO-Tenant-Id", tenantID);
      httpPut.addHeader("X-IO-Partner-Id", parnterID);
      httpPut.addHeader("Content-Type", "application/json");

      HttpResponse response = client.execute(httpPut);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String createRandomNumber(int charNumber) {

    String allowedChars = "123456789";
    String temp = RandomStringUtils.random(charNumber, allowedChars);
    String randomName = temp;
    logAction("Random  Number :" + randomName);
    return randomName;

  }

  public String createRandomEmail() {
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    String reportDate = df.format(today);
    String remove = reportDate.replaceAll("[/:a-zA-Z]", "");
    String removespace = remove.replaceAll("\\s", "");
    String allowedChars = "abcdefghijklmnopqrstuvwxyz";
    String temp = RandomStringUtils.random(5, allowedChars);
    String randomName = "auto" + temp + removespace;
    Reporter.log("Created random name :" + randomName);
    randomName = randomName + "@itsoninc.net";
    logAction("Random  email :" + randomName);

    return randomName;
  }

  public String getUtcTimeStamp() {
    long getUnixTime = System.currentTimeMillis();
    logAction("UnixTime is: " + getUnixTime);
    String unixTime = Long.toString(getUnixTime);
    return unixTime;
  }

  public void getSubScriberNetworkId() throws Exception {
    logAction("Getting ScriberNetworkId");
    SSLContextBuilder builder = new SSLContextBuilder();
    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

    CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
            sslsf).build();
    logAction("given hadware id " + hardwareId);
    HttpGet httpGet = new HttpGet("https://home-di01-service.qa.itsonsaas.net:8443/hls/services/1.0/bootstrap/" + savedIMSI);
    CloseableHttpResponse response = null;
    try {
      httpGet.addHeader("Authorization", "Bearer " + access_token);
      httpGet.addHeader("X-IO-Tenant-Id", tenantID);
      httpGet.addHeader("X-IO-Hardware-Id", hardwareId);
      httpGet.addHeader("X-IO-Partner-Id", parnterID);
      response = httpclient.execute(httpGet);

      HttpEntity entity = response.getEntity();
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");
        subscriberId = saveData[11];
        logAction("Got subscriberId " + saveData[11]);
      }
      EntityUtils.consume(entity);

    } finally {
      response.close();
    }
  }

  public void getAccoutId() throws Exception { //need when associating an email to a subscriber , only on zact
    logAction("Getting Account Id");

    HttpClient client = new DefaultHttpClient();
    String line = null;
    zactEmail = createRandomEmail();

    SSLContextBuilder builder = new SSLContextBuilder();
    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

    CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
            sslsf).build();
    HttpPost httppost = new HttpPost("https://310120.home.qa.itsonsaas.net:8443/hls/services/1.0/account/discover/" + subscriberId);
    CloseableHttpResponse response = null;
    try {
      httppost.addHeader("Connection", "close");
      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("Content-Type", "application/json");
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("X-IO-Hardware-Id", hardwareId);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("User-Agent", "Apache-HttpClient/4.2.6 (java 1.5)");

      JSONObject a = new JSONObject();

      a.put("loginId", zactEmail);
      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);

      response = httpclient.execute(httppost);

      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      while ((line = rd.readLine()) != null) {
        logAction("&&" + line);
        String[] data1 = line.split("\"");
        accountId = data1[11];

      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    logAction("getAccoutId: " + accountId);
  }

  public void deleteAccountRole() {
    logAction("Deleteing Account Role");
    HttpClient client = new DefaultHttpClient();
    String url = adminUrl + "/adminaccount/" + adminAccountId + "/roles/" + roleId;
    HttpDelete httpDelete = new HttpDelete(url);

    try {

      httpDelete.addHeader("Authorization", "Bearer " + access_token);
      httpDelete.addHeader("X-IO-Tenant-Id", tenantID);
      httpDelete.addHeader("X-IO-Partner-Id", parnterID);
      httpDelete.addHeader("Content-Type", "application/json");

      HttpResponse response = client.execute(httpDelete);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    deleteAdminAccount();
  }

  public void deleteAdminAccount() {
    logAction("Deleteing Account Role");
    HttpClient client = new DefaultHttpClient();
    String url = adminUrl + "/adminaccount/" + adminAccountId;
    logAction("got url " + url);
    HttpDelete httpDelete = new HttpDelete(url);

    try {

      httpDelete.addHeader("Authorization", "Bearer " + access_token);
      httpDelete.addHeader("X-IO-Tenant-Id", tenantID);
      httpDelete.addHeader("X-IO-Partner-Id", parnterID);
      httpDelete.addHeader("Content-Type", "application/json");

      HttpResponse response = client.execute(httpDelete);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String addEmailToAccount() throws Exception {
    logAction("Adding Email to Account " + subscriberId);
    HttpClient client = new DefaultHttpClient();
    String line = null;

    HttpPost httppost = new HttpPost("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/subscriber/account/");
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");
      httppost.addHeader("X-IO-Hardware-Id", hardwareId);

      JSONObject a = new JSONObject();
      a.put("id", accountId);
      a.put("partnerId", parnterID);
      a.put("tenantId", tenantID);
      a.put("carrierAccountId", zactEmail);
      a.put("lastUpdateBy", "Auto");
      a.put("billingzip", "94680");
      a.put("utcTimeStamp", System.currentTimeMillis());
      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);

      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    logAction("Phonenumber Created: " + phoneNumber);
    return phoneNumber;
  }

  public String updateSubscriber() throws Exception {
    logAction("Update Subscriber with Email");
    HttpClient client = new DefaultHttpClient();
    String line = null;

    HttpPut httpPut = new HttpPut("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/subscriber/networkid/" + subscriberId + "/accountid");
    try {

      httpPut.addHeader("Authorization", "Bearer " + access_token);
      httpPut.addHeader("X-IO-Tenant-Id", tenantID);
      httpPut.addHeader("X-IO-Partner-Id", parnterID);
      httpPut.addHeader("Content-Type", "application/json");
      httpPut.addHeader("X-IO-Hardware-Id", hardwareId);

      JSONObject a = new JSONObject();
      a.put("id", subscriberId);
      a.put("accountId", accountId);
      a.put("partnerId", parnterID);
      a.put("imsi", savedIMSI);
      a.put("phoneNumber", phoneNumber);
      a.put("lastUpdateBy", "Auto");
      a.put("provisioningState", "ACTIVE");
      a.put("provisionTimestamp", System.currentTimeMillis());
      StringEntity userEntity = new StringEntity(a.toString());
      httpPut.setEntity(userEntity);
      HttpResponse response = client.execute(httpPut);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      line = "";
      while ((line = rd.readLine()) != null) {
        logAction("from here" + line);
        if (tenantID.toLowerCase().equals("zact")) {
          String[] saveData = line.split("\"");
          subscriberId = saveData[5];
        }
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    logAction("Phonenumber Created: " + phoneNumber);
    return phoneNumber;
  }

  public String getImsi() {
    return savedIMSI;
  }

  public String getSubscriberNetworkId() {
    return subscriberId;
  }

  public String createAccount() throws Exception {

    myAccountEmail = createRandomEmail();
    logAction("Creating My Account " + myAccountEmail);
    //provision device
    provisioningDeviceZact();
    sleep(7000);
    //bootstrap
    getSubScriberNetworkId();
    //make discover call
    myAccountDiscoverCall();
    //create account
    myAccountCreateAccount();
    //creat payment
    myAccountCreatePayment();
    sleep(7000);
    //Checkout:
    myAccountCheckOut();
    //join
    //myAccountJoin();
    return myAccountEmail;

  }

  

  public void myAccountDiscoverCall() {
    HttpClient client = new DefaultHttpClient();

    logAction("My Account Discover Call");
    HttpPost httppost = new HttpPost("https://310120.home.qa.itsonsaas.net:8443/hls/services/1.0/account/discover/" + subscriberId);
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      JSONObject a = new JSONObject();
      a.append("loginId", myAccountEmail);

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");
        saasAccountId = saveData[11];   //only for zact
        logAction("Account Id: " + saasAccountId);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void myAccountCreateAccount() {
    HttpClient client = new DefaultHttpClient();

    logAction("My Account Creating Account");

    HttpPost httppost = new HttpPost("http://op01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/cart/registration?siteId=" + parnterID + "&subscriberNetworkId=" + subscriberId);

    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      //country     
      JSONObject country = new JSONObject();
      country.put("abbreviation", "US");
      country.put("name", "US");
      //state
      JSONObject state = new JSONObject();
      state.put("name", "California");
      state.put("abbreviation", "CA");
      //address
      JSONObject address = new JSONObject();
      address.put("id", (String) null);
      address.put("addressLine1", "3 Lagoon Dr");
      address.put("addressLine2", "");
      address.put("city", "Redwood City");
      address.put("country", country);
      address.put("state", state);
      address.put("isBusiness", (String) null);
      address.put("isDefault", true);

      //phone
      JSONObject phone = new JSONObject();
      phone.put("phoneNumber", phoneNumber);
      phone.put("isActive", true);

      JSONObject accountBalance = new JSONObject();
      accountBalance.append("amount", 0.0);
      accountBalance.append("currency", "USD");

      //main json 
      JSONObject a = new JSONObject();
      a.put("customerToken", (String) null);
      a.put("defaultPaymentToken", (String) null);
      a.put("emailAddress", myAccountEmail);
      a.put("firstName", "Auto");
      a.put("id", (String) null);

      a.put("lastName", "Test");
      a.put("username", myAccountEmail);
      a.put("password", accountPassword);
      a.put("challengeQuestionId", 1);
      a.put("challengeQuestionAnswer", "diesel");
      a.put("subscriberNetworkId", subscriberId);
      savedSaasAccountId = saasAccountId;
      a.put("saasAccountId", saasAccountId);
      a.put("accountBalance", (String) null);
      a.put("customerAttributes", new JSONArray());
      a.put("customerPayments", (String) null);
      a.put("address", address);

      a.put("phone", phone);
      logAction("myAccountCreateAccount1");
      StringEntity userEntity = new StringEntity(a.toString());
      logAction(a.toString());

      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      System.out.println(response.toString());
      logAction("myAccountCreateAccount2");
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");
        customerId = getCustomerId(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void myAccountAddItemToCart(String sku) throws Exception {
    logAction("Adding Item, with sku  : " + sku);
    SSLContextBuilder builder = new SSLContextBuilder();
    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

    CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
            sslsf).build();
    logAction("given hadware id " + hardwareId);
    HttpPost httpPost = new HttpPost("http://app01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/cart/" + sku + "/10718?siteId=" + parnterID + "&customerId=" + customerId);
    CloseableHttpResponse response = null;
    try {
      httpPost.addHeader("Authorization", "Bearer " + access_token);
      httpPost.addHeader("X-IO-Tenant-Id", tenantID);
      httpPost.addHeader("X-IO-Hardware-Id", hardwareId);
      httpPost.addHeader("X-IO-Partner-Id", parnterID);
      response = httpclient.execute(httpPost);

      HttpEntity entity = response.getEntity();
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");
        subscriberId = saveData[11];
        logAction("Got subscriberId " + saveData[11]);
      }
      EntityUtils.consume(entity);

    } finally {
      response.close();
    }
  }

  public void unPublishFeature(String featureID) {
    logAction("unPublishFeature");
    //get oauth 
    getOauth();
    HttpClient client = new DefaultHttpClient();

    HttpPost httppost = new HttpPost("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/partner/" + parnterID + "/feature/" + featureID + "/actions");

    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      JSONObject a = new JSONObject();
      a.append("type", "DISABLE");

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void deleteFeature(String featureID) {
    logAction("deleteFeature");
    //get oauth 
    getOauth();
    HttpClient client = new DefaultHttpClient();

    HttpPost httppost = new HttpPost("http://subscriber-di01-service.qa.itsonsaas.net:8280/saas/services/1.0/partner/" + parnterID + "/feature/" + featureID);
    logAction("+++1deleteFeature");

    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("Connection", "close");
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");
      logAction("+++2deleteFeature");

      JSONObject a = new JSONObject();
      a.append("type", "DELETE");

      StringEntity userEntity = new StringEntity(a.toString());
      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      logAction("+++3deleteFeature");

      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void myAccountCreatePayment() {
    HttpClient client = new DefaultHttpClient();

    logAction("My Account Creating Account");

    HttpPost httppost = new HttpPost("http://op01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/payment/provider/method?siteId=" + parnterID + "&customerId=" + customerId);
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      //country     
      JSONObject country = new JSONObject();
      country.put("abbreviation", "US");
      country.put("name", "US");
      //state
      JSONObject state = new JSONObject();
      state.put("name", "California");
      state.put("abbreviation", "CA");
      logAction("myAccountCreatePayment1");
      //address
      JSONObject billingAddress = new JSONObject();
      billingAddress.put("id", (String) null);
      billingAddress.put("addressLine1", "3 Lagoon Dr");
      billingAddress.put("addressLine2", "");
      billingAddress.put("city", "Redwood City");
      billingAddress.put("country", country);
      billingAddress.put("state", state);
      billingAddress.put("isBusiness", (String) null);
      billingAddress.put("isDefault", true);

      //main json 
      JSONObject a = new JSONObject();
      a.put("billingAddress", billingAddress);
      //card info
      a.put("cardHolderName", "Integration Test");
      a.put("cardNumber", "4111111111111111");
      a.put("defaultCard", true);
      a.put("expirationMonth", 12);
      a.put("expirationYear", 2020);
      a.put("failOnDupe", false);
      a.put("recurring", true);
      a.put("cvv", 411);

      StringEntity userEntity = new StringEntity(a.toString());

      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      logAction("myAccountCreatePayment2");
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");

      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getCustomerId(String id) {
    String[] getid = id.split("id");
    customerId = getid[1];
    getid = customerId.split(":");
    customerId = getid[1];
    getid = customerId.split(",");
    customerId = getid[0];
    logAction("CustomerId is " + customerId);
    return customerId;
  }

  public void myAccountCheckOut() {
    logAction("Checkout my account");
    HttpClient client = new DefaultHttpClient();

    HttpPost httppost = new HttpPost("http://op01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/checkout?siteId=" + parnterID + "&customerId=" + customerId);

    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      HttpResponse response = client.execute(httppost);
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public RestCalls sleep(int time) {
    try {
      Thread.sleep(time);
      logAction("ZZZzz Sleeping zzZZZZ for " + time);
    }
    catch (InterruptedException ex) {
    }
    return this;
  }

  public void myAccountJoin() {
    HttpClient client = new DefaultHttpClient();
    provisioningDeviceZact();
    logAction("My Account Join Account");

    HttpPost httppost = new HttpPost("http://op01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/cart/join?siteId=" + parnterID);
    try {

      httppost.addHeader("Authorization", "Bearer " + access_token);
      httppost.addHeader("X-IO-Tenant-Id", tenantID);
      httppost.addHeader("X-IO-Partner-Id", parnterID);
      httppost.addHeader("Content-Type", "application/json");

      //country     
      JSONObject country = new JSONObject();
      country.put("abbreviation", "US");
      country.put("name", "US");
      //state
      JSONObject state = new JSONObject();
      state.put("name", "California");
      state.put("abbreviation", "CA");
      logAction("myAccountJoin1");
      //address
      JSONObject address = new JSONObject();
      address.put("id", (String) null);
      address.put("addressLine1", "3 Lagoon Dr");
      address.put("addressLine2", "");
      address.put("city", "Redwood City");
      address.put("country", country);
      address.put("state", state);
      address.put("isBusiness", (String) null);
      address.put("isDefault", true);

      //phone
      JSONObject phone = new JSONObject();
      phone.put("phoneNumber", phoneNumber);
      phone.put("isActive", true);

      JSONObject accountBalance = new JSONObject();
      accountBalance.append("amount", 0.0);
      accountBalance.append("currency", "USD");

      //main json 
      JSONObject a = new JSONObject();
      a.put("customerToken", (String) null);
      a.put("defaultPaymentToken", (String) null);
      a.put("emailAddress", myAccountEmail);
      a.put("firstName", "Auto");
      a.put("id", (String) null);

      a.put("lastName", "test");

      a.put("username", myAccountEmail);
      a.put("password", accountPassword);
      a.put("challengeQuestionId", 1);
      a.put("challengeQuestionAnswer", "diesel");
      a.put("subscriberNetworkId", subscriberId);

      a.put("saasAccountId", savedSaasAccountId);
      a.put("accountBalance", (String) null);

      a.put("customerAttributes", new JSONArray());
      a.put("customerPayments", (String) null);
      a.put("address", address);

      a.put("phone", phone);
      logAction("myAccountJoin2");
      StringEntity userEntity = new StringEntity(a.toString());

      httppost.setEntity(userEntity);
      HttpResponse response = client.execute(httppost);
      logAction("myAccountJoin3");
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        logAction(line);
        String[] saveData = line.split("\"");
        customerId = getCustomerId(line);
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }
    myAccountCheckOut();
  }

  public void myAccountDelete() {
    getOauth();
    logAction("Deleting my account");
    sleep(3000);
    HttpClient client = new DefaultHttpClient();

    logAction("X-IO-Subscriber-Id: " + subscriberId);
    logAction("customerId: " + customerId);

    HttpDelete httpDelete = new HttpDelete("http://op01.di01.qa.itsonsaas.com:8980/op-api/rs/v1/customer/terminateAccount?siteId=" + parnterID + "&customerId=" + customerId);

    httpDelete.addHeader("Authorization", "Bearer " + access_token);
    httpDelete.addHeader("X-IO-Tenant-Id", tenantID);
    httpDelete.addHeader("X-IO-Partner-Id", parnterID);
    httpDelete.addHeader("X-IO-Account-Id", savedSaasAccountId);
    httpDelete.addHeader("X-IO-Subscriber-Id", subscriberId);
    try {
      HttpResponse response = client.execute(httpDelete);
    }
    catch (IOException ex) {
      Logger.getLogger(RestCalls.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
