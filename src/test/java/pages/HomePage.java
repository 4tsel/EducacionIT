package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	WebElement btn_SignIn;

	@FindBy(xpath = "//h1[contains(text(),'Authentication')]")
	WebElement txt_Login;

	public HomePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}

	public void clickSignIn() {

		btn_SignIn.click();
	}
}
