package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class UserRemovalTests extends TestBase {

  @Test
  public void testUserRemoval() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getUserHelper().selectUser();
    app.getUserHelper().initUserRemoval();
    app.getNavigationHelper().goToHomePage();
  }
}