package EducacionIT;

import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class Laboratorio3_Ejercicio1 {
	
	ChromeDriver driver;
	String URL = "http://www.automationpractice.pl/index.php";
	
	@BeforeSuite (description="Instanciar Navegador")
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "..\\EducacionIT\\Drivers\\chromedriver.exe");	
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(chromeOptions);
	}
	
	@BeforeTest (description="Navegar a URL")
	public void getURL() {
		
		driver.get(URL);
	}
	
	@BeforeClass (description="Borrar Cookies y Maximizar")
	public void maximizeBrowser() {
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	@Test (description="Realizar Registro")
//	TEST DE REGISTRO.
	public void TestRegister() throws InterruptedException {
	
//		Por cada elemento a encontrar,
//		previa espera según tipo.
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign in')]")));
		WebElement btn_SignIn = driver.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
		
		btn_SignIn.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_create']")));
		WebElement txt_Email = driver.findElement(By.xpath("//input[@id='email_create']"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='SubmitCreate']")));
		WebElement btn_CreateAccount = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
		
		int random = (int)Math.round(Math.random()*100) + 1;
		String email = "homer_simpson" + random + "@fakemail.com";
		String password = "Marge.2023";
		System.out.println("Mail: " + email + "\nPass: " + password);
		
		txt_Email.clear();
		txt_Email.sendKeys(email);
		
		btn_CreateAccount.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id_gender1']")));
		WebElement rad_Mr = driver.findElement(By.xpath("//input[@id='id_gender1']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer_firstname']")));
		WebElement txt_FirstName = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer_lastname']")));
		WebElement txt_LastName = driver.findElement(By.xpath("//input[@id='customer_lastname']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passwd']")));
		WebElement txt_Password = driver.findElement(By.xpath("//input[@id='passwd']"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submitAccount']")));
		WebElement btn_Register = driver.findElement(By.xpath("//button[@id='submitAccount']"));
		
//		SE LLENAN CAMPOS DEL FORMULARIO
		
		txt_FirstName.clear();
		txt_FirstName.sendKeys("Homer");
		
		txt_LastName.clear();
		txt_LastName.sendKeys("Simpson");
		
		txt_Password.clear();
		txt_Password.sendKeys(password);
		
		rad_Mr.click();
		
		btn_Register.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Your account has been created.')]")));
		
//		SE HACE UNA ESPERA DE 10 SEGUNDOS
//		PARA VERIFICAR A OJO QUE SE HAYA CREADO LA CUENTA.
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Your account has been created.')]")));
		WebElement txt_Message = driver.findElement(By.xpath("//p[contains(text(),'Your account has been created.')]"));
		
		String message = txt_Message.getText();
		message = message.trim();
		
		boolean result = message.equals("Your account has been created.");
		
		Assert.assertTrue(result);
		
		
	}
	
	@AfterMethod (description="Capturar Evidencia")
	public void captureScreenshot() throws IOException {
		
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\EducacionIT\\Evidencias\\screen.png"));
	}
	
	@AfterTest (description="Cerrar Navegador")
	public void closeBrowser() {
		
		driver.close();
	}
	
	@AfterSuite (description="Fin Suite de Pruebas")
	public void finishSuite() {
		
		System.out.println("Fin suite de pruebas");
	}
}
