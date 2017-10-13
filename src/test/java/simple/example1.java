package simple;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class example1 {

	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/aline/Desktop/chromedriver.exe");
	WebDriver driver = new ChromeDriver();

	driver.navigate().to("https://twitter.com/ProjetoTesteS2B");
	
	// Procura o elemento do form de login e salva na variavel
	WebElement loginForm = driver.findElement(By.xpath("//ul[contains(@id, 'session')]//form[contains(@class, 'LoginForm')]"));
    // Insere o usuário
	loginForm.findElement(By.xpath("div[1]/input")).sendKeys("ProjetoTesteS2B");
	// insere a senha
	loginForm.findElement(By.xpath("div[2]/input")).sendKeys("Aline64034240");
	// Clica no botão login
	loginForm.findElement(By.xpath("input[1]")).click();	
	
	}

}
