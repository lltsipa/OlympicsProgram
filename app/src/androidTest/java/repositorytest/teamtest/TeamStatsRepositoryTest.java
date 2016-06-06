package repositorytest.teamtest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import domain.team.TeamStats;
import repository.team.TeamStatsRepository;
import repository.team.impl.TeamStatsRepositoryImpl;

/**
 * Created by lodz on 2016/04/26.
 */
public class TeamStatsRepositoryTest extends AndroidTestCase{

    public static final String TAG = " TEAMSTATS TEST ";
    private Long id;

    TeamStatsRepository repo = new TeamStatsRepositoryImpl(this.getContext());

    public void testCreateReadUpdateDelete()
    {
        //CREATE
        TeamStats teamStatsCreate = new TeamStats.Builder()
                .Played(50)
                .Wins(32)
                .Loses(17)
                .build();
        TeamStats teamStatsInserted = repo.save(teamStatsCreate);
        id = teamStatsInserted.getId();
        Assert.assertNotNull(TAG + " CREATE ",teamStatsInserted);

        //READ ALL
        Set<TeamStats> teamStatsSet = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL ",teamStatsSet.size()>0);

        //READ
        TeamStats teamStatsRead = repo.findByID(id);
        Assert.assertNotNull(TAG + " READ ",teamStatsRead);

        //UPDATE
        TeamStats teamStatsUpdate = new TeamStats.Builder()
                .copy(teamStatsRead)
                .Played(51)
                .Wins(33)
                .build();
        repo.update(teamStatsUpdate);
        TeamStats newTeamStats = repo.findByID(id);
        Assert.assertEquals(TAG + " UPDATE ", newTeamStats);

        //DELETE
        repo.delete(teamStatsUpdate);
        TeamStats teamStatsDelete = repo.findByID(id);
        Assert.assertNull(TAG + " DELETE ", teamStatsDelete);

    }
}
