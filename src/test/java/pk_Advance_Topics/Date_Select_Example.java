package pk_Advance_Topics;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Date_Select_Example {
		WebDriver driver;
		//String UserChoiceDate="22";
		@Test(enabled=true)
		public void Date_Select() {
	
			//WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get("http://jqueryui.com/resources/demos/datepicker/other-months.html");
			driver.findElement(By.id("datepicker")).click();
			List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
			for (WebElement ele : allDates) {
	
				String date = ele.getText();
				Calendar now = Calendar.getInstance();
				int day = now.get(Calendar.DAY_OF_MONTH);
//				int dayaftertomo=day+2;
//				String currentday = Integer.toString(dayaftertomo);
				String currentday = Integer.toString(day);
				System.out.println(day);
//				String currentday = "26";
				if (date.equalsIgnoreCase(currentday)) {
				ele.click();

				break;
			}
			// driver.quit();
		}
	}
		
		
		@Test(enabled=false)
		public void Date__Direct_Select() {
	
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://jqueryui.com/resources/demos/datepicker/other-months.html");
			driver.findElement(By.id("datepicker")).click();
			driver.findElement(By.id("datepicker")).sendKeys("21");
	}
}

