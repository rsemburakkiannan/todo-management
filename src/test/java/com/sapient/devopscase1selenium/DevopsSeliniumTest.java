package com.sapient.devopscase1selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DevopsSeliniumTest {

	private static WebDriver driver;
	private static WebDriverWait wait;	

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/Raghu_Project/chromedriver_win32/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		// chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("window-size=1200x1100");
		driver = new ChromeDriver(chromeOptions);
		driver.get("http://localhost:8001/login");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
	}

	// admin application status
	@Test
	public void testLoginForm() throws InterruptedException {

		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td/input")).click();
		boolean isDisplayed = driver.findElement(By.xpath("/html/body/nav/div[2]/ul[1]/li[1]/a")).isDisplayed();
		assertTrue(isDisplayed);
	}
	
	@Test(dependsOnMethods = "testLoginForm")
    public void checkTodos() throws InterruptedException{
		driver.findElement(By.xpath("/html/body/nav/div[2]/ul[1]/li[2]/a")).click();
		boolean isDisplayed = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).isDisplayed();
		assertTrue(isDisplayed);		
	}
	
	@Test(dependsOnMethods = "checkTodos")
    public void checkAddTodo() throws InterruptedException{
		driver.findElement(By.xpath("	/html/body/div[1]/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("Todo1_Devops");
		driver.findElement(By.xpath("//*[@id=\"targetDate\"]")).sendKeys("01/03/2019");
		driver.findElement(By.xpath("/html/body")).click();		
		driver.findElement(By.xpath("//*[@id=\"todo\"]/button")).click();

		boolean isDisplayed = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody/tr/td[1]")).isDisplayed();
		assertTrue(isDisplayed);
		WebElement rowCount = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody"));
		List<WebElement> rows = rowCount.findElements(By.tagName("tr"));
		assertNotEquals(0, rows.size());	
	}

	@Test(dependsOnMethods = "checkAddTodo")
    public void checkUpdate() throws InterruptedException{
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody/tr/td[3]/a[1]")).click();		
		driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("Todo1_Devops_New");
		driver.findElement(By.xpath("//*[@id=\"targetDate\"]")).sendKeys("01/03/2019");
		driver.findElement(By.xpath("/html/body")).click();		
		driver.findElement(By.xpath("//*[@id=\"todo\"]/button")).click();

		WebElement rowCount = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody"));
		List<WebElement> rows = rowCount.findElements(By.tagName("tr"));
		boolean isDisplayed = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody/tr/td[1]")).isDisplayed();
		assertTrue(isDisplayed);	
		assertNotEquals(0, rows.size());
	}
	
	@Test(dependsOnMethods = "checkUpdate")
    public void checkDelete() throws InterruptedException{
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody/tr/td[3]/a[2]")).click();		
		WebElement rowCount = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/table/tbody"));
		List<WebElement> rows = rowCount.findElements(By.tagName("tr"));
		assertEquals(0, rows.size());		
	}
	
	@Test(dependsOnMethods = "checkUpdate")
    public void checkLogout() throws InterruptedException{
		driver.findElement(By.xpath("/html/body/nav/div[2]/ul[2]/li/a")).click();			
		boolean isDisplayed = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td/input")).isDisplayed();
		assertTrue(isDisplayed);
	}
}
