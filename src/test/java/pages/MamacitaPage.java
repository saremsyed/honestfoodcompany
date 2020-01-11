package pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MamacitaPage {

	WebDriver driver;

	public MamacitaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//a[@class='product--title']/button[1]") })
	public List<WebElement> allItemsList;

	public void waitForPage() throws InterruptedException {
		// TODO Need to write a custom wait
		Thread.sleep(10000);
	}

	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
	}

	public void selectItem(String itemName, String extras) throws InterruptedException {

		String newline = System.getProperty("line.separator");
		boolean hasNewline = extras.contains(newline);

		Set<String> extraItems = new HashSet<String>();
		if (hasNewline) {
			String[] arr = extras.split(newline);
			for (String a : arr) {
				extraItems.add(a);
			}
		} else {
			extraItems.add(extras);
		}

		selectItem(itemName, extraItems);

	}

	public void selectItem(String itemName, Set<String> extras) throws InterruptedException {

		allItemsList.get(0).click();

		ExtrasPopUp e = new ExtrasPopUp(driver);
		e.waitForPage();
		e.closeExtrasPopUp();

		for (WebElement ele : allItemsList) {

			if (ele.getText().equals(itemName)) {
				ele.click();
				e.waitForPage();
				e.selectExtras(extras);
				break;
			}

		}

	}

}
