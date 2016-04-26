package repositoryTest.sportTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.sport.Venue;
import repository.sport.VenueRepository;
import repository.sport.impl.VenueRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class VenueRepositoryTest extends AndroidTestCase {
    public static final String TAG = " VENUE TEST ";
    private Long id;

    VenueRepository repo = new VenueRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        Venue venueCreate = new Venue.Builder()
                .setVenue("Belguim")
                .build();
        Venue venueInserted = repo.save(venueCreate);
        id = venueInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ", venueInserted);

        //READ ALL
        Set<Venue> venueSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ",venueSet.size() > 0);

        //READ
        Venue venueRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ",venueRead);

        //UPDATE
        Venue venueUpdate  = new Venue.Builder()
                .copy(venueRead)
                .setVenue("Spain")
                .build();
        repo.update(venueUpdate);
        Venue newVenue = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", "Spain", newVenue);

        //DELETE
        repo.delete(venueUpdate);
        Venue venueDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", venueDeleted);
    }
}
