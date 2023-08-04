package tests.browserstack;

import com.codeborne.selenide.Configuration;
import drivers.AndroidBrowserstackDriver;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selenide.*;


public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = AndroidBrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId().toString();

//        Attach.screenshotAs("Last screenshot"); //todo
        Attach.pageSource();

        closeWebDriver();

        Attach.addVideo(sessionId);
    }
}
