package stepDefinitions;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import base.AppiumBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import pageObjects.VerifyEbayFunctionlaity;

public class StepDefinitonFile extends AppiumBase{
	
	VerifyEbayFunctionlaity verifyObj;
	
	@Before
	public void startAppiumSession() {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "AndroidEmulator");
			cap.setCapability("udid", "emulator-5554");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "8.1.0");
			cap.setCapability("appPackage", "com.ebay.mobile");
			cap.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, cap);
			System.out.println("Application started");
		}catch(Exception exp) {
			exp.getMessage();
			exp.getCause();
			exp.getStackTrace();
		}
	}
	
	@After
	public void closeAppiumSession() {
		driver.close();
	}
	
	@Given("^the user is on the eBay homepage$")
	public void verifyEbayHoempage() throws Throwable{
		verifyObj = new VerifyEbayFunctionlaity();
		verifyObj.verfiyEbayPage();
	}
	
	@And("^the user searches for the product \"([^\"]*)\"$")
	public void the_user_searches_for_the_product(String arg1) throws Throwable {
		verifyObj.searchForProduct(arg1);
		verifyObj.verifyProductDisplayed(arg1);
	}

	@Given("^the user sorts the item by price from low to high$")
	public void the_user_sorts_the_item_by_price_from_low_to_high() throws Throwable {
	    verifyObj.sortItemsFromLowToHigh();
	}

	@Given("^the user sorts the item price from high to low$")
	public void the_user_sorts_the_item_price_from_high_to_low() throws Throwable {
	    verifyObj.sortItemsFromHighToLow();
	}

	@Given("^the user collects the first \"([^\"]*)\" item prices$")
	public void the_user_collects_the_first_item_prices(String arg1) throws Throwable {
		verifyObj.selectNItemsPriceAsList(arg1);
	}

	@Given("^the user chooses the first item and clicks on watch hyperlink$")
	public void the_user_chooses_the_first_item() throws Throwable {	
		verifyObj.selectFirstItemOnDisplayAndClickOnWatch();
	}


	@Given("^the user verifies sign in page$")
	public void the_user_verifies_sign_in_page() throws Throwable {
	   verifyObj.verifySignInPage();
	}

	@Given("^the user enters the username \"([^\"]*)\"$")
	public void the_user_enters_the_username(String arg1) throws Throwable {
	    //The sign-in pag is protected and the elements can't be accessed neither from UIAutoamtorViewer nor by Desktop Doctor
	}

	@Given("^the user closes the pop-up and navigates back to the product page$")
	public void the_user_closes_the_pop_up_and_navigates_back_to_the_product_page() throws Throwable {
		try{
			((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		}catch(Exception e) {
			
		}
	}
}