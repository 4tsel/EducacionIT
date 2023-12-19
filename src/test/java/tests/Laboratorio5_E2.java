package tests;

import java.io.IOException;

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

import pages.UploadPage;
import utils.Utils;

public class Laboratorio5_E2 {

	WebDriver driver;
	String URL = "https://demo.guru99.com/test/upload/";
	String CHROME_PATH = "..\\EducacionIT\\Drivers\\chromedriver.exe";
	String GECKO_PATH = "..\\EducacionIT\\Drivers\\geckodriver.exe";
	
	String FILE_PATH = "F:/Eclipse/eclipse-workspace/EducacionIT/Data/notepad.txt";
	
	String startTime = "Hora de inicio: ";
	String endTime = "Hora de fin: ";

	@BeforeSuite(description = "Terminar Instancias Abiertas de Chrome y Firefox")
	public void cleanUpScenario() {

		startTime += Utils.getTimestamp("dd/MM/yyyy - HH:mm:ss");
		
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
