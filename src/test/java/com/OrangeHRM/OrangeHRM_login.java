package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class OrangeHRM_login {
	@Test
	public void login() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		// WebDriver driver = new EdgeDriver();

		// driver.findElement(By.className("oxd-button oxd-button--medium
		// oxd-button--main orangehrm-login-button")).click();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(10000);
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		Thread.sleep(15000);
		driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).isDisplayed();
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

		driver.quit();

	}
}
