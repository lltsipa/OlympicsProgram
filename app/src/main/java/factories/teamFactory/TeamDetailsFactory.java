package factories.teamFactory;

import java.util.Map;

import domain.team.TeamDetails;

/**
 * Created by lodz on 2016/04/18.
 */
public class TeamDetailsFactory {
    public static TeamDetails getTeamDetails(Map<String, String> stringMap)
    {
        TeamDetails teamDetails = new TeamDetails.Builder()
                .established(stringMap.get("Established"))
                .appearances(stringMap.get("Appearances"))
                .build();
        return teamDetails;
    }
}
