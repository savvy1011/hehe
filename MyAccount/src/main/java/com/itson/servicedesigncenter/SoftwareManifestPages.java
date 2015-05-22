/**
 * locators and methods for SoftwareManifest sections
 *
 * @author
 */
package com.itson.servicedesigncenter;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SoftwareManifestPages {

  private Browser browser;

  public SoftwareManifestPages(Browser brw) {
    browser = brw;
    browser.initElements(this);
  }

  //New manifest
  @FindBy(how = How.ID, using = "createNewServiceBtnGroup")
  private WebElement newManifestBtn;
  @FindBy(how = How.CSS, using = ".btn-primary")
  private WebElement createNewManifestBtn;

  @FindBy(how = How.XPATH, using = "//div/div[2]/div/div/div/ul/li/a")
  private WebElement createNewFirstSelection;

  @FindBy(how = How.ID, using = "input-Menifest_Version")
  private WebElement newVersionField;

  @FindBy(how = How.ID, using = "input-Manifest_minVersion")
  private WebElement newMinVersionField;

  @FindBy(how = How.ID, using = "input-Manifest_fingerPrint")
  private WebElement newFingerPrintField;

  @FindBy(how = How.ID, using = "addCancelBtn")
  private WebElement newCancelBtn;
  @FindBy(how = How.ID, using = "confirmAction_btn")
  private WebElement newSaveBtn;
  @FindBy(how = How.ID, using = "confirmOkBtn")
  public WebElement searchDeleteConfirmBtn;
  @FindBy(how = How.ID, using = "input-OTA-Policy-Start_Date")
  public WebElement inputOTAPolicyStartDateField;

  /**
   * set value to version field
   *
   * @param version String
   * @return this
   */
  public SoftwareManifestPages setNewVersion(String version) {
    browser.logAction("Inputing Manifest Version: " + version);
    newVersionField.clear();
    newVersionField.sendKeys(version);

    return this;
  }

  /**
   * set value to min version field
   *
   * @param version String
   * @return this
   */
  public SoftwareManifestPages setNewMinVersion(String version) {
    browser.logAction("Inputing Manifest minVersion: " + version);
    browser.waitForClickableElement(newMinVersionField);
    newMinVersionField.clear();
    newMinVersionField.sendKeys(version);
    return this;
  }

  /**
   * set value to fingerPrint field
   *
   * @param fingerPrint String
   * @return this
   */
  public SoftwareManifestPages setNewFingerPrint(String fingerPrint) {
    browser.logAction("Inputing Manifest fingerPrint: " + fingerPrint);
    newFingerPrintField.clear();
    newFingerPrintField.sendKeys(fingerPrint);

    return this;
  }

  /**
   * action click to New button
   *
   * @return
   */
  public SoftwareManifestPages setNewManifestBtn() {
    createNewManifestBtn.click();
    browser.waitForVisibilityOfElement(By.xpath("//div/div[2]/div/div/div/ul/li/a"));
    createNewFirstSelection.click();
    browser.invisibilityOfElementLocated(By.xpath("//div/div[2]/div/div/div/ul/li/a"));

    return this;
  }

  /**
   * action click cancel button
   *
   * @return this
   */
  public SoftwareManifestPages setCancelbtn() {
    browser.logAction("Clicking Cancel Button ");
    newCancelBtn.click();

    return this;
  }

  /**
   * action click save button
   *
   * @return
   */
  public SoftwareManifestPages setSavebtn() {
    browser.logAction("Clicking Save Button ");
    newSaveBtn.click();

    return this;
  }

  /**
   * action click save button
   *
   * @return
   */
  public SoftwareManifestPages createNewPolicy() {
    browser.logAction("Clicking button addPolicy on list");

    List<WebElement> buttons = (List<WebElement>) browser.findElements(By.className("addpolicy"));

    for (WebElement btn : buttons) {
      if (!"true".equals(btn.getAttribute("disabled"))) {
        browser.click(btn);
        inputOTAPolicyStartDateField.clear();
        inputOTAPolicyStartDateField.sendKeys("10-29-2017");
        newSaveBtn.click();
      }
    }

    return this;
  }
}
