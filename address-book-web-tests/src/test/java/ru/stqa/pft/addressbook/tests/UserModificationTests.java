package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.db().users().size() == 0) {
      File photo = new File("src/test/resources/stru.png");
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12").withPhoto(photo), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testUserModification() throws Exception {
    Users before = app.db().users();
    app.goTo().homePage();
    UserData modifiedUser = before.iterator().next();
    File photo = new File("src/test/resources/stru.png");
    UserData user = new UserData().withId(modifiedUser.getId()).withFirstName("name_upd").withMiddleName("middle name")
            .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
            .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
            .withMobile("mobile").withHome("home").withBirthDay("12").withPhoto(photo);

    app.user().modify(user);
    app.goTo().homePage();
    assertThat(app.user().count(), equalTo(before.size()));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    verifyUserListInUi();
  }
}