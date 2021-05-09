package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("name", "middle name", "last name", "nickname", "title",
              "raik.tatyana@gmail.com", "notes", "January", "company", "address",
              "1", "January", "1992", "2", "homepage", "fax",
              "work", "mobile", "home", "q"), true);
    }
    app.getNavigationHelper().goToHomePage();
    List<UserData> before = app.getUserHelper().getUserList();

    app.getUserHelper().selectUser(before.size()-1);
    app.getUserHelper().initUserModification(before.get(before.size()-1).getId());
    UserData user = new UserData(before.get(before.size()-1).getId(), "name_upd", "middle name", "last name", "nickname", "title",
            "raik.tatyana@gmail.com", "notes", "January", "company", "address",
            "1", "January", "1992", "2", "homepage", "fax",
            "work", "mobile", "home", null);

    app.getUserHelper().fillUserForm(user, false);
    app.getUserHelper().submitUserModification();

    app.getNavigationHelper().goToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}