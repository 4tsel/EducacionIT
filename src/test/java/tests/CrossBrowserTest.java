package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import utils.Utils;

public class CrossBrowserTest {

	WebDriver driver;
	String URL = "http://www.automationpractice.pl/index.php";
	String CHROME_PATH = "..\\EducacionIT\\Drivers\\chromedriver.exe";
	String GECKO_PATH = "..\\EducacionIT\\Drivers\\geckodriver.exe";

	@BeforeSuite(description = "Terminar Instancias Abiertas de Chrome")
	public void cleanUpScenario() {
		
		Utils.cleanUpScenario();
	}

	@BeforeClass(description = "Borrar Cookies, Ingresar y Maximizar")
	@Parameters("browser")
	public void setUpBrowser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", CHROME_PATH);
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", GECKO_PATH);
			
			driver = new FirefoxDriver();
		}
		
		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@Test(description = "Login con Credenciales Válidas")
	public void testValidLogin() throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		String email = "homer_simpson39@fakemail.com";
		String password = "Marge.2023";

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.login(email, password);

		Thread.sleep(5000);
		title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "MY ACCOUNT");
	}

	@Test(description = "Login con Credenciales Inválidas")
	public void testInvalidLogin() throws InterruptedException {

		BasePage objBasePage = new BasePage(driver);
		HomePage objHomePage = new HomePage(driver);
		LoginPage objLoginPage = new LoginPage(driver);

		String email = "test@test.com";
		String password = "password";

		objHomePage.clickSignIn();

		String title = objBasePage.getPageTitle();
		Assert.assertEquals(title, "AUTHENTICATION");

		objLoginPage.login(email, password);

		Thread.sleep(5000);
		title = objBasePage.getPageTitle();
		Assert.assertNotEquals(title, "MY ACCOUNT");
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
