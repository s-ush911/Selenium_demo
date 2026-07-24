package pk_Advance_Topics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebTable_Pagination_Example {
WebDriver driver;

	String tgtFName = "Router";
	String expPrice = "$24.99";
//    String tgtFName = "Laptoppp";
//    String expPrice = "$19.99";
//	String tgtFName = "Laptop";
//    String expPrice = "$19.99";
	int pageno=1;


	// searching for name and check salary
	public boolean checkPrice(List<WebElement> fNameElements) {

		for (WebElement fName : fNameElements) {
			String currentFName = fName.getText();
			if (currentFName.equals(tgtFName)) {
				WebElement salaryElement = driver.findElement(By.xpath(
						"//td[text()='" + currentFName + "']/following-sibling::td[text()='" + expPrice + "']"));
				System.out.println(salaryElement.getText());
				Assert.assertEquals(salaryElement.getText(), expPrice);
				return true;
			}
		}
		pageno++;
		return false;
	}

	
	@Test
	public void searchName() throws InterruptedException {
		boolean found = false;
		driver.get("https://testautomationpractice.blogspot.com/");
		Thread.sleep(2000);

		// Fetch first page names
		List<WebElement> fNameElements = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr/td[2]"));
		found = checkPrice(fNameElements);
		


		while (!found && pageno<=4) {
			driver.findElement(By.xpath("//a[normalize-space()='"+pageno+"']")).click();
			Thread.sleep(2000);
			fNameElements = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr/td[2]"));
			found = checkPrice(fNameElements);
			
		}

		if (found) {
			Assert.assertTrue(found);
			driver.findElement(By.xpath("//td[normalize-space()='"+tgtFName+"']/following-sibling::td//input[@type='checkbox']")).click();
		} else {
			Assert.assertFalse(found);
			System.out.println("❌ Product '" + tgtFName + "' NOT found with price: " + expPrice);
		}
	}
	
	
	
	

	@BeforeTest
	public void pre_condition() {
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void post_condition() {
		//driver.close();
	}

	
}
