package ru.stqa.pft.rest.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.tests.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.stop();
  }

  private boolean  isIssueOpen(int issueId) throws IOException {
    String issueJson = getExecutor().execute(Request
            .Get(app.getProperty("web.baseUrl") + "/" + issueId + ".json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(issueJson);
    JsonElement issue = parsed.getAsJsonObject().get("issues");
    Set<Issue> newIssue = new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
    }.getType());
    String status = newIssue.stream().map((p) -> (p).getStatus()).findFirst().get().toString();
    System.out.println(status);
    return ! "Open".equals(status);
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
  }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}