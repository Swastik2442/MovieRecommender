import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public static void main(String[] args) {
        MovieRunnerSimilarRatings obj = new MovieRunnerSimilarRatings();
//        obj.printSimilarRatings();
//        obj.printSimilarRatingsByGenre();
//        obj.printSimilarRatingsByDirector();
//        obj.printSimilarRatingsByGenreAndMinutes();
        obj.printSimilarRatingsByYearAfterAndMinutes();
    }

    void initialize() {
        String movieFile = "src/test/resources/data/ratedmoviesfull.csv";
        String ratingFile = "src/test/resources/data/ratings.csv";
        try {
            MovieDatabase.initialize(movieFile);
            RaterDatabase.initialize(ratingFile);
        } catch (IOException e) {
            System.out.println("File nahi khul rahi bhai");
            System.exit(1);
        }
        System.out.println("No. of Movies read: " + MovieDatabase.size());
        System.out.println("No. of Raters read: " + RaterDatabase.size());
        System.out.println();
    }

    void printAverageRatings() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(i.getItem()));
        }
    }

    void printAverageRatingsByYear() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 20;
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(2000));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getYear(movieID) + " " + MovieDatabase.getTitle(movieID));
        }
    }

    void printAverageRatingsByGenre() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 20;
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, new GenreFilter("Comedy"));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getGenres(movieID));
        }
    }

    void printAverageRatingsByMinutes() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 5;
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(105, 135));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getMinutes(movieID) + " minutes");
        }
    }

    void printAverageRatingsByDirectors() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 4;
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getDirector(movieID));
        }
    }

    void printAverageRatingsByYearAfterAndGenre() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 8;

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " : " + MovieDatabase.getYear(movieID) + " - " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getGenres(movieID));
        }
    }

    void printAverageRatingsByDirectorsAndMinutes() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 3;

        AllFilters af = new AllFilters();
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        af.addFilter(new MinutesFilter(90, 180));

        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("Movies with atleast " + minimalRaters + " ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating i : avgRatings) {
            String movieID = i.getItem();
            System.out.println(i.getValue() + " : " + MovieDatabase.getTitle(movieID) + " - " + MovieDatabase.getDirector(movieID) + " - " + MovieDatabase.getMinutes(movieID) + " minutes");
        }
    }

    void printSimilarRatings() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings("71", 20, 5);
        for (Rating i : similarRatings) {
            System.out.println(MovieDatabase.getTitle(i.getItem()) + " " + i.getValue());
        }
    }

    void printSimilarRatingsByGenre() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for (Rating i : similarRatings) {
            String movie = i.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " " + i.getValue());
            System.out.println("\t" + MovieDatabase.getGenres(movie));
        }
    }

    void printSimilarRatingsByDirector() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating i : similarRatings) {
            String movie = i.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " " + i.getValue() + " " + MovieDatabase.getDirector(movie));
        }
    }

    void printSimilarRatingsByGenreAndMinutes() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Drama"));
        af.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("168", 10, 3, af);
        for (Rating i : similarRatings) {
            String movie = i.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " " + i.getValue() + " " + MovieDatabase.getMinutes(movie) + " minutes");
            System.out.println("\t" + MovieDatabase.getGenres(movie));
        }
    }

    void printSimilarRatingsByYearAfterAndMinutes() {
        this.initialize();
        FourthRatings fr = new FourthRatings();
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1975));
        af.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("314", 10, 5, af);
        for (Rating i : similarRatings) {
            String movie = i.getItem();
            System.out.println(MovieDatabase.getTitle(movie) + " " + i.getValue() + " - " + MovieDatabase.getYear(movie) + " " + MovieDatabase.getMinutes(movie) + " minutes");
        }
    }
}
