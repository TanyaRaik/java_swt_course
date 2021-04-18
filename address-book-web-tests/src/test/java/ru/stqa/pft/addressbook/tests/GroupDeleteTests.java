package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;

public class GroupDeleteTests extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
  }
}


