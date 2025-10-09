package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement btn_myaccount;
	@FindBy(xpath = "(//li/a[text()='Register'])[1]")
	WebElement lnk_register;
	@FindBy(xpath = "(//li/a[text()='Login'])[1]")
	WebElement lnk_login;
	@FindBy(xpath = "(//li[@class='breadcrumb-item'])[3]")
	WebElement text_accountcreated;

	public void clickOnMyAccount() {
		btn_myaccount.click();
	}

	public void clickOnRegisterLink() {
		lnk_register.click();
	}

	public void clickOnLoginLink() {
		lnk_login.click();
	}

	public String accountCreatedText() {
		return text_accountcreated.getText();
	}
}
