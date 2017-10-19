package simple;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class example1 {
	WebDriver driver;
	
	public String geraTexto(int numCaracteres) {
		String texto = "";
		for (int i = 0; i < numCaracteres; i++) {
			Random r = new Random();
			texto = texto + (char)(r.nextInt(26) + 'a');
		}
		return texto;
	}
	
	public void setupTest() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/aline/Desktop/chromedriver.exe");
		
		driver = new ChromeDriver();

		driver.navigate().to("https://twitter.com/ProjetoTesteS2B");
		
		//Fazer Login		
		// Procura o elemento do form de login e salva na variavel
		WebElement loginForm = driver.findElement(By.xpath("//ul[contains(@id, 'session')]//form[contains(@class, 'LoginForm')]"));
		// Insere o usuário
		loginForm.findElement(By.xpath("div[1]/input")).sendKeys("ProjetoTesteS2B");
		// insere a senha
		loginForm.findElement(By.xpath("div[2]/input")).sendKeys("Aline64034240");
		// Clica no botão login
		loginForm.findElement(By.xpath("input[1]")).click();
		
	}
	
	@Test
	public void tweet140Caracteres() {
		try 
		{
			// Inicializa o browser e faz login
			setupTest();
			// Busca botão de tweetar
		    WebElement botaoTweetar = driver.findElement(By.xpath("//button[@id='global-new-tweet-button']"));
		    // Clica no botão
		    botaoTweetar.click();
		    //Busca elemento de digitação de texto
			WebElement texto = driver.findElement(By.xpath("//div[@id='tweet-box-global']"));
		    //Envia texto de 140 caracteres
			texto.sendKeys(geraTexto(140));
			
			// Clica no botão 
			driver.findElement(By.xpath("//button[@class='tweet-action EdgeButton EdgeButton--primary js-tweet-btn']")).click();	
			
			// Busca a mensage de retorno
			WebElement mensagemRetorno = driver.findElement(By.xpath("//div[@id='message-drawer']"));
			
			// Espera a mensagem aparecer
			(new WebDriverWait(driver, 500))
					   .until(ExpectedConditions.visibilityOf(mensagemRetorno));

			assertTrue("Erro: Tweet não foi postado, mensagem: "+mensagemRetorno.getAttribute("innerText"), mensagemRetorno.getAttribute("innerText").contains("Seu Tweet foi publicado!"));
		}
		catch (Exception ex ) {
			assertTrue("Exceção", false);
		}
		finally {
			driver.close();
		}
	}
	
	@Test
	public void tweet170Caracteres() {
		try 
		{
			// Inicializa o browser e faz login
			setupTest();
			// Busca botão de tweetar
		    WebElement botaoTweetar = driver.findElement(By.xpath("//button[@id='global-new-tweet-button']"));
		    // Clica no botão
		    botaoTweetar.click();
		    //Busca elemento de digitação de texto
			WebElement texto = driver.findElement(By.xpath("//div[@id='tweet-box-global']"));
		    //Envia texto de 170 caracteres
			texto.sendKeys(geraTexto(170));

			// Verifica se botão "Tweetar" desabilitado existe na tela, se existir o teste passou (Não será possível clicar no botão para postar o tweet)
			assertTrue("Sucesso", driver.findElements(By.xpath("//button[@class='tweet-action EdgeButton EdgeButton--primary js-tweet-btn disabled']")).size() != 0);
//			assertFalse("Erro: Tweet não foi postado", mensagemRetorno.getAttribute("innerText").contains("Your Tweet was posted!"));
	    }
		catch (Exception ex ) {
			assertTrue("Exceção", false);
		}
		finally {
			driver.close();
		}
	}			
	
	@Test
	public void testRetweetSemTexto() {
		try 
		{
			// Inicializa o browser e faz login
			setupTest();

			// Busca o primeiro botão de retweetar em um post na pagina inicial
			WebElement botaoRetweet = driver.findElement(By.xpath("(//button[@class='ProfileTweet-actionButton  js-actionButton js-actionRetweet'])[1]"));	
		    // Clica no botão
			botaoRetweet.click();
			
		    // Busca o botão enviar Retweetar na tela de dialogo
		    WebElement botaoExecRetweet = driver.findElement(By.xpath("//button[@class='EdgeButton EdgeButton--primary retweet-action']"));
		    // Verifica se o botão está com o nome "Retweetar
		    assertTrue("Erro: Botão não está com o texto 'Retweetar', texto do botão: "+botaoExecRetweet.getAttribute("innerText"), 
		    		 botaoExecRetweet.getAttribute("innerText").trim().equals("Retweetar"));
		}
		catch (Exception ex ) {
			assertTrue("Exceção", false);
		}
		finally {
			driver.close();
		}
	}
	
	@Test
	public void testRetweetComTexto() {
		try 
		{
			setupTest();

			// Busca o primeiro botão de retweetar em um post na pagina inicial
			WebElement botaoRetweet = driver.findElement(By.xpath("(//button[@class='ProfileTweet-actionButton  js-actionButton js-actionRetweet'])[1]"));	
		    // Clica no botão
			botaoRetweet.click();
			
			// Busca o elemento de digitação de texto no dialogo
			WebElement texto = driver.findElement(By.xpath("//div[@id='retweet-with-comment']"));
			
			//Digita um texto randomico de 40 caracteres 
			texto.sendKeys(geraTexto(40));
			
		    // Busca o botão enviar Retweetar na tela de dialogo
		    WebElement botaoExecRetweet = driver.findElement(By.xpath("//button[@class='EdgeButton EdgeButton--primary retweet-action']"));	
			
			// Verifica se o botão do dialogo mudou o nome para "Tweetar"
		    assertTrue("Erro: Botão não está com o texto 'Tweetar', texto do botão: "+botaoExecRetweet.getAttribute("innerText"), botaoExecRetweet.getAttribute("innerText").trim().equals("Tweetar"));
		    
		    botaoExecRetweet.click();
		    
			// Busca a mensage de retorno
			WebElement mensagemRetorno = driver.findElement(By.xpath("//div[@id='message-drawer']"));
			
			// Espera a mensagem aparecer
			(new WebDriverWait(driver, 500))
					   .until(ExpectedConditions.visibilityOf(mensagemRetorno));

			assertTrue("Erro: Tweet não foi postado, mensagem: "+mensagemRetorno.getAttribute("innerText"), mensagemRetorno.getAttribute("innerText").contains("Seu Tweet foi publicado!"));

		}
		catch (Exception ex ) {
			assertTrue("Exceção", false);
		}
		finally {
			driver.close();
		}
	}
}