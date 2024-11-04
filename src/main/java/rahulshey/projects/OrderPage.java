package rahulshey.projects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponent.AbstractComponent;

public class OrderPage extends  AbstractComponent {
	WebDriver driver;

	@FindBy(css=".cartSection h3")
	List<WebElement> productTiles;
	
	@FindBy(css="tr td:nth-child(3)")
	WebElement checkoutele;
	
	public  OrderPage(WebDriver driver)
    {
		super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
	
	public boolean VerifyOrderDisplay(String productName) {
		boolean match=productTiles.stream().anyMatch(cartProduct-> cartProduct.getText().
 		       equalsIgnoreCase(productName));
		return match;
	}

}
