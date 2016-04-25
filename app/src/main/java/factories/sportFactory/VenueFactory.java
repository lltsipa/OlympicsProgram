package factories.sportFactory;

import java.util.Map;

import domain.sport.Venue;

/**
 * Created by lodz on 2016/04/18.
 */
public class VenueFactory {
    public static Venue getVenue(Map<String, String> stringMap)
    {
        Venue venue = new Venue.Builder()
                .setVenue(stringMap.get("Venue"))
                .build();
                return venue;
    }
}
