/**
 * Common functions is used to compare between expected value and fact value.
 * Includes Util JavaScript, JQuery, TraceBack ... classes.
 *
 * @author HaNT
 */
package com.itson.myaccount;

import com.itson.servicedesigncenter.Browser;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class Util {

  protected Browser browser;
  protected RemoteWebDriver driver;
  protected JavascriptExecutor jsDriver;
  protected JQuery jq;
  protected JavaScript js;

  /**
   * ***************************************************************************
   * SLEEPING TIME
   * ***************************************************************************
   */
  public static final int sleepTimeDegree1 = 3000;
  public static final int sleepTimeDegree2 = 5000;
  public static final int sleepTimeDegree3 = 8000;
  public static final int sleepTimeDegree4 = 10000;
  public static final int sleepTimeDegree5 = 20000;
  public static final int sleepTimeDegree60 = 60000;
  
  public Util(Browser browser) {
    this.browser = browser;
  }

  /**
   * ***************************************************************************
   * COMPARE CLASS TO CHECK VALUES BETWEEN EXPECTATION AND FACT
   *
   * @author HaNT
   * ***************************************************************************
   */
  public class Compare {

    private Boolean isPassedGlobal = true;
    
    public Boolean getIsPassedGlobal() {
      browser.logAction("Get ISPASSEDGLOBAL: " + isPassedGlobal.toString());
      return isPassedGlobal;
    }

    public void resetIsPassedGlobal() {
      browser.logAction("Reset ISPASSEDGLOBAL = TRUE at the start point of each test case!!!");
      isPassedGlobal = true;
    }

    public void setIsPassedGlobal(Boolean isPassed) {
      browser.logAction("Set ISPASSEDGLOBAL = " + isPassed.toString());
      isPassedGlobal = isPassed;

      if (!isPassed) {
        browser.logAction("\n ************** BackStrace: ************** \n" + TraceBack.TraceBack());
      }
    }

    /**
     * @description Common function to check expected value and fact value.
     * @param expectedValue
     * @param factValue
     * @param operation: Parse to Integer or Double before comparing: ==, !=, <, <=,
     * >, >= > Util as String: equals, contains, notcontains > Check the element
     * is displayed, existed, selected (or not). (Input the boolean value and
     * this function will help to display the comparison sentence.)
     * @param stepContent: content of the step
     * @param isPrecondition = true: This comparison is checking for
     * precondition, if it is failed, test case has to be broken.
     * @param isExpected = true: Comparison is checking for expectation result,
     * if it is failed, the case will be failed (the isPassedGlobal will be set
     * to FALSE). = false: Comparison is checking for a if condition only, if it
     * is failed, the case will NOT be failed (the isPassedGlobal will NOT be
     * set to FALSE).
     * @return the result of this comparison.
     * @author HaNT
     */
    public Boolean compare(Object factValueObj, String expectedValue, String operation, String stepContent) {
      browser.logAction(stepContent);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      browser.logAction(stepContent);
      String factValue = factValueObj.toString();

      try {
        expectedValue = expectedValue.replace("\n", "").replace("\r", "").toLowerCase().trim();
        factValue = factValue.replace("\n", "").replace("\r", "").toLowerCase().trim();

        Boolean returnValue = false;
        Boolean boolean1 = true;
        Boolean boolean2 = true;

        int expectedValueInt = 0;
        int factValueInt = 0;
        double expectedValueDouble = 0.00;
        double factValueDouble = 0.00;
        if (operation.equals("==") || operation.equals("!=") || operation.equals(">") || operation.equals(">=") || operation.equals("<") || operation.equals("<=")) {
          try {
            expectedValueDouble = CustomNumber.parseDouble(expectedValue);
            factValueDouble = CustomNumber.parseDouble(factValue);
          } catch (Exception ex) {
            browser.logAction("Convert expectedValue and factValue to Double: " + ex.toString());
          }

          try {
            expectedValueInt = CustomNumber.parseInt(expectedValue);
            factValueInt = CustomNumber.parseInt(factValue);
          } catch (Exception ex) {
            browser.logAction("Convert expectedValue and factValue to Int: " + ex.toString());
          }

          if (expectedValue.equals("null")) {
            expectedValueInt = -1;
            expectedValueDouble = -1.0;

            if (factValueObj == null) {
              factValueInt = -1;
              factValueDouble = -1.0;
            } else {
              factValueInt = 1;
              factValueDouble = 1.0;
            }
          }
        }

        switch (operation) {
          //  WITH NUMBER LIKE INTEGER OR DOUBLE
          case "==":
            boolean1 = (expectedValueInt == factValueInt);
            boolean2 = (expectedValueDouble == factValueDouble);
            returnValue = boolean1 || boolean2;
            break;
          case "!=":
            boolean1 = (expectedValueInt != factValueInt);
            boolean2 = (expectedValueDouble != factValueDouble);
            returnValue = boolean1 || boolean2;
            break;
          case ">":
            boolean1 = (expectedValueInt > factValueInt);
            boolean2 = (expectedValueDouble > factValueDouble);
            returnValue = boolean1 || boolean2;
            break;
          case ">=":
            boolean1 = (expectedValueInt >= factValueInt);
            boolean2 = (expectedValueDouble >= factValueDouble);
            returnValue = boolean1 || boolean2;
            break;
          case "<":
            boolean1 = (expectedValueInt < factValueInt);
            boolean2 = (expectedValueDouble < factValueDouble);
            returnValue = boolean1 || boolean2;
            break;
          case "<=":
            boolean1 = (expectedValueInt <= factValueInt);
            boolean2 = (expectedValueDouble <= factValueDouble);
            returnValue = boolean1 || boolean2;
            break;

          case "isInteger":
            try {
              Integer.valueOf(CustomNumber.ignoreDataUnit(factValue));
              returnValue = (Boolean.parseBoolean(expectedValue) == true);
            } catch (NumberFormatException e) {
              returnValue = (Boolean.parseBoolean(expectedValue) == false);
            }
            break;
          case "isDouble":
            try {
              factValue = CustomNumber.ignoreDataUnit(factValue);
              Double.valueOf(factValue);
              if (factValue.contains(".")) {
                returnValue = (Boolean.parseBoolean(expectedValue) == true);
              } else {
                returnValue = (Boolean.parseBoolean(expectedValue) == false);
              }
            } catch (Exception e) {
              returnValue = (Boolean.parseBoolean(expectedValue) == false);
            }
            break;

          //  WITH STRING
          case "equals":
          case "contentEquals":
            returnValue = (factValue.equals(expectedValue));
            break;
          case "startsWith":
            returnValue = (factValue.startsWith(expectedValue));
            break;
          case "endsWith":
            returnValue = (factValue.endsWith(expectedValue));
            break;
          case "notEquals":
            returnValue = (!factValue.equals(expectedValue));
            break;
          case "contains":
            returnValue = (factValue.contains(expectedValue));
            break;
          case "notContains":
            returnValue = (!factValue.contains(expectedValue));
            break;

          //  WITH ELEMENT
          case "isDisplayed":
          case "isExisted":
          case "isSelected":
            returnValue = (Boolean.parseBoolean(expectedValue) == Boolean.parseBoolean(factValue));
            break;
          case "isNotDisplayed":
          case "isNotExisted":
          case "isNotSelected":
            returnValue = (Boolean.parseBoolean(expectedValue) != Boolean.parseBoolean(factValue));
            break;
        }

        if (!returnValue)
          writeToReportAndLog(expectedValue, factValue, operation);

        return returnValue;
      } catch (Exception ex) {
        writeToReportAndLog(expectedValue, factValue, operation);
        throw ex;
      }
    }
    
    public Boolean verify(Object factValueObj, String expectedValue, String operation, String stepContent, Boolean... isPrecondition) {
      boolean returnValue = compare(factValueObj, expectedValue, operation, stepContent);
      if (!returnValue) {
        if (isPrecondition.length > 0 && isPrecondition[0]) {
          Reporter.log("FAILED AT PRECONDITION: " + stepContent);
          throw new RuntimeException();
        }
        setIsPassedGlobal(false);
      }
      return returnValue;
    }

    /**
     * @description Work with Selenium By finder to determine about Element
     * status:displayed, existed, selected, or not
     * @param driver
     * @param by
     * @param operation > isDisplayed (isNotDisplayed) > isExisted
     * (isNotExisted) > isSelected (isNotSelected)
     * @param stepContent: content of the step
     * @param isPrecondition = true: This comparison is checking for
     * precondition, if it is failed, test case has to be broken.
     * @param isExpected = true: Comparison is checking for expectation result,
     * if it is failed, the case will be failed (the isPassedGlobal will be set
     * to FALSE). = false: Comparison is checking for a if condition only, if it
     * is failed, the case will NOT be failed (the isPassedGlobal will NOT be
     * set to FALSE).
     * @return the result of this comparison.
     * @throws InterruptedException
     * @author HaNT
     */
    public Boolean compare(By by, String operation, String stepContent, Boolean... isPrecondition) throws InterruptedException {
      browser.logAction(stepContent);

      try {
        Boolean expectedValue = true;
        Boolean factValue = true;
        Boolean returnValue = true;

        switch (operation) {
          case "isDisplayed":
            expectedValue = Boolean.TRUE;
            factValue = driver.findElement(by).isDisplayed();
            break;
          case "isNotDisplayed": //isExistedNotDisplayed
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(driver.findElement(by).isDisplayed());
            break;
          case "isExisted":
            expectedValue = Boolean.TRUE;
            factValue = browser.isElementExisted(by);
            break;
          case "isNotExisted":
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(browser.isElementExisted(by));
            break;
          case "isSelected":
            expectedValue = Boolean.TRUE;
            factValue = driver.findElement(by).isSelected();
            break;
          case "isNotSelected":
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(driver.findElement(by).isSelected());
            break;
        }

        returnValue = logicXNOR(expectedValue, factValue);

        if (!returnValue)
          writeToReportAndLog(expectedValue.toString(), factValue.toString(), operation);

        return returnValue;
      } catch (Exception ex) {
        writeToReportAndLog("driver", by.toString(), operation);
        throw ex;
      }
    }
    
    public Boolean verify(By by, String operation, String stepContent, Boolean... isPrecondition) throws InterruptedException {
      boolean returnValue = compare(by, operation, stepContent, isPrecondition);
      if (!returnValue) {
        if (isPrecondition.length > 0 && isPrecondition[0]) {
          Reporter.log("FAILED AT PRECONDITION: " + stepContent);
          throw new RuntimeException();
        }
        setIsPassedGlobal(false);
      }
      return returnValue;
    }

    /**
     * @description Work with jQuery finder to determine about Element
     * status:displayed, existed, selected, or not Should to use this function
     * with un-existed element to save time
     * @param driver
     * @param jQuerySelector
     * @param operation > isDisplayed (isNotDisplayed) > isExisted
     * (isNotExisted) > isSelected (isNotSelected)
     * @param stepContent: content of the step
     * @param isPrecondition = true: This comparison is checking for
     * precondition, if it is failed, test case has to be broken.
     * @param isExpected = true: Comparison is checking for expectation result,
     * if it is failed, the case will be failed (the isPassedGlobal will be set
     * to FALSE). = false: Comparison is checking for a if condition only, if it
     * is failed, the case will NOT be failed (the isPassedGlobal will NOT be
     * set to FALSE).
     * @return the result of this comparison.
     * @throws InterruptedException
     * @author HaNT
     */
    public Boolean compare(String jQuerySelector, String operation, String stepContent, Boolean... isPrecondition) throws InterruptedException {
      browser.logAction(stepContent);

      try {
        Boolean expectedValue = true;
        Boolean factValue = true;
        Boolean returnValue = true;

        switch (operation) {
          case "isDisplayed":
            expectedValue = Boolean.TRUE;
            factValue = jq.isElementDisplayed(jQuerySelector);
            break;
          case "isNotDisplayed":
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(jq.isElementDisplayed(jQuerySelector));
            break;
          case "isExisted":
            expectedValue = Boolean.TRUE;
            factValue = jq.isElementExisted(jQuerySelector);
            break;
          case "isNotExisted":
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(jq.isElementExisted(jQuerySelector));
            break;
          case "isSelected":
            expectedValue = Boolean.TRUE;
            factValue = jq.findElement(jQuerySelector).isSelected();
            break;
          case "isNotSelected":
            expectedValue = Boolean.FALSE;
            factValue = new Boolean(jq.findElement(jQuerySelector).isSelected());
            break;
        }

        returnValue = logicXNOR(expectedValue, factValue);

        if (!returnValue)
          writeToReportAndLog(expectedValue.toString(), factValue.toString(), operation);

        return returnValue;
      } catch (Exception ex) {
        writeToReportAndLog("driver", jQuerySelector, operation);
        throw ex;
      }
    }
    
    public Boolean verify(String jQuerySelector, String operation, String stepContent, Boolean... isPrecondition) throws InterruptedException {
      boolean returnValue = compare(jQuerySelector, operation, stepContent, isPrecondition);
      if (!returnValue) {
        if (isPrecondition.length > 0 && isPrecondition[0]) {
          Reporter.log("FAILED AT PRECONDITION: " + stepContent);
          throw new RuntimeException();
        }
        setIsPassedGlobal(false);
      }
      return returnValue;
    }

    /**
     * @description Common function to format the message in report and in log.
     * @param expected
     * @param real
     * @param operation
     * @param stepContent
     * @author HaNT
     */
    private void writeToReportAndLog(String expected, String real, String operation) {
      browser.logAction("EXPECTED : " + expected);
      browser.logAction("FACT     : " + real);
      browser.logAction("OPERATION: " + operation);
    }
    
    public void begin(String methodName) {
      browser.logAction("\n\nTESTING................................"+ methodName +"................................\\n");
      resetIsPassedGlobal();
    }
    
    public void conclusion(String methodName) {
      if (getIsPassedGlobal()) {
        browser.logAction("\nEND~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ methodName +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PASSED\n");
      } else {
        browser.logAction("\nEND~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ methodName +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED\n");
        throw new RuntimeException();
      }
    }

    private boolean logicXNOR(boolean a, boolean b) {
      return ((a && b) || (!a && !b));
    }
  }

  public static class CustomNumber {

    public static Integer parseInt(String aString) {
      return Integer.parseInt(ignoreDataUnit(aString).replace(".00", "").replace(".0", "").replace("px", ""));
    }

    public static Long parseLong(String aString) {
      return Long.parseLong(ignoreDataUnit(aString).replace(".00", "").replace(".0", "").replace("px", ""));
    }

    public static Double parseDouble(String aString) {
      return Double.parseDouble(ignoreDataUnit(aString));
    }

    public static String ignoreDataUnit(String data) {
      return data.replace("\n", "").replace("\r", "").replace("MB", "").replace("mb", "").replace("GB", "").replace("gb", "").replace("B", "").replace("b", "").trim();
    }
  }

  /**
   * ***************************************************************************
   * TRACEBACK CLASS TO TRACE BACK ROWS THAT OCCUR UNEXPECTED VALUES
   *
   * @author HaNT
   * ***************************************************************************
   */
  public static class TraceBack {

    /**
     * Retrieve the stacktrace by sending an exception, catching it and getting
     * its stacktrace.
     */
    private static StackTraceElement[] getStackTrace() {
      try {
        throw new Exception();
      } catch (Exception e) {
        return e.getStackTrace();
      }
    }

    /**
     * Returns the full TraceBack corresponding to the StackTraceElement's
     * passed. This is useful to get the TraceBack from a catch block.
     */
    public static String TraceBackFull(StackTraceElement[] trace) {
      StringBuffer sb = new StringBuffer();
      for (int i = 2; i < trace.length; i++) {
        sb.append("\t").append(trace[i].toString()).append("\n");
      }
      return sb.toString();
    }

    /**
     * Returns the full TraceBack of the caller method.
     */
    public static String TraceBackFull() {
      return TraceBack.TraceBackFull(TraceBack.getStackTrace());
    }

    /**
     * Returns the TraceBack corresponding to the StackTraceElement's passed,
     * limited to my classes. This is useful to get the TraceBack from a catch
     * block.
     */
    public static String TraceBack(StackTraceElement[] trace) {
      StringBuffer sb = new StringBuffer();
      for (int i = 2;
          i < trace.length;
          i++) {
        if (trace[i].toString().toLowerCase().contains("com.itson.virgin.".toLowerCase()) && !trace[i].toString().toLowerCase().contains("com.itson.virgin.Utilities".toLowerCase())) {
          sb.append("\t").append(trace[i].toString()).append("\n");
        }
      }
      return sb.toString();
    }

    /**
     * Returns the TraceBack of the caller method, limited to mnc classes.
     */
    public static String TraceBack() {
      return TraceBack.TraceBack(TraceBack.getStackTrace());
    }
  }

  /**
   * ***************************************************************************
   * JAVASCRIPT CLASS USE JAVASCRIPT FOR SAVING TIME WHEN CHECKING UNEXISTED
   * ELEMENTS OR OTHER PURPOSES
   *
   * @author HaNT
   * ***************************************************************************
   */
  public class JavaScript {

    /**
     * Reset all elements of form to default value (by js)
     *
     * @param id
     * @return true or false
     * @author HaNT
     */
    public Boolean resetForm(String id) {
      try {
        jsDriver.executeScript("document.getElementById(\"" + id + "\").reset();");
        return true;
      } catch (Exception ex) {
        browser.logAction("Error: js.resetForm('" + id + "'): " + ex.toString());
      }
      return false;
    }

    /**
     * Back to the previous window (by js)
     *
     * @return true or false
     * @author HaNT
     */
    public Boolean windowBack() {
      try {
        jsDriver.executeScript("window.history.back();");
        return true;
      } catch (Exception ex) {
        browser.logAction("Error: js.windowBack(): " + ex.toString());
      }
      return false;
    }

    /**
     * Find element by js
     *
     * @param byType
     * @param byValue
     * @return WebElement
     * @author HaNT
     */
    public WebElement findElement(String byType, String byValue) {
      browser.logAction("js.findElement('" + byType + "', '" + byValue + "');");
      return findElements(byType, byValue).get(0);
    }

    /**
     * Find list of elements by js
     *
     * @param byType
     * @param byValue
     * @return List of WebElement
     * @author HaNT
     */
    public List<WebElement> findElements(String byType, String byValue) {
      browser.logAction("js.findElements('" + byType + "', '" + byValue + "');");

      jsDriver.executeScript("document.getElementByXPath = function(sValue) "
          + "{ "
          + "     var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); "
          + "     return a.snapshotItem(0); "
          + "};");
      jsDriver.executeScript("document.getElementsByXPath = function(sValue) "
          + "{ "
          + "     var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); "
          + "     var arr_elements = new Array(); "
          + "     for(var i = 0; i < a.snapshotLength; i++)"
          + "     { "
          + "         arr_elements.push(a.snapshotItem(i));"
          + "     } "
          + "     return arr_elements; "
          + "};");

      String realByValue;
      String add1 = "";

      switch (byType.toLowerCase()) {
        case "id":
        default:
          realByValue = "ById";
          break;
        case "xpath":
          realByValue = "ByXPath";
          break;
        case "tagname":
          realByValue = "ByTagName";
          add1 = "s";
          break;
        case "classname":
          realByValue = "ByClassName";
          add1 = "s";
          break;
        case "name":
          realByValue = "ByName";
          add1 = "s";
          break;
        case "cssselector":
        case "css":
          realByValue = "querySelector";
          add1 = "All";
          break;
      }

      try {
        List<WebElement> elements = null;

        if (!byType.toLowerCase().equals("cssselector") && !byType.toLowerCase().equals("css")) {
          elements = (List<WebElement>) jsDriver.executeScript("return document.getElement" + add1 + realByValue + "(\"" + byValue + "\")");
        } else {
          elements = (List<WebElement>) jsDriver.executeScript("return document." + realByValue + add1 + "(\"" + byValue + "\")");
        }

        if (elements.size() <= 0) {
          browser.logAction("These elements " + realByValue + " are not existed: " + byValue);
          return null;
        }

        return elements;
      } catch (Exception ex) {
        browser.logAction("Error: getElementsBy" + realByValue + "(\"" + byValue + "\"): " + ex.toString());
      }
      return null;
    }

    /**
     * Js check if the element is existed.
     *
     * @param byType
     * @param byValue
     * @return
     * @author HaNT
     */
    public Boolean isElementExisted(String byType, String byValue) {
      if (findElement(byType, byValue) != null) {
        return true;
      }
      browser.sleep(sleepTimeDegree2);
      if (findElement(byType, byValue) != null) {
        return true;
      }
      return false;
    }

    /**
     * @description Use js to get the selected option text by Id
     * @param id
     * @return string
     * @note I did try to use selenium to get that one but it returned "",
     * because we don't use the normal DOM select/option element, we use ul/li
     * (class="selectBox-dropdown-menu selectBox-options
     * exp_month-selectBox-dropdown-menu form-select-selectBox-dropdown-menu
     * required-selectBox-dropdown-menu
     * select-processed-selectBox-dropdown-menu") ex creditCardExpMonthIDString
     * text = Utilities.getSelectedOptionText(driver,
     * By.id(creditCardExpMonthID)); browser.logAction("text: " + text);
     * browser.logAction("text2: " +
     * driver.findElement(By.id(creditCardExpMonthID)).findElements(By.xpath("./option[@selected='selected']")).get(0).getText());
     *
     * @author HaNT
     */
    public String getSelectedOptionTextById(String id) {
      jsDriver.executeScript("document.getSelectedOptionTextById = function(sValue) "
          + "{ "
          + "     var e = document.getElementById(sValue); "
          + "     return e.options[e.selectedIndex].text; "
          + "};");

      try {
        return (String) jsDriver.executeScript("return document.getSelectedOptionTextById(\"" + id + "\")");
      } catch (Exception ex) {
        browser.logAction("Error: js.getSelectedOptionText(\"" + id + "\"): " + ex.toString());
      }
      return null;
    }

    /**
     * @description Use js to get the selected option value by Id
     * @param id
     * @return string
     * @author HaNT
     */
    public String getSelectedOptionValueById(String id) {
      jsDriver.executeScript("document.getSelectedOptionValueById = function(sValue) "
          + "{ "
          + "     var e = document.getElementById(sValue); "
          + "     return e.options[e.selectedIndex].value; "
          + "};");
      try {
        return (String) jsDriver.executeScript("return document.getSelectedOptionValueById(\"" + id + "\")");
      } catch (Exception ex) {
        browser.logAction("Error: js.getSelectedOptionValueById(\"" + id + "\"): " + ex.toString());
      }
      return null;
    }

    /**
     * @description Js click on Xpath element
     * @param xpath
     * @return true or false
     * @author HaNT
     * @note Use with non DOM element. Ex: > Not always worked case: >
     * (jQuery)("#text_1000000000000289").click(); => The Monthly Difference is
     * NOT updated.
     * (http://cbv-qa.zact.com/account/plans/change-unlimited-plans) > OR
     * driver.findElementByXPath("//label[@for='"+ textCheckboxID
     * +"']").click(); (MyAccountUnlimitedPlans.java row 396 on the days before
     * 2014/12/19) > Refer to
     * https://bm3.itsoninc.com/jenkins/job/Web_LocalHub_Virgin_MyAccount_Payments/59/artifact/Virgin/target/surefire-reports/emailable-report.html.
     * Failed at this step so the Place Order page shows "Can not place
     * order..." > Refer to files ChangeUnlimitedPlan_20141219.png and
     * PlaceOrder_20141219.png > OR: // terminateConfirmationButton.click();
     * Utilities.jsClickElementByXpath(driver, terminateConfirmButtonXpath); //
     * Can NOT click on this element by Selenium
     *
     * > Worked case:
     * document.getElementByXPath("//label[@for='text_1000000000000289']").click();
     * => The Monthly Difference is updated.
     */
    public boolean jsClickElementByXpath(String xpath) {
      jsDriver.executeScript("document.getElementByXPath = function(sValue) "
          + "{ "
          + "     var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); "
          + "     return a.snapshotItem(0); "
          + "};");
      try {
        jsDriver.executeScript("return document.getElementByXPath(\"" + xpath + "\").click();");
        return true;
      } catch (Exception ex) {
        browser.logAction("Error: js.clickElementByXpath(\"" + xpath + "\"): " + ex.toString());
      }
      return false;
    }

    public String getTextByXpath(String xpath) {
      jsDriver.executeScript("document.jsGetTextByXpath = function(sValue) {"
          + "return document.getElementByXPath(sValue).textContent; "
          + "}");
      try {
        return (String) jsDriver.executeScript("return document.jsGetTextByXpath(\"" + xpath + "\");");
      } catch (Exception ex) {
        browser.logAction("Error: js.getTextByXpath('" + xpath + "'): " + ex.toString());
      }
      return null;
    }
  }

  /**
   * ***************************************************************************
   * JQUERY CLASS USE JQUERY FOR SAVING TIME WHEN CHECKING UNEXISTED ELEMENTS OR
   * OTHER PURPOSES
   *
   * @author HaNT
   * ***************************************************************************
   */
  public class JQuery {

    /**
     * @description Find element by jquery
     * @param jQuerySelector
     * @return WebElement
     * @author HaNT
     */
    public WebElement findElement(String jQuerySelector) {
      browser.logAction("jq.findElement('" + jQuerySelector + "');");
      return findElements(jQuerySelector).get(0);
    }

    /**
     * Find list of elements by jquery
     *
     * @param jQuerySelector
     * @return List of WebElement
     * @author HaNT
     */
    public List<WebElement> findElements(String jQuerySelector) {
      browser.logAction("jq.findElements('" + jQuerySelector + "');");
      try {
        List<WebElement> elements = (List<WebElement>) jsDriver.executeScript("return (jQuery)(\"" + jQuerySelector + "\");");

        if (elements.size() <= 0) {
          browser.logAction("This jquery elements are not existed: " + "(jQuery)(\"" + jQuerySelector + "\");");
        }

        return elements;
      } catch (Exception ex) {
        browser.logAction("Error: jq.findElements(\"" + jQuerySelector + "\"): " + ex.toString());
      }
      return null;
    }

    /**
     * jQuery check if the element is existed.
     *
     * @param jQuerySelector
     * @return
     * @author HaNT
     */
    public boolean isElementExisted(String jQuerySelector) {
      if (jq.findElement(jQuerySelector) != null) {
        return true;
      }
      browser.sleep(sleepTimeDegree2);
      if (jq.findElement(jQuerySelector) != null) {
        return true;
      }
      return false;
    }

    /**
     * jQuery check if the element is displayed.
     *
     * @param jQuerySelector
     * @return
     * @author HaNT
     */
    public boolean isElementDisplayed(String jQuerySelector) {
      try {
        if (!(Boolean) jsDriver.executeScript("return jQuery(\"" + jQuerySelector + "\").is( \":visible\" );")) {
          return true;
        }
        browser.sleep(sleepTimeDegree2);
        if (!(Boolean) jsDriver.executeScript("return jQuery(\"" + jQuerySelector + "\").is( \":visible\" );")) {
          return true;
        }
      } catch (Exception ex) {
        browser.logAction("Error: jq.isElementDisplayed(\"" + jQuerySelector + "\").is( \":visible\" ); " + ex.toString());
      }
      return false;
    }

    /**
     * Clear all elements of the form to blank value.
     *
     * @param id
     * @return true or false
     * @author HaNT
     */
    public Boolean clearForm(String id) {
      jsDriver.executeScript("document.clearForm = function(sValue) {"
          + "   var theForm = jQuery(document.getElementById(sValue));"
          + "   jQuery(':input', theForm).each(function() {"
          + "       var type = this.type;"
          + "       var tag = this.tagName.toLowerCase();"
          + "       if (type == 'text' || type == 'password' || tag == 'textarea')"
          + "           this.value = \"\";"
          + "       else if (type == 'checkbox' || type == 'radio')"
          + "           this.checked = false;"
          + "       else if (tag == 'select')"
          + "           this.selectedIndex = 0;"
          + "   });"
          + "};");
      try {
        jsDriver.executeScript("document.clearForm(\"" + id + "\")");
        return true;
      } catch (Exception ex) {
        browser.logAction("Error: jq.clearForm(driver, '" + id + "'): " + ex.toString());
      }
      return false;
    }

    /**
     * Get Top Position of an element by jQuery selector
     *
     * @param selector
     * @return Top position in double value
     * @author HaNT
     */
    public Double getPositionTop(String jQuerySelector) {
      jsDriver.executeScript("document.getPosition = function(sValue) {"
          + "return {\n"
          + "        top: (jQuery)(sValue).position().top,\n"
          + "        left: (jQuery)(sValue).position().left\n"
          + "    }; "
          + "}");
      try {
        return (Double) jsDriver.executeScript("return document.getPosition(\"" + jQuerySelector + "\").top;");
      } catch (Exception ex) {
        browser.logAction("Error: jq.getPositionTop('" + jQuerySelector + "'): " + ex.toString());
      }
      return null;
    }

    public String jqGetHtml(String jQuerySelector) {
      jsDriver.executeScript("document.jqGetHtml = function(sValue) {"
          + "return (jQuery)(sValue).html(); "
          + "}");
      try {
        return (String) jsDriver.executeScript("return document.jqGetHtml(\"" + jQuerySelector + "\");");
      } catch (Exception ex) {
        browser.logAction("Error: jq.getHtml('" + jQuerySelector + "'):" + ex.toString());
      }
      return null;
    }

    public String jqGetText(String jQuerySelector) {
      jsDriver.executeScript("document.jqGetText = function(sValue) {"
          + "return (jQuery)(sValue).text(); "
          + "}");
      try {
        return (String) jsDriver.executeScript("return document.jqGetText(\"" + jQuerySelector + "\");");
      } catch (Exception ex) {
        browser.logAction("Error: jq.getText('" + jQuerySelector + "'): " + ex.toString());
      }
      return null;
    }

    public String getTextValue(String jQuerySelector) {
      try {
        String value = (String) jsDriver.executeScript("return (jQuery)(\"" + jQuerySelector + "\").val();");
        if (value.isEmpty()) {
          System.out.println("This jquery element is not existed: " + "(jQuery)(\"" + jQuerySelector + "\");");
        }
        return value;
      } catch (Exception ex) {
        System.err.println("Error: jqGetTextValue(\"" + jQuerySelector + "\")" + ex.toString());
      }
      return null;
    }
  }
}
