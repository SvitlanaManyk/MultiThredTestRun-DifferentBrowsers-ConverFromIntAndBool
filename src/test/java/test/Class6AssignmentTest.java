// Copyright, Svitlana Manyk 2016
package test;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.PropertiesLoader;

import org.junit.Assert;

public class Class6AssignmentTest {
	WebDriver driver = null;

	@Test
	public void usingXPath() {
		driver = new ChromeDriver();
		PropertiesLoader props = new PropertiesLoader();
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

	@Test
	public void usingCSStoFindAndCountElements() {
		driver = new ChromeDriver();
		String url = "http://www.google.com/";
		By searchField = new By.ByCssSelector("input[name='q']");
		By resultPageToolBar = new By.ByCssSelector("#rcnt");
		WebDriverWait wbWait = new WebDriverWait(driver, 5);
		driver.get(url);

		WebElement webSearchBar = driver.findElement(searchField);
		webSearchBar.sendKeys("cheese" + Keys.ENTER);
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(resultPageToolBar));

		List<WebElement> allResults = driver.findElements(By.cssSelector("h3[class=r]"));
		System.out.println("Results count is " + allResults.size() + " and they are as follows: ");
		System.out.println("_______________________________________________");

		for (WebElement link : allResults) {
			System.out.println(link.getText());
		}

		driver.close();
	}

	 @Test
	public void testPropertiesBoolean() {
		PropertiesLoader booleanProp = new PropertiesLoader();
		boolean boolVal1 = booleanProp.getPropertyAsBoolean("is.it.true");
		Assert.assertTrue("Value in property was not true!", boolVal1);
	}

	 @Test
	public void testPropertiesInt() {
		PropertiesLoader intProp = new PropertiesLoader();
		int intVal1 = intProp.getPropertyAsInt("this.is.int");
		Assert.assertEquals(10, intVal1);
	}

}
