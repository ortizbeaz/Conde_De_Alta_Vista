package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css = ".action_submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	public void selectCountry(String countryName) {
	//	Actions a = new Actions(driver);
	//	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(countryName);
		waitForElementToAppear(By.cssSelector(".ta-results"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement country = driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]"));
		js.executeScript("arguments[0].click(0);", country);
	}
	
	public ConfirmationPage submitOrder() {
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", submit);
		return new ConfirmationPage(driver);
	}
	
}
