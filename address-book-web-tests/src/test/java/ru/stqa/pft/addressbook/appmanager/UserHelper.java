package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.UserData;

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

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void initUserRemoval() {
    click(By.xpath("//td[@class='center']//img[@title='Edit']"));
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initUserModification() {
    click(By.xpath("//td[@class='center']//img[@title='Edit']"));
  }

  public void submitUserModification(){
    click(By.xpath("//input[@value='Update']"));
  }

  public void fillUserForm(UserData userData) {
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
  }
}
