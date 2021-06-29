package patientpop.Test_assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

//Class contains reusable methods

public class Base {
	
	public WebDriver driver;
	String url;
	
	public WebDriver initialiseDriver() throws IOException {
		
		Properties prop = new Properties();
		String filePath =System.getProperty("user.dir")+"\\src\\main\\java\\patientpop\\Test_assignment\\data.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		else if(browserName.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equals("ie")) {
			
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
		
	}
	
	public String visitUrl() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Test_assignment\\src\\main\\java\\patientpop\\Test_assignment\\data.properties");
		prop.load(fis);
		String env = prop.getProperty("env");
		
		if(env.equals("stage")) {
			
			 url = "http://compare-staging.patientpop.com/checkup";
		}
		else if(env.equals("dev")) {
			
			url = "http://dev.com"; //enter your dev url here
		}
		else if(env.equals("test")) {
			
			url = "http://test.com"; //enter your test url here
		}
		
		return url;
		}

}

	
	
	


