package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

public class UserData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstName;
  @Expose
  private String middleName;
  @Expose
  private String lastName;
  @Expose
  private String nickname;
  @Expose
  private String title;
  @Expose
  private String email;
  @XStreamOmitField
  private String email2;
  @XStreamOmitField
  private String email3;
  @Expose
  private String notes;
  @Expose
  private String company;
  @Expose
  private String address;
  @Expose
  private String birthDay;
  @Expose
  private String work;
  @Expose
  private String mobile;
  @Expose
  private String home;
  @XStreamOmitField
  private String group;
  @XStreamOmitField
  private String allPhones;
  @XStreamOmitField
  private String allEmails;

  public File getPhoto() {
    return photo;
  }

  public UserData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  private File photo;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public UserData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public UserData withTitle(String title) {
    this.title = title;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public UserData withCompany(String company) {
    this.company = company;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withBirthDay(String birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public UserData withWork(String work) {
    this.work = work;
    return this;
  }

  public UserData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public UserData withHome(String home) {
    this.home = home;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
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

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getNotes() {
    return notes;
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

  public String getWork() {
    return work;
  }

  public String getMobile() { return mobile; }

  public String getHome() {
    return home;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id && Objects.equals(firstName, userData.firstName) && Objects.equals(lastName, userData.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
}
