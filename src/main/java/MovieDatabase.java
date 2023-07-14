import java.io.IOException;
import java.util.*;

/**
 * A Database class that can be used to provide with movie information upon feeding with movie data.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    /**
     * Loads the Database with movie data from the provided file.
     * @param moviefile Movie Data file
     * @throws IOException
     */
    public static void initialize(String moviefile) throws IOException {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies(moviefile);
        }
    }

    /**
     * A private initialize method to ensure a non-empty database.
     */
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            try {
                loadMovies("src/test/resources/data/ratedmoviesfull.csv");
            } catch (IOException e) {
                System.out.println("The example file cannot be accessed.");
            }
        }
    }

    /**
     * Loads movie data from the specified file.
     * @param filename Data File Name
     * @throws IOException
     */
    private static void loadMovies(String filename) throws IOException {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    /**
     * Checks whether a movie with specified ID exists in the database.
     * @param id Movie ID
     * @return boolean
     */
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    /**
     * @param id Movie ID
     * @return Year of Release of the Movie
     */
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    /**
     * @param id Movie ID
     * @return Genre(s) of the Movie (Comma-Separated)
     */
    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    /**
     * @param id Movie ID
     * @return Title of the Movie
     */
    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    /**
     * @param id Movie ID
     * @return Movie with the specified ID
     */
    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    /**
     * @param id Movie ID
     * @return URL of the Poster of the Movie
     */
    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    /**
     * @param id Movie ID
     * @return No. of Minutes the Movie runs
     */
    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    /**
     * @param id Movie ID
     * @return Country of Origin of the Movie
     */
    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    /**
     * @param id Movie ID
     * @return Director(s) of the Movie (Comma-Separated)
     */
    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    /**
     * @return No. of Movies in the Database
     */
    public static int size() {
        return ourMovies.size();
    }

    /**
     * Returns an ArrayList of Movie IDs that satisfy the provided Filter.
     * @param f    Filter object
     * @return ArrayList containing the Filtered movies
     */
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }

        return list;
    }

}
