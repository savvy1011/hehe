/*
 /**My Account testSuite WIP
 *
 * @author gurtejphangureh
 */
package com.itson.myaccount;

import org.testng.annotations.Test;

public class MyAccountProfileTestSuite extends MyAccountAbstract {

  public final String wrongPhoneNumber = "654686142";
  private final String currentFirstName = "Auto";
  private final String currentLastName = "Test";
  private final String newFirstName = "firstnew";
  private final String newLastName = "lastnew";

  @Test(groups = {"CustomerSupport", "MyAccount", "Zact"}, priority = 1)
  public void checkAccountHolderInformation() {
    myAccountPages.checkAccountHolder(currentFirstName+" "+currentLastName, email);
  }

  @Test(groups = {"CustomerSupport", "MyAccount", "Zact"}, priority = 2)
  public void checkAccountHolderInformationInEdit() {
    myAccountPages.checkEditNameAndEmail(currentFirstName, currentLastName, email);
  }

  // C30616, C30702
  @Test(groups = {"CustomerSupport", "MyAccount", "Zact"}, priority = 6)
  public void editAccountHolderInformation() {
    String newEmail = "new" + email;
    myAccountPages.editAccountHolderInformation(newFirstName, newLastName, newEmail, password);
    myAccountPages.checkEditNameAndEmail(newFirstName, newLastName, newEmail);
    
    browser.logAction("Change first name, last name, email back");
    myAccountPages.editAccountHolderInformation(currentFirstName, currentLastName, email, password);
    myAccountPages.checkAccountHolder(currentFirstName + " " + currentLastName, email);
  }

  // C30703, C30705, C30706
  @Test(groups = {"CustomerSupport","MyAccount", "Zact"}, priority = 3)
  public void changePassword() {
    String newPassword = "new" + password;
    //change password: incorrect current password
    myAccountPages.changeMyPassword("incorrect" + password, newPassword, email, myAccountPages.passwordIncorrect);
    //change password
    myAccountPages.changeMyPassword(password, newPassword, email, myAccountPages.passwordSavedSuccessfully);
    //change password back
    myAccountPages.changeMyPassword(newPassword, password, email, myAccountPages.passwordSavedSuccessfully);
  }

  // C30617: Edit Account Holder Information > Cancel
  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint"}, priority = 4)
  public void editAccountHolderInformationCancel() {
    String newEmail = "new" + email;
    myAccountPages.editAccountHolderInformation(newFirstName, newLastName, newEmail, password, true);
    myAccountPages.checkAccountHolder(currentFirstName + " " + currentLastName, email);
  }
  
  // C30704: Change password > Cancel
  @Test(groups = {"CustomerSupport", "SDC", "Zact", "Sprint"}, priority = 5)
  public void changePasswordCancel() {
    String newPassword = "new" + password;
    //change password
    myAccountPages.changeMyPassword(password, newPassword, email, "", true);
    //check current password
    myAccountPages.setLogOut();
    myAccountPages.setLogIn(email,password);
  }
}
