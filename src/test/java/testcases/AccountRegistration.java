package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MainPage;
import pageObjects.RegistrationPage;
import testBase.BaseTestScripts;

public class AccountRegistration extends BaseTestScripts{


	@Test(groups = {"Regression","Master"})
	public void TC_AccountRegistartion_001() {
		logger.info("*** Started the TC_AccountRegistartion_001 ***");
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		logger.info("*** Clicked on my account link ***");
		hp.clickOnRegisterLink();
		logger.info("*** Clicked on my Registartion link ***");
		RegistrationPage rp = new RegistrationPage(driver);
		rp.setTheFirstName(randomStringForName());
		logger.info("*** Entered first name ***");
		rp.setTheLastName(randomStringForLastName());
		logger.info("*** Entered Last name ***");
		rp.setEmail(randomStringForEmail() + "@gmail.com");// ascscacsg@gmail.com
		logger.info("*** Entered Email ***");
		rp.setPassword(randomPassword());// Pass@123
		logger.info("*** Entered password ***");
		rp.selectPrivacyToggle();
		logger.info("*** Clicked on the toggle button ***");
		rp.clickOnContinue();
		logger.info("*** Clicked on the continue ***");
		MainPage mp = new MainPage(driver);
		Assert.assertEquals(mp.accountCreatedDisplayed(), true);
		logger.info("*** Ended the TC_AccountRegistartion_001 ***");

	}

	
}
