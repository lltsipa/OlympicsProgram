package services.team;

import android.content.Context;

import domain.team.TeamStats;

/**
 * Created by lodz on 2016/05/10.
 */
public interface TeamListing {
    void getTeamRankings(Context context,TeamStats teamStats);
}
