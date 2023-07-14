import java.util.*;

/**
 * A Passive Data Structure (PDS) to represent rater data through ArrayList.
 * @author Swastik Kulshreshtha
 * @version 1.0.1
 */
public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for (Rating myRating : myRatings) {
            if (myRating.getItem().equals(item)) {
                return true;
            }
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for (Rating myRating : myRatings) {
            if (myRating.getItem().equals(item)) {
                return myRating.getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (Rating myRating : myRatings) {
            list.add(myRating.getItem());
        }
        return list;
    }
}
