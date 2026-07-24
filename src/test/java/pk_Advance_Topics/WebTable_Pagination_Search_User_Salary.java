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


public class WebTable_Pagination_Search_User_Salary {

	WebDriver driver;
	
//	String tgtFName = "Paul";
//	String expSalary = "$725,000";
//	String tgtFName = "Abhi";
//	String expSalary = "$85,600";
	String tgtFName = "Doris";
	String expSalary = "$85,600";
//
//	String tgtFName = "Bruno";
//	String expSalary = "$163,500";

	// searching for name and check salary
	public boolean checkSalary(List<WebElement> fNameElements) {

		for (WebElement fName : fNameElements) {
			String currentFName = fName.getText();
			if (currentFName.equals(tgtFName)) {
				WebElement salaryElement = driver.findElement(By.xpath(
						"//td[text()='" + currentFName + "']/following-sibling::td[text()='" + expSalary + "']"));
				System.out.println(salaryElement.getText());
				Assert.assertEquals(salaryElement.getText(), expSalary);
				return true;
			}
		}
		return false;
	}

	@BeforeTest
	public void pre_condition() {
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void post_condition() {
		driver.close();
	}

	@Test
	public void searchName() throws InterruptedException {
		boolean found = false;
		driver.get("https://datatables.net/examples/data_sources/server_side");
		Thread.sleep(2000);

		// Fetch first page names
		List<WebElement> fNameElements = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		found = checkSalary(fNameElements);

		String nextButton = driver.findElement(By.cssSelector("button[aria-label='Next']")).getAttribute("class");

		while (!found && !nextButton.contains("disabled")) {
			driver.findElement(By.cssSelector("button[aria-label='Next']")).click();
			Thread.sleep(2000);
			fNameElements = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
			found = checkSalary(fNameElements);
			nextButton = driver.findElement(By.cssSelector("button[aria-label='Next']")).getAttribute("class");
		}

		if (found) {
			Assert.assertTrue(found);
			System.out.println("✅ User '" + tgtFName + "' found with salary: " + expSalary);
		} else {
			Assert.assertFalse(found);
			System.out.println("❌ User '" + tgtFName + "' NOT found with salary: " + expSalary);
		}
	}

}
