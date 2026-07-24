package pk_Advance_Topics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SSL_Certificate_Example_All_Browser {

	
	WebDriver driver;

	@BeforeTest
	public void LaunchBrowserChrome() {
		 
		EdgeOptions options = new EdgeOptions();
		options.addArguments("incognito");
		options.setAcceptInsecureCerts(true);
		driver = new EdgeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void openApplication() {
		System.out.println("Navigating to application");
		driver.get("https://expired.badssl.com/");
		String ActTitle=driver.getTitle(); 
		String ExpTitle="expired.badssl.com";
		Assert.assertEquals(ActTitle, ExpTitle);
	}

	@AfterTest
	public void tearDown() {
		/*if (driver != null)
			driver.quit();*/
	}
}
