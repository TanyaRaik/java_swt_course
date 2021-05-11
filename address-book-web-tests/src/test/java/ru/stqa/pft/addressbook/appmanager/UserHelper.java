package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase{

  public UserHelper (WebDriver wd){
    super(wd);
  }

  public void submitUserCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void initUserCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initUserRemoval(int id) {
    click(By.xpath("//td[@class='center']//a[@href='edit.php?id="+ id + "']"));
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initUserModification(int id) {
    click(By.xpath("//td[@class='center']//a[@href='edit.php?id="+ id + "']"));
  }

  public void submitUserModification(){
    click(By.xpath("//input[@value='Update']"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstName());
    type(By.name("middlename"), userData.getMiddleName());
    type(By.name("lastname"), userData.getLastName());
    type(By.name("nickname"), userData.getNickname());
    type(By.name("title"), userData.getTitle());
    type(By.name("email"), userData.getEmail());
    type(By.name("notes"), userData.getNotes());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("mobile"), userData.getMobile());
    type(By.name("work"), userData.getWork());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(userData.getBirthDay());
    click(By.name("bday"));

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void create(UserData userData, boolean creation) {
    initUserCreation();
    fillUserForm(userData, creation);
    submitUserCreation();
  }

//  public void modify(List<UserData> before, int index, UserData user) {
//    selectUser(index);
//    initUserModification(before.get(index).getId());
//    fillUserForm(user, false);
//    submitUserModification();
//  }

  public void modify(UserData user) {
    selectUserById(user.getId());
    initUserModification(user.getId());
    fillUserForm(user, false);
    submitUserModification();
  }
  public void delete(List<UserData> before, int index) {
    selectUser(index);
    initUserRemoval(before.get(index).getId());
  }

  public void delete(UserData user) {
    selectUserById(user.getId());
    initUserRemoval(user.getId());
  }

  public boolean isThereAUser(){
    return isElementPresent(By.name("selected[]"));
  }

  public int getUserCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> list() {
    List<UserData> listOfUsers = new ArrayList<UserData>();
    List<WebElement> listOfRows = wd.findElements(By.cssSelector("tr"));
    listOfRows.remove(0);
    for (WebElement row : listOfRows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id =Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      UserData user = new UserData().withId(id).withFirstName(firstname).withMiddleName("middle name")
              .withLastName(lastname).withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12").withGroup("q");
      listOfUsers.add(user);
    }
    return listOfUsers;
  }

  public Users all() {
    Users listOfUsers = new Users();
    List<WebElement> listOfrow = wd.findElements(By.cssSelector("tr"));
    listOfrow.remove(0);
    for (WebElement row : listOfrow) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      listOfUsers.add(new UserData().withId(id).withFirstName(firstname).withMiddleName("middle name")
              .withLastName(lastname).withNickname("nickname").withTitle("title").withEmail("raik.tatyana@gmail.com")
              .withNotes("notes").withCompany("company").withAddress("address").withWork("work")
              .withMobile("mobile").withHome("home").withBirthDay("12").withGroup("q"));
    }
    return listOfUsers;
  }
}

