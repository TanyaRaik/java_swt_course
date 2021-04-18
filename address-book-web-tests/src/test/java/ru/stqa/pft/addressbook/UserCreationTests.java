package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    initUserCreation();
    fillUserForm(
            new UserData("name", "middle name", "last name", "nickname", "title",
                    "raik.tatyana@gmail.com", "notes", "January", "company", "address",
                    "1", "January", "1992", "2", "homepage", "fax",
                    "work", "mobile", "home"));
    submitUserCreation();
  }
}