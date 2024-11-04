package rahulshey.projects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponent.AbstractComponent;

public class CartPage extends  AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTiles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	
	
	public boolean VerifyProductDisplay(String productName) {
		boolean match=productTiles.stream().anyMatch(cartProduct-> cartProduct.getText().
 		       equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage gotoCheckOut() {
		checkoutele.click();
		return new CheckOutPage(driver);
	}
}
