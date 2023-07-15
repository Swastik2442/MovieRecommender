import java.io.IOException;
import java.util.ArrayList;

/**
 * Part Five of the Recommendation System that displays the Recommendation on a HTML page.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> moviesRated =  fr.getSimilarRatingsByFilter("65", 20, 10, new GenreFilter("Action"));
        ArrayList<String> movies = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            System.out.println(MovieDatabase.getTitle(moviesRated.get(i).getItem()));
            movies.add(moviesRated.get(i).getItem());
        }
        return movies;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> movies = fr.getSimilarRatings(webRaterID, 10, 5);
        int total = 10;
        if (movies.size() < total) total = movies.size();
        if (total <= 0) {
            System.out.println("No Movies were found.");
            return;
        }

        System.out.println("<table>");
        System.out.println("<tr>");
        System.out.println("<th>Movie No.</th>");
        System.out.println("<th>Movie</th>");
        System.out.println("<th>Movie Details</th>");
        System.out.println("</tr>");
        for (int i = 1; i < total + 1; i++) {
            String movie = movies.get(i-1).getItem();
            System.out.println("<tr>");
            System.out.println("<td>" + i + "</td>");
            System.out.println("<td><img src='" + MovieDatabase.getPoster(movie) + "' alt='" + MovieDatabase.getTitle(movie) + "' /></td>");
            System.out.println(
                    "<td><ul>" +
                    "<li>Released In: " + MovieDatabase.getYear(movie) + "</li>" +
                    "<li>Director(s): " + MovieDatabase.getDirector(movie) + "</li>" +
                    "<li>Genre(s): " + MovieDatabase.getGenres(movie) + "</li>" +
                    "<li>Runs for: " + MovieDatabase.getMinutes(movie) + " minutes</li>" +
                    "</ul></td>"
            );
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }
}
