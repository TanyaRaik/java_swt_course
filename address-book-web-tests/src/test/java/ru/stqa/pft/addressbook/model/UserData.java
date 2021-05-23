package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="addressbook")
public class UserData {
  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstName;
  @Expose
  @Column(name = "middlename")
  private String middleName;
  @Expose
  @Column(name = "lastname")
  private String lastName;
  @Expose
  @Column(name = "nickname")
  private String nickname;
  @Expose@Column(name = "title")
  private String title;
  @Expose
  @Column(name = "email")
  @Type(type="text")
  private String email;
  @XStreamOmitField
  @Column(name = "email2")
  @Type(type="text")
  private String email2;
  @XStreamOmitField
  @Column(name = "email3")
  @Type(type="text")
  private String email3;
  @Expose
  @Column(name = "notes")
  @Type(type="text")
  private String notes;
  @Expose
  @Column(name = "company")
  private String company;
  @Expose
  @Column(name = "address")
  @Type(type="text")
  private String address;
  @Expose
  @Transient
  private String birthDay;
  @Expose
  @Column(name = "work")
  @Type(type="text")
  private String work;
  @Expose
  @Column(name = "mobile")
  @Type(type="text")
  private String mobile;
  @Expose
  @Column(name = "home")
  @Type(type="text")
  private String home;
//  @XStreamOmitField
//  @Transient
//  private String group;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @XStreamOmitField
  @Transient
  private String allEmails;

  @Column(name = "photo")
  @Type(type="text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    return new File(photo);
  }

  public UserData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

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

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public Groups getGroups(){
    return new Groups(groups);
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
