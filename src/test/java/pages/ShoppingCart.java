package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCart {

	WebDriver driver;

	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='ajax--cart']//div[@class='alert--content']")
	WebElement alertMessage;

	@FindAll({ @FindBy(xpath = "//div[@class='ajax--cart']//div[@class='cart--item']") })
	public List<WebElement> cartItems;

	@FindAll({ @FindBy(xpath = "//div[@class='ajax--cart']//span[@class='item--name']") })
	public List<WebElement> cartItemNames;

	public String getAlertMessage() {
		return alertMessage.getText().trim();
	}

	public int getCartItemsCount() {
		return cartItems.size();
	}

	public boolean checkItemPresent(String itemName) {

		for (WebElement e : cartItemNames) {
			if (e.getText().trim().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

}
