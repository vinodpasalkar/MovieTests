package net.webtestautomation.dataModel;

import com.google.gson.Gson;
import net.thucydides.core.annotations.Step;
import net.webtestautomation.movieAPIClasses.DataItem;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import net.webtestautomation.movieAPIClasses.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class MovieDataModel {

    HttpResponse httpResponse;
    private static String responseString;
    private Response movieResponse;

    private List<DataItem> movieItems;
    private CloseableHttpClient httpClient;
    private int httpStatus;
    private Gson gson;

    public MovieDataModel() throws IOException {
        httpClient = HttpClients.createDefault();
        gson = new Gson();
    }

    @Step("Step {0}")
    public void generateStep(String s){
    }

    public Response getAllMoviesAPI() {
        //Get all movies
        String allMovies = given().when().get("http://localhost:5001/api/movies").print();
        System.out.println("Get all movies : " + allMovies);
        this.movieResponse = gson.fromJson(allMovies, Response.class);
        return movieResponse;
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

    private String executeRequestAndGetResponse(String url) {
        HttpGet request = new HttpGet(url);
        try {
            httpResponse = httpClient.execute(request);
            this.httpStatus = httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            responseString = convertResponseToString(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;

    }

    private String executeRequestAndPostResponse(String url) {
        HttpPost request = new HttpPost(url);
        try {
            httpResponse = httpClient.execute(request);
            this.httpStatus = httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            responseString = convertResponseToString(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public int getHttpStatusCode() {
        return httpStatus;
    }

    public void UpdateMovieRating(String movieName, String movieRating, String movieTime) {

        String movieId = getMovieId(movieName);

        //create single movie
        String payload = "{\n" +
                "    \"name\": \"" + movieName + "\",\n" +
                "    \"rating\": \"" + movieRating + "\",\n" +
                "    \"time\": [\n" +
                "        \"" + movieTime + "\"\n" +
                "    ]\n" +
                "}";

        given().
                baseUri("http://localhost:5001/api").
                contentType("application/json").
                body(payload).
                when().
                put("/movie/"+movieId).
                then().
                log().all().
                assertThat().
                statusCode(200);

    }

    public void postNewMovie(String movieName, String movieRating, String movieTime) {

        //create single movie
        String payload = "{\n" +
                "    \"name\": \"" + movieName + "\",\n" +
                "    \"rating\": \"" + movieRating + "\",\n" +
                "    \"time\": [\n" +
                "        \"" + movieTime + "\"\n" +
                "    ]\n" +
                "}";
        given().
                baseUri("http://localhost:5001/api").
                contentType("application/json").
                body(payload).
                when().
                post("/movie").
                then().
                log().all().
                assertThat().
                statusCode(208);

    }


    public boolean checkMovieInResponse(String movieName) {
        String allMovies = given().when().get("http://localhost:5001/api/movies").print();
        boolean flag;
        if (allMovies.toString().contains(movieName)) {
            flag = true;
            generateStep(movieName + " is present in the all movies response");
        } else {
            flag = true;
            Assert.fail(movieName + " is not present in the all movies response");
        }
        return flag;
    }

    public void checkMovieRatingInResponse(String movieRating, String movieName) {
        getAllMoviesAPI();
        for (int i = 0; i< movieResponse.getData().size(); i++) {
            if (movieResponse.getData().get(i).getName().equalsIgnoreCase(movieName)) {
                if (movieResponse.getData().get(i).getRating()==Integer.parseInt(movieRating)) {
                    generateStep(movieName + "'s rating is updated to " + movieRating);
                } else {
                    Assert.fail(movieName + "'s rating is not updated to " + movieRating);
                }
            }
        }
    }

    public void checkMovieTimeInResponse(String movieTime, String movieName) {
        getAllMoviesAPI();
        for (int i = 0; i< movieResponse.getData().size(); i++) {
            if (movieResponse.getData().get(i).getName().equalsIgnoreCase(movieName)) {
                System.out.println("Value API" + movieResponse.getData().get(i).getTime().get(0));
                System.out.println("Value provided" +movieTime);
                if (movieResponse.getData().get(i).getTime().get(0).equalsIgnoreCase(movieTime)) {
                    generateStep(movieName + "'s time is updated to " + movieTime);
                } else {
                    Assert.fail(movieName + "'s time is not updated to " + movieTime);
                }
            }
        }
    }

    public String getMovieId(String movieName) {
        String movieId = "null";
        for(int i = 0;i< movieResponse.getData().size();i++){
            if (movieResponse.getData().get(i).getName().equalsIgnoreCase(movieName)) {
                movieId = movieResponse.getData().get(i).getId();
                System.out.println("Movie String : " + movieResponse.getData().toString());
                System.out.println("Movie Id : " + movieResponse.getData().get(i).getId());
                System.out.println("movie name : "+ movieResponse.getData().get(i).getName());
                return movieId;
            }
        }
        return movieId;
    }

    public void deleteMovieInAPI(String movieName) {
        String movieId = getMovieId(movieName);

        given().
                baseUri("http://localhost:5001/api").
                contentType("application/json").
                when().
                delete("/movie/"+movieId).
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    public void getMovieDetails(String movieName){
        //Get single movie details
        String movieId = "";
        String movieString = given().queryParam("id",movieId).when().get("http://localhost:5001/api/movie/").print();
        System.out.println("Get single movie details "+movieString);
    }

    public void checkDeletedMovieTimeInResponse(String movieName) throws InterruptedException {
        Thread.sleep(10000);
        getAllMoviesAPI();
        boolean flag= false;
        for (int i = 0; i< movieResponse.getData().size(); i++) {
            if (movieResponse.getData().get(i).getName().equalsIgnoreCase(movieName)) {
                flag= true;
            }
            if (flag) {
                Assert.fail(movieName + "is not deleted from DB");
            } else {
                generateStep(movieName + "is deleted from DB");
            }
        }
    }
}
