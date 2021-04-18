package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.initUserCreation();
    app.fillUserForm(
            new UserData("name", "middle name", "last name", "nickname", "title",
                    "raik.tatyana@gmail.com", "notes", "January", "company", "address",
                    "1", "January", "1992", "2", "homepage", "fax",
                    "work", "mobile", "home"));
    app.submitUserCreation();
  }
}