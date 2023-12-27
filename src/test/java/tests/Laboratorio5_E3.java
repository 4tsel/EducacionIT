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

import pages.AlertsPage;
import pages.TablesPage;
import utils.Utils;

public class Laboratorio5_E3 {

	WebDriver driver;

	String startTime = "Hora de inicio: ";
	String endTime = "Hora de fin: ";

	@BeforeSuite(description = "Terminar Instancias Abiertas de Chrome y Firefox")
	public void cleanUpScenario() {

		startTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");

		Utils.cleanUpScenario();
	}

	@BeforeClass(description = "Borrar Cookies, Ingresar y Maximizar")
	@Parameters({ "browser", "URL" })
	public void setUpBrowser(String browser, String URL) {

		driver = Utils.setUpBrowser(driver, browser);

		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@Test(description = "Enviar un Nombre por Alerta")
	public void testAlertManipulation() throws InterruptedException {

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

	@AfterMethod(description = "Capturar Evidencia")
	public void captureEvidenceTest() throws IOException, InterruptedException {

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
