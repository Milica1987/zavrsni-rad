package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LogInPage;

public class LogInTests extends BaseTests {

    LogInPage loginPage = new LogInPage(driver);

    @Before
    public void before() {
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testLoginWithWalidUserNamePassword() throws InterruptedException {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue("Cannot login with given credentials", expectedUrl.equals(actualUrl));

    }

    @Test
    public void testLoginWithInvalidUserName() throws InterruptedException {
        loginPage.enterUserName("aaaa");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        String expectedErrorText = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorText = loginPage.getErrorMessage();
        Assert.assertTrue("Expected error text is not found", expectedErrorText.equals(actualErrorText));

    }

    @Test
    public void testLoginWithInvalidPassword() throws InterruptedException {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("aaaa");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        String expectedErrorText = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorText = loginPage.getErrorMessage();
        Assert.assertTrue("Expected error text is not found", expectedErrorText.equals(actualErrorText));

    }

    @Test
    public void testLoginWithInvalidUserNamePassword() throws InterruptedException {
        loginPage.enterUserName("aaaa");
        loginPage.enterPassword("aaaa");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        String expectedErrorText = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorText = loginPage.getErrorMessage();
        Assert.assertTrue("Expected error text is not found", expectedErrorText.equals(actualErrorText));
    }

    @Test
    public void testLoginWithEmptyUserNameAndPassword() throws InterruptedException {
        loginPage.enterUserName("");
        loginPage.enterPassword("");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        String expectedErrorText = "Epic sadface: Username is required";
        String actualErrorText = loginPage.getErrorMessage();
        Assert.assertTrue("Expected error text is not found", expectedErrorText.equals(actualErrorText));
    }


}
