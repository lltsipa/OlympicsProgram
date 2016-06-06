package servicetest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.player.PlayerStats;
import factories.playerfactory.PlayerStatsFactory;
import services.player.PlayerStatsService;
import services.player.impl.PlayerServiceImpl;
import services.player.impl.PlayerStatsServiceImpl;

/**
 * Created by lodz on 2016/06/06.
 */
public class PlayerStatsTest extends AndroidTestCase {

    PlayerStatsServiceImpl playerStatsService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        playerStatsService = PlayerStatsServiceImpl.getInstance();
    }

    public void testAddPlayerStats() throws Exception {
        PlayerStats playerStats = PlayerStatsFactory.getPlayerStats("13","7","11","5");
        playerStatsService.addPlayerStats(App.getAppContext(),playerStats);
     //   playerStatsService.handleStats(playerStats);

        Assert.assertNotNull("Testing player Stats: ", playerStatsService);
    }
}
