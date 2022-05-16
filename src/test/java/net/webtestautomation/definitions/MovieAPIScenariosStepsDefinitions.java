package net.webtestautomation.definitions;

import net.thucydides.core.annotations.Steps;
import net.webtestautomation.tests.MovieAPITests;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MovieAPIScenariosStepsDefinitions {

    @Steps
    MovieAPITests movieAPITests;

    public String strMovieName = "";
    public String strMovieRating = "";
    public String strMovieTime = "";

    @Given("Movies are already present")
    public void getListOfAllMovies(){
        movieAPITests.getAllMoviesOriginal();
    }

    @Given("I create a movie with $movieName $movieRating and $movieTime")
    public void addNewMovieFromAPI(String movieName,String movieRating ,String movieTime){
        strMovieName = movieName;
        strMovieRating = movieRating;
        strMovieTime = movieTime;
        movieAPITests.postNewMovie(strMovieName ,strMovieRating , strMovieTime);
    }


    @When("I update $movieName rating to $movieRating and time to $movieTime")
    public void updateMovieDetailsInAPI(String movieName ,String movieRating,String movieTime){
        strMovieName = movieName;
        strMovieRating = movieRating;
        strMovieTime = movieTime;
        movieAPITests.UpdateMovieRating(strMovieName ,strMovieRating , strMovieTime);
    }


    @When("I delete $movieName from API")
    public void deleteMovieFromAPI(String movieName){
        movieAPITests.deleteMovieFromAPI(movieName);

    }

    @Then("the movieName should be created successfully")
    public void verifyCreatedMovieFromAPI(String movieName){
        movieAPITests.checkMovieInResponse(movieName);

    }

    @Then("the values should be updated successfully")
    public void verifyMovieDataInAllResponse(){
        movieAPITests.checkMovieRatingInResponse(strMovieRating,strMovieName);
        movieAPITests.checkMovieTimeInResponse(strMovieTime,strMovieName);

    }

    @Then("the movieName should be deleted successfully from API")
    public void verifyDeletedMovieFromAPI(String movieName) throws InterruptedException {
        movieAPITests.checkDeletedMovieFromAPI(movieName);
    }




}

