package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

public class RemoveUserFromGroupTests extends TestBase {
  private int maxId;
  GroupData modifyGroup = new GroupData();
  UserData modifyUser = new UserData();

  @BeforeMethod
  public void ensurePreconditions() {


    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("q"));
    }

    modifyGroup = app.db().groups().iterator().next();

    app.goTo().homePage();
    File file = new File("src/test/resources/stru.png");
    app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
            .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
            .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
            .withMobile("mobile").withHome("home").withBirthDay("12").withPhoto(file), true);
    app.goTo().homePage();
  }

  @Test
  public void testRemoveUserFromGroup() throws Exception {
    Users getUsersAfterCreation = app.db().users();
    for (UserData eachUser : getUsersAfterCreation) {
      if (eachUser.getId() > maxId) {
        modifyUser = eachUser;
        app.user().addUserToGroup(modifyUser, modifyGroup);
        app.goTo().homePage();
        app.user().filterByGroup(modifyGroup);
        app.user().deleteUserFromGroup(modifyUser);
        break;
      }

    }

    Groups groupBefore = modifyUser.ActionsWithGroup(modifyGroup, false).getGroups();

    // After adding a user to group get an updated list and check if the user was really removed to the group
    Users getUsersListAfter = app.db().users();
    int givenId = modifyUser.getId();
    Groups newGroupsList = getUsersListAfter.stream().filter(c -> c.getId() == givenId).findFirst().get().getGroups();
    MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
  }
}