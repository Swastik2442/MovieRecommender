import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public static void main(String[] args) {
        MovieRunnerWithFilters obj = new MovieRunnerWithFilters();
//        obj.printAverageRatings();
//        obj.printAverageRatingsByYear();
//        obj.printAverageRatingsByGenre();
//        obj.printAverageRatingsByMinutes();
//        obj.printAverageRatingsByDirectors();
//        obj.printAverageRatingsByYearAfterAndGenre();
        obj.printAverageRatingsByDirectorsAndMinutes();
    }

    ThirdRatings reader() {
        String movieFile = "src/test/resources/data/ratedmoviesfull.csv";
        String ratingFile = "src/test/resources/data/ratings.csv";
        ThirdRatings tr = null;
        try {
            MovieDatabase.initialize(movieFile);
            tr = new ThirdRatings(ratingFile);
        } catch (IOException e) {
            System.out.println("File nahi khul rahi bhai");
            System.exit(1);
        }
        System.out.println("No. of Movies read: " + MovieDatabase.size());
        System.out.println("No. of Raters read: " + tr.getRaterSize());
        return tr;
    }

    void printAverageRatings() {
        ThirdRatings tr = reader();
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(i.getItem()));
        }
    }

    void printAverageRatingsByYear() {
        ThirdRatings tr = reader();
        int minimalRaters = 20;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(2000));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getYear(movieID) + " " + MovieDatabase.getTitle(movieID));
        }
    }

    void printAverageRatingsByGenre() {
        ThirdRatings tr = reader();
        int minimalRaters = 20;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new GenreFilter("Comedy"));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getGenres(movieID));
        }
    }

    void printAverageRatingsByMinutes() {
        ThirdRatings tr = reader();
        int minimalRaters = 5;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(105, 135));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getMinutes(movieID) + " minutes");
        }
    }

    void printAverageRatingsByDirectors() {
        ThirdRatings tr = reader();
        int minimalRaters = 4;
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getDirector(movieID));
        }
    }

    void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = reader();
        int minimalRaters = 8;

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " : " + MovieDatabase.getYear(movieID) + " - " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getGenres(movieID));
        }
    }

    void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = reader();
        int minimalRaters = 3;

        AllFilters af = new AllFilters();
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        af.addFilter(new MinutesFilter(90, 180));

        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " : " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getDirector(movieID) + " - " + MovieDatabase.getMinutes(movieID) + " minutes");
        }
    }
}
