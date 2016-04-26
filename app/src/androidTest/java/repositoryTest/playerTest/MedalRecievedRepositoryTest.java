package repositoryTest.playerTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.player.MedalRecieved;
import repository.player.MedalRecievedRepository;
import repository.player.impl.MedalRecievedRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class MedalRecievedRepositoryTest extends AndroidTestCase {
    public static final String TAG = " MEDALRECIEVED TEST ";
    private Long id;

    MedalRecievedRepository repo = new MedalRecievedRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        MedalRecieved medalRecievedCreate = new MedalRecieved.Builder()
                .setNextPosition(2)
                .build();
        MedalRecieved insertedEntity = repo.save(medalRecievedCreate);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ALL
        Set<MedalRecieved> medalRecievedSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ", medalRecievedSet.size() > 0);

        //READ
        MedalRecieved medalRecievedRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ENTITY ", medalRecievedRead);

        //UPDATE
        MedalRecieved medalRecievedUpdate = new MedalRecieved.Builder()
                .copy(medalRecievedRead)
                .setNextPosition(1)
                .build();
        repo.update(medalRecievedUpdate);
        MedalRecieved medalRecieved = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", medalRecieved);

        //DELETE
        repo.delete(medalRecievedUpdate);
        MedalRecieved medalRecievedDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ",medalRecievedDeleted);
    }

}
