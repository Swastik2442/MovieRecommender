import java.util.ArrayList;
import java.io.IOException;

/**
 * Part Two of Recommendation System handling Calculation of Average Rating for Movies.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    /**
     * Reads an example Movie and Rater file.
     * @throws IOException
     */
    public SecondRatings() throws IOException {
        this("src/test/resources/data/ratedmoviesfull.csv", "src/test/resources/data/ratings.csv");
    }

    /**
     * Reads the provided Movie and Rater file.
     * @throws IOException
     */
    public SecondRatings(String movieFile, String ratingsFile) throws IOException {
        FirstRatings fr = new FirstRatings();
        this.myMovies = fr.loadMovies(movieFile);
        this.myRaters = fr.loadRaters(ratingsFile);
    }

    /**
     * Returns the number of Movies read.
     */
    public int getMovieSize() {
        return this.myMovies.size();
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
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (Movie i : this.myMovies) {
            String movieID = i.getID();
            double avgRating = this.getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }

    /**
     * Returns the Title of the Movie with the specified ID or "N/A" if not found.
     * @param id    ID of the Movie
     * @return Title of the Movie
     */
    public String getTitle(String id) {
        String title = "N/A";
        for (Movie i : this.myMovies) {
            if (id.equals(i.getID())) {
                title = i.getTitle();
                break;
            }
        }
        return title;
    }

    /**
     * Returns the ID of the Movie with the specified title or "N/A" if not found.
     * @param title     Title of the Movie
     * @return ID of the Movie
     */
    public String getID(String title) {
        String id = "N/A";
        for (Movie i : this.myMovies) {
            if (title.equals(i.getTitle())) {
                id = i.getID();
                break;
            }
        }
        return id;
    }
}
