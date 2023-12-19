package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class Laboratorio4_E1 {

	WebDriver driver;
	String URL = "http://www.automationpractice.pl/index.php";
	String PATH = "..\\EducacionIT\\Drivers\\chromedriver.exe";

	@BeforeSuite(description = "Instanciar Navegador y Navegar a URL")
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", PATH);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(chromeOptions);
		driver.get(URL);
	}

	@BeforeClass(description = "Borrar Cookies y Maximizar")
	public void maximizeBrowser() {

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@Test(description = "Ir al Registro completando Mail Válido")
	public void testRegisterValidMail() throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		int random = (int) Math.round(Math.random() * 100) + 1;
		String email = "marge_bouvier" + random + "@fakemail.com";

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.createAccount(email);

		Thread.sleep(5000);
		title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "CREATE AN ACCOUNT");
	}

	@Test(description = "Ir al Registro completando Mail Inválido")
	public void testRegisterUnvalidMail() throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		String email = "test@test.com";

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.createAccount(email);

		Thread.sleep(5000);
		title = objBasePage.getPageTitle();
		Assert.assertNotEquals(title, "CREATE AN ACCOUNT");
	}

	@AfterMethod(description = "Capturar Evidencia y Volver al Home")
	public void cleanUpTest() throws IOException, InterruptedException {
		
		Thread.sleep(5000);
		
		int random = (int)Math.round(Math.random()*100) + 1;
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\EducacionIT\\Evidencias\\screen" + random + ".png"));

		driver.get(URL);
	}

	@AfterTest(description = "Cerrar Navegador")
	public void closeBrowser() {

		driver.close();
	}

	@AfterSuite(description = "Fin Suite de Pruebas")
	public void finishSuite() {

		System.out.println("Fin suite de pruebas");
	}
}
