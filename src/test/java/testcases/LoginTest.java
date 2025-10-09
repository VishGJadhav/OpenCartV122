package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import testBase.BaseTestScripts;

public class LoginTest extends BaseTestScripts {

	@Test(groups = {"Sanity","Master"})
	public void TC_LOGIN_001_VerifySuccessfulLoginWithValidCredentials() {

		logger.info("*** Started TC_LOGIN_001_VerifySuccessfulLoginWithValidCredentials ***");
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		logger.info("*** Clicked on my account link ***");
		hp.clickOnLoginLink();
		logger.info("*** Clicked on login link ***");
		LoginPage lp = new LoginPage(driver);
		lp.enterUsername(p.getProperty("username"));
		logger.info("*** Entered username ***");
		lp.enterPassword(p.getProperty("password"));
		logger.info("*** Entered password ***");
		lp.clickBtnLogin();
		logger.info("*** Clicked on login buttn ***");
		MainPage mp = new MainPage(driver);
		Assert.assertTrue(mp.logoutIsDisplayed(), "Login Failed");
		logger.info("*** Ended TC_LOGIN_001_VerifySuccessfulLoginWithValidCredentials ***");
	}

	@Test(groups = {"Sanity","Master"})
	public void TC_LOGIN_002_VerifyLoginFailsWithInvalidUsername() {
		try {
			logger.info("*** Started TC_LOGIN_002_VerifyLoginFailsWithInvalidUsername ***");
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccount();
			logger.info("*** Clicked on my account link ***");
			hp.clickOnLoginLink();
			logger.info("*** Clicked on login link ***");
			LoginPage lp = new LoginPage(driver);
			lp.enterUsername(p.getProperty("invalidUsername"));
			logger.info("*** Entered username ***");
			lp.enterPassword(p.getProperty("password"));
			logger.info("*** Entered password ***");
			lp.clickBtnLogin();
			logger.info("*** Clicked on login buttn ***");
			MainPage mp = new MainPage(driver);
			Assert.assertFalse(mp.logoutIsDisplayed(), "Login should fail with invalid username!");
			logger.info("*** Ended TC_LOGIN_002_VerifyLoginFailsWithInvalidUsername ***");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	public void TC_LOGIN_003_VerifyLoginFailsWithInvalidPassword() {
		logger.info("*** Started TC_LOGIN_003_VerifyLoginFailsWithInvalidPassword ***");
	}
}
