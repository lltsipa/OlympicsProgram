package factories.teamfactory;

import java.util.Map;

import domain.team.TeamStats;

/**
 * Created by lodz on 2016/04/18.
 */
public class TeamStatsFactory {
    public static TeamStats getTeamStats(Map<String, Integer> stringIntegerMap)
    {
        TeamStats teamDetails = new TeamStats.Builder()
                .Wins(stringIntegerMap.get("Wins"))
                .Loses(stringIntegerMap.get("Loses"))
                .Played(stringIntegerMap.get("Played"))
                .build();
        return teamDetails;
    }
}
