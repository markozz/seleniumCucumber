package com.capgemini.utils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
        Properties properties = new Properties();
        String browser = "";
        try {
            FileInputStream propertiesFile = new FileInputStream("src/test/resources/com/capgemini/config/config.properties");
            properties.load(propertiesFile);
            browser = properties.getProperty("browser");
        } catch (FileNotFoundException e) {
            System.out.println("WARN: could not find config file, using default browser (Chrome)");
            browser = "chrome";

        } catch (IOException e) {
            System.out.println("WARN: could not load properties file, using default browser (Chrome)");
            browser = "chrome";
        } finally {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
                    this.browser = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
                    this.browser = new FirefoxDriver();
                    break;
                default:
                    System.out.println("WARN: Browser specified in properties file is unsupported, using default (Chrome)");
                    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
                    this.browser = new ChromeDriver();
                    break;
            }
        }


    }

    private void afterAll() {
        browser.quit();
    }
}
