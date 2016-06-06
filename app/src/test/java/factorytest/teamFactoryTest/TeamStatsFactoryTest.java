package factoryTest.teamFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.team.TeamStats;
import factories.teamfactory.TeamStatsFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class TeamStatsFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,Integer> stringMap = new HashMap<>();
        stringMap.put("Wins",5);
        stringMap.put("Loses",10);
        stringMap.put("Played",17);

        TeamStats teamStats = TeamStatsFactory.getTeamStats(stringMap);
        Assert.assertEquals(5,teamStats.getWins());
        Assert.assertEquals(10,teamStats.getLoses());
        Assert.assertEquals(17,teamStats.getPlayed());
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,Integer> stringMap = new HashMap<>();
        stringMap.put("Wins",5);
        stringMap.put("Loses",10);
        stringMap.put("Played",17);

        Map<String,Integer> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("Wins",6);
        stringUpdateMap.put("Loses",11);
        stringUpdateMap.put("Played",18);


        TeamStats teamStats = TeamStatsFactory.getTeamStats(stringMap);


        TeamStats newTeamDetails = new TeamStats.Builder()
                .copy(teamStats)
                .Wins(stringUpdateMap.get("Wins"))
                .Loses(stringUpdateMap.get("Loses"))
                .Played(stringUpdateMap.get("Played"))
                .build();
        Assert.assertEquals(6,newTeamDetails.getWins());
        Assert.assertEquals(11,newTeamDetails.getLoses());
        Assert.assertEquals(18,newTeamDetails.getPlayed());
    }
}
