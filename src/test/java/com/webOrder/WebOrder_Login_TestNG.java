package com.webOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebOrder_Login_TestNG {
  @Test
  public void Login() {
	  
	//WebDriverManager.chromedriver().setup();
			//Object reference of chrome driver
			  WebDriver driver = new ChromeDriver();
			  //WebDriver driver = new EdgeDriver();
			  
			  //driver.findElement(By.className("oxd-button oxd-button--medium oxd-button--main orangehrm-login-button")).click();
			  
			  driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
			  driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
			  driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
			  driver.findElement(By.name("ctl00$MainContent$login_button")).click();
			  driver.findElement(By.linkText("Logout")).isDisplayed();
			  driver.findElement(By.linkText("Logout")).click();
			  driver.quit();
  }
}
