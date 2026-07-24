package com.DemoWebShop;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoWebShop_AddToCart_MultipleProduct {

	WebDriver driver;
	String prdNameText;
	String processor;

	@Test(dataProvider="demoWebShop_Add_Multi_Product",dataProviderClass=DemoWebShop_TestData.class)
	public void add_item_to_cart(String prdName,String processor) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		WebElement ele = driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Computers']"));
		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();

		// Locating the element from Sub Menu
		WebElement subMenu = driver.findElement(By.xpath("(//a[normalize-space()='Desktops'])"));
		subMenu.click();

		prdNameText = driver.findElement(By.xpath("//a[normalize-space()='"+prdName+"']")).getText();

		//Add to cart
		driver.findElement(By.xpath(
				"//a[normalize-space()='"+prdName+"']/parent::h2//following-sibling::div//input[@type='button']"))
				.click();

		//Radio button click
		driver.findElement(By.xpath("//label[normalize-space()='"+processor+"']")).click();

		driver.findElement(By.xpath("//input[@id='add-to-cart-button-72']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
		String actualPrdName = driver.findElement(
				By.xpath("//h1[normalize-space()='Shopping cart']/parent::div/following-sibling::div//a[text()='"
						+ prdName + "']"))
				.getText();

		Assert.assertEquals(prdNameText, actualPrdName);

	}

	@BeforeTest
	public void pre_condition() {
//		EdgeOptions options =new EdgeOptions();
//		options.addArguments("--headless");
//		driver = new EdgeDriver(options);
		FirefoxOptions options = new FirefoxOptions();
//		 Options: "accept", "dismiss", "ignore", "accept and notify", "dismiss and
//		 notify"
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		driver = new FirefoxDriver(options);
		// driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.get("https://demowebshop.tricentis.com/");

	}

	@AfterTest
	public void post_condition() {
		// driver.findElement(By.linkText("Logout")).click();
		driver.close();
	}
}
