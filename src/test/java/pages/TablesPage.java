package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TablesPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//td[contains(text(),'8')]")
	WebElement txt_Cell;

	public TablesPage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		PageFactory.initElements(new AjaxElementLocatorFactory (driver, 3), this);
	}
	
	public String getCellValue() {
		
		return txt_Cell.getText();
	}
}
