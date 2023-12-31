package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import utils.ExcelData;
import utils.Utils;

public class Laboratorio5_E1 {

	WebDriver driver;
	
	String startTime = "Hora de inicio: ";
	String endTime = "Hora de fin: ";

	@BeforeSuite(description = "Terminar Instancias Abiertas de Chrome y Firefox")
	public void cleanUpScenario() {

		startTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");
		
		Utils.cleanUpScenario();
	}

	@BeforeClass(description = "Borrar Cookies, Ingresar y Maximizar")
	@Parameters({"browser","URL"})
	public void setUpBrowser(String browser, String URL) {

		driver = Utils.setUpBrowser(driver, browser);

		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@DataProvider(name = "validLoginData")
	public static Object[][] provideValidData() throws IOException {
		String excelPath = "./Data/valid-mail.xlsx"; // Asegúrate de que esta ruta es relativa y correcta
		String sheetName = "Hoja1";

		Object[][] excelData = ExcelData.retrieveExcelData(excelPath, sheetName);

		return excelData;
	}

	@Test(description = "Login con Credenciales Válidas", dataProvider = "validLoginData")
	public void testValidLogin(String email, String password) throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.login(email, password);

		Thread.sleep(1500);
		title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "MY ACCOUNT");
	}

	@DataProvider(name = "invalidLoginData")
	public static Object[][] provideInvalidData() {

		String excelPath = "./Data/invalid-mail.xlsx";
		String sheetName = "Hoja1";

		Object excelData[][] = ExcelData.retrieveExcelData(excelPath, sheetName);

		return excelData;
	}

	@Test(description = "Login con Credenciales Inválidas", dataProvider = "invalidLoginData")
	public void testInvalidLogin(String email, String password) throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.login(email, password);

		Thread.sleep(500);
		title = objBasePage.getPageTitle();
		Assert.assertNotEquals(title, "MY ACCOUNT");
	}

	@AfterMethod(description = "Capturar Evidencia y Volver al Home")
	public void cleanUpTest() throws IOException, InterruptedException {

		BasePage objBasePage = new BasePage(driver);

		Thread.sleep(1000);

		Utils.getEvidence(driver);

		if (objBasePage.isSignOutVisible()) {

			objBasePage.clickSignOut();
		}

		objBasePage.clickHome();
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
