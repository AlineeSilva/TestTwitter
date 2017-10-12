package test.java.simple;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class example1 {

	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/aline/Desktop/chromedriver.exe");
	WebDriver driver = new ChromeDriver();

	driver.navigate().to("https://twitter.com/ProjetoTesteS2B");
	
	driver.quit();
	
	
	}

}
