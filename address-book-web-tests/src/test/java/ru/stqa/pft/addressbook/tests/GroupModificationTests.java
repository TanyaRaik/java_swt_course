package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("q"));
    }
  }

  @Test
  public void testGroupModification() throws Exception {
    Groups before = app.group().all();

    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("q").withHeader("test12").withFooter("test123");

    app.group().modify(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    before.remove(modifiedGroup);
    before.add(group);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}