package EducacionIT;

import org.testng.annotations.Test;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Laboratorio1 {
	
	@Test
	public void lab1_test() {
		
		System.out.println("Hola Mundo de Automatización!");
	}
	
	@Test
	public void lab1_E1() {
		
		System.setProperty("webdriver.chrome.driver", "..\\EducacionIT\\Drivers\\chromedriver.exe");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		driver.get("http://www.automationpractice.pl/index.php");
		driver.manage().window().maximize();
		
		driver.close();
	}
	
	@Test
	public void lab1_E2() {
		
		System.setProperty("webdriver.gecko.driver", "..\\EducacionIT\\Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.automationpractice.pl/index.php");
		driver.manage().window().maximize();
		
		driver.quit();
	}
	
	@Test
	public void lab1_E3() {
		
		System.setProperty("webdriver.chrome.driver", "..\\EducacionIT\\Drivers\\chromedriver.exe");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		driver.get("http://www.automationpractice.pl/index.php");
		driver.manage().window().maximize();
		
		WebElement txt_Search = driver.findElement(By.xpath("//input[@id='search_query_top']"));
		
		txt_Search.sendKeys("Printed Dress");
		txt_Search.sendKeys(Keys.ENTER);
		
		
		driver.close();
	}
}
