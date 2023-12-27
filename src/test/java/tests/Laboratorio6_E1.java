package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import utils.Utils;

public class Laboratorio6_E1 {

	WebDriver driver;
	
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

		driver = Utils.setUpBrowser(driver, browser);

		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@Test(description = "Buscar una Palabra en Barra de Búsqueda")
	public void testUseSearchBar() throws InterruptedException{

		BasePage objBasePage = new BasePage(driver);
		String query = "Summer";
		String result;
		
		objBasePage.fillSearchBar(query);
		
		Thread.sleep(1000);
		
		result = objBasePage.getPageTitle();
		
		System.out.println(result);
		
		Assert.assertTrue(result.startsWith("SEARCH  \"SUMMER\""));
	}

	@AfterMethod(description = "Capturar Evidencia y Volver al Home")
	public void cleanUpTest() throws IOException, InterruptedException {

		Utils.getEvidence(driver);
	}

	@AfterTest(description = "Cerrar Navegador")
	public void closeBrowser() {

		Utils.cleanUpDriver(driver);
	}

	@AfterSuite(description = "Fin Suite de Pruebas")
	public void finishSuite() {

		endTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");
		System.out.println("Fin suite de pruebas");
		
		System.out.println(startTime + "\n" + endTime);
		
	}
}
