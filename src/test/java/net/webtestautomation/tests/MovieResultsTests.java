package net.webtestautomation.tests;

import net.thucydides.core.annotations.Step;
import net.webtestautomation.pages.MovieResultsPage;

//This class is used for test assertions
public class MovieResultsTests {

    @Step("Step {0}")
    public void generateStep(String s){
    }

    private MovieResultsPage movieResultsPage;

    public void launchMoviePage() {
        movieResultsPage = new MovieResultsPage();
        movieResultsPage.gotoMovieSiteOnBrowser();
        generateStep("Opened Movie Application");
    }


    public void sendMovieDetailsForUI(String movieName, String movieRating , String movieTime) {
        movieResultsPage.createMovieFromUI( movieName, movieRating , movieTime);
    }

    public void updateMovieRatingDetailsOnUI(String movieName, String movieRating) {
        movieResultsPage.updateMovieFromUI( movieName, movieRating);
    }

    public void checkMovieDetailsOnUI(String movieName,String movieRating, String movieTime) {
        movieResultsPage.verifyMovieNameOnUI(movieName);
        movieResultsPage.verifyMovieRatingOnUI(movieName,movieRating);
        movieResultsPage.verifyMovieTimeOnUI(movieName,movieTime);
    }

    public void  checkMovieRating(String movieName,String movieRating) {
        movieResultsPage.verifyMovieRatingOnUI(movieName,movieRating);
    }

    public void gotoMovieListPageOnUI() {
        movieResultsPage.clickOnMovieListOption();
    }

    public void deleteMovieFromUILink(String movieName) {
        movieResultsPage.deleteMovieFromUIViaLinkClick(movieName);
    }

    public void checkDeletedMovie(String movieName) {
        movieResultsPage.verifyDeletedMoviePresentOnUI(movieName);
    }

    public void closeBrowser() {
        movieResultsPage.EndBrowser();
    }

}
