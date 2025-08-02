package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass {
	
	@Test(groups= {"Regression", "Master"})
	public void verifyRegistration() {
		
		log.info("**** Start TC01_AccountRegistrationTest ****");
		HomePage homepage = new HomePage(driver);
		homepage.clickMyaccount();
		log.info("Clicked on account Link");
		
		try {
			homepage.clickRegister();
			log.info("Clicked on register Link");
			
			RegistrationPage rspage = new RegistrationPage(driver);
			
			log.info("Enter user details");
			rspage.setFirstName(randomString().toUpperCase());
			rspage.setLastName(randomString().toUpperCase());
			rspage.setEmail(randomString()+"@gmail.com");
			rspage.setTelephone(randomNumber());
			
			String password = randomPassword();
			rspage.setPassword(password);
			rspage.setConfirmPassword(password);
			
			rspage.checkPolicy();
			rspage.clickConfirm();
			Thread.sleep(3000);
			
			log.info("Verifying confirmation message...");
			String expectedResult = rspage.confimRegistration();
			System.out.println(expectedResult);
			
			if(expectedResult.contains("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} 
			else {
				log.error("Test Failed...");
				Assert.assertTrue(false);
				Assert.fail();
			
			}
		} catch (Exception e) {
			log.error("Test Failed...");
			Assert.fail();
		}
		
		log.info("**** Finished TC01_AccountRegistrationTest ****");
	}

}
