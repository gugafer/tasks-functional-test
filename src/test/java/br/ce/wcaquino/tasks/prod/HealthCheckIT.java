package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {
		ChromeOptions cap = new ChromeOptions(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
		UnexpectedAlertBehaviour.IGNORE);
		org.openqa.selenium.remote.RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.4:4444/wd/hub"),cap);
		try {
		driver.navigate().to("http://192.168.100.4:9999/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String version = driver.findElement(By.id("version")).getText();
		Assert.assertTrue(version.startsWith("build"));
		} finally {			
		driver.quit();
		}
	}
	
}
