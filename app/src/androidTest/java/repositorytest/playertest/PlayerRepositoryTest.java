package repositorytest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.player.Player;
import repository.player.PlayerRepository;
import repository.player.impl.PlayerRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class PlayerRepositoryTest extends AndroidTestCase {
    public static final String TAG = " PLAYER TEST ";
    private Long id;

    PlayerRepository repo = new PlayerRepositoryImpl(this.getContext());
    public void testCreateReadUpdateDelete()
    {
        //CREATE
        Player playerCreate = new Player.Builder()
                .PlayerName("Oliver")
                .PlayerSurname("Twist")
                .PlayerAge("20")
                .build();
        Player playerInserted = repo.save(playerCreate);
        id = playerInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ");

        //READ ALL
        Set<Player> playerSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ", playerSet.size() > 0);

        //READ
        Player playerRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ", playerRead);

        //UPDATE
        Player playerUpdate = new Player.Builder()
                .copy(playerRead)
                .PlayerAge("25")
                .build();
        repo.update(playerUpdate);
        Player newplayer = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newplayer);

        //DELETE
        repo.delete(playerUpdate);
        Player playerDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", playerDeleted);
    }
}
