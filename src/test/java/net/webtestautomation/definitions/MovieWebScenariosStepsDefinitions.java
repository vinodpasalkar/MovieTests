package net.webtestautomation.definitions;

import net.thucydides.core.annotations.Steps;
import net.webtestautomation.tests.MovieResultsTests;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


//This class contains all the steps definitions for the gherkin feature steps for Movie.story file
public class MovieWebScenariosStepsDefinitions {

    @Steps
    MovieResultsTests movieResultsTests;

    String movieNameString = "";
    String movieRatingString = "";
    String movieTimeString = "";

    @Given("I am on a movie list page")
    public void navigateToMovieListPageOnUI(){
        movieResultsTests.launchMoviePage();
    }

    @When("I add a new movie with details as $movieName $movieRating and $movieTime")
    public void createMovieFromUI(String movieName,String movieRating ,String movieTime){
        movieNameString = movieName;
        movieRatingString = movieRating;
        movieTimeString = movieTime;
        movieResultsTests.sendMovieDetailsForUI(movieName ,movieRating , movieTime);
    }

    @When("I delete the $movieName from UI")
    public void deleteMovieFromUI(String movieName){
        movieNameString = movieName;
        movieResultsTests.deleteMovieFromUILink(movieNameString);
    }


    @When("I update rating for movie $movieName as $movieRating")
    public void updateMovieFromUI(String movieName,String movieRating){
        movieNameString = movieName;
        movieRatingString = movieRating;
        movieResultsTests.updateMovieRatingDetailsOnUI(movieName ,movieRating);
    }

    @Then("the $movieName should be added successfully")
    public void verifyMovieOnUIList(String movieName){
        movieResultsTests.gotoMovieListPageOnUI();
        movieResultsTests.checkMovieDetailsOnUI(movieName,movieRatingString,movieTimeString);
    }

    @Then("the movie should be updated successfully for $movieRating")
    public void verifyUpdatedMovieRatingOnUI(String movieRating){
        movieResultsTests.gotoMovieListPageOnUI();
        movieResultsTests.checkMovieRating(movieNameString,movieRating);
    }

    @Then("the $movieName should be deleted successfully")
    public void verifyDeletedMovieOnUI(String movieName){
        movieResultsTests.gotoMovieListPageOnUI();
        movieResultsTests.checkDeletedMovie(movieName);
    }



    @AfterScenario()
    public void quitBrowser(){
        movieResultsTests.closeBrowser();
    }


}
