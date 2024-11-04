package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshey.projects.LandingPage;

public class BaseTest {

	public WebDriver driver ;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		
		Properties prop=new Properties();
		//dynamically launch karne k liye write this ("user.dir")
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):
				prop.getProperty("browser");
				//prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			
			
			
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				
			}
			
		     driver=new ChromeDriver(op);
		     driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			
		}
		
        else if(browserName.equalsIgnoreCase("edge")) {
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File Source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+
		          testCaseName+".png");
		
		FileUtils.copyFile(Source,file);
		return System.getProperty("user.dir")+"//reports//"+
        testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to String
		String jsonContent=FileUtils.readFileToString(new File(filePath));
		
		//String to HashMap we use jackson databind
		
		ObjectMapper 	mapper= new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		}
	
}
