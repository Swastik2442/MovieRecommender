import java.util.ArrayList;

/**
 * An Interface defining the Structure of Rater classes.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public interface Rater {
    /**
     * Adds rating to the specified item in the dataset.
     * @param item Item ID
     * @param rating Rating Value
     */
    void addRating(String item, double rating);

    /**
     * Checks if rating for the specified item exists.
     * @param item Item ID
     * @return boolean
     */
    boolean hasRating(String item);

    /**
     * @return Rater ID
     */
    String getID();

    /**
     * Returns the Rating for the specified Item.
     * @param item Item ID
     * @return Rating
     */
    double getRating(String item);

    /**
     * @return Number of Ratings in the dataset.
     */
    int numRatings();

    /**
     * Returns the IDs of the Items in the dataset.
     * @return ArrayList of IDs
     */
    ArrayList<String> getItemsRated();
}
