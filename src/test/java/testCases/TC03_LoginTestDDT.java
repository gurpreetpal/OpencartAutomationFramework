package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginTestDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verifyLogin(String username, String password, String exp) {

		log.info("**** Start TC03_LoginTestDDT ****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			log.info("Clicked My account Link");

			hp.clickLogin();
			log.info("Clicked Login Link");

			log.info("Enter credentials");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(username);
			lp.setPassword(password);

			lp.clickLogin();
			log.info("Clicked login");

			MyAccountPage accountPage = new MyAccountPage(driver);
			boolean targetPage = accountPage.confirmMyAccountPage();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					
					accountPage.clickLogout();
					Assert.assertTrue(true);
					
				} else {

					Assert.assertTrue(false);
				}
			} if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					
					accountPage.clickLogout();
					Assert.assertTrue(false);
					
				} else {
					Assert.assertTrue(true);
				}

			}

		} catch (Exception e) {
			log.error("Test Failed...");
			Assert.fail();
		}

		log.info("**** Finished TC03_LoginTestDDT ****");

	}

}
