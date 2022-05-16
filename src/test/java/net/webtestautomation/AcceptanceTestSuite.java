package net.webtestautomation;


import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.annotations.Managed;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AcceptanceTestSuite extends SerenityStories {

    @Managed(uniqueSession = false)
    public static WebDriver driver;

    @BeforeStory
    public void beforeStory() {
    	 System.out.println("--- Before Story Ends---");
    }

    @BeforeStories
    public void setUp() {
    }

    @AfterStory
    public void afterStory() {
    	System.out.println("--- After Story Ends---");
    }
    
    @AfterStories
    public static void finish() {
        driver.quit();
    }
    
  
        
}
