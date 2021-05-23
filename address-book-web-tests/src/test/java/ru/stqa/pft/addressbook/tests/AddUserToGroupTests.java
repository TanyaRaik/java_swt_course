package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class AddUserToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12"), true);
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("q"));
      }
    }
    app.goTo().homePage();
  }

  @Test
  public void testAddUserToGroup() throws Exception {
    Users before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    UserData user = modifiedUser;

    String group_id = app.user().getGroupId();
    app.goTo().homePage();
    app.user().addUserToGroup(modifiedUser, group_id);
    Users after = app.db().users();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(user).withAdded(modifiedUser)));
  }
}
