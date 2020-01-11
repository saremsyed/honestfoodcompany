package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.EnterYourAddressPopUp;
import pages.ExtrasPopUp;
import pages.HomePage;
import pages.MamacitaPage;
import pages.ShoppingCart;
import utils.ExcelReader;

public class FirstTest {

	private WebDriver driver;
	private WebDriverWait wait;
	HomePage homePage = null;
	EnterYourAddressPopUp enterYourAddress = null;
	MamacitaPage mamacitaPage = null;
	ExtrasPopUp extrasPopUp = null;
	ShoppingCart shoppingCart = null;
	ExcelReader excelReader = null;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 60);
	}

	@Test
	public void testOne() throws Exception {

		homePage = new HomePage(driver);
		driver.get("https://clubkitchen.at/");

		homePage.clickAgreeCookieButton();
		homePage.clickMamcitaOrderNowButton();

		enterYourAddress = new EnterYourAddressPopUp(driver);
		enterYourAddress.enterAddress("Semperstraße 44, 1180 Wien, Austria");
		enterYourAddress.clickToTheMenuButton();

		mamacitaPage = new MamacitaPage(driver);
		mamacitaPage.waitForPage();

		excelReader = new ExcelReader();
		Object[][] tableArray = excelReader.getTableArray("OrderData.xlsx", "Mamacita");

		// Loop through all rows
		for (int i = 0; i < tableArray.length; i++) {
			mamacitaPage.selectItem(String.valueOf(tableArray[i][0]), String.valueOf(tableArray[i][1]));
			mamacitaPage.waitForPage();
		}

		shoppingCart = new ShoppingCart(driver);
		Assert.assertEquals(shoppingCart.getAlertMessage(), "Der Artikel wurde erfolgreich in den Warenkorb gelegt");
		Assert.assertEquals(shoppingCart.getCartItemsCount(), tableArray.length);

		// Loop through all rows
		for (int i = 0; i < tableArray.length; i++) {
			Assert.assertTrue(shoppingCart.checkItemPresent(String.valueOf(tableArray[i][0])));
		}

		driver.quit();
	}

}
