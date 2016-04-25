package factoryTest.playerFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.player.Player;
import factories.playerFactory.PlayerFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class PlayerFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("PlayerName","Sive");
        stringMap.put("PlayerAge", "19");
        stringMap.put("PlayerSurname", "Tsipa");

        Player player = PlayerFactory.getPlayer(stringMap);
        Assert.assertEquals("Sive",player.getPlayerName());
        Assert.assertEquals("19",player.getPlayerAge());
        Assert.assertEquals("Tsipa",player.getPlayerSurname());

    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("PlayerName","Sive");
        stringMap.put("PlayerAge","19");
        stringMap.put("PlayerSurname","Tsipa");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("PlayerName","Miracle");
        stringUpdateMap.put("PlayerAge","22");
        stringUpdateMap.put("PlayerSurname","Toppe");

        Player player = PlayerFactory.getPlayer(stringMap);

        Player newPlayer = new Player.Builder()
                .copy(player)
                .PlayerAge(stringUpdateMap.get("PlayerName"))
                .PlayerAge(stringUpdateMap.get("PlayerAge"))
                .PlayerSurname(stringUpdateMap.get("PlayerSurname"))
                .build();

        Assert.assertEquals("Miracle",newPlayer.getPlayerName());
        Assert.assertEquals("22", newPlayer.getPlayerAge());
        Assert.assertEquals("Toppe",newPlayer.getPlayerSurname());
    }
}
