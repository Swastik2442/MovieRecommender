public class MinutesFilter implements Filter {
    private int minMinutes, maxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        int movieMinutes = MovieDatabase.getMinutes(id);
        return (minMinutes <= movieMinutes && movieMinutes <= maxMinutes);
    }
}
