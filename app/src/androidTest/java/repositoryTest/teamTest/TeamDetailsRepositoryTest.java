package repositoryTest.teamTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.team.TeamDetails;
import repository.team.TeamDetailsRepository;
import repository.team.impl.TeamDetailsRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class TeamDetailsRepositoryTest extends AndroidTestCase {

    public static final String TAG = " TEAMDETAILS TEST ";
    private Long id;

    TeamDetailsRepository repo = new TeamDetailsRepositoryImpl(this.getContext());
    public void testCreateReadUpdateDelete()
    {
        //CREATE
        TeamDetails teamDetailsCreate = new TeamDetails.Builder()
                .established("1954")
                .appearances("13")
                .build();
        TeamDetails teamDetailsInserted = repo.save(teamDetailsCreate);
        id = teamDetailsInserted.getId();
        Assert.assertNotNull(TAG + " CREATE", teamDetailsInserted);

        //READ ALL
        Set<TeamDetails> teamDetailsSet = repo.findAll();
        Assert.assertTrue(TAG +" READ ALL ", teamDetailsSet.size() > 0);

        //READ
        TeamDetails teamDetailsRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ", teamDetailsRead);

        //UPDATE
        TeamDetails teamDetailsUpdate = new TeamDetails.Builder()
                .copy(teamDetailsRead)
                .appearances("14")
                .build();
        repo.update(teamDetailsUpdate);
        TeamDetails newTeamDetails = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newTeamDetails);

        //DELETE
        repo.delete(teamDetailsUpdate);
        TeamDetails teamDetailsDeleted = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", teamDetailsDeleted);
    }
}
