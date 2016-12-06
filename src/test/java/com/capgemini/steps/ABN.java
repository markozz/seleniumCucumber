package com.capgemini.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.capgemini.utils.BrowserFactory;

import java.net.MalformedURLException;

public class ABN {
    private WebDriver browser;

    public ABN() throws MalformedURLException {
        this.browser = BrowserFactory.getBrowser();
    }

    @Given("^I am at ABN website$")
    public void iMOnABNAmroSite() throws Throwable {
        browser.get("https://www.abnamro.nl/nl/prive/index.html");
    }

    @And("^I accept cookies$")
    public void iAcceptCookies() throws Throwable {
        browser.findElement(By.cssSelector(".mlf-js-cookie-accept.mlf-button.mlf-button-action")).click();
    }

    @And("^I select menu option Verzekeren$")
    public void iSelectMenuOptionVerzekeren() throws Throwable {
        browser.findElement(By.cssSelector("a[href='/nl/prive/verzekeren/']")).click();
    }

    @And("^I select menu option \"([^\"]*)\"$")
    public void iSelectMenuOption(String menuOption) throws Throwable {
        browser.findElement(By.cssSelector("a[href='/nl/prive/" + menuOption + "/']")).click();
    }
}
