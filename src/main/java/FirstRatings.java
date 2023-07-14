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
            if (i == null) i = new EfficientRater(raterID);
            i.addRating(movieID, rating);
            raters.put(raterID, i);
        }
        return new ArrayList<>(raters.values());
    }
}
