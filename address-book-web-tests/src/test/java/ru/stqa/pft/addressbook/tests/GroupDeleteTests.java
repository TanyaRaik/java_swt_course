package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;

public class GroupDeleteTests extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.goToGroupPage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.goToGroupPage();
  }
}


