package factories.playerFactory;

import java.util.Map;

import domain.player.Player;

/**
 * Created by lodz on 2016/04/18.
 */
public class PlayerFactory {
    public static Player getPlayer(Map<String, String> stringIntegerMap)
    {
        Player player = new Player.Builder()
                .PlayerName(stringIntegerMap.get("PlayerName"))
                .PlayerAge(stringIntegerMap.get("PlayerAge"))
                .PlayerSurname(stringIntegerMap.get("PlayerSurname"))
                .build();
        return player;
    }
}
