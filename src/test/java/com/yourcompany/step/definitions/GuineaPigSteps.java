package com.yourcompany.step.definitions;

import com.yourcompany.utils.SauceUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.util.UUID;
import static org.junit.Assert.*;


import com.yourcompany.Pages.*;

import static org.hamcrest.CoreMatchers.containsString;

public class GuineaPigSteps {

    public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static String app = "https://github.com/saucelabs-sample-test-frameworks/GuineaPig-Sample-App/blob/master/android/GuineaPigApp-debug.apk?raw=true";
    public static AndroidDriver driver;
    public static GuineaPigPage page;
    public String commentInputText;
    public String sessionId;
    public String jobName;
    public String buildTag;
    protected String platformName = System.getenv("platformName");
    protected String appiumVersion = System.getenv("appiumVersion");
    protected String platformVersion = System.getenv("platformVersion");
    protected String deviceName = System.getenv("deviceName");
    protected String deviceOrientation = System.getenv("deviceOrientation");

    @Before
    public void setUp(Scenario scenario) throws Exception {
        jobName = scenario.getName();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", this.platformName);
        capabilities.setCapability("platformVersion", this.platformVersion);
        capabilities.setCapability("deviceName", this.deviceName);
        capabilities.setCapability("deviceOrientation", this.deviceOrientation);
        capabilities.setCapability("appiumVersion", this.appiumVersion);
        capabilities.setCapability("app", app);
        capabilities.setCapability("name", jobName);

        buildTag = System.getenv("BUILD_TAG");
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        driver = new AndroidDriver(new URL(URL), capabilities);
        sessionId = driver.getSessionId().toString();
    }

    @Given("^I am on the Guinea Pig homepage$")
    public void user_is_on_guinea_pig_page() throws Exception {
        page = new GuineaPigPage(driver);
    }

    @When("^I click on the link$")
    public void user_click_on_the_link() throws Exception {
        page.followLink();
    }

    @When("^I submit a comment$")
    public void user_submit_comment() throws Exception {
        commentInputText = UUID.randomUUID().toString();
        page.submitComment(commentInputText);
    }

    @Then("^I should be on another page$")
    public void new_page_displayed() throws Exception {
        assertFalse(page.isOnPage());
    }

    @Then("^I should see that comment displayed$")
    public void comment_displayed() throws Exception {
        assertThat(page.getSubmittedCommentText(), containsString(commentInputText));
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        driver.quit();
        SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId);
        System.out.println("SauceOnDemandSessionID="+ sessionId + "job-name="+ jobName);
    }
}