import java.io.*;
import java.util.*;
import java.nio.file.*;

import org.apache.commons.csv.*;

/**
 * A Database class that can be used to provide with rater information upon feeding with rater data.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;

    /**
     * Initializes the Raters HashMap if empty.
     */
    private static void initialize() {
		if (ourRaters == null) ourRaters = new HashMap<String,Rater>();
	}

    /**
     * Initializes the Database with the provided CSV file.
     * @param filename Name of the File
     * @throws IOException
     */
    public static void initialize(String filename) throws IOException {
 		initialize();
        addRatings(filename);
 	}

    /**
     * Reads the provided CSV File and adds the Ratings to the Database.
     * @param filename Name of the File
     * @throws IOException
     */
    public static void addRatings(String filename) throws IOException {
        initialize();
        Path path = Paths.get(filename);
        Reader reader = Files.newBufferedReader(path);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for(CSVRecord rec : parser) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }

    /**
     * Adds the provided Rating to the Database.
     * @param raterID Rater ID
     * @param movieID Movie ID
     * @param rating Movie Rating
     */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
         }
         rater.addRating(movieID,rating);
    }

    /**
     * @param id Rater ID
     * @return Rater with the provided ID.
     */
    public static Rater getRater(String id) {
    	initialize();
    	return ourRaters.get(id);
    }

    /**
     * @return ArrayList of all the Raters available in the Database.
     */
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	return list;
    }

    /**
     * @return No. of Raters available in the Database.
     */
    public static int size() {
	    return ourRaters.size();
    }
}
