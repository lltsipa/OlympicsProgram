package servicetest.sporttest;

import android.test.AndroidTestCase;

import org.junit.Assert;

import conf.util.App;
import domain.sport.Venue;
import factories.sportfactory.VenueFactory;
import services.sport.impl.VenueServiceImpl;

/**
 * Created by lodz on 2016/06/06.
 */
public class VenueTest extends AndroidTestCase {

    VenueServiceImpl venueService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        venueService = VenueServiceImpl.getInstance();
    }

    public void testAddVenue() throws Exception {
        Venue venue = VenueFactory.getVenue("Moses Mabhida Stadium");
        venueService.addVenue(App.getAppContext(),venue);

        Assert.assertNotNull("Testing Venue Service: ", venueService);
    }
}
