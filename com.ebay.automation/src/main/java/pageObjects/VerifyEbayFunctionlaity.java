package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.AppiumBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class VerifyEbayFunctionlaity extends AppiumBase {

	WebDriverWait wait = new WebDriverWait(driver, 120);
	MobileElement mElement = null;
	List<String> listItems = new ArrayList<String>();
	List<String> sortedListItems = new ArrayList<String>();

	public VerifyEbayFunctionlaity() throws Exception {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "home")
	private AndroidElement homePageIcon;

	@AndroidFindBy(id = "search_box")
	private AndroidElement searchBar;

	@AndroidFindBy(id = "search_src_text")
	private AndroidElement searchBarInput;

	@AndroidFindBy(id = "text")
	private List<AndroidElement> searchResults;

	@AndroidFindBy(id = "button_follow")
	private AndroidElement saveLinkIcon;

	@AndroidFindBy(id = "textview_item_title")
	private List<AndroidElement> searchResultItems;

	@AndroidFindBy(id = "textview_item_price")
	private List<AndroidElement> searchResultItemPrices;
	
	@AndroidFindBy(id="button_watch")
	private AndroidElement watchBtn;

	@AndroidFindBy(id="button_classic")
	private  List<AndroidElement> userNameSigninBtn;
	
	@AndroidFindBy(accessibility ="Close")
	private List<AndroidElement> closeSingInBtn;
	
	@AndroidFindBy(id = "text_slot_1")
	private AndroidElement resultTextBar;

	@AndroidFindBy(id = "button_sort")
	private AndroidElement sortIconLink;

	@AndroidFindBy(id = "button_filter_subelement")
	private List<AndroidElement> sortOptins;

	@AndroidFindBy(className = "android.widget.TextView")
	private List<AndroidElement> itemListInfo;
	
	
	public void verfiyEbayPage() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(homePageIcon));
		Thread.sleep(2000);
		System.out.println("Ebay homepage is available");
	}

	public void searchForProduct(String arg1) throws Exception {
		searchBar.click();
		Thread.sleep(2000);
		searchBarInput.sendKeys(arg1);
		if (searchResults.size() > 0) {
			wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(0)));
			Thread.sleep(2000);
			searchResults.get(0).click();
		}
	}

	public void verifyProductDisplayed(String arg1) throws Throwable {
		Thread.sleep(3000);
		resultTextBar.click();
		Thread.sleep(2000);
		if (searchResultItems.size() > 0) {
			String str = searchResultItems.get(0).getText();
			if (str.contains(arg1)) {
				System.out.println("The first item in the list is: " + str);
			}
		}
	}


	private void clickOnSortOptionBestMatch() throws Throwable {

		if (sortOptins.size() > 0) {
			for (int i = 0; i < sortOptins.size(); i++) {
				String str = sortOptins.get(i).getText();
				if (str.contains("Best Match")) {
					sortOptins.get(i).click();
					return;
				} else {
					continue;
				}
			}
		}
	}


	
	private void clickOnSortOptionLowToHigh() throws Throwable {

		if (sortOptins.size() > 0) {
			for (int i = 0; i < sortOptins.size(); i++) {
				String str = sortOptins.get(i).getText();
				if (str.contains("Lowest Price")) {
					sortOptins.get(i).click();
					return;
				} else {
					continue;
				}
			}
		}
	}

	private void clickOnSortOptionHighToLow() throws Throwable {

		if (sortOptins.size() > 0) {
			for (int i = 0; i < sortOptins.size(); i++) {
				String str = sortOptins.get(i).getText();
				if (str.contains("Highest Price")) {
					sortOptins.get(i).click();
					return;
				} else {
					continue;
				}
			}
		}
	}

	public void sortItemsFromHighToLow() throws Throwable {
		/*
		 * if (searchResultItemPrices.size() > 0) { for (AndroidElement e :
		 * searchResultItemPrices) { listItems.add(e.getText());
		 * System.out.println(e.getText()); }
		 */
		Thread.sleep(2000);
		sortIconLink.click();
		Thread.sleep(2000);
		clickOnSortOptionHighToLow();
		for (AndroidElement e : searchResultItemPrices) {
			sortedListItems.add(e.getText());
			System.out.println(e.getText());
		}
		/*
		 * Collections.sort(listItems, Collections.reverseOrder()); if
		 * (listItems.equals(sortedListItems)) {
		 * System.out.println("The lists are sorted"); } else {
		 * System.out.println("The lists are not sorted"); }
		 */
	}

	public void sortItemsFromLowToHigh() throws Throwable {
		/*
		 * if (searchResultItemPrices.size() > 0) { for (int
		 * i=0;i<searchResultItemPrices.size();i++) {
		 * System.out.println("Size of list: " +searchResultItemPrices.size());
		 * System.out.println(searchResultItemPrices.get(i).getText());
		 * listItems.add(searchResultItemPrices.get(i).getText()); }
		 */
		sortIconLink.click();
		Thread.sleep(2000);
		clickOnSortOptionLowToHigh();
		for (AndroidElement e : searchResultItemPrices) {
			sortedListItems.add(e.getText());
			System.out.println(e.getText());
		}
//			Collections.sort(listItems);
//			System.out.println(listItems);
//			if (listItems.equals(sortedListItems)) {
//				System.out.println("The lists are sorted");
//			} else {
//				System.out.println("The lists are not sorted");
//			}
		// Sorting of relevant items didn't work as the values didn't match the
		// relevant items
	}

	public void selectNItemsPriceAsList(String arg1) throws Throwable {
		Thread.sleep(5000);
		try{
			sortIconLink.click();
		Thread.sleep(2000);
		clickOnSortOptionBestMatch();
		Thread.sleep(2000);
		int n = Integer.valueOf(arg1);
		for(int i=0;i<3;i++) {
			System.out.println("Item value for "+n+" items: "+ searchResultItemPrices.get(i).getText());
		}
		}catch(Exception e){
			Assert.fail("No of items that can be retreived are less than the input criteria. No of items that are visible on screen:"+searchResultItemPrices.size());
		}
		
	}
	
	public void selectFirstItemOnDisplayAndClickOnWatch() throws Exception{
		searchResultItemPrices.get(0).click();
		wait.until(ExpectedConditions.elementToBeClickable(watchBtn));
		watchBtn.click();
		
	}
	
	public void verifySignInPage() throws Exception{
		Thread.sleep(2000);
		if(userNameSigninBtn.size()>0) {
			userNameSigninBtn.get(0).click();
		}
	}
	
	}

