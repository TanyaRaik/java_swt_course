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
    if (app.user().list().size() == 0) {
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12").withGroup("q"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testUserRemoval() throws Exception {
    Users before = app.user().all();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    Users after = app.user().all();
    assertThat(after.size(), equalTo(before.size() - 1));

    assertThat(after, equalTo(before.without(deletedUser)));
  }
}