package utilities;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public interface IHtmlWebElements {
	
	public WebElement getElement(String path, String locatorType);
	public List<WebElement> getElements(String path,String locatorType);
	public  void acceptAlert();
	public  void dismissAlert();
	public String getAlertMessage();
	public Alert inputStringAlert(String inputString);

}
