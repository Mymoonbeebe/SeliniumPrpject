package com.hackthonproject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SiginPage {
	static WebDriver driver;
	static String mainWindow,currentWindow;
	static ArrayList<String> ss;
public static void main(String[] args) throws InterruptedException
{


	String URL="https://in.bookmyshow.com/";
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\2081105\\eclipse-workspace\\Hackthon\\drivers\\chromedriver.exe");
	
int count=0;
	driver=new ChromeDriver(options);
	
	driver.get(URL);
	//String mainWindow=driver.getWindowHandle();
	driver.manage().window().maximize();
	String PageTitle=driver.getTitle();
	Assert.assertEquals(PageTitle, "Movie Tickets, Plays, Sports, Events & Cinemas nearby - BookMyShow");
	String googleLink="//*[@id=\"modal-root\"]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/div";
	
	WebElement city=driver.findElement(By.xpath("//input[@placeholder='Search for your city']"));
	city.sendKeys("coimbatore");
	city.sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("//div[@class='sc-chbbiW bQENkS']")).click();
	
	/*WebElement wb=driver.findElement(By.xpath("//*[@id=\'modal-root\']/div/div/div/div/div[2]/div/div[1]/div/div[2]"));
	wb.click();
	Thread.sleep(5000);
	WebElement cwg=driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[2]/div/div[1]/div/div[2]/div"));
	String javascript="arguments[0].click()";
	JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
	jsExecutor.executeScript(javascript, cwg);
	ss=new ArrayList<String>(driver.getWindowHandles());
	Iterator<String> itr=ss.iterator();
	while(itr.hasNext())
	{
		if(itr.next()!=mainWindow)
		{
		currentWindow=itr.next();	
		}
	}*/
	
	 System.out.println(driver);
	
	boolean flag = true;
        while (flag) {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(googleLink))));
            driver.findElement(By.xpath(googleLink)).click();
            Set<String> allWindows = driver.getWindowHandles();
            System.out.println("Total: " + allWindows.size() + "\tcount: " + count);
            if (allWindows.size() > 1 || count == 8) {
                break;
            } else {
                count++;
            }
        }

       
        System.out.println("Google signin window is opened");

    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String Mainwindow=driver.getWindowHandle();
        driver.switchTo().window(Mainwindow);
        System.out.println(driver);
        WebDriverWait wait1=new WebDriverWait(driver,20);
		WebElement email=wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("identifier"))));
		email.sendKeys("12345cee@gmail.com");

	//WebDriverWait wait = new WebDriverWait(driver, 40);
	WebElement next=driver.findElement(By.xpath("//*[@id=\'identifierNext\']/div/button/span"));
	 //wait.until(ExpectedConditions.elementToBeClickable(next));
	 next.click();
	Thread.sleep(5000);
	 TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcFile,new File("./Screenshot/img.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	driver.close();
	
	driver.switchTo().window(mainWindow);
	driver.close();
	
}
}
