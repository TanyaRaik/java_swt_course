package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class UserRemovalTests extends TestBase {

  @Test
  public void testUserRemoval() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("name", "middle name", "last name", "nickname", "title",
              "raik.tatyana@gmail.com", "notes", "January", "company", "address",
              "1", "January", "1992", "2", "homepage", "fax",
              "work", "mobile", "home", "q"), true);
    }
    app.getUserHelper().selectUser();
    app.getUserHelper().initUserRemoval();
    app.getNavigationHelper().goToHomePage();
  }
}