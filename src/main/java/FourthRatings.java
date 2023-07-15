import java.util.ArrayList;
import java.util.Collections;

/**
 * Part Four of the Recommendation System handling the Calculation of Average and Similar Ratings for Movies through MovieDatabase and RaterDatabase.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class FourthRatings {

    /**
     * Returns the Average Rating of a Movie with the specified ID and minimum ratings.
     * @param id    ID of the Movie
     * @param minimalRaters     Minimum Ratings
     * @return Average Rating of a Movie
     */
    private double getAverageByID(String id, int minimalRaters) {
        double totalRatings = 0.0;
        int totalRaters = 0;
        for (Rater i : RaterDatabase.getRaters()) {
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

    /**
     * @param r1 Rater 1
     * @param r2 Rater 2
     * @return Dot Product of the Rating of the Movies both the Raters have rated.
     */
    private double dotProduct(Rater r1, Rater r2) {
        double ans = 0.0;
        for (String item : r1.getItemsRated()) {
            double rating1 = r1.getRating(item) - 5.0;
            if (r2.hasRating(item)) {
                double rating2 = r2.getRating(item) - 5.0;
                ans += rating1 * rating2;
            }
        }
        return ans;
    }

    /**
     * Returns the Similarity Value of the provided Rater with all other Raters available.
     * @param id Rater ID
     * @return ArrayList of Rating objects containing the Rater ID along with Similarity Value.
     */
    private ArrayList<Rating> getSimilarities(String id) {
        Rater currentRater = RaterDatabase.getRater(id);
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> ans = new ArrayList<Rating>();
        for (Rater rater : raters) {
            String rID = rater.getID();
            if (!rID.equals(id)) {
                double similarity = this.dotProduct(currentRater, rater);
                if (similarity >= 0.0) {
                    Rating rating = new Rating(rID, similarity);
                    ans.add(rating);
                }
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }

    /**
     * Returns the Movies that have atleast minimalRaters who are one of the top numSimilarRaters similar raters to rater with the provided ID, and also pass the provided filter.
     * @param id Rater ID
     * @param numSimilarRaters No. of Similar Raters
     * @param minimalRaters No. of Minimum Ratings for the Movies
     * @param filter Filter
     * @return ArrayList of Rating containing the Movie ID and Weighted Average Rating.
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter) {
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<Rating> raters = this.getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filter);

        int rSize = raters.size();
        if (rSize > numSimilarRaters) raters.subList(numSimilarRaters, rSize).clear();

        for (String movie : movies) {
            int ratedBy = 0;
            double weightedRating = 0.0;
            for (Rating rs : raters) {
                Rater rater = RaterDatabase.getRater(rs.getItem());
                if (rater.hasRating(movie)) {
                    ratedBy++;
                    weightedRating += rs.getValue() * rater.getRating(movie);
                }
            }
            if (ratedBy >= minimalRaters) {
                Rating movieRating = new Rating(movie, weightedRating/ratedBy);
                ans.add(movieRating);
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }

    /**
     * Returns the Movies that have atleast minimalRaters who are one of the top numSimilarRaters similar raters to rater with the provided ID.
     * @param id Rater ID
     * @param numSimilarRaters No. of Similar Raters
     * @param minimalRaters No. of Minimum Ratings for the Movies
     * @return ArrayList of Rating containing the Movie ID and Weighted Average Rating.
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return this.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
}
