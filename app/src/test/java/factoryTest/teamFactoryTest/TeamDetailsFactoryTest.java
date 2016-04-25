package factoryTest.teamFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.team.TeamDetails;
import factories.teamFactory.TeamDetailsFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class TeamDetailsFactoryTest {

    @Test
    public void testName() throws Exception {

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Established","1905");
        stringMap.put("Appearances","30");

        TeamDetails teamDetails = TeamDetailsFactory.getTeamDetails(stringMap);
        Assert.assertEquals("1905", teamDetails.getEstablished());
        Assert.assertEquals("30", teamDetails.getAppearances());
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Established","1905");
        stringMap.put("Appearances","30");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("Established","1909");
        stringUpdateMap.put("Appearances","20");

        TeamDetails teamDetails = TeamDetailsFactory.getTeamDetails(stringMap);

        TeamDetails newTeamDetails = new TeamDetails.Builder()
                .copy(teamDetails)
                .established(stringUpdateMap.get("Established"))
                .appearances(stringUpdateMap.get("Appearances"))
                .build();
        Assert.assertEquals("1909", newTeamDetails.getEstablished());
        Assert.assertEquals("20", newTeamDetails.getAppearances());
    }
}
