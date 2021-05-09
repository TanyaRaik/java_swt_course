package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

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
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("mobile"), userData.getMobile());
    type(By.name("work"), userData.getWork());
    type(By.name("fax"), userData.getFax());
    type(By.name("email"), userData.getEmail());
    type(By.name("homepage"), userData.getHomepage());
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(userData.getBirthDay());
    click(By.name("bday"));
    click(By.name("bmonth"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(userData.getBirthMonth());
    click(By.name("bmonth"));
    type(By.name("byear"), userData.getBirthYear());
    click(By.name("aday"));
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(userData.getDay());
    click(By.name("aday"));
    click(By.name("amonth"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(userData.getMonth());
    click(By.name("amonth"));
    type(By.name("notes"), userData.getNotes());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void createUser(UserData userData, boolean creation) {
    initUserCreation();
    fillUserForm(userData, creation);
    submitUserCreation();
  }

  public boolean isThereAUser(){
    return isElementPresent(By.name("selected[]"));
  }

  public int getUserCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> getUserList() {
    List<UserData> listOfUsers = new ArrayList<UserData>();
    List<WebElement> listOfRows = wd.findElements(By.cssSelector("tr"));
    listOfRows.remove(0);
    for (WebElement row : listOfRows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id =Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      UserData user = new UserData(id, firstname, "middle name", lastname, "nickname", "title",
              "raik.tatyana@gmail.com", "notes", "January", "company", "address",
              "1", "January", "1992", "2", "homepage", "fax",
              "work", "mobile", "home", "q");
      listOfUsers.add(user);
    }
    return listOfUsers;
  }
}

