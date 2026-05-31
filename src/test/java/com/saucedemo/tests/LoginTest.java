package com.saucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import utilsNew.LoginDataProvider;
import utilsNew.ScreenshotUtil;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void fillForm(String username, String password) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Take screenshot before starting
		try {
			String screenshotName = "LoginScreen_" + username + System.currentTimeMillis();
			ScreenshotUtil.takeScreenshot(driver, screenshotName);
			System.out.println("Screenshot taken for: " + username);
		} catch (Exception e) {
			System.out.println("Screenshot failed: " + e.getMessage());
		}

		// Enter username
		try {
			wait.until(ExpectedConditions.visibilityOf(lgnPage.usernameTest));
			wait.until(ExpectedConditions.elementToBeClickable(lgnPage.usernameTest));
			lgnPage.usernameTest.click();
			lgnPage.usernameTest.sendKeys(username);
			System.out.println("Username entered successfully: " + username);
		} catch (Exception e) {
			System.out.println("Failed to enter username: " + e.getMessage());
			Assert.fail("Username field not interactable: " + e.getMessage());
		}

		// Enter password
		try {
			wait.until(ExpectedConditions.visibilityOf(lgnPage.passwordTest));
			wait.until(ExpectedConditions.elementToBeClickable(lgnPage.passwordTest));
			lgnPage.passwordTest.click();
			lgnPage.passwordTest.sendKeys(password);
			System.out.println("Password entered successfully.");
		} catch (Exception e) {
			System.out.println("Failed to enter password: " + e.getMessage());
			Assert.fail("Password field not interactable: " + e.getMessage());
		}

		// Click login button
		try {
			wait.until(ExpectedConditions.visibilityOf(lgnPage.loginBtn));
			wait.until(ExpectedConditions.elementToBeClickable(lgnPage.loginBtn));
			lgnPage.clickLoginButton();
			System.out.println("Login button clicked successfully.");
		} catch (Exception e) {
			System.out.println("Failed to click login button: " + e.getMessage());
			Assert.fail("Login button not clickable: " + e.getMessage());
		}

		// Validate login result
		if (lgnPage.isErrorMessageDisplayed()) {

			// Invalid credentials — capture and assert error message
			try {
				wait.until(ExpectedConditions.visibilityOf(lgnPage.errorMessage));
				String errorText = lgnPage.getErrorMessage();
				System.out.println("Login failed — Error message displayed: " + errorText);
				Assert.assertTrue(
					errorText.contains("Username and password do not match") ||
					errorText.contains("Username is required") ||
					errorText.contains("Password is required"),
					"Unexpected error message: " + errorText
				);
			} catch (Exception e) {
				System.out.println("Failed to capture error message: " + e.getMessage());
				Assert.fail("Error message not visible: " + e.getMessage());
			}

		} else {

			// Valid credentials — validate home page is displayed
			try {
				HomePage homePage = new HomePage(driver);
				wait.until(ExpectedConditions.visibilityOf(homePage.inventoryContainer));
				wait.until(ExpectedConditions.visibilityOf(homePage.productsTitle));
				Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page inventory container not displayed.");
				Assert.assertEquals(homePage.getProductsTitle(), "Products", "Home page title mismatch.");
				System.out.println("Login successful — Home page validated. Title: " + homePage.getProductsTitle());
			} catch (Exception e) {
				System.out.println("Home page validation failed: " + e.getMessage());
				Assert.fail("Home page not loaded after valid login: " + e.getMessage());
			}

		}

	}

}
