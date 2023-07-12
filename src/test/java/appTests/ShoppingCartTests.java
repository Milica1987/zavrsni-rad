package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.*;

import java.util.ArrayList;

public class ShoppingCartTests extends BaseTests {

    LogInPage loginPage = new LogInPage(driver);

    ProductPage productPage = new ProductPage(driver);

    YourCartPage yourCartPage = new YourCartPage(driver);

    @Before
    public void before() throws InterruptedException {

        driver.get("https://www.saucedemo.com");
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);
    }


    @Test
    public void testAddToShoppingCart() throws InterruptedException {

        productPage.addFirstProduct();
        Thread.sleep(2000);

        productPage.addSecondProduct();
        Thread.sleep(2000);

        productPage.goToShoppingCart();
        Thread.sleep(2000);

        String expectedPage = "https://www.saucedemo.com/cart.html";
        String actualPage = driver.getCurrentUrl();
        Assert.assertTrue("Wrong page url", expectedPage.equals(actualPage));

        String expectedFirstItemText = "Sauce Labs Bike Light";
        String actualFirstItemTest = yourCartPage.getFirstItemName();
        System.out.println("actualFirstItemTest: " + actualFirstItemTest);
        Assert.assertTrue(actualFirstItemTest.equals(expectedFirstItemText));

        String expectedSecondItemText = "Sauce Labs Bolt T-Shirt";
        String actualSecondItemTest = yourCartPage.getSecondItemName();
        System.out.println("actualSecondItemTest: " + actualSecondItemTest);
        Assert.assertTrue(actualSecondItemTest.equals(expectedSecondItemText));

    }

    @Test
    public void testRemoveFromShoppingCart() throws InterruptedException {

        productPage.addFirstProduct();
        Thread.sleep(2000);

        productPage.goToShoppingCart();
        Thread.sleep(2000);

        String shoppingCartText = yourCartPage.getShoppingCartContentText();
        System.out.println("shoppingCartText: " + shoppingCartText);

        yourCartPage.removeFirstProduct();
        Thread.sleep(2000);

        shoppingCartText = yourCartPage.getShoppingCartContentText();
        System.out.println("shoppingCartText: " + shoppingCartText);

        Assert.assertFalse(shoppingCartText.contains("Remove"));

    }

    @Test
    public void testTwitterButton() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(2000);

        productPage.clickOnTwitterLogo();
        Thread.sleep(2000);

        //https://stackoverflow.com/questions/12729265/switch-tabs-using-selenium-webdriver-with-java
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);



        String currentPage = driver.getCurrentUrl();
        System.out.println(currentPage);
        String expectedPage = "https://twitter.com/i/flow/login?redirect_after_login=%2Fsaucelabs";
        Assert.assertTrue(currentPage.equals(expectedPage));

    }

    @Test
    public void testFacebookPage() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(2000);

        productPage.clickOnFacebookLogo();
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);

        String currentPage = driver.getCurrentUrl();
        String expectedPage = "https://www.facebook.com/saucelabs";
        Assert.assertTrue(currentPage.equals(expectedPage));

    }

    @Test
    public void testLinkedinPage() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(2000);

        productPage.clickOnLinkedinLogo();
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);

        String currentPage = driver.getCurrentUrl();
        String expectedPage = "https://www.linkedin.com/company/sauce-labs/";
        Assert.assertTrue(currentPage.equals(expectedPage));

    }

}
