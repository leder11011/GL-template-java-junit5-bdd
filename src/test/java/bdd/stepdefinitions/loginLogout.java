package bdd.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;


import java.time.Duration;

public class loginLogout {
    public static final String PAGE_TITLE = "ALDI ONLINESHOP - Gutes für alle";
    public static final String MAIL = "xc9vfn@re-gister.com";
    public static final String PASSWORD = "9R64wTB4mUhKXUjdP8vI!";

    WebDriver driver;

    Wait<WebDriver> wait;


    @Given("initialize driver")
    public void initializeDriver() {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @When("locate home page")
    public void locateHomePage() {
        driver.get("https://aldi-onlineshop.de");
    }

    @Then("check title")
    public void checkTitle() {
        String title = driver.getTitle();

        System.out.println(title);

        Assert.assertEquals("Title is valid", PAGE_TITLE, title);
    }

    @Then("cookie handling")
    public void cookieHandling() throws InterruptedException {
        WebElement shadowHost = driver.findElement(By.id("usercentrics-root"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        Thread.sleep(2000);

        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("[data-testid='uc-accept-all-button']"));

//        WebElement element = (WebElement) ((JavascriptExecutor)driver)
//                .executeScript("return arguments[0].shadowRoot", driver.findElement(By.id("usercentrics-root")));
//
//        element.findElement(By.xpath("//button[data-testid()='uc-accept-all-button']")).click();

        shadowContent.click();
    }

    @Then("login testuser")
    public void loginTestUser() throws InterruptedException {
        WebElement loginMenu = driver.findElement(By.cssSelector("[data-attr-value='My Account']"));

        loginMenu.click();

        Thread.sleep(2000);

        WebElement eMail = driver.findElement(By.id("gigya-loginID-131224082105353660"));
        eMail.sendKeys(MAIL);

        Thread.sleep(2000);

        WebElement password = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Hier Passwort eingeben']")));


//        WebElement myElement = driver.findElement(By.className("gigya-input-password"));
//        String js = "arguments[0].setAttribute('value','"+PASSWORD+"')";
//        ((JavascriptExecutor) driver).executeScript(js, myElement);

//        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Hier Passwort eingeben']"));
        password.sendKeys(PASSWORD);

        WebElement button = driver.findElement(By.xpath("//input[@value='Anmelden und weiter']"));

        button.click();

        Thread.sleep(2000);

        WebElement logged_in = driver.findElement(By.xpath("//div[contains(.,' test test')]"));

        Assert.assertTrue("Logged in", logged_in.isDisplayed());
    }

    @Then("shutdown driver")
    public void shutdownDriver() {
        driver.quit();
    }
}
