package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

	public MainPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//li/a[text()='Your Account Has Been Created!']")
	WebElement txt_AccounCreated;

	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement btn_logout;

	public boolean accountCreatedDisplayed() {
		return txt_AccounCreated.isDisplayed();
	}

	public boolean logoutIsDisplayed() {
		try {
			return btn_logout.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickOnLogout() {
		btn_logout.click();
	}
}
