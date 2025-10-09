package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import testBase.BaseTestScripts;
import utilities.DataProviders;

public class LoginTestDTT extends BaseTestScripts {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class,groups = "DataDriven")
	public void TC_loginDTT_001(String username, String password, String exp) {
		logger.info("*** Started TC_loginDTT_001 ***");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccount();
			logger.info("*** Clicked on my account link ***");
			hp.clickOnLoginLink();
			logger.info("*** Clicked on login link ***");
			LoginPage lp = new LoginPage(driver);
			lp.enterUsername(username);
			logger.info("*** Entered username ***");
			lp.enterPassword(password);
			logger.info("*** Entered password ***");
			lp.clickBtnLogin();
			logger.info("*** Clicked on login buttn ***");
			MainPage mp = new MainPage(driver);
			boolean targetPage = mp.logoutIsDisplayed();
			if (exp.equalsIgnoreCase("valid")) {
				if (targetPage == true) {
					mp.clickOnLogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("invalid")) {
				if (targetPage == true) {
					mp.clickOnLogout();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*** Ended TC_loginDTT_001 ***");
	}
}
