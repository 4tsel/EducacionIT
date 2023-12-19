package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AlertsPage;
import pages.TablesPage;
import utils.Utils;

public class Laboratorio5_E3 {

	WebDriver driver;
	String CHROME_PATH = "..\\EducacionIT\\Drivers\\chromedriver.exe";
	String GECKO_PATH = "..\\EducacionIT\\Drivers\\geckodriver.exe";
	String EDGE_PATH = "..\\EducacionIT\\Drivers\\msedgedriver.exe";
	
	String startTime = "Hora de inicio: ";
	String endTime = "Hora de fin: ";

	@BeforeSuite(description = "Terminar Instancias Abiertas de Chrome y Firefox")
	public void cleanUpScenario() {

		startTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");
		
		Utils.cleanUpScenario();
	}

	@BeforeClass(description = "Borrar Cookies, Ingresar y Maximizar")
	@Parameters({"browser", "URL"})
	public void setUpBrowser(String browser, String URL) {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", CHROME_PATH);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", GECKO_PATH);

			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver", EDGE_PATH);
			
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
		}

		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@Test(description = "Enviar un Nombre por Alerta")
	public void testAlertManipulation() throws InterruptedException{

		AlertsPage objAlertsPage = new AlertsPage(driver);
		String result;

		objAlertsPage.clickPromptButton();

		objAlertsPage.fillAlertText("Axel");

		Thread.sleep(1000);
		
		result = objAlertsPage.getPromptResult();
		
		System.out.println(result);
		
		Assert.assertEquals(result, "You entered Axel");
	}
	
	@Test(description = "Manipular una Tabla")
	public void testTableManipulation() {
		
		TablesPage objTablesPage = new TablesPage(driver);
		
		String result = objTablesPage.getCellValue();
		
		System.out.println(result);
		
		Assert.assertEquals(result, "8");
	}

	@AfterMethod(description = "Capturar Evidencia y Volver al Home")
	public void cleanUpTest() throws IOException, InterruptedException {

		Thread.sleep(1500);

		Utils.takeScreenshot(driver);
	}

	@AfterTest(description = "Cerrar Navegador")
	public void closeBrowser() {

		driver.close();
	}

	@AfterSuite(description = "Fin Suite de Pruebas")
	public void finishSuite() {

		endTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");
		System.out.println("Fin suite de pruebas");
		
		System.out.println(startTime + "\n" + endTime);
		
	}
}
