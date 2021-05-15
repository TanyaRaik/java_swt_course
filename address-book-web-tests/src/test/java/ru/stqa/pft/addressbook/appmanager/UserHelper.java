package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Groups;
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
    type(By.name("email2"), userData.getEmail2());
    type(By.name("email3"), userData.getEmail3());
    type(By.name("notes"), userData.getNotes());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("mobile"), userData.getMobile());
    type(By.name("work"), userData.getWork());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(userData.getBirthDay());
    click(By.name("bday"));
    attach(By.name("photo"), userData.getPhoto());

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
    userCashe = null;
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
    userCashe = null;
  }
  public void delete(List<UserData> before, int index) {
    selectUser(index);
    initUserRemoval(before.get(index).getId());
  }

  public void delete(UserData user) {
    selectUserById(user.getId());
    initUserRemoval(user.getId());
    userCashe = null;
  }

  public boolean isThereAUser(){
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
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

  public Users userCashe = null;

  public Users all() {
    if (userCashe != null){
    return  new Users(userCashe);
  }
    userCashe = new Users();
    List<WebElement> listOfrow = wd.findElements(By.cssSelector("tr"));
    listOfrow.remove(0);
    for (WebElement row : listOfrow) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      userCashe.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Users(userCashe);
  }

  public UserData dataFromEditForm(UserData user) {
    initUserModification(user.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withId(user.getId()).withFirstName(firstname).withLastName(lastname)
            .withHome(home).withMobile(mobile).withWork(work).
                    withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
}

