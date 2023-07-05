import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class test_FirstRatings {
    public static void main(String[] args) {
        test_FirstRatings obj = new test_FirstRatings();
        try {
            obj.testLoadMovies();
            obj.testLoadRaters();
        } catch (IOException e) {
            System.out.println("Path sahi kar bhai");
        }
    }
    
    void testLoadMovies() throws IOException {
        HashMap<String, ArrayList<Movie>> dirs = new HashMap<String, ArrayList<Movie>>();
        int comedy = 0, dedsauplus = 0;
        int maxMovies = 0;
        String maxMoviesDirector = "";
        String path = "src/test/resources/data/ratedmoviesfull.csv";
        FirstRatings obj = new FirstRatings();
        ArrayList<Movie> movies = obj.loadMovies(path);
        System.out.println("Total Movies: " + movies.size());
        for (Movie i : movies) {
//            System.out.println(i);
            if (i.getGenres().contains("Comedy")) comedy++;
            if (i.getMinutes() > 150) dedsauplus++;
            for (String dir : i.getDirector().split(",")) {
                ArrayList<Movie> mov = dirs.get(dir.trim());
                if (mov == null) mov = new ArrayList<Movie>();
                mov.add(i);
                dirs.put(dir.trim(), mov);
            }
        }
        System.out.println();
        System.out.println("Comedy Movies: " + comedy);
        System.out.println("150+ Minutes Movies: " + dedsauplus);
//        System.out.println("Movies per Director:");
        for (String i : dirs.keySet()) {
//            System.out.print(i + " : ");
            ArrayList<Movie> mov = dirs.get(i);
            if (mov.size() > maxMovies) {
                maxMovies = mov.size();
                maxMoviesDirector = i;
            }
//            for (Movie j : mov) {
//                System.out.print(j + ", ");
//            }
//            System.out.println("\b\b");
        }
        System.out.println("Maximum " + maxMovies + " Movies made by " + maxMoviesDirector);
    }

    void testLoadRaters() throws IOException {
        int maxRatings = 0;
        ArrayList<Rater> maxRaters = new ArrayList<Rater>();
        HashMap<String, ArrayList<Rating>> moviesRating = new HashMap<String, ArrayList<Rating>>();

        String path = "src/test/resources/data/ratings.csv";
        FirstRatings obj = new FirstRatings();
        ArrayList<Rater> raters = obj.loadRaters(path);
        System.out.println("Total Raters: " + raters.size());
        for (Rater i : raters) {
//            if (i.getID().equals("193")) // Add for checking for one Rater
//            System.out.println("rater_" + i.getID() + " rated " + i.numRatings() + " movies.");
            if (i.numRatings() > maxRatings) {
                maxRatings = i.numRatings();
                maxRaters = new ArrayList<Rater>();
            }
            if (i.numRatings() == maxRatings) maxRaters.add(i);
            for (String j : i.getItemsRated()) {
                ArrayList<Rating> ratings = moviesRating.get(j);
                if (ratings == null) ratings = new ArrayList<Rating>();
                Rating movRating = new Rating(j, i.getRating(j));
                ratings.add(movRating);
                moviesRating.put(j, ratings);
            }
        }
        System.out.println("Maximum Ratings by any Rater: " + maxRatings);
        System.out.print("Raters: ");
        for (Rater i : maxRaters) {
            System.out.print("rater_" + i.getID() + ", ");
        }
        System.out.println("\b\b");

        System.out.println("\nTotal Movies Rated: " + moviesRating.size());
//        System.out.println("Ratings per Movie: ");
        for (String i : moviesRating.keySet()) {
//            if (i.equals("1798709")) System.out.println(i + " got " + moviesRating.get(i).size() + " ratings");    // Add for checking for one Movie
//            System.out.print("movie_" + i + " : ");
//            for (Rating j : moviesRating.get(i)) {
//                System.out.print(j.getValue() + ", ");
//            }
//            System.out.println("\b\b");

        }
    }
}
