package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.Utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class BasePage {

	WebDriver driver;

	@FindBy(tagName = "h1")
	WebElement txt_Title;

	@FindBy(className = "logout")
	WebElement btn_SignOut;
	
	@FindBy(xpath="//input[@id='search_query_top']")
	WebElement txt_Search;
	
	@FindBy(xpath="//a[@class='home']")
	WebElement btn_Home;

	public BasePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}

	public void fillSearchBar(String query) {
		
		txt_Search.clear();
		txt_Search.sendKeys(query);
		txt_Search.sendKeys(Keys.ENTER);
	}
	
	public String getPageTitle() {

		Utils.highlightElement(driver, txt_Title);
		
		return txt_Title.getText();
	}

	public void clickSignOut() {

		btn_SignOut.click();
	}
	
	public void clickHome() {
		
		btn_Home.click();
	}
	
	public boolean isSignOutVisible() {
		
	    try {
	    	
	        return btn_SignOut.isDisplayed();
	    } catch (NoSuchElementException e) {
	    	
	        return false;
	    }
	}
}
