package net.webtestautomation.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//This class is used for all UI actions and extends common class MobilePageObject which contains driver and common methods
public class MovieResultsPage {

    @FindBy(xpath="//a[normalize-space()='List Movies']")
    @AndroidFindBy(xpath="")
    @iOSFindBy(xpath = "")
    private WebElement movieListOption;

    @FindBy(xpath="//a[normalize-space()='Create Movie']")
    @AndroidFindBy(xpath="")
    @iOSFindBy(xpath = "")
    private WebElement createMovieOption;

    @FindBy(xpath="//*[@id=\"root\"]/div[1]/nav/div/div/div[1]/a")
    private WebElement moviesListMenu;

    @FindBy(xpath="//input[1]")
    private WebElement movieNameField;

    @FindBy(xpath="//input[1]")
    private WebElement movieNameOnList;

    @FindBy(css="input[type='number']")
    private WebElement movieRatingField;
    @FindBy(css = "body > div:nth-child(2) > div:nth-child(2) > input:nth-child(7)")
    private WebElement movieTimeField;

    @FindBy(xpath = "//button[@class='sc-fznyAO bQTgqJ btn btn-primary']")
    private WebElement addMovieButton;

    @FindBy(css = ".sc-fzoyAV.gQUMYZ.btn.btn-primary")
    private WebElement updateMovieButton;

    @Step("Step {0}")
    public void generateStep(String s){
    }

    String movieName = "//div[normalize-space()='movieName']";
    String ratingForMovie = "//div[normalize-space()='movieName']/following-sibling::div[1]";
    String timeForMovie = "//div[normalize-space()='movieName']/following-sibling::div[2]";
    String updateLinkForMovie = "//div[normalize-space()='movieName']/following-sibling::div[normalize-space()='Upadate']";
    String deleteLinkForMovie = "//div[normalize-space()='movieName']/following-sibling::div[normalize-space()='Delete']";

    private  String platform = System.getProperty("platform");

    //Added this to use driver in this base class
    private  WebDriver driver ;

    public MovieResultsPage() {

        WebDriverManager.chromedriver().setup();
        //Create driver object for Chrome
        this.driver = new ChromeDriver();
        PageFactory.initElements(driver, this);

    }

    //Opens the given website in the browser
    public void gotoMovieSiteOnBrowser() {

        System.out.println("Launching the movie site in browser");
        driver.get("http://localhost:3000/movies/list");
        driver.manage().window().maximize();
        if(moviesListMenu.isDisplayed()){
            generateStep("Movie site is launched successfully");
            generateStep("Movie list option is visible");
        }
        else{
            generateStep("Failed to launch the movie site");
            Assert.fail("Movie list option is not visible");

        }
    }


    //Enters the details for the movie on the form and creates the same.
    public void createMovieFromUI(String movieName,String movieRating ,String movieTime) {
        createMovieOption.click();
        movieNameField.sendKeys(movieName);
        movieRatingField.sendKeys(movieRating);
        movieTimeField.sendKeys(movieTime);
        addMovieButton.click();
        acceptAlert();
        generateStep("Added movie "+movieName);
    }

    //Updates the details for the movie on the form and saves the same.
    public void updateMovieFromUI(String movieName,String movieRating) {
        String movieSpecificUpdateLink = updateLinkForMovie.replace("movieName",movieName);
        //scrollOnPage(driver.findElement(By.xpath(movieSpecificUpdateLink)));
        driver.findElement(By.xpath(movieSpecificUpdateLink)).click();
        movieRatingField.clear();
        movieRatingField.sendKeys(movieRating);
        updateMovieButton.click();
        acceptAlert();
        generateStep("Updated movie rating to "+movieRating);
    }

    //Deletes specific movie from the UI
    public void deleteMovieFromUIViaLinkClick(String movieName) {
        String movieSpecificDeleteLink = deleteLinkForMovie.replace("movieName",movieName);
        System.out.println("Locator:" + movieSpecificDeleteLink);
        driver.findElement(By.xpath(movieSpecificDeleteLink)).click();
/*        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(movieSpecificDeleteLink))));
        scrollOnPage(driver.findElement(By.xpath(movieSpecificDeleteLink)));
        if(driver.findElements(By.xpath(movieSpecificDeleteLink)).size()>1)
        {
            driver.findElements(By.xpath(movieSpecificDeleteLink)).get(0).click();
        }*/
        acceptAlert();
        generateStep(movieName+" is deleted from the application");
    }


    public void verifyMovieNameOnUI(String movieName) {
        String updatedMovieName = this.movieName.replace("movieName",movieName);
        scrollOnPage(driver.findElement(By.xpath(updatedMovieName)));
        if (driver.findElement(By.xpath(updatedMovieName)).isDisplayed()){
            generateStep(movieName+" is present in the list");
        }
        else{
            generateStep(movieName+" is not present in the list");
            Assert.fail(movieName+" is not present in the list");
        }
    }

    //Verifies the rating for specific movie on the UI
    public void verifyMovieRatingOnUI(String movieName, String movieRating) {
        String updatedMovieRating = this.ratingForMovie.replace("movieName",movieName);
        scrollOnPage(driver.findElement(By.xpath(updatedMovieRating)));
        Assert.assertEquals(movieRating,driver.findElement(By.xpath(updatedMovieRating)).getText());
        generateStep(movieRating+" is displayed for movie "+movieName);
    }

    // Verifies that the deleted movie is not present on the UI
    public void verifyDeletedMoviePresentOnUI(String movieName) {
        String updatedMovieName = this.movieName.replace("movieName",movieName);
        if (driver.findElements(By.xpath(updatedMovieName)).size() > 0){
            generateStep(movieName+" is not deleted from on UI");
            Assert.fail(movieName+" is not deleted from UI");

        }
        else{
            generateStep(movieName+" is deleted successfully");
        }
    }

    // Performs Mouse hover action on the element
    public void mouseOverElement(WebElement element)
    {
        System.out.println("Hovering over an element");
        generateStep("Hovering over an element");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    //Scrolls on the page until element is visible
    public void scrollOnPage(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Clicks on the Movie list option
    public void clickOnMovieListOption() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(movieListOption)).click();
        generateStep("Clicked on Movies option");
    }

    public void acceptAlert(){
        new WebDriverWait(driver, 5 * 1000).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void verifyMovieTimeOnUI(String movieName,String movieTime) {
        String updatedMovieRating = this.timeForMovie.replace("movieName",movieName);
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedMovieRating)));
        scrollOnPage(driver.findElement(By.xpath(updatedMovieRating)));
        Assert.assertEquals(movieTime,driver.findElement(By.xpath(updatedMovieRating)).getText());
        generateStep(movieTime+" is displayed for movie "+movieName);
    }

    public void EndBrowser() {
        driver.close();
    }
}
