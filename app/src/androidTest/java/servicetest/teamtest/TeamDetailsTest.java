package servicetest.teamtest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.team.TeamDetails;
import factories.teamfactory.TeamDetailsFactory;
import services.team.TeamDetailsServiceImpl;

/**
 * Created by lodz on 2016/06/07.
 */
public class TeamDetailsTest extends AndroidTestCase {

    TeamDetailsServiceImpl teamDetailsService;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        teamDetailsService = TeamDetailsServiceImpl.getInstance();
    }

    public void testAddTeamDetails() throws Exception {
        TeamDetails teamDetails = TeamDetailsFactory.getTeamDetails("1998","4");
        teamDetailsService.addTeamDetails(App.getAppContext(),teamDetails);

        Assert.assertNotNull("Testing Team details service: ", teamDetailsService );

    }
}
