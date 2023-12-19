package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class UploadPage {

	WebDriver driver;
	
	@FindBy (xpath="//input[@id='uploadfile_0']")
	WebElement file_UploadElement;
	
	@FindBy (xpath="//input[@id='terms']")
	WebElement chk_AcceptTerms;
	
	@FindBy (xpath="//button[@id='submitbutton']")
	WebElement btn_SubmitFile;
	
	@FindBy (xpath="//h3[@id='res']")
	WebElement txt_Result;
	
	public UploadPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(new AjaxElementLocatorFactory (driver, 3), this);
	}
	
	public void fillFilePath(String path) {
		
		file_UploadElement.clear();
		file_UploadElement.sendKeys(path);
	}
	
	public void acceptTerms() {
		
		chk_AcceptTerms.click();
	}
	
	public void clickSubmitFile() {
		
		btn_SubmitFile.click();
	}
	
	public String getResult() {
		
		return txt_Result.getText();
	}
}
