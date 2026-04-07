package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static void takeScreenshot(WebDriver driver, String screenshotName)
	{
		File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String desPath=System.getProperty("user.dir")+"./screenshot/"+screenshotName+".png";
		File des=new File(desPath);
		try {
			FileUtils.copyFile(srcFile, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
