package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.db().users().size() == 0) {
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testUserRemoval() throws Exception {
    Users before = app.db().users();
    app.goTo().homePage();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    assertThat(app.user().count(), equalTo(before.size()-1));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(deletedUser)));
    verifyUserListInUi();
  }
}