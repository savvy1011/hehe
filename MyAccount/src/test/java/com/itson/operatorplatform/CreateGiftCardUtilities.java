/*
 /**Create Gift card account and send account number to csv
 *
 * @author gurtejphangureh
 */
package com.itson.operatorplatform;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateGiftCardUtilities extends AbstractMethod{
  @BeforeMethod
  protected void logInSetUp() {
    logIn();
  }

  @Test(dataProviderClass = com.itson.operatorplatform.FileDataProvider.class, dataProvider = "getDataFromFile")
  @DataProviderArguments("filePath=../givendata.csv")
  public void CreateGiftCard(String givendata)  {
    spiltData(givendata);
    browser.logAction("Got This For Amount:" + data[0] + " Got This For email: " + data[1]);
    accountNumber = giftCardAccount.CreateGiftCardAccount(data[0], data[1]);
    writeCSV("../opAccountNumber.csv",accountNumber);
    browser.close();
  }
   protected void writeCSV(String file, String data) {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
      writer.append(data);
      writer.append('\n');
      writer.flush();
    }
    catch (IOException ex) {
      Logger.getLogger(SetUpAbstract.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        writer.close();
      }
      catch (IOException ex) {
        Logger.getLogger(SetUpAbstract.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
