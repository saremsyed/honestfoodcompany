package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterYourAddressPopUp {

	WebDriver driver;

	public EnterYourAddressPopUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='address-input']")
	WebElement addressTextBox;

	@FindBy(xpath = "//input[@value='zum menü']")
	WebElement toTheMenuButton;

	public void enterAddress(String address) {

		addressTextBox.sendKeys(address);

	}

	public void clickToTheMenuButton() {

		toTheMenuButton.click();

	}

}
