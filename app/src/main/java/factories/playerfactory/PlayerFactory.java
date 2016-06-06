package factories.playerfactory;

import java.util.Map;
import java.util.UUID;

import domain.player.Player;

/**
 * Created by lodz on 2016/04/18.
 */
public class PlayerFactory {
    public static Player getPlayer(String name, String surname,String age)
    {
        Long id;
       do{
            id = UUID.randomUUID().getMostSignificantBits();
        } while( id >0);
        Player player = new Player.Builder()
                .setId(id)
                .PlayerName(name)
                .PlayerAge(surname)
                .PlayerSurname(age)
                .build();
        return player;
    }
}
