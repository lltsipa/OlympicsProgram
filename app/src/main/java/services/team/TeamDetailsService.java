package services.team;

import android.content.Context;

import domain.team.TeamDetails;

/**
 * Created by lodz on 2016/05/13.
 */
public interface TeamDetailsService {
    void addTeamDetails(Context context,TeamDetails teamDetails);
    void updateTeamDetails(Context context,TeamDetails teamDetails);
}
