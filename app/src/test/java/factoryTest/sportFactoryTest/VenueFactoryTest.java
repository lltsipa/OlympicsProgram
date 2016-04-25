package factoryTest.sportFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.sport.Venue;
import factories.sportFactory.VenueFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class VenueFactoryTest {
    @Test
    public void testCreate() throws Exception {

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Venue", "Russia");

        Venue venue = VenueFactory.getVenue(stringMap);
        Assert.assertEquals("Russia", venue.getVenue());

    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Venue", "Russia");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("Venue", "Belgium");

        Venue venue = VenueFactory.getVenue(stringMap);
        Venue newVenue = new Venue.Builder()
                .copy(venue)
                .setVenue(stringUpdateMap.get("Venue"))
                .build();
        Assert.assertEquals("Belgium", newVenue.getVenue());
    }
}

