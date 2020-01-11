package pages;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExtrasPopUp {

	WebDriver driver;

	public ExtrasPopUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='content product--details']//button")
	WebElement addToCartButton;

	@FindBy(xpath = "//div[contains(@class, 'js--modal')]//div[contains(@class, 'icon--cross')]")
	WebElement closeExtrasPopUpButton;

	@FindAll({ @FindBy(xpath = "//li[@class='top-up-item']//label") })
	public List<WebElement> allExtrasList;

	public void waitForPage() throws InterruptedException {
		// TODO Need to write custom wait
		Thread.sleep(5000);
	}

	public void clickAddToCartButton() {
		addToCartButton.click();
	}

	public void closeExtrasPopUp() {
		closeExtrasPopUpButton.click();
	}

	public void selectExtras(Set<String> extrasList) {
		for (WebElement element : allExtrasList) {
			int noOfExtrasSelected = 0;
			if (noOfExtrasSelected < extrasList.size()) {
				if (extrasList.contains(element.getText())) {
					element.click();
					noOfExtrasSelected++;
				}
			} else {
				break;
			}
		}
		clickAddToCartButton();
	}

}
