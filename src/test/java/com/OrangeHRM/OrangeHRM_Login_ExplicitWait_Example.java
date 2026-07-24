package com.OrangeHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrangeHRM_Login_ExplicitWait_Example {

	@Test
	public void Login() throws InterruptedException {

		// Launch the Browser
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		//WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		// Enter the URL
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//Thread.sleep(15000);
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		 //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		//---------------------------------------------------------------
		// Enter the valid Username and valid Password
		//driver.findElement(By.name("username")).sendKeys("Admin");
		element.sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		// click on the Login Button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		// verify Dashboard page is displayed
		driver.findElement(By.partialLinkText("Dash")).isDisplayed();
		driver.quit();
	}

}

