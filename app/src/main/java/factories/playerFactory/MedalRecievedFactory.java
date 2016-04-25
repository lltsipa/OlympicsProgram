package factories.playerFactory;

import java.util.Map;

import domain.player.MedalRecieved;

/**
 * Created by lodz on 2016/04/18.
 */
public class MedalRecievedFactory {
    public static MedalRecieved getMedalRecieved(Map<String, Integer> stringIntegerMap) {

        MedalRecieved medalRecieved = new MedalRecieved.Builder()
                .setNextPosition(stringIntegerMap.get("Position"))
                .build();
        return medalRecieved;
    }
}
