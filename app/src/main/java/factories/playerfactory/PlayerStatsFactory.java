package factories.playerfactory;

import java.util.Map;

import domain.player.PlayerStats;

/**
 * Created by lodz on 2016/04/18.
 */
public class PlayerStatsFactory {
    public static PlayerStats getPlayerStats(String caps,String gamesLost,String gamesStarted,String gamesWon){
        PlayerStats playerStats = new PlayerStats.Builder()
                .Caps(caps)
                .GamesLost(gamesLost)
                .GamesStarted(gamesStarted)
                .GamesWon(gamesWon)
                .build();
        return playerStats;
    }
}
