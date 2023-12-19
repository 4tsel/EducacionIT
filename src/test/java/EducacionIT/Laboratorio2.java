package EducacionIT;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Laboratorio2 {

	@Test(description="Prueba de Registro")
//	TEST DE REGISTRO.
	public void lab2_E1() {
		
//		SE CONFIGURA WEBDRIVER E INGRESA A WEB.
		
		System.setProperty("webdriver.chrome.driver", "..\\EducacionIT\\Drivers\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		driver.get("http://www.automationpractice.pl/index.php");
		driver.manage().window().maximize();
		
//		SE CREAN ESPERAS EXPLÍCITAS
		
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
		
		txt_Email.clear();
		txt_Email.sendKeys("homer_simpson" + random + "@fakemail.com");
		
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
		txt_Password.sendKeys("Marge2023");
		
		rad_Mr.click();
		
		btn_Register.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Your account has been created.')]")));
		
//		SE HACE UNA ESPERA DE 10 SEGUNDOS
//		PARA VERIFICAR A OJO QUE SE HAYA CREADO LA CUENTA.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.close();
		}
}
