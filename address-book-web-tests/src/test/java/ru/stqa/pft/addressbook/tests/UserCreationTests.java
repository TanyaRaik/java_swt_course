package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFromJson() throws IOException {
    try(BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/users.json")))){
      String json="";
      String line = reader.readLine();
      while (line!=null) {
        json+=line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>(){}.getType());
      return users.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validUsersFromJson")
  public void testUserCreation(UserData user) throws Exception {
    Users before = app.db().users();
    app.goTo().homePage();
    File file = new File("src/test/resources/stru.png");
    user.withPhoto(file);
    user.withGroup("q");
    app.user().create(user, true);
    app.goTo().homePage();

    assertThat(app.user().count(), equalTo(before.size()+1));
    Users after = app.db().users();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

  @Test
  public  void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());

    File file = new File("src/test/resources/stru.png");
    System.out.println(file.getAbsolutePath());
    System.out.println(file.exists());

  }
}