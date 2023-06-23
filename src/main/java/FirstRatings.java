import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.apache.commons.csv.*;

/**
 * Part One of Recommendation System handling Loading of Movies and Ratings.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class FirstRatings {
    public static void main(String[] args) {
        FirstRatings obj = new FirstRatings();
        try {
            obj.testLoadMovies();
            obj.testLoadRaters();
        } catch (IOException e) {
            System.out.println("Path sahi kar bhai");
        }
    }

    /**
     * Loads Movies' Data from specified CSV File.
     * @param filename      Name/Path of the CSV File
     * @return ArrayList containing Movie objects
     * @throws IOException
     */
    public ArrayList<Movie> loadMovies(String filename) throws IOException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Path path = Paths.get(filename);
        Reader reader = Files.newBufferedReader(path);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord record : parser) {
            String id = record.get(0);
            String title = record.get(1);
            String year = record.get(2);
            String country = record.get(3);
            String genres = record.get(4);
            String director = record.get(5);
            int minutes = Integer.parseInt(record.get(6));
            String poster = record.get(7);
            Movie i = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(i);
        }
        return movies;
    }

    /**
     * Loads Raters' Data from specified CSV File.
     * @param filename      Name/Path of the CSV File
     * @return ArrayList containing Rater objects
     * @throws IOException
     */
    public ArrayList<Rater> loadRaters(String filename) throws IOException {
        HashMap<String, Rater> raters = new HashMap<String, Rater>();
        Path path = Paths.get(filename);
        Reader reader = Files.newBufferedReader(path);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord record : parser) {
            String raterID = record.get(0);
            String movieID = record.get(1);
            double rating = Double.parseDouble(record.get(2));
            long time = Long.parseLong(record.get(3));
            Rater i = raters.get(raterID);
            if (i == null) i = new Rater(raterID);
            i.addRating(movieID, rating);
            raters.put(raterID, i);
        }
        return new ArrayList<>(raters.values());
    }

    void testLoadMovies() throws IOException {
        HashMap<String, ArrayList<Movie>> dirs = new HashMap<String, ArrayList<Movie>>();
        int comedy = 0, dedsauplus = 0;
        int maxMovies = 0;
        String maxMoviesDirector = "";
        String path = "src/test/resources/data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = this.loadMovies(path);
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
        ArrayList<Rater> raters = this.loadRaters(path);
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
