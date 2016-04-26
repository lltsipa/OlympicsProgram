package repositoryTest.playerTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.player.Nationality;
import repository.player.NationalityRepository;
import repository.player.impl.NationalityRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class NationalityRepositoryTest extends AndroidTestCase {
    public static final String TAG = " NATIONALITY TEST ";
    private Long id;

    NationalityRepository repo = new NationalityRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        Nationality nationalityCreate = new Nationality.Builder()
                .Continent("Africa")
                .Country("Niger")
                .build();
        Nationality nationalityInserted = repo.save(nationalityCreate);
        id = nationalityInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ",nationalityInserted);

        //READ ALL
        Set<Nationality> nationalitySet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ", nationalitySet.size() > 0);

        //READ
        Nationality nationalityRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ", nationalityRead);
        //UPDATE
        Nationality nationalityUpdate = new Nationality.Builder()
                .copy(nationalityRead)
                .Continent("Europe")
                .Country("Belguim")
                .build();
        repo.update(nationalityRead);
        Nationality newNationality = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newNationality);

        //DELETE
        repo.delete(nationalityUpdate);
        Nationality nationalityDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ",nationalityDeleted);
    }
}
