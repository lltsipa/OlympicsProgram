package repositorytest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.player.PlayerStats;
import repository.player.PlayerStatsRepository;
import repository.player.impl.PlayerStatsRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class PlayerStatsRepositoryTest extends AndroidTestCase {
    public static final String TAG = " PLAYERSTATS TEST ";
    private Long id;

    PlayerStatsRepository repo = new PlayerStatsRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        PlayerStats playerStatsCreate = new PlayerStats.Builder()
                .Caps("20")
                .GamesStarted("14")
                .GamesWon("12")
                .GamesLost("6")
                .build();
        PlayerStats playerStatsInserted = repo.save(playerStatsCreate);
        id = playerStatsInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ", playerStatsInserted);

        //READ ALL
        Set<PlayerStats> playerStatsSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ",playerStatsSet.size() > 0);

        //READ
        PlayerStats playerStatsRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ", playerStatsRead);

        //UPDATE
        PlayerStats playerStatsUpdate = new PlayerStats.Builder()
                .copy(playerStatsRead)
                .Caps("21")
                .GamesWon("13")
                .build();
        repo.update(playerStatsUpdate);
        PlayerStats newPlayerStats = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newPlayerStats);
        //DELETE
        repo.delete(playerStatsRead);
        PlayerStats playerStatsDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", playerStatsDeleted);
    }

}
