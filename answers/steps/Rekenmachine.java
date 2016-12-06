package com.capgemini.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.capgemini.utils.BrowserFactory;
import cucumber.api.java.en.Given;

/**
 * Created by MARABRAH on 5-12-2016.
 */
public class Rekenmachine {

    private WebDriver browser;

    public Rekenmachine() {
        this.browser = BrowserFactory.getBrowser();
    }

    @Given("^I am at \"([^\"]*)\"$")
    public void iAmAt(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        browser.get(url);
    }

    @When("^I sum (\\d+) and (\\d+)$")
    public void iSumAnd(int digit1, int digit2) throws Throwable {
        browser.findElement(By.cssSelector(".operand[data-value=\"" + digit1 + "\"]")).click(); // click on button 5
        browser.findElement(By.cssSelector(".plus")).click(); // click on button +
        browser.findElement(By.cssSelector(".operand[data-value=\"" + digit2 + "\"]")).click(); // click on button 8;
    }

    @And("^press \"([^\"]*)\"$")
    public void press(String operand) throws Throwable {
        browser.findElement(By.cssSelector(".equal")).click(); // click on button =
    }

    @Then("^I expect the result is (\\d+)$")
    public void iExpectTheResultIs(int expected) throws Throwable {
        String actual = browser.findElement(By.cssSelector(".result")).getText(); // get result from textfield
        int actualInteger = Integer.parseInt(actual);
        Assert.assertEquals("Outcome is not as expected.", expected, actualInteger);
    }

    @And("^I maximize the screen$")
    public void iAcceptTheCookies() throws Throwable {
        browser.manage().window().maximize();
    }

    @When("^I substract (\\d+) from (\\d+)$")
    public void iSubstractFrom(String digit1,String digit2) throws Throwable {
        enterNumber(digit2); // enter 5
        browser.findElement(By.cssSelector(".minus")).click(); // click on button -
        enterNumber(digit1); // enter 3
    }

    @When("^I enter the calculation (\\d+) ([/*+-]) (\\d+)$")
    public void iEnterTheCalculation(String digit1, String operand, String digit2) throws Throwable {
        enterNumber(digit1);
        enterOperator(operand);
        enterNumber(digit2);
    }

    private void enterNumber(String number) {
        if (number.length() > 1) {
            // number has multiple digits so need to enter more digits
            for (int i = 0; i < number.length(); i++) {
                String part = number.substring(i, i + 1);
                String cssSelector = ".operand[data-value=\"" + part + "\"]";
                browser.findElement(By.cssSelector(cssSelector)).click();
            }
        } else {
            // number can be entered in one time
            browser.findElement(By.cssSelector(".operand[data-value=\"" + number + "\"]")).click();
        }
    }
    private void enterOperator(String operator) {

        String operatorSelector = null;
        switch (operator) {
            case "-":
                operatorSelector = ".minus";
                break;
            case "+":
                operatorSelector = ".plus";
                break;
            case "/":
                operatorSelector = ".divider";
                break;
            case "*":
                operatorSelector = ".multiply";
                break;
            default:
                Assert.fail("Operator \'" + operator + "\' is not a valid operator!");
        }
        browser.findElement(By.cssSelector(operatorSelector)).click();
    }
}
