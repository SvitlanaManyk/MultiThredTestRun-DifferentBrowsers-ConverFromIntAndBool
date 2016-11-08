// Copyright, Svitlana Manyk 2016

package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.PropertiesLoader;

public class DifferentBrowsers {
	WebDriver driver = null;
	
	@Before
	public void setup(){
		System.out.println("Test starting...");
		startDriver();
	}
	
	@After
	public void byeByeDriver(){
		System.out.println("Closing the driver");
		driver.close();
		System.out.println("==========================================");
	}
	@Test
	public void testBrowserLaunching() {
		PropertiesLoader props = new PropertiesLoader();
		String browser = props.getProperty("browser.name"); 
															
		String url = props.getProperty("website"); // using properties file
		By searchBarLocator = new By.ByXPath("//*[@id='lst-ib']");
		By searchButton = new By.ByXPath("//*[@name='btnG']");
		By resultsPageToolbar = new By.ByXPath("//*[@id='hdtb-msb']");
		WebDriverWait wdWait = new WebDriverWait(driver, 5);

		driver.get(url);
		WebElement webSearchBar = driver.findElement(searchBarLocator);
		webSearchBar.sendKeys("cheese");
		WebElement webSearchButton = driver.findElement(searchButton);

		webSearchButton.click();

		wdWait.until(ExpectedConditions.visibilityOfElementLocated(resultsPageToolbar));

		driver.close();
	}

	public void startDriver(){
		PropertiesLoader props = new PropertiesLoader();
		String browser = props.getProperty("browser");
		
		System.out.println("Starting driver...");
		
		switch (browser){
		case "firefox":
			System.out.println("Firefox driver requested");
			driver = new FirefoxDriver();
			break;
		
	    case "chrome":
		    System.out.println("Chrome driver requested");
		    driver = new ChromeDriver();
		    break;
		    
		default:
			String message = "Unknown browser requested " + browser;
			System.out.println(message);
			throw new RuntimeException(message);
		
	}
	}

}
