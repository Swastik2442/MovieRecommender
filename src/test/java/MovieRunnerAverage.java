import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public static void main(String[] args) {
        MovieRunnerAverage obj = new MovieRunnerAverage();
        obj.printAverageRatings();
//        obj.getAverageRatingOneMovie();
    }

    void printAverageRatings() {
        String movieFile = "src/test/resources/data/ratedmoviesfull.csv";
        String ratingFile = "src/test/resources/data/ratings.csv";
        SecondRatings sr = null;
        try {
            sr = new SecondRatings(movieFile, ratingFile);
        } catch (IOException e) {
            System.out.println("Path sahi kar bhai");
            System.exit(1);
        }
        System.out.println("No. of Movies read: " + sr.getMovieSize());
        System.out.println("No. of Raters read: " + sr.getRaterSize());
        int minimalRaters = 12;
        ArrayList<Rating> avgRatings = sr.getAverageRatings(minimalRaters);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            System.out.println(i.getValue() + " " + sr.getTitle(i.getItem()));
        }
    }

    void getAverageRatingOneMovie() {
        String movieFile = "src/test/resources/data/ratedmoviesfull.csv";
        String ratingFile = "src/test/resources/data/ratings.csv";
        SecondRatings sr = null;
        try {
            sr = new SecondRatings(movieFile, ratingFile);
        } catch (IOException e) {
            System.out.println("Path sahi kar bhai");
            System.exit(1);
        }
        ArrayList<Rating> avgRatings = sr.getAverageRatings(3);
        String movieTitle = "Vacation";
        String movieID = sr.getID(movieTitle);
        for (Rating i : avgRatings) {
            if (movieID.equals(i.getItem())) {
                System.out.println(i.getValue());
                break;
            }
        }
    }
}
