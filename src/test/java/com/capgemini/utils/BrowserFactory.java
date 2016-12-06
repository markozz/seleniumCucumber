package com.capgemini.utils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    private static WebDriver browser;
    private static boolean running = false;

    public static WebDriver getBrowser() {
        return browser;
    }

    @Before
    public void beforeAfterAllHooks() {
        if (!running) {
            //beforeAll hook
            beforeAll();

            // afterAll hook
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    afterAll();
                }
            });
            running = true;
        }
    }

    private void beforeAll() {
//        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\geckodriver.exe");
//        browser = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        browser = new ChromeDriver();
    }

    private void afterAll() {
        browser.quit();
    }
}
