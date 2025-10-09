package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "firstname")
	WebElement txt_firstname;
	@FindBy(name = "lastname")
	WebElement txt_lastname;
	@FindBy(name = "email")
	WebElement txt_email;
	@FindBy(name = "password")
	WebElement txt_password;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement radbtn_agree;
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement btn_continue;
	
	public void setTheFirstName(String firstName) {
		txt_firstname.sendKeys(firstName);
	}

	public void setTheLastName(String lastName) {
		txt_lastname.sendKeys(lastName);
	}

	public void setEmail(String email) {
		txt_email.sendKeys(email);
	}

	public void setPassword(String pass) {
		txt_password.sendKeys(pass);
	}

	public void selectPrivacyToggle() {
		radbtn_agree.click();
	}

	public void clickOnContinue() {
		btn_continue.click();
	}

	
}
