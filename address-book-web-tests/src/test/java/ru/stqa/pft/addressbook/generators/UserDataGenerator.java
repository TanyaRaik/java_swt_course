package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  @Parameter(names="-c", description="Contact count")
  public int count;

  @Parameter(names="-f", description = "Target file")
  public String file;

  @Parameter(names="-d", description = "Data format")
  public String format;

  public static void main (String [] args) throws IOException {
    UserDataGenerator generator=new UserDataGenerator();
    JCommander jCommander=new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserData> users = generateContacts (count);
    if (format.equals("csv")){
      saveAsCsv(users,new File (file));
    } else if (format.equals("xml")) {
      saveAsXml(users,new File (file));
    } else if (format.equals("json")) {
      saveAsJson(users,new File (file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(users);
    try (Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXml(List<UserData> users, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(users);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCsv (List<UserData> users, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)){
      for (UserData user : users) {
        writer.write(String.format("%s;%s;%s\n", user.getFirstName(), user.getMiddleName(), user.getLastName()));
      }
    }
  }

  private List <UserData> generateContacts(int count) {
    List <UserData> users = new ArrayList<UserData>();
    for (int i=0; i<count; i++){
      users.add(new UserData()
              .withFirstName(String.format("FirstName %s",i))
              .withMiddleName(String.format("MiddleName %s",i))
              .withLastName(String.format("LastName %s",i))
              .withNickname(String.format("Nickname %s",i))
              .withTitle(String.format("Title %s",i))
              .withEmail(String.format("Email %s",i))
              .withNotes(String.format("Notes %s",i))
              .withCompany(String.format("Company %s",i))
              .withAddress(String.format("Address %s",i))
              .withWork(String.format("WorkPhone %s",i))
              .withMobile(String.format("MobilePhone %s",i))
              .withHome(String.format("HomePhone %s",i))
              .withBirthDay(String.format("%s",DayGenerator()))
      );
    }
    return users;
  }

  private int DayGenerator() {
    int a = (int) (Math.random() * 29);
    return a;
  }
}