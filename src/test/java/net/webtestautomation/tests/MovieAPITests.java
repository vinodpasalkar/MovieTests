package net.webtestautomation.tests;

import com.google.gson.Gson;
import net.thucydides.core.annotations.Step;
import net.webtestautomation.dataModel.MovieDataModel;
import net.webtestautomation.movieAPIClasses.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MovieAPITests {

    private MovieDataModel movieDataModel;
    private Response movieResponse;

    private Gson gson;

    @Step("Step {0}")
    public void generateStep(String s){
    }

    public MovieAPITests() {
        try {
            movieDataModel = new MovieDataModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.movieResponse = movieDataModel.getAllMoviesAPI();
    }

    public void postNewMovie(String strMovieName, String strMovieRating, String strMovieTime) {
        movieDataModel.postNewMovie( strMovieName ,strMovieRating , strMovieTime);
    }

    public void UpdateMovieRating(String strMovieName, String strMovieRating, String strMovieTime) {
        //getAllMoviesOriginal();
        movieDataModel.UpdateMovieRating( strMovieName ,strMovieRating , strMovieTime);
    }

    public void checkMovieInResponse(String movieName) {
        movieDataModel.checkMovieInResponse( movieName);
    }

    public void checkMovieRatingInResponse(String strMovieRating, String strMovieName) {
        movieDataModel.checkMovieRatingInResponse(strMovieRating, strMovieName);
    }

    public void checkMovieTimeInResponse(String strMovieTime, String strMovieName) {
        movieDataModel.checkMovieTimeInResponse(strMovieTime, strMovieName);
    }



    public String getAllMoviesOriginal() {
        //Get all movies
        String allMovies = given().when().get("http://localhost:5001/api/movies").print();
        System.out.println(allMovies);
        return allMovies;
    }

    public void deleteMovieFromAPI(String movieName) {
        movieDataModel.deleteMovieInAPI( movieName);
    }

    public void checkDeletedMovieFromAPI(String movieName) throws InterruptedException {
        movieDataModel.checkDeletedMovieTimeInResponse(movieName);
    }
}
