package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {

	private Utils() {
		
		throw new AssertionError("Esta clase de utilidad no debe ser instanciada.");
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
