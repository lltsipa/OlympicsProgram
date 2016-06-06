package factories.sportfactory;

import java.util.Map;

import domain.sport.Sport;

/**
 * Created by lodz on 2016/04/18.
 */
public class SportFactory {
    public static Sport getSport(String sports)
    {
        Sport sport = new Sport.Builder()
                .Sport(sports)
                .build();
        return sport;
    }
}
