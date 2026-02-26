package com.qa.openCart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openCart.exceptions.ElementException;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method is used for null check. if value is null it will throw exception
	 * 
	 * @param value
	 */
	// null chcek method
	public void nullCheck(String value) {
		if (value == null) {
			throw new ElementException("Value Is Null.." + value);
		}
	}

	/**
	 * This method is used to pass the value in input fields
	 * 
	 * @param locator
	 * @param value
	 */
	// sendkeys
	@Step("Entering value {1} in text field using locator : {0}")
	public void doSendkeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);

	}

	// Overloaded method with wait
	@Step("Entering value {1} in text field using locator : {0} and waiting for eleent with : {2} sec..")
	public void doSendkeys(By locator, String value, int timeOut) {
		nullCheck(value);
		waitForElementVisible(locator, timeOut).clear();
		waitForElementVisible(locator, timeOut).sendKeys(value);

	}

	/**
	 * This method is used for click on webelement
	 * 
	 * @param locator
	 */
	// click
	@Step("Clicking on lement using locator : {0}")
	public void doClick(By locator) {
		getElement(locator).click();

	}

	// Overloaded method with wait
	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	/**
	 * This method is used for locate the element on webpage and return single
	 * webelement
	 * 
	 * @param locator
	 * @return
	 */
	// findelement
	@Step("Findinng the element using locator : {0}")
	public WebElement getElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page.." + locator);
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * This method is used to locate list of webelements on webpage and return list
	 * of webelements
	 * 
	 * @param locator
	 * @return
	 */
	// findelements
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * This method is used to captured the text of webelemnt and return the text in
	 * string format
	 * 
	 * @param locator
	 * @return
	 */
	// getText
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	// getAttribute
	public String doGetAttribute(By locator, String attrValue) {
		return getElement(locator).getAttribute(attrValue);
	}

	// idDisplay method
	@Step("Checking the state of locators : {0} is displayed or not...")
	public boolean doIsDisplay(By locator) {
		try {
			boolean flag = getElement(locator).isDisplayed();
			System.out.println("Element is displayed : " + locator);
			return flag;
		} catch (NoSuchElementException e) {
			System.out.println("Element with locator : " + locator + " is not displayed");
			return false;

		}

	}

	// check is element is display or not by sing findelements method
	public boolean isElementDisplayed(By locator) {
		int elementCount = getElements(locator).size();
		if (elementCount == 1) {
			System.out.println("Single element is displayed : " + locator);
			return true;
		} else {
			System.out.println("Multiple or zero elements are displayed : " + locator);
			return false;
		}
	}

	// Overloading method
	public boolean isElementDisplayed(By locator, int expElementCount) {
		int elementCount = getElements(locator).size();
		if (elementCount == expElementCount) {
			System.out.println(
					"expected elements are displayed : " + locator + " with the occurance of : " + expElementCount);
			return true;
		} else {
			System.out.println(
					"Expected elements are not displayed : " + locator + " with the occurance of : " + expElementCount);
			return false;
		}
	}

	// Count elements on webpage
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	// get the text of elements on webpage
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0 || text.isEmpty() == false) {
				eleTextList.add(text);
			}
		}

		return eleTextList;
	}

	// get the attribute value of elements on webpage
	public List<String> getElementAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> attrValueList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			if ((attrValue.length() != 0 || attrValue.isEmpty() == false) && attrValue != null) {
				attrValueList.add(attrValue);
				// System.out.println(attrValue);
			}
		}

		return attrValueList;
	}

	// *****************Select dropdown utils***********************

	// select by index
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	// select by visible text
	public void doSelectByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	// select by value
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	// dropdown options count
	public int getdropdownOptionsCounts(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	// drop down options text list
	public List<String> getDropdownOptionsTextList(By locator) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		for (WebElement e : optionsList) {
			String option = e.getText();
			optionsTextList.add(option);
		}

		return optionsTextList;

	}

	// Select the valus from dropdown without using select methods : index,
	// visibletext, value
	public void selectValueFromDropdown(By locator, String optionText) {
		Select select = new Select(getElement(locator));

		List<WebElement> allOptions = select.getOptions();
		for (WebElement e : allOptions) {
			String text = e.getText();
			if (text.equals(optionText.trim())) {
				e.click();
				break;
			}

		}
	}

	// Select the valus from dropdown without select class
	public void selectValueFromDropdownWithoutSelectClass(By locator, String optionText) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(optionText.trim())) {
				e.click();
				break;
			}
		}

	}

	// De-select by index
	public void doDeSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.deselectByIndex(index);

	}

	// De-select by visible text
	public void doDeSelectByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.deselectByVisibleText(visibleText);
	}

	// De-select by value
	public void doDeSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.deselectByValue(value);
	}

	// deselect all
	public void doDeselectAll(By locator) {
		Select select = new Select(getElement(locator));
		select.deselectAll();

	}

	// **************************xxxx******************************

	// Auto suggetion options
	public void autosuggetionOptions(By searchField, String searchKey, By suggetions, String value)
			throws InterruptedException {

		doSendkeys(searchField, searchKey);
		Thread.sleep(3000);

		List<WebElement> sugList = getElements(suggetions);
		for (WebElement e : sugList) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}

	// ***************************Actions Utils*****************************

	// move to element for 2 steps
	public void handleParentSubMenu(By parentLocator, By childLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentLocator)).perform();
		Thread.sleep(3000);
		doClick(childLocator);

	}

	// drag And drop
	public void doDragAnddrop(By sourceEle, By targetEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(getElement(sourceEle), getElement(targetEle)).perform();

	}

	// right Click
	public void doRightClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();

	}

	// Actions class sendkeys
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();

	}

	// Actions class click
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	// Actions class send keys with paus
	public void doActionsSendkeysWithPaus(By locator, String value, long pausTime) {
		Actions act = new Actions(driver);
		char ch[] = value.toCharArray();
		for (char c : ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(pausTime).perform();
		}
	}

	// overloading method
	public void doActionsSendkeysWithPaus(By locator, String value) {
		Actions act = new Actions(driver);
		char ch[] = value.toCharArray();
		for (char c : ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).perform();
		}
	}

	/**
	 * Move to element method for 4 steps by passing string values in parameter for
	 * first & last click option
	 * 
	 * @param level1
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	// Move to element method for 4 steps by using String values for first & last
	// click option
	public void level4MenuSubMenuHandlingUsingClick(By level1, String level2, String level3, String level4)
			throws InterruptedException {

		doClick(level1);
		Thread.sleep(2000);

		Actions act = new Actions(driver);
		act.moveToElement(getElement(By.linkText(level2))).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(By.linkText(level3))).perform();
		Thread.sleep(2000);
		act.click(getElement(By.linkText(level4))).perform();
		// OR
		// doClick(By.linkText(level4));

	}

	/**
	 * Move to element method for 4 steps by passing webelements in parameter for
	 * first & last click option
	 * 
	 * @param level1
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	// Overloading method
	// Move to element method for 4 steps by using Webelements for first & last
	// click option
	public void level4MenuSubMenuHandlingUsingClick(By level1, By level2, By level3, By level4)
			throws InterruptedException {

		doClick(level1);
		Thread.sleep(2000);

		Actions act = new Actions(driver);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(2000);
		doClick(level4);

	}

	/**
	 * Move to element method for 4 steps by passing webelements in parameter for
	 * all mouse over options except last
	 * 
	 * @param level1
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	// Move to element method for 4 steps by using Webelements for all mouse over
	// options except last
	public void level4MenuSubMenuHandlingUsingMouseOver(By level1, By level2, By level3, By level4)
			throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(level1)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(2000);
		doClick(level4);

	}

	// ************************Waits Utils***********************************

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * Overloaded method with fluent wait
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Overloaded method with fluent wait
	public WebElement waitForElementPresence(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.ignoring(NoSuchElementException.class)
				.withMessage("====elemnt is not found===");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible. Overloaded method
	 * with fluent wait
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// overloaded method with fluent wait
	public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.ignoring(NoSuchElementException.class)
				.withMessage("====elemnt is not found===");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	// wait for all elemnts
	public List<WebElement> waitForPresenceOfElementsLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElementsLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			return List.of();   // it will return blank/empty Arraylist
		}
	}
 

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param titleContainsValue
	 * @param timeOut
	 * @return
	 */
	// if element is visible and enable then only click
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking that the title contains a case-sensitive
	 * substring
	 * 
	 * @param titleContainsValue
	 * @param timeOut
	 * @return
	 */
	// wait for title and return title if title contains value available
	public String waitForTitleContains(String titleContainsValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleContains(titleContainsValue))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Title not found...");
		}
		return driver.getTitle();
	}

	/**
	 * An expectation for checking the title of a page.
	 * 
	 * @param titleValue
	 * @param timeOut
	 * @return
	 */
	// wait for title and return title if title value available
	@Step("Waiting for the title and capturing...")
	public String waitForTitleToBe(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(titleValue))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Title not found...");
		}
		return driver.getTitle();
	}

	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * 
	 * @param URLContainsValue
	 * @param timeOut
	 * @return
	 */
	// wait for URL and return URL if URL contains value available
	public String waitForURLContains(String URLContainsValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(URLContainsValue))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("URL not found...");
		}
		return driver.getCurrentUrl();
	}

	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * 
	 * @param URLValue
	 * @param timeOut
	 * @return
	 */
	// wait for URL and return URL if URL value available
	public String waitForURLToBe(String URLValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(URLValue))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("Title not found...");
		}
		return driver.getCurrentUrl();
	}

	// ********** wait for alert********
	// handle alert with wait and return alert
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	// Overloaded method with fluent wait
	public Alert waitForJSAlert(int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.ignoring(NoAlertPresentException.class)
				.withMessage("====Alert is not found===");
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	// get alert text
	public String getAlertText(int timeOut) {
		Alert alert = waitForJSAlert(timeOut);
		String text = alert.getText();
		alert.accept();
		return text;
	}

	// accept alert
	public void acceptAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	// dismiss alert
	public void dismissAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}

	// send value in alert
	public void alertSendKeys(String value, int timeOut) {
		Alert alert = waitForJSAlert(timeOut);
		alert.sendKeys(value);
		alert.accept();
	}

	// ********wait for frame********
	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to. If the frame is available it switches the given driver to the specified frame.
	 * Overloaded method with fluent wait
	 * @param frameLocator
	 * @param timeOut
	 */
	// By Locator
	public void waitForFrameBylocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	// Overloaded method with fluent wait
	public void waitForFrameBylocator(By frameLocator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.ignoring(NoSuchFrameException.class)
				.withMessage("====Frame is not found===");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	// By Index
	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	// By Id or name
	public void waitForFrameByIDOrName(String frameIDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
	}

	// By WebElement
	public void waitForFrameByWebElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	// *************end**************

	/**
	 * Wait apply for browser window
	 * 
	 * @param tottalWindow
	 * @param timeOut
	 * @return
	 */
	// wait for browser window
	public boolean waitForWindowToBe(int tottalWindow, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(tottalWindow));
	}
	
	
	/**
	 * this method checks to the page is fully loaded or not
	 * @param timeOut
	 * @return
	 */
	// chcek page is fully loaded or not
	public void isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		
		if(Boolean.parseBoolean(flag)) {
			System.out.println("Page Is Fully Loaded...");
		}
		else {
			throw new RuntimeException("Page Is Not Loaded...");
		}
	}

}
