package ru.stqa.pft.addressbook.model;

public class UserData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickname;
  private final String title;
  private final String email;
  private final String notes;
  private final String month;
  private final String company;
  private final String address;
  private final String birthDay;
  private final String birthMonth;
  private final String birthYear;
  private final String day;
  private final String homepage;
  private final String fax;
  private final String work;
  private final String mobile;
  private final String home;

  public UserData(String firstName, String middleName, String lastName, String nickname, String title, String email, String notes, String month, String company, String address, String birthDay, String birthMonth, String birthYear, String day, String homepage, String fax, String work, String mobile, String home) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.title = title;
    this.email = email;
    this.notes = notes;
    this.month = month;
    this.company = company;
    this.address = address;
    this.birthDay = birthDay;
    this.birthMonth = birthMonth;
    this.birthYear = birthYear;
    this.day = day;
    this.homepage = homepage;
    this.fax = fax;
    this.work = work;
    this.mobile = mobile;
    this.home = home;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getEmail() {
    return email;
  }

  public String getNotes() {
    return notes;
  }

  public String getMonth() {
    return month;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getBirthDay() {
    return birthDay;
  }

  public String getBirthMonth() {
    return birthMonth;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public String getDay() {
    return day;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getFax() {
    return fax;
  }

  public String getWork() {
    return work;
  }

  public String getMobile() {
    return mobile;
  }

  public String getHome() {
    return home;
  }
}
