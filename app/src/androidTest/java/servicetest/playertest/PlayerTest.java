package servicetest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.player.Player;
import factories.playerfactory.PlayerFactory;
import services.player.impl.PlayerServiceImpl;

/**
 * Created by lodz on 2016/06/06.
 */
public class PlayerTest extends AndroidTestCase {

    PlayerServiceImpl playerService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        playerService = PlayerServiceImpl.getInstance();
    }

    public void testAddPlayer() throws Exception {
        Player player = PlayerFactory.getPlayer("John","Sivy","17");
        playerService.addPlayer(App.getAppContext(),player);
     //   playerService.handleAddPlayer(player);

        Assert.assertNotNull("Testing add player: ", playerService);

    }
}
