package utilsNew;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFileAndDownload {
	
	@Test
	public void uploadFile() throws InterruptedException {
		
	//	System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		Thread.sleep(3000);
		driver.manage().window().maximize();
	WebElement elementFile=	driver.findElement(By.xpath("//input[@type='file']"));
	File file=new File("./logo.png");
	elementFile.sendKeys(file.getAbsolutePath());
	WebElement startUpload=	driver.findElement(By.xpath("//span[contains(text(),'Start upload')]"));
	startUpload.click();
	WebElement deleteBtn=driver.findElement(By.xpath("//span[contains(text(),'Delete')]"));
	WebDriverWait wait= new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOf(deleteBtn));
	deleteBtn.click();
	
	}

}
