package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<UserData> before = app.getUserHelper().getUserList();

    UserData user = new UserData("name", "middle name", "last name", "nickname", "title",
            "raik.tatyana@gmail.com", "notes", "January", "company", "address",
            "1", "January", "1992", "2", "homepage", "fax",
            "work", "mobile", "home", "q");

    app.getUserHelper().createUser(user, true);
    app.getNavigationHelper().goToHomePage();

    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}