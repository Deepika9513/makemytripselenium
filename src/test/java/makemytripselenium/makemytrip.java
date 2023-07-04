package makemytripselenium;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import makemytripselenium.common;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;

public class makemytrip {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extenttest;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		// open the url
		driver.get("https://www.makemytrip.com/");
//		driver.get(
//				"https://holidayz.makemytrip.com/holidays/india/search?fromSearchWidget=true&searchDep=Singapore&dest=Singapore&destValue=Singapore&depCity=Bangalore&initd=searchwidget_landing_Singapore_notheme&dateSearched=29%2F07%2F2023&glp=false&rooms=2%2C0%2C0%2C0%2C%2C%2C&affiliate=MMT##page_header");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Set up ChromeDriver path
		 extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/makemytripTest.html");
		 extent.loadConfig(new File(System.getProperty("user.dir") +"/src/test/java/extent-config.xml"));
	}

	@Test
	public void selectholidaypackage() throws InterruptedException, IOException {
		extenttest = extent.startTest("Selecting Holiday Package of Makemytrip");
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "HomePageMakemyTrip"))) + "MakemyTrip Homepage selected as expected");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[@class='chNavText darkGreyText'])[4]")).click();
		Thread.sleep(2000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "HolidayPackage"))) + "MakemyTrip Holiday package selected as expected");
		// Click on the input element to open the dropdown options
		WebElement dropdownInput = driver.findElement(By.id("fromCity"));
		dropdownInput.click();

		// Locate and click on the desired option ("Bangalore")
		WebElement optionBangalore = driver.findElement(By.xpath("//li[contains(text(), 'Bangalore')]"));
		optionBangalore.click();

		// Click on the input element to open the dropdown options
		WebElement dropdownInput1 = driver.findElement(By.id("toCity"));
		dropdownInput1.click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("Singapore");
		Thread.sleep(2000);
		// Locate and click on the desired option ("Singapore")
		WebElement optionSingapore = driver
				.findElement(By.xpath("//*[@class='dest-city-name'][contains(text(),'Singapore')]"));
		optionSingapore.click();
		Thread.sleep(3000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, " FromcityandToCityPackage"))) + "Selecetd From city and To City selected as expected");
		// select the date from datepicker
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@aria-label='Sat Jul 29 2023']")).click();
		System.out.println("date is choosen");
		Thread.sleep(4000);
		//extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, " From city and To City Package"))) + "DatePicker Selecetd is selected as expected");
		driver.findElement(By.xpath("//button[normalize-space()='APPLY']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='action']")).click();
		Thread.sleep(2000);
		// click search
		WebElement search = driver.findElement(By.id("search_button"));
		search.click();
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "search"))) + "Selecetd From city and To City and click search selected as expected");
		driver.findElement(By.xpath("//button[@class='skipBtn']")).click();
		// close the msg bar
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By popupLocator = By.xpath("//*[@id='modalpopup']"); // Adjust the locator as per the actual popup element

		wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
		WebElement popup = driver.findElement(popupLocator);
		// Perform actions on the popup, e.g., close the popup
		popup.findElement(By.xpath("//span[@class='close closeIcon']")).click(); // Adjust the locator for the close
																					// button
		Thread.sleep(5000);

		driver.findElement(By.xpath("(//*[@data-testid='days-badge'][contains(text(),'5N/6D')])[1]")).click();

		Thread.sleep(2000);
		driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='skipBtn']")).click();
		Thread.sleep(2000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "ScrollandSelect"))) + "Scrolled and Selected selected as expected");
		// change on new hotel
		WebElement ChangeButton = driver
				.findElement(By.xpath("//div[@data-testid='changeRemoveBtn_hotel']//span[@id='change']"));
		ChangeButton.click();
		// change of hotel
		Thread.sleep(15000);
		driver.findElement(By.xpath("(//*[@class='modalContent']//*[@class='primaryBtn fill selectBtn'])[1]")).click();
		Thread.sleep(2000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "NewHotel"))) + "Selecetd New Hotel selected as expected");
		driver.findElement(By.id("updateHotelBtnClick")).click();
		Thread.sleep(1000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "UpdatedPackage"))) + " updated package selected as expected");
		System.out.println("package updated");
		Thread.sleep(3000);
		
		// Find the element containing the text
		WebElement element = driver.findElement(By.xpath("//*[@class='hotel-row-details-title']"));

		// Get the text from the element
		String actualText = element.getText();

		// Define the expected text
		String expectedText = "DASH LIVING ROCHER (Previously Aqueen Heritage Little India) Holidays Selections";

		// Compare the actual and expected text
		if (actualText.contains(expectedText)) {
			System.out.println("Text is present on the page.");
		} else {
			System.out.println("Text is not present on the page.");
		}
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "HotelValidation"))) + "Validation finished as expected");

		// to click add an activity
		driver.findElement(By.xpath("(//*[@id='chooseAndAddBtn'])[1]")).click();
		Thread.sleep(2000);
		// after clicking add and search
		driver.findElement(By.xpath("//input[@placeholder='Search by Activity name']")).sendKeys("Mirror Maze");
		Thread.sleep(2000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "Activity"))) + "Activity selected as expected");

		driver.findElement(By.xpath("//span[@class='primaryBtn fill selectBtn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[@class='updatePackageBtnText font10 btn btn-primary btn-sm']")).click();

		Thread.sleep(3000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "afterUpdate"))) + " Updation finished as expected");

		WebElement element1 = driver.findElement(By.xpath("//*[@class='activity-row-details-title']"));

		// Get the text from the element
		String actualText1 = element1.getText();

		// Define the expected text
		String expectedText1 = "Jewel Changi Airport (Mirror Maze)- Ticket Only";

		// Compare the actual and expected text
		if (actualText1.contains(expectedText1)) {
			System.out.println("Activity Text is present on the page.");
		} else {
			System.out.println("Activity Text is not present on the page.");
		}
		Thread.sleep(2000);
		// verify all the selected hotels and activity
		driver.findElement(By.xpath("//ul[@id='initeraryNav']//li[3]")).click();
		Thread.sleep(2000);
		// Find the element containing the text
		WebElement HotelName = driver.findElement(By.xpath("//*[@class='hotel-row-details-title']"));

		// Get the text from the element
		String actualText2 = HotelName.getText();

		// Define the expected text
		String expectedText2 = "DASH LIVING ROCHER (Previously Aqueen Heritage Little India) Holidays Selections";

		// Compare the actual and expected text
		if (actualText2.contains(expectedText2)) {
			System.out.println("Hotel Name is present on the page.");
		} else {
			System.out.println("Hotel Name is not present on the page.");
		}
		Thread.sleep(2000);
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "valiandupdate"))) + "Validated and updated as expected");
//validate all the selected links
		driver.findElement(By.xpath("//ul[@id='initeraryNav']//li[5]")).click();
		Thread.sleep(3000);
		WebElement element3 = driver.findElement(By.xpath("//*[@class='activity-row-details-title']"));

		// Get the text from the element
		String actualText3 = element3.getText();

		// Define the expected text
		String expectedText3 = "Jewel Changi Airport (Mirror Maze)- Ticket Only";

		// Compare the actual and expected text
		if (actualText3.contains(expectedText3)) {
			System.out.println("Activity Text is present on the page.");
		} else {
			System.out.println("Activity Text is not present on the page.");
		}
		extenttest.log(LogStatus.PASS, extenttest.addScreenCapture((common.screenshot(driver, "ValidateLinks"))) + "validate all the Selected Links as expected");

	}

	@AfterTest
	public void closeBrowser() {
		extent.endTest(extenttest);
		extent.flush();
		extent.close();
		driver.quit();
	}
}
