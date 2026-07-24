package com.OrangeHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrangeHRM_Login_Using_Keyboard {

	WebDriver driver;
	@Test
	public void Login_Validation() throws InterruptedException {
		// Enter the URL
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Enter the valid Username and valid Password
		driver.findElement(By.name("username")).sendKeys("Admin",Keys.TAB,"admin123",Keys.ENTER);
		
		// verify Dashboard page is displayed
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		System.out.print("Test Success");
		
	}
	@BeforeTest
	public void LaunchBrowser()
	{
		// Launch the Browser
		//WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}

}
