package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.goTo().homePage();
    Users before = app.user().all();

    UserData user = new UserData().withFirstName("name").withMiddleName("middle name")
            .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
            .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
            .withMobile("mobile").withHome("home").withBirthDay("12").withGroup("q");

    app.user().create(user, true);
    app.goTo().homePage();


    assertThat(app.user().count(), equalTo(before.size()+1));
    Users after = app.user().all();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }
}