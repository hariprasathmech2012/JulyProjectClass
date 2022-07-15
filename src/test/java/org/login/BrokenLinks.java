package org.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	
	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("no of links in a page"+links.size());
		
		int count=0;
		for(int i=0; i<links.size(); i++) {
			
			WebElement link = links.get(i);
			String attribute = link.getAttribute("href");
			URL url = new URL(attribute);
			URLConnection openConnection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
			int responseCode = httpURLConnection.getResponseCode();
			if(responseCode>400 && responseCode<500) {
				
				count++;
				System.out.println(attribute+"Broken Link");
				
				
			}
			
			System.out.println("no of broken links"+count);
		}
		
		
		
		
	}

}
