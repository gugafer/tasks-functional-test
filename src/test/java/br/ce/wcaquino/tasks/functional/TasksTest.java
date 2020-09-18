package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao () throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
//		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		WebDriver driver = RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub"), cap);
		//WebDriver driver = new RemoteWebDriver("http://192.168.100.4:4444/wd/hub", cap);
//		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub", cap));
/*        WebDriver driver;
    driver = RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub"), cap);*/
		ChromeOptions cap = new ChromeOptions(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
		                  UnexpectedAlertBehaviour.IGNORE);

		org.openqa.selenium.remote.RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub"),cap);
		
		driver.navigate().to("http://192.168.100.4:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	

	public WebDriver RemoteWebDriver(URL url, DesiredCapabilities cap) {
		// TODO Auto-generated method stub
		return null;
	}


	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {
			//clicar Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
		}
		finally{
			//fechar o browser
			driver.quit();	
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {
			//clicar Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		}
		finally{
			//fechar o browser
			driver.quit();	
		}
	}

	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {
			//clicar Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
		}
		finally{
			//fechar o browser
			driver.quit();	
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {
			//clicar Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
		}
		finally{
			//fechar o browser
			driver.quit();	
		}
	}
	
}