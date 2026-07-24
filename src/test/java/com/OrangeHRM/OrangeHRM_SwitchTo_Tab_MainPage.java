package com.OrangeHRM;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrangeHRM_SwitchTo_Tab_MainPage {
	
	WebDriver driver;
	
	@Test
	public void ResetPasswordPage() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
		Object[] windowHandles = driver.getWindowHandles().toArray();
		driver.switchTo().window((String) windowHandles[1]);
		// assert Contact Sales is visible
		driver.findElement(By.xpath("//button[contains(text(),'Contact Sales')]")).isDisplayed();
		driver.close();
		driver.switchTo().window((String) windowHandles[0]);
		
		
		driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
		String titleReset= driver.findElement(By.xpath("//h6[normalize-space()='Reset Password']")).getText();
		assertEquals("Reset Password", titleReset);
		
			

	}
	
	@BeforeTest
	public void pre_condition() {
		// WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@AfterTest
	public void post_condition() {

		driver.close();
	}
	
}
  
