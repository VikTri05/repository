Feature: Automation of ebay app via appium		

@InTest
Scenario Outline: Search for the product on ebay and verify the details
Given the user is on the eBay homepage
And the user searches for the product "<Product_Name>"
And the user sorts the item by price from low to high
And the user sorts the item price from high to low
And the user collects the first "<Item_Number>" item prices
And the user chooses the first item and clicks on watch hyperlink
And the user verifies sign in page
And the user enters the username "<Random_Username>"
And the user closes the pop-up and navigates back to the product page
Examples:
|Product_Name|Item_Number|Random_Username|
|Rolex		 |		3	 |adasfefefefef	 |