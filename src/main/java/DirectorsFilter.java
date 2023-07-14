import java.util.ArrayList;

public class DirectorsFilter implements Filter {
    private ArrayList<String> directorsList;

    public DirectorsFilter(String directors) {
        int startIndex = 0;
        int commaIndex = directors.indexOf(',', startIndex);
        directorsList = new ArrayList<String>();
        while (commaIndex != -1) {
            directorsList.add(directors.substring(startIndex, commaIndex));
            startIndex = commaIndex + 1;
            commaIndex = directors.indexOf(',', startIndex);
        }
        commaIndex = directors.length() - 1;
        directorsList.add(directors.substring(startIndex));
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        for (String director : directorsList) {
            if (movieDirectors.contains(director)) return true;
        }
        return false;
    }
}
