package rahulshey.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponent.AbstractComponent;

/**
 * Hello world!
 */
public class LandingPage extends  AbstractComponent {
    
	
	
	WebDriver driver;
	
    public LandingPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    //WebElement userEmail=driver.findElement(By.id("userEmail"));
    
    @FindBy(id="userEmail")
    WebElement userEmail;
    
    
	//driver.findElement(By.id("userPassword")).sendKeys("Manuson7@");
    @FindBy(id="userPassword")
    WebElement passwordele;
	
    @FindBy(id="login")
    WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    
    public ProductCatalouge loginApplication(String email,String password)
    {
    	userEmail.sendKeys(email);
    	passwordele.sendKeys(password);
    	submit.click();
    	
		ProductCatalouge productCatelogue=new ProductCatalouge(driver);	
        return  productCatelogue;
    }

	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
