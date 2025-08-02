package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//input[@name = 'firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@name = 'lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@name = 'email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@name = 'telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@name = 'password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@name = 'confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name = 'agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath = "//input[@type= 'submit']")
	WebElement btnSubmit;
	
	@FindBy(xpath = "//*[@id='content']/h1")
	WebElement confirmationMessage;
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void checkPolicy() {
		chkPrivacyPolicy.click();
	}
	
	public void clickConfirm() {
		btnSubmit.click();
	}
	
	public String confimRegistration() {
		try {
			return (confirmationMessage.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
