package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserMainPageDataTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      app.user().create(new UserData().withFirstName("name").withMiddleName("middle name")
              .withLastName("lastname").withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testUserMainPageData(){
    UserData user =app.user().all().iterator().next();
    UserData userDataFromEditForm = app.user().dataFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userDataFromEditForm)));
    assertThat(user.getAllEmails(), equalTo(mergeEmails(userDataFromEditForm)));
    assertThat(user.getAddress(), equalTo(userDataFromEditForm.getAddress()));
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHome(),user.getMobile(),user.getWork())
            .stream().filter((s)-> !s.equals(""))
            .map(UserMainPageDataTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(UserData user) {
    return Arrays.asList(user.getEmail(),user.getEmail2(),user.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
