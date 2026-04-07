package utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandles {

	
	@Test
	public void handleMWindows() throws InterruptedException {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.get("https://vinothqaacademy.com/multiple-windows/");
	Thread.sleep(3000);
	driver.manage().window().maximize();
	   String parentTitle=  driver.getTitle();
	   System.out.println("Parent title="+parentTitle);
WebElement followall=	driver.findElement(By.xpath("//button[@id='button1']"));
        String parentWindow=driver.getWindowHandle();
        System.out.println(parentWindow);
        
        followall.click();
        Thread.sleep(3000);
     Set<String> mWHandles=driver.getWindowHandles();
     for (String windows : mWHandles) {
    	 
    	 if(!windows.equals(parentWindow))
    	 {
    		driver.switchTo().window(windows);
    		  String childTitle=  driver.getTitle();
    		   System.out.println("ChildTitile="+childTitle);
    		   WebElement nameInput=	driver.findElement(By.xpath("//input[@id='nameInput']"));
    		   try {
    			   
    				WebDriverWait wait= new WebDriverWait(driver, 10);
    				wait.until(ExpectedConditions.visibilityOf(nameInput));

                   nameInput.sendKeys("Test23");

               } catch (Exception e) {
                   System.out.println("Element not found in this window");
               }

               driver.close(); // close child tab
           }
       }
    		   
    		   
    		
    	
     
     driver.switchTo().window(parentWindow);
     String parentTitle1=  driver.getTitle();
	   System.out.println("Parent title="+parentTitle1);
	   
	   driver.quit();
}
}
