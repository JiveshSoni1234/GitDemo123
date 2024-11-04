package rahulshettyacademy;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshey.projects.CartPage;
import rahulshey.projects.CheckOutPage;
import rahulshey.projects.ConfirmationPage;
import rahulshey.projects.ProductCatalouge;


public class ErrorValidation extends  BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation () throws IOException {
		
		String productName="ZARA COAT 3";
		
		ProductCatalouge productCatelogue=landingPage.loginApplication("rahulshettyacademy123434@gmail.com", 
				"Manuson7@");	
		Assert.assertEquals("IncorrectEmail or Password", landingPage.getErrorMessage());
		
		
	}

	
	@Test
	public void ProductErrorValidation() throws IOException {
		
		String productName="ZARA COAT 3";
		
		ProductCatalouge productCatelogue=landingPage.loginApplication("rahulshettyacademy1234@gmail.com", 
				"Manuson7@");	
		
		List<WebElement> products=productCatelogue.getproductList();
		productCatelogue.addProductToCart(productName);
		
		//click on cart option		
		
		//get Element text from cart Section and check whether the element we selected it's equal to 
		// productName and any-matches return boolean value
		
		CartPage cartPage=productCatelogue.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay("Zracoat333");
        
        Assert.assertTrue(match);
	
	}
}
