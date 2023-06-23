/**
 * An immutable Passive Data Structure (PDS) to represent rating data.
 * @author Swastik Kulshreshtha
 * @version 1.0.0
 */
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating (String anItem, double aValue) {
        item = anItem;
        value = aValue;
    }

    public String getItem () {
        return item;
    }

    public double getValue () {
        return value;
    }

    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        return Double.compare(value, other.value);

    }
}
