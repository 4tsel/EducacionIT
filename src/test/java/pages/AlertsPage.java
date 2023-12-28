package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class AlertsPage {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy (xpath="//button[@id='promtButton']")
	WebElement btn_Prompt;
	
	@FindBy (xpath="//span[@id='promptResult']")
	WebElement txt_PromptResult;
	
	public AlertsPage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		PageFactory.initElements(new AjaxElementLocatorFactory (driver, 3), this);
	}
	
	public void clickPromptButton() {
		
		btn_Prompt.click();
		
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void fillAlertText(String text) {
		
		Alert alert = driver.switchTo().alert();
		
		alert.sendKeys(text);
		alert.accept();
	}
	
	public String getPromptResult() {
		
		Utils.highlightElement(driver, txt_PromptResult);
		
		return txt_PromptResult.getText();
	}

}
