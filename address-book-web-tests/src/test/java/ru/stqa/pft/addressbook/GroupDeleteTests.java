package ru.stqa.pft.addressbook;


import org.testng.annotations.*;

public class GroupDeleteTests extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroup();
    goToGroupPage();
  }
}


