package repositorytest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.player.Catagory;
import repository.player.CatagoryRepository;
import repository.player.impl.CatagoryRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class CatagoryRepositoryTest extends AndroidTestCase{
    public static final String TAG = " CATAGORY TEST ";
    private Long id;
    CatagoryRepository repo = new CatagoryRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete() throws Exception {

        //CREATE
        Catagory catagoryCreate = new Catagory.Builder()
                .Men("Men")
                .Women("Women")
                .build();
        Catagory insertedEntity = repo.save(catagoryCreate);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ALL
        Set<Catagory> catagorySet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ", catagorySet.size() > 0);

        //READ
        Catagory catagoryRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ENTITY", catagoryRead);

        //UPDATE
        Catagory catagoryUpdate = new Catagory.Builder()
                .copy(catagoryRead)
                .setId(id)
                .Men("Man")
                .build();
        repo.update(catagoryUpdate);
        Catagory newCatagoty = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY ", "Man", newCatagoty.getMen());

        //DELETE
        repo.delete(catagoryUpdate);
        Catagory catagoryDelete = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ",catagoryDelete);
    }


}
