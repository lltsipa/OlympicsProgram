package services.player.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import conf.util.App;
import domain.player.PlayerStats;
import repository.player.PlayerStatsRepository;
import repository.player.impl.PlayerStatsRepositoryImpl;
import services.player.PlayerStatsService;


public class PlayerStatsServiceImpl extends IntentService implements PlayerStatsService {

    private final PlayerStatsRepository repo;
    public static final String ACTION_ADD_STATS = " services.player.impl.action.ADDSTATS ";
    public static final String EXTRA_ADD_STATS = " services.player.impl.action.EXTRASTATS ";

    public static PlayerStatsServiceImpl playerStatsService = null;

    public static PlayerStatsServiceImpl getInstance()
    {
        if(playerStatsService == null)
        {
            playerStatsService = new PlayerStatsServiceImpl();
        }
        return playerStatsService;
    }

    public PlayerStatsServiceImpl() {
        super("PlayerStatsServiceImpl");
        repo = new PlayerStatsRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addPlayerStats(Context context, PlayerStats playerStats) {
        Intent intent = new Intent(context,PlayerStatsServiceImpl.class);
        intent.setAction(ACTION_ADD_STATS);
        intent.putExtra(EXTRA_ADD_STATS, playerStats);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.i(TAG, " Intent Service started ");
        if(intent != null)
        {
            String action = intent.getAction();
            if(ACTION_ADD_STATS.equals(action))
            {
                final PlayerStats playerStats = (PlayerStats)intent.getSerializableExtra(EXTRA_ADD_STATS);
                handleStats(playerStats);
            }
        }
    }
    private void handleStats(PlayerStats playerStats)
    {
        repo.save(playerStats);
    }


}
