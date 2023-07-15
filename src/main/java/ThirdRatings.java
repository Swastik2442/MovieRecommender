import java.util.ArrayList;
import java.io.IOException;

/**
 * Part Three of Recommendation System handling Calculation of Average Rating for Movies using MovieDatabase.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    /**
     * Reads an example Movie and Rater file.
     * @throws IOException
     */
    public ThirdRatings() throws IOException {
        this("src/test/resources/data/ratings.csv");
    }

    /**
     * Reads the provided Movie and Rater file.
     * @throws IOException
     */
    public ThirdRatings(String ratingsFile) throws IOException {
        FirstRatings fr = new FirstRatings();
        this.myRaters = fr.loadRaters(ratingsFile);
    }

    /**
     * Returns the number of Raters read.
     */
    public int getRaterSize() {
        return this.myRaters.size();
    }

    /**
     * Returns the Average Rating of a Movie with the specified ID and minimum ratings.
     * @param id    ID of the Movie
     * @param minimalRaters     Minimum Ratings
     * @return Average Rating of a Movie
     */
    private double getAverageByID(String id, int minimalRaters) {
        double totalRatings = 0.0;
        int totalRaters = 0;
        for (Rater i : this.myRaters) {
            if (i.hasRating(id)) {
                totalRatings += i.getRating(id);
                totalRaters++;
            }
        }
        if (totalRaters >= minimalRaters) return totalRatings / totalRaters;
        else return 0.0;
    }

    /**
     * Returns the Average Ratings of the provided Movies with atleast the specified minimum ratings.
     * @param minimalRaters     Minimum Ratings
     * @return ArrayList containing the Movie ID along with Average Rating
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (String movie : myMovies) {
            double avgRating = this.getAverageByID(movie, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }

    /**
     * Returns the Average Ratings of the provided Movies with atleast the specified minimum ratings and passing the specified Filter.
     * @param minimalRaters Minimum Ratings
     * @param filterCriteria Filter
     * @return ArrayList containing the Movie ID along with Average Rating
     */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (String movie : myMovies) {
            double avgRating = this.getAverageByID(movie, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movie, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
}
