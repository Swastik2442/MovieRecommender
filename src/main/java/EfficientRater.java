import java.util.*;

/**
 * A Passive Data Structure (PDS) to represent rater data through HashMap.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        Rating rating = myRatings.get(item);
        return rating != null;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating rating = myRatings.get(item);
        if (rating != null) return rating.getValue();
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(myRatings.keySet());
    }
}
