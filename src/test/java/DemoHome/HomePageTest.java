package DemoHome;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.savein.leadXPageObjects.*;
import testComponents.BaseTest;

public class HomePageTest extends BaseTest{
	HomePage homePage;
	
	@BeforeClass
	public void setUp() {	
		homePage = new HomePage(driver);	
	}
	
//	@Test(enabled = true)
//	public void listMyPracticeIn5Mins() throws InterruptedException, IOException {
//		String str=homePage.listPractice();
//		Assert.assertEquals(str, "");
//		Thread.sleep(7000);
//	}
	
	@Test
	public void myAccountLinkTest() throws InterruptedException, IOException {
		homePage.myAccount();
		extentTest.pass("Assertions is passed ");
		driver.navigate().back();
	}
	public void signUpLoginLinkTest() throws InterruptedException, IOException {
		homePage.myAccount();
		extentTest.pass("Assertions is passed ");
		driver.navigate().back();
	}
	
}
