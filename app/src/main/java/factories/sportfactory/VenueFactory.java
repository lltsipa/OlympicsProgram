package factories.sportfactory;

import java.util.Map;

import domain.sport.Venue;

/**
 * Created by lodz on 2016/04/18.
 */
public class VenueFactory {
    public static Venue getVenue(String venues)
    {
        Venue venue = new Venue.Builder()
                .setVenue(venues)
                .build();
                return venue;
    }
}
