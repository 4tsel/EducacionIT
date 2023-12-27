package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class BrowserTest {

	@Test
	public void browserTest() throws InterruptedException {
		
		WebDriver driver;
//		String projectPath = System.getProperty("user.dir");
//		String EDGE_PATH = projectPath + "\\Drivers\\msedgedriver.exe";
//		String URL = "http://www.automationpractice.pl/index.php";
		
		System.setProperty("webdriver.edge.driver", "F:\\Eclipse\\eclipse-workspace\\EducacionIT\\Drivers\\msedgedriver.exe");
		
		
		EdgeOptions options = new EdgeOptions();
		
		options.addArguments("--remote-allow-origins=*");
		
		driver = new EdgeDriver(options);
		
		driver.get("http://www.automationpractice.pl/index.php");
		
		Thread.sleep(3000);
	}
}
