package testComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class ITestListenerClass extends BaseTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");

	  }

	  public void onTestSuccess(ITestResult result) {
		  try {
			  System.out.println(result.getTestContext().getName()+"_"+result.getMethod().getMethodName());
			  System.out.println("SS name: "+result.getMethod().getMethodName());
			captureScreenshot(result.getMethod().getMethodName()+".jpg");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	  }


	  public  void onTestFailure(ITestResult result) {
		  
	  }


	  public void onTestSkipped(ITestResult result) {
	   
	  }

	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	   
	  }

	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }


	  public  void onStart(ITestContext context) {
		  System.out.println("onStart");
	   
	  }


	  public void onFinish(ITestContext context) {
		  System.out.println("onFinish");
	   
	  }
}
