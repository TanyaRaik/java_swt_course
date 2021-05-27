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
//  GroupData modifyGroup = new GroupData();
//  UserData modifyUser = new UserData();

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      File file = new File("src/test/resources/stru.png");
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12").withPhoto(file), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("q"));
    }

    app.goTo().homePage();
  }

  @Test
  public void testRemoveUserFromGroup() throws Exception {
    Groups groupsList = new Groups();
    GroupData modifyGroup = new GroupData();
    modifyGroup = app.db().groups().iterator().next();

    // Create modifyUser object
    UserData modifyUser = new UserData();

    // Get list of all the Users
    Users getUsersListBefore = app.db().users();
    int numberOfUsers = 0;
    int numberOfGroups = 0;

    for (UserData user : getUsersListBefore) {
      if (user.getGroups().size() != 0) {
        groupsList = user.getGroups();
        for (GroupData group : groupsList) {
          if (group.getId() == modifyGroup.getId()) {
            modifyUser = user;
          }
          break;
        }
        break;
      }
      numberOfUsers += 1;
    }
    if (numberOfUsers == getUsersListBefore.size()) {
      for (UserData user_second_try : getUsersListBefore) {
        if (user_second_try.getGroups().size() == 0) {
          modifyUser = user_second_try;
          app.user().addUserToGroup(modifyUser, modifyGroup);
          break;
        }
      }
    }
    app.goTo().homePage();
    app.user().filterByGroup(modifyGroup);
    app.user().deleteUserFromGroup(modifyUser);

    Groups groupBefore = modifyUser.ActionsWithGroup(modifyGroup, false).getGroups();

    // After adding a user to group get an updated list and check if the user was really removed from the group
    Users getUsersListAfter = app.db().users();
    int givenId = modifyUser.getId();
    Groups newGroupsList = getUsersListAfter.stream().filter(c -> c.getId() == givenId).findFirst().get().getGroups();
    MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
  }
}