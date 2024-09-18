package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
		@Test (dataProvider = "getData", groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws IOException {
			

			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage = productCatalogue.goToCartPage();

			Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goToCheckout();
			checkoutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
			String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
		
		@Test(dependsOnMethods = {"submitOrder"})
		public void OrderHistoryTest() {
		
			//To verify ZARA COAT 3 is displaying in orders page
			ProductCatalogue productCatalogue = landingPage.loginApplication("example2024@gmail.com", "Example1");
			OrderPage ordersPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
		

		
		//Extent Reports - 
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
			
			List<HashMap<String, String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
			return new Object[][] { {data.get(0)}, {data.get(1)} };
			
		}
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "example2024@gmail.com");
//		map.put("password", "Example1");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		

}
