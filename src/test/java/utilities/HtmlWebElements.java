package utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.LocatorType;
 


public class HtmlWebElements implements IHtmlWebElements {
	WebDriver driver;
	WebElement webElement;
	List<WebElement> webElements;
	WebDriverWait wait;
	Actions actions;


	public HtmlWebElements(WebDriver driver) {
		this.driver = driver;
		 actions = new Actions(driver);
	}

	public WebElement getElement(String locatorValue, String locatorType) {
		Object obj = switchCase( locatorValue, locatorType);
		webElement = driver.findElement((By) obj);
		return webElement;
	}
				
	
	public List<WebElement> getElements(String locatorValue, String locatorType) {
		Object obj = switchCase( locatorValue, locatorType);
		webElements = driver.findElements((By) obj);
		return webElements;
	}
	
	public void click(String locatorValue, String locatorType) {
		getElement(locatorValue, locatorType).click();
	}
			
	
	public  void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public  void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public String getAlertMessage() {
		String alertMessage = driver.switchTo().alert().getText();
		return alertMessage;
	}
	
	public Alert inputStringAlert(String inputString) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(inputString);
		return alert;
	}
	
	public void threadSleep(int timeToSleep) throws InterruptedException {
		Thread.sleep(timeToSleep*1000);
	}
	
	
	
	public void moveAndClick(String locatorValue, String locatorType) {
		WebElement webElement = getElement(locatorValue, locatorType);
		actions.moveToElement(webElement).perform();
	}
	
	JavascriptExecutor javascriptExecuter = (JavascriptExecutor) driver;
	
	public void scrollTillElementFound(String locatorValue, String locatorType) {
		WebElement webElement = getElement(locatorValue, locatorType);
		javascriptExecuter.executeScript("arguments[0].scrollIntoView();", webElement);
	}
	
	public void scrollToPageBottom() {
		javascriptExecuter.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollToPixels() {
		javascriptExecuter.executeScript("window.scrollBy(0,350)", "");
	}
	
	public String getAttributes(String locatorValue, String locatorType, String attribute) {
		WebElement element = getElement(locatorValue, locatorType);
		String elementAttribute = element.getAttribute(attribute);
		return elementAttribute;
	}
	
	public String getText(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		String elementText = element.getText();
		return elementText;
	}
	
	
	public void isDisplayed(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		element.isDisplayed();
	}
	public void isEnabled(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		element.isDisplayed();
	}
	public void isSelected(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		element.isSelected();
	}
	
	public void clear(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		element.clear();
	}
	
	public void submit(String locatorValue, String locatorType) {
		WebElement element = getElement(locatorValue, locatorType);
		element.submit();
	}

//	public void setAttribute(WebDriver driver, String locatorValue, String locatorType) {
//		WebElement element = getElement(driver, locatorValue, locatorType);
//		javascriptExecuter.executeScript ("document.getElementsByClassName('mui-btn')[0].setAttribute('style', " + "'background-color: yellow')");
//	}
	

	public void wait(WebDriver driver, int waitInSeccond) {
		driver.manage().timeouts().implicitlyWait(waitInSeccond, TimeUnit.SECONDS);
	}	
	public void visibilityOfElementLocatedBy(WebDriver driver, String xpath ) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public void elementToBeClickable(WebDriver driver, String xpath ) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	public void elementToBeSelected(WebDriver driver, String xpath ) {
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
	}
	public void presenceOfElementLocated(WebDriver driver, String xpath ) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	
	public Object switchCase(String locatorValue, String locatorType) {
		Object obj = null ;
		LocatorType enumLocatorType = LocatorType.valueOf(locatorType);
		switch(enumLocatorType) { 
		
		case LocatorType.XPATH:
			 obj =  By.xpath(locatorValue);
			 break;
			
		case LocatorType.CLASS_NAME:
			 obj =  By.className(locatorValue);
			 break;
			
		case LocatorType.CSS_SELECTOR :
			 obj = By.cssSelector(locatorValue);
			break;
			
		case LocatorType.ID :
			 obj = By.id(locatorValue);
			break;
			
		case LocatorType.LINK_TEXT :
			 obj = By.linkText(locatorValue);
			break;
		
		case LocatorType.NAME :
			 obj = By.name(locatorValue);
			break;
			
		case LocatorType.PARTIAL_LINK_TEXT:
			 obj = By.partialLinkText(locatorValue);
			break;
			
		case LocatorType.TAG_NAME :
			 obj = By.tagName(locatorValue);	
			break;
		}
		return obj;
	}
	

}
