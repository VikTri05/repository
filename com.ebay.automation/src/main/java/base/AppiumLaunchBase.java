package base;

import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;




public class AppiumLaunchBase {
	AppiumDriver<MobileElement> driver;
	{
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
		exp.printStackTrace();
	}
}
}
