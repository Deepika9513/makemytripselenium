package makemytripselenium;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;

public class common {
	private static WebDriver driver;

	public static void scrollToElement(WebElement tripCard) throws InterruptedException {
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", tripCard);
		Thread.sleep(2000);
	}

	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public static String screenshot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);

		File screenshotFile = (scrShot.getScreenshotAs(OutputType.FILE));
        String screenshotPath =  System.getProperty("user.dir") + "/test-output/screenshots/"+screenshotName+".png";
        FileHandler.copy(screenshotFile, new File(screenshotPath));
        System.out.println("Screenshot saved at: " + screenshotPath);
        return screenshotPath;
	}


}