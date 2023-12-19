package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy (xpath="//h1[contains(text(),'Create an account')]")
	WebElement txt_Register;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(new AjaxElementLocatorFactory (driver, 3), this);
	}
}
