package com.OrangeHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrangeHRM_Login_All_TCs {
	WebDriver driver;

	@Test(dataProvider = "OarngeHRM_LoginAll_TCs", dataProviderClass = OrangeHRM_TestData.class)
	public void login(String username, String Password, String Exp_Result) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.navigate().refresh();
		//Thread.sleep(5000);
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		//Thread.sleep(5000);

		if (Exp_Result.equalsIgnoreCase("Dashboard")) {
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).isDisplayed();
			String Act_Msg = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).getText();
			String ActMsg = Act_Msg.trim();
			Assert.assertEquals(ActMsg, Exp_Result);
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

		}
		else if(Exp_Result.equalsIgnoreCase("Invalid credentials")) {
			
			Boolean Act_Msg = driver.getPageSource().contains(Exp_Result);
			Assert.assertTrue(Act_Msg);
			
			
		}
		
		else {
			
			Boolean Act_Msg = driver.getPageSource().contains(Exp_Result);
			Assert.assertTrue(Act_Msg);
			
		}

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
