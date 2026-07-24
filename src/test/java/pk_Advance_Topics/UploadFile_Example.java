package pk_Advance_Topics;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UploadFile_Example {

	WebDriver driver;

	@Test
	public void UploadFile() throws InterruptedException {

		String filePath = System.getProperty("user.dir");

		String image = filePath + "\\FileUpload\\KeyTakeAway.docx";

		//String image = ".//Images//Image0019.jpg";
		String url = "https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// identify element
		WebElement uploadfile = driver.findElement(By.name("picture"));
		// file selection field with file path
		uploadfile.sendKeys(image);
		// Thread.sleep(5000);

	}

	@BeforeTest
	public void LaunchBrowser() {
		// Launch the Browser
		// WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.quit();
	}
}
