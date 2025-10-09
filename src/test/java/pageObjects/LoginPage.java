package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(name = "email") WebElement txt_email;
	@FindBy(name = "password") WebElement txt_password;
	@FindBy(xpath = "//button[text()='Login']") WebElement btn_login;
	
	
	public void enterUsername(String username) {
		txt_email.clear();
		txt_email.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		txt_password.clear();
		txt_password.sendKeys(password);
	}
	
	public void clickBtnLogin() {
		btn_login.click();
	}
}
