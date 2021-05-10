package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.user().list().size() == 0) {
      app.user().create(new UserData("name", "middle name", "last name", "nickname", "title",
              "raik.tatyana@gmail.com", "notes", "January", "company", "address",
              "1", "January", "1992", "2", "homepage", "fax",
              "work", "mobile", "home", "q"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testUserModification() throws Exception {
    List<UserData> before = app.user().list();
    int index = before.size()-1;
    UserData user = new UserData(before.get(index).getId(), "name_upd", "middle name", "last name", "nickname", "title",
            "raik.tatyana@gmail.com", "notes", "January", "company", "address",
            "1", "January", "1992", "2", "homepage", "fax",
            "work", "mobile", "home", null);

    app.user().modify(before, index, user);
    app.goTo().homePage();
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}