package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Utils {

	private Utils() {
		
		throw new AssertionError("Esta clase de utilidad no debe ser instanciada.");
	}
	
	public static WebDriver setUpBrowser(WebDriver driver, String browser) {
		
		if (driver != null) {
			
			driver.close();
		}
		
		String projectPath = System.getProperty("user.dir");
		String CHROME_PATH = projectPath + "\\Drivers\\chromedriver.exe";
		String GECKO_PATH = projectPath + "\\Drivers\\geckodriver.exe";
		String EDGE_PATH = projectPath + "\\Drivers\\msedgedriver.exe";
		
		try {
			
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
	    } catch (IllegalStateException e) {
	        System.out.println("WebDriver path is incorrect or WebDriver is not executable: " + e.getMessage());
	        // Manejo específico para errores de configuración del WebDriver
	    } catch (Exception e) {
	        System.out.println("Error occurred while setting up the browser: " + e.getMessage());
	        // Manejo general para otros tipos de excepciones
	    }
		
		return driver;
	}
	
	public static void closeChromeInstances() {
		
		try {
			
            // Ejecuta el comando para cerrar todas las instancias de Chrome en Windows
            ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", "chrome.exe");
            processBuilder.start();
            
            System.out.println("Se han cerrado todas las instancias de Chrome.");
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
	}
	
	public static void closeFirefoxInstances() {
		
		try {
			
            // Ejecuta el comando para cerrar todas las instancias de Firefox en Windows
            ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", "firefox.exe");
            processBuilder.start();
            
            System.out.println("Se han cerrado todas las instancias de Firefox.");
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
	}
	
	public static void cleanUpScenario() {
		
		closeChromeInstances();
		
		closeFirefoxInstances();
	}
	
	public static void cleanUpDriver(WebDriver driver) {
		
		if (driver != null) {
			driver.quit();
		}
	}
	
	public static void getEvidence(WebDriver driver) throws InterruptedException, IOException {
		
		Thread.sleep(1500);

		takeScreenshot(driver);
	}
	
	public static void takeScreenshot(WebDriver driver) throws IOException {
		
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screen, new File("./Evidencias/evidencia-" + getTimestamp("yyyyMMddHHmmss") + ".png"));
	}
	
	public static String getTimestamp(String format) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timestamp = sdf.format(new Date());
		
		return timestamp;
	}

	
}
