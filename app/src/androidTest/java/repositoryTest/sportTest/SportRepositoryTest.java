package repositoryTest.sportTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.sport.Sport;
import repository.sport.SportRepository;
import repository.sport.impl.SportRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class SportRepositoryTest extends AndroidTestCase {
    public static final String TAG = " SPORT TEST ";
    private Long id;

    SportRepository repo = new SportRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        Sport sportCreate = new Sport.Builder()
                .Sport("Shot Put")
                .build();
        Sport sportInserted = repo.save(sportCreate);
        id = sportInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ", sportInserted);

        //READ ALL
        Set<Sport> sportSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ",sportSet.size() > 0);

        //READ
        Sport sportRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ", sportRead);

        //UPDATE
        Sport sportUpdate = new Sport.Builder()
                .copy(sportRead)
                .Sport("Javelin")
                .build();
        repo.update(sportUpdate);
        Sport newSport = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newSport);

        //DELETE
        repo.delete(sportUpdate);
        Sport sportDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", sportDeleted);
    }
}
