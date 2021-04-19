package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getUserHelper().selectUser();
    app.getUserHelper().initUserModification(new UserData("name_upd", "middle name_upd", "last name_upd", "nickname", "title",
            "raik.tatyana@gmail.com", "notes_upd", "January", "company", "address",
            "1", "January", "1992", "2", "homepage", "fax",
            "work", "mobile", "home"));
    app.getNavigationHelper().goToHomePage();
  }
}