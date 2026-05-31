package com.saucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

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

		// Validate login result — capture error message on invalid credentials
		try {
			boolean errorPresent = wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")),
				ExpectedConditions.urlContains("inventory")
			));

			if (lgnPage.isErrorMessageDisplayed()) {
				String errorText = lgnPage.getErrorMessage();
				System.out.println("Login failed — Error message displayed: " + errorText);
				Assert.assertTrue(
					errorText.contains("Username and password do not match") ||
					errorText.contains("Username is required") ||
					errorText.contains("Password is required"),
					"Unexpected error message: " + errorText
				);
			} else {
				System.out.println("Login successful — Redirected to inventory page for user: " + username);
			}
		} catch (Exception e) {
			System.out.println("Post-login validation failed: " + e.getMessage());
			Assert.fail("Unable to verify login result: " + e.getMessage());
		}

	}

}
