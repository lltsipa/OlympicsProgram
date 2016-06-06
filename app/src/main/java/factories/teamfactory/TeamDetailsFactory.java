package factories.teamfactory;

import java.util.Map;

import domain.team.TeamDetails;

/**
 * Created by lodz on 2016/04/18.
 */
public class TeamDetailsFactory {
    public static TeamDetails getTeamDetails(String established, String appearances)
    {
        TeamDetails teamDetails = new TeamDetails.Builder()
                .established(established)
                .appearances(appearances)
                .build();
        return teamDetails;
    }
}
