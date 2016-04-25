package factories.playerFactory;

import java.util.Map;

import domain.player.PlayerStats;

/**
 * Created by lodz on 2016/04/18.
 */
public class PlayerStatsFactory {
    public static PlayerStats getPlayerStats(Map<String, String> stringMap){
        PlayerStats playerStats = new PlayerStats.Builder()
                .Caps(stringMap.get("Caps"))
                .GamesLost(stringMap.get("GamesLost"))
                .GamesStarted(stringMap.get("GamesStarted"))
                .GamesWon(stringMap.get("GamesWon"))
                .build();
        return playerStats;
    }
}
