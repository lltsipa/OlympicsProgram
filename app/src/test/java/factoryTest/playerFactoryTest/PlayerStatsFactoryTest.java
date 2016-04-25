package factoryTest.playerFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.player.PlayerStats;
import factories.playerFactory.PlayerStatsFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class PlayerStatsFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Caps", "15");
        stringMap.put("GamesLost", "6");
        stringMap.put("GamesStarted","14");
        stringMap.put("GamesWon","6");

        PlayerStats playerStats = PlayerStatsFactory.getPlayerStats(stringMap);
        Assert.assertEquals("15",playerStats.getCaps());
        Assert.assertEquals("6",playerStats.getGamesLost());
        Assert.assertEquals("14",playerStats.getGamesStarted());
        Assert.assertEquals("6",playerStats.getGamesWon());

    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Caps", "15");
        stringMap.put("GamesLost", "6");
        stringMap.put("GamesStarted","14");
        stringMap.put("GamesWon","6");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("Caps", "16");
        stringUpdateMap.put("GamesLost", "7");
        stringUpdateMap.put("GamesStarted","15");
        stringUpdateMap.put("GamesWon","7");

        PlayerStats playerStats = PlayerStatsFactory.getPlayerStats(stringMap);
        PlayerStats newPlayerStats = new PlayerStats.Builder()
                .copy(playerStats)
                .Caps(stringUpdateMap.get("Caps"))
                .GamesLost(stringUpdateMap.get("GamesLost"))
                .GamesStarted(stringUpdateMap.get("GamesStarted"))
                .GamesWon(stringUpdateMap.get("GamesWon"))
                .build();

        Assert.assertEquals("16", newPlayerStats.getCaps());
        Assert.assertEquals("7", newPlayerStats.getGamesLost());
        Assert.assertEquals("15",newPlayerStats.getGamesStarted());
        Assert.assertEquals("7",newPlayerStats.getGamesWon());


    }
}
