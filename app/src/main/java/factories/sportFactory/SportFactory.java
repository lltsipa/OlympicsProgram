package factories.sportFactory;

import java.util.Map;

import domain.sport.Sport;

/**
 * Created by lodz on 2016/04/18.
 */
public class SportFactory {
    public static Sport getSport(Map<String,String> stringMap)
    {
        Sport sport = new Sport.Builder()
                .Sport(stringMap.get("Sport"))
                .build();
        return sport;
    }
}
