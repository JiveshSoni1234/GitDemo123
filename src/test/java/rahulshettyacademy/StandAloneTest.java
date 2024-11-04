package rahulshettyacademy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshey.projects.CartPage;
import rahulshey.projects.CheckOutPage;
import rahulshey.projects.ConfirmationPage;
import rahulshey.projects.OrderPage;
import rahulshey.projects.ProductCatalouge;

public class StandAloneTest extends BaseTest{

	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder (HashMap<String, String> input) throws IOException {
		
		ProductCatalouge productCatelogue=landingPage.loginApplication(input.get("email"), input.get("password"));	
		
		List<WebElement> products=productCatelogue.getproductList();
		productCatelogue.addProductToCart(input.get("product"));
		
		//click on cart option		
		
		//get Element text from cart Section and check whether the element we selected it's equal to 
		// productName and any-matches return boolean value
		
		CartPage cartPage=productCatelogue.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay(input.get("product"));
        
        //Assert.assertTrue(match);
	
        
        //click on checkout Button
		CheckOutPage checkOutPage=cartPage.gotoCheckOut();
		checkOutPage.SelectCountry("india");
		 ConfirmationPage  confirmationPage =checkOutPage.submitOrder();
		String messageText= confirmationPage.verifyConfirmationMessage();
		System.out.println(messageText.equalsIgnoreCase("Thankyou for the order."));
    	
    	//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void seeOrderHistory() {
		ProductCatalouge productCatelogue=landingPage.loginApplication("rahulshettyacademy1234@gmail.com", 
				"Manuson7@");	
		OrderPage orderspage=productCatelogue.goToOrderPage();
		orderspage.VerifyOrderDisplay(productName);
		
		//table[class="table table-bordered table-hover ng-star-inserted"]
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		
//		HashMap <String, String> map =new HashMap<String, String> ();
//		map.put("email", "rahulshettyacademy1234@gmail.com" );
//		map.put("pasword", "Manuson7@" );
//		map.put("product", "ZARA COAT 3");
//		
//		
//		HashMap <String, String> map1 =new HashMap<String, String> ();
//		map1.put("email", "anshika@gmail.com" );
//		map1.put("pasword", "Iamking@000" );
//		map1.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>>  map=getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object [][] {{map.get(0)} ,
			   {map.get(1)} };
	}

	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File Source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+
		          testCaseName+".png");
		
		FileUtils.copyFile(Source,file);
		return System.getProperty("user.dir")+"//reports//"+
        testCaseName+".png";
	}
}

