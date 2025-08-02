package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void verifyLogin() {
		
		log.info("**** Start TC02_LoginTest ****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			log.info("Clicked My account Link");
			
			hp.clickLogin();
			log.info("Clicked Login Link");
			
			log.info("Enter credentials");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			
			lp.setPassword(prop.getProperty("password"));
			
			lp.clickLogin();
			log.info("Clicked login");
			
			MyAccountPage accountPage = new MyAccountPage(driver);
			
			boolean pageConfirmation = accountPage.confirmMyAccountPage();
			if(pageConfirmation == true) {
				Assert.assertTrue(true);
			}
			else{
				
				log.error("Test Failed...");
				Assert.assertTrue(false);
				Assert.fail();
			}
		} catch (Exception e) {
			log.error("Test Failed...");
			Assert.fail();
		}
		
		log.info("**** Finished TC02_LoginTest ****");
		
	}

}
