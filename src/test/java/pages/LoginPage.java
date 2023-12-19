package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy (xpath="//input[@id='email_create']")
	WebElement txt_Email;
	
	@FindBy (xpath="//button[@id='SubmitCreate']")
	WebElement btn_CreateAccount;
	
	@FindBy (xpath="//input[@id='email']")
	WebElement txt_EmailLogin;
	
	@FindBy (xpath="//input[@id='passwd']")
	WebElement txt_PasswordLogin;
	
	@FindBy (xpath="//button[@id='SubmitLogin']")
	WebElement btn_SignIn;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(new AjaxElementLocatorFactory (driver, 3), this);
	}
	
	public void createAccount(String email) {
		
		txt_Email.clear();
		txt_Email.sendKeys(email);
		
		btn_CreateAccount.click();
	}
	
	public void login(String email, String password) {
		
		txt_EmailLogin.clear();
		txt_EmailLogin.sendKeys(email);
		
		txt_PasswordLogin.clear();
		txt_PasswordLogin.sendKeys(password);
		
		btn_SignIn.click();
	}
}
