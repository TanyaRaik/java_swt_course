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

public class AddUserToGroupTests extends TestBase {
  private int maxId;

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
  public void testAddUserToGroup() throws Exception {
    // Get group in which we are planning to add a user
    GroupData modifyGroup = new GroupData();
    modifyGroup = app.db().groups().iterator().next();

    // Create modifyUser object
    UserData modifyUser = new UserData();

    // Get list of all the Users
    Users getUsersListBefore = app.db().users();
    int i = 0;

    // Add a loop in with we are checking if at least one user has NO group and add him to the selected group
    for (UserData user : getUsersListBefore) {
      if (user.getGroups().size() == 0) {
        modifyUser = user;
        app.user().addUserToGroup(modifyUser, modifyGroup);
        break;
      }
      i += 1;

      // If no user without group is found create a new one and add him to selected group
      if (i == getUsersListBefore.size()) {
        app.goTo().homePage();
        File file = new File("src/test/resources/stru.png");
        app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
                .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
                .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
                .withMobile("mobile").withHome("home").withBirthDay("12").withPhoto(file), true);
        app.goTo().homePage();
        Users getUsersAfterCreation = app.db().users();
        for (UserData eachUser : getUsersAfterCreation) {
          if (eachUser.getId() > maxId) {
            modifyUser = eachUser;
            app.user().addUserToGroup(modifyUser, modifyGroup);
          }
        }
      }
    }

    Groups groupBefore = modifyUser.ActionsWithGroup(modifyGroup, true).getGroups();

    // After adding a user to group get an updated list and check if the user was really added to the group
    Users getUsersListAfter = app.db().users();
    int givenId = modifyUser.getId();
    Groups newGroupsList = getUsersListAfter.stream().filter(c -> c.getId() == givenId).findFirst().get().getGroups();
    MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
  }
}
