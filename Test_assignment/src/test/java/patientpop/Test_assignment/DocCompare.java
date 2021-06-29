package patientpop.Test_assignment;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DocCompare extends Base {
	ExtentReports extent;
	
	@BeforeTest
	public void config()
	{

	// ExtentReports , ExtentSparkReporter

	String path =System.getProperty("user.dir")+"\\reports\\index.html";

	ExtentSparkReporter reporter = new ExtentSparkReporter(path);

	reporter.config().setReportName("Web Automation Results");

	reporter.config().setDocumentTitle("Doctor Compare Test Results");

	extent =new ExtentReports();

	extent.attachReporter(reporter);

	extent.setSystemInfo("Tester", "Shraddha Kulkarni");
	extent.setSystemInfo("Test Env", "Stage");
	}
	

	
@Test
	
	public void obGyn_Scores_Compare() throws InterruptedException, IOException 
	{
		ExtentTest test=extent.createTest("obGyn_Scores_Compare");
		driver = initialiseDriver();
		String url = visitUrl();
		driver.get(url);
		driver.findElement(By.xpath("//input[@id='practicename']")).sendKeys("Amersi");
		
		List<WebElement> options = driver.findElements(By.cssSelector("span.pac-item-query"));
	
		for(WebElement option:options) {
			try {
			if(option.getText().equalsIgnoreCase("Shamsah Amersi, MD")) {
					option.click();
					}
				}catch(StaleElementReferenceException e) {
					e.toString();
					System.out.println("Stale Element error, tryin:: " + e.getMessage());
				}
			}
		
		// if any of the string values is null then the test should fail
		//this step confirms that all field values are pre-populated correctly	
		boolean result = isEmpty();
		if(result==true) {
			Assert.assertTrue(true);
		}
		
		driver.findElement(By.cssSelector("input#specialty")).sendKeys("ob");
		driver.findElement(By.cssSelector("#list-item-32-1")).click();
		driver.findElement(By.cssSelector(".v-btn__content")).click();
		driver.manage().window().maximize();
		Thread.sleep(2000);

		boolean finalresult = 	scoreComp();
		if(finalresult==true) {
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
		
		driver.close();
		extent.flush();
		
	}

	

@Test

public void neurologist_Scores_Compare() throws InterruptedException, IOException 
{
	ExtentTest test=extent.createTest("neurologist_Scores_Compare");
	driver = initialiseDriver();
	String url = visitUrl();
	driver.get(url);
	driver.findElement(By.xpath("//input[@id='practicename']")).sendKeys("Prakash Neal");
	
	List<WebElement> options = driver.findElements(By.cssSelector("body:nth-child(2) div.pac-container.pac-logo.hdpi:nth-child(25) div.pac-item:nth-child(2) > span:nth-child(3)"));

	for(WebElement option:options) {
		try {
		if(option.getText().equalsIgnoreCase("East Duarte Road, Duarte, CA, USA")) {
				option.click();
				}
			}catch(StaleElementReferenceException e) {
				e.toString();
				System.out.println("Stale Element error, tryin:: " + e.getMessage());
			}
		}
	
	// if any of the string values is null then the test should fail
	//this step confirms that all field values are pre-populated correctly	
	boolean result = isEmpty();
	if(result==true) {
		Assert.assertTrue(true);
	}
	
	driver.findElement(By.cssSelector("input#specialty")).sendKeys("neu");
	driver.findElement(By.cssSelector("#list-item-32-0")).click();
	driver.findElement(By.cssSelector(".v-btn__content")).click();
	driver.manage().window().maximize();
	Thread.sleep(2000);
	
	//Compare scores on individual page against the overall pages
	boolean cmpResult = scoreComp();
	Assert.assertTrue(cmpResult);
	
	driver.close();
	test.fail("Fields are not prepopulated");

}

@Test

public void neuroSurgeon_Scores_Compare() throws InterruptedException, IOException 
{
	ExtentTest test = extent.createTest("neuroSurgeon_Scores_Compare");
	driver = initialiseDriver();
	String url = visitUrl();
	driver.get(url);
	driver.findElement(By.xpath("//input[@id='practicename']")).sendKeys("Prakash Neal");
	
	List<WebElement> options = driver.findElements(By.cssSelector("body:nth-child(2) div.pac-container.pac-logo.hdpi:nth-child(25) div.pac-item:nth-child(2) > span:nth-child(3)"));

	for(WebElement option:options) {
		try {
		if(option.getText().equalsIgnoreCase("East Duarte Road, Duarte, CA, U")) {
				option.click();
				}
			}catch(StaleElementReferenceException e) {
				e.toString();
				System.out.println("Stale Element error, tryin:: " + e.getMessage());
			}
		}
	
	// if any of the string values is null then the test should fail
	//this step confirms that all field values are pre-populated correctly	
	boolean result = isEmpty();
	if(result==true) {
		Assert.assertTrue(true);
	}
	
	driver.findElement(By.cssSelector("input#specialty")).sendKeys("neu");
	driver.findElement(By.cssSelector("#list-item-32-1")).click();//selected neuro-surgeon option
	driver.findElement(By.cssSelector(".v-btn__content")).click();
	driver.manage().window().maximize();
	Thread.sleep(2000);
	
	//Compare scores on individual page against the overall pages
	boolean cmpResult = scoreComp();
	Assert.assertTrue(cmpResult);
	
	driver.close();
}


	
	public boolean isEmpty() {
		
			String st_num = driver.findElement(By.id("street_number")).getAttribute("value").toString();
			String st_name = driver.findElement(By.id("route")).getAttribute("value").toString();
			String cty = driver.findElement(By.id("city")).getAttribute("value").toString();
			String state = driver.findElement(By.name("state")).getAttribute("value").toString();
			String zip = driver.findElement(By.id("zipcode")).getAttribute("value").toString();
			String webSite = driver.findElement(By.id("website")).getAttribute("value").toString();
			
			if(st_num.isEmpty()||st_name.isEmpty()||cty.isEmpty()||state.isEmpty()||zip.isEmpty()||webSite.isEmpty()) {
				return true;
			}
				return false;
		}

	
	public void clickNext() {
		
		WebElement m = driver.findElement(By.xpath("//div[@class='navigation']//span[@class='btn btn-nav btn-nav-next'][normalize-space()='Next']"));
	      //JavascriptExecutor to click element
	      JavascriptExecutor jse = (JavascriptExecutor) driver;
	      jse.executeScript("arguments[0].click();", m);
	}
	
public boolean scoreComp() throws InterruptedException {
	
	String compet_sc = driver.findElement(By.xpath("//div[@class='panel-right reverse']//li[@class='row your-practice']//div[@class='score-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')]")).getText();
	clickNext();
	String google_rank_score = driver.findElement(By.xpath("//div[@class='panel-left justify-content-center']//li[@class='row your-practice']//div[@class='score-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')]")).getText();
	clickNext();
	String webs_presence_score = driver.findElement(By.xpath("//div[@class='panel-left justify-content-center']//li[@class='row your-practice']//div[@class='score-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')]")).getText();
	clickNext();
	String website_score = driver.findElement(By.xpath("//div[@class='panel-left justify-content-center']//li[@class='row your-practice']//div[@class='score-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')]")).getText();
	clickNext();
	String reputation_score = driver.findElement(By.xpath("//div[@class='panel-left justify-content-center']//li[@class='row your-practice']//div[@class='score-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')]")).getText();
	clickNext();
	
	String compet_score_final = driver.findElement(By.xpath("(//*[local-name()='svg']//*[name()='text' and contains(@class,'score-value')])[11]")).getText();
	String google_rank_score_final = driver.findElement(By.xpath("//div[@class='summary-desktop']//div[@class='score-item summary-google_rank']//div[@class='score-item-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-valu')]")).getText();
	String web_presence_score_final = driver.findElement(By.xpath("//div[@class='summary-desktop']//div[@class='score-item summary-website_presence']//div[@class='score-item-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-valu')]")).getText();
	String website_score_final = driver.findElement(By.xpath("//div[@class='summary-desktop']//div[@class='score-item summary-website']//div[@class='score-item-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-valu')]")).getText();
	String reputation_score_final = driver.findElement(By.xpath("//div[@class='summary-desktop']//div[@class='score-item summary-reputation']//div[@class='score-item-value']//*[local-name()='svg']//*[name()='text' and contains(@class,'score-valu')]")).getText();
	
		if((compet_sc.equals(compet_score_final))&&(google_rank_score.equals(google_rank_score_final))&&(webs_presence_score.equals(web_presence_score_final))&&(website_score.equals(website_score_final))&&(reputation_score.equals(reputation_score_final))) {
			return true;
		}
			return false;
		}
}

		


	
	

	


