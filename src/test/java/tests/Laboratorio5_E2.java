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

import pages.UploadPage;
import utils.Utils;

public class Laboratorio5_E2 {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	String FILE_PATH = "F:/Eclipse/eclipse-workspace/EducacionIT/Data/notepad.txt";

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

	@Test(description = "Subir un Archivo")
	public void testFileUpload() throws InterruptedException {

		UploadPage objUploadPage = new UploadPage(driver);
		String result;

		objUploadPage.fillFilePath(FILE_PATH);

		objUploadPage.acceptTerms();

		objUploadPage.clickSubmitFile();

		Thread.sleep(2500);

		result = objUploadPage.getResult();

		System.out.println(result);

		Assert.assertEquals(result, "1 file\nhas been successfully uploaded.");
	}

	@AfterMethod(description = "Capturar Evidencia")
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
