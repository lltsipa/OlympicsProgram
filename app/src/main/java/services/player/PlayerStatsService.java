package services.player;

import android.content.Context;

import domain.player.PlayerStats;

/**
 * Created by lodz on 2016/05/08.
 */
public interface PlayerStatsService {
    void addPlayerStats(Context context, PlayerStats playerStats);
}
