package factories.playerfactory;

import java.util.Map;

import domain.player.MedalRecieved;

/**
 * Created by lodz on 2016/04/18.
 */
public class MedalRecievedFactory {
    public static MedalRecieved getMedalRecieved(int position) {

        MedalRecieved medalRecieved = new MedalRecieved.Builder()
                .setNextPosition(position)
                .build();
        return medalRecieved;
    }
}
