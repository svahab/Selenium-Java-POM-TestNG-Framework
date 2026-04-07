package Practise.PageObjectModel;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utils.LoginDataProvider;
import utils.ScreenshotUtil;

public class LoginTest extends SeleniumGBaseTest{
	
	@Test(dataProvider="loginData", dataProviderClass=LoginDataProvider.class)
	public void fillForm(String username, String password) {
		
		
		String screenshotName= "LoginScreen_"+username+System.currentTimeMillis();
		ScreenshotUtil.takeScreenshot(driver, screenshotName);
		System.out.println("Taking screenshot for: " + username);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(lgnPage.usernameTest));
		lgnPage.usernameTest.click(); 
		lgnPage.usernameTest.sendKeys(username);
		System.out.println("UserName entered successfully!"); 
		wait.until(ExpectedConditions.visibilityOf(lgnPage.passwordTest));
		lgnPage.passwordTest.click(); 
		lgnPage.passwordTest.sendKeys(password);
		System.out.println("Password entered successfully!"); 
		wait.until(ExpectedConditions.visibilityOf(lgnPage.loginBtn));
		lgnPage.loginBtn.click(); 
		
	}

}
