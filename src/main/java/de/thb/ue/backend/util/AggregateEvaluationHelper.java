package de.thb.ue.backend.util;

/**
 * Created by Bartz, Tobias @Tobi-PC on 10.12.2016 at 14:23.
 */
public class AggregateEvaluationHelper {

    private final float rating;
    private final int numberOfRatings;

    public AggregateEvaluationHelper(double rating, long numberOfRatings) {
        this.rating = (float) rating;
        this.numberOfRatings = (int) numberOfRatings;
    }

    public float getRating() {
        return rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    @Override
    public String toString() {
        return "Rating: " + rating + "; numberOfRatings: " + numberOfRatings;
    }
}
