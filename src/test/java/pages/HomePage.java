package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='/speisekarte/mamacita/wallenstein/' and @class='button-home is--primary']")
	WebElement mamacitaOrderNowButton;

	@FindBy(xpath = "//button[@class='agree-cookie']")
	WebElement agreeCookieButton;

	public void clickMamcitaOrderNowButton() {
		mamacitaOrderNowButton.click();
	}

	public void clickAgreeCookieButton() {
		agreeCookieButton.click();
	}

}
