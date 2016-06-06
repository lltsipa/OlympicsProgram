package services.player.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import conf.util.App;
import domain.player.Player;
import factories.playerfactory.PlayerFactory;
import repository.player.PlayerRepository;
import repository.player.impl.PlayerRepositoryImpl;
import services.player.PlayerService;


public class PlayerServiceImpl extends IntentService implements PlayerService {

    private final PlayerRepository repo;

    public static final String ACTION_ADD_PLAYER = " services.player.impl.action.ADD_PLAYER_NAME ";

    public static final String ACTION_UPDATE_PLAYER = " services.player.impl.action. ";
    public static final String ACTION_DELETE_PLAYER = " services.player.impl.action. ";

    public static final String EXTRA_UPDATE_PLAYER = " services.player.impl.action. ";
    public static final String EXTRA_DELETE_PLAYER = " services.player.impl.action. ";
    public static final String EXTRA_ADD_PLAYER = " services.player.impl.action.ADD_PLAYER_NAME ";

    public static PlayerServiceImpl playerService = null;

    public static PlayerServiceImpl getInstance(){

        if(playerService == null)
        {
            playerService = new PlayerServiceImpl();
        }
        return playerService;
    }
    private PlayerServiceImpl() {
        super("PlayerServiceImpl");
        repo = new PlayerRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addPlayer(Context context, Player player) {
        Intent intent = new Intent(App.getAppContext(), PlayerServiceImpl.class);
        intent.setAction(ACTION_ADD_PLAYER);
        intent.putExtra(EXTRA_ADD_PLAYER, player);
        context.startService(intent);

    }

    @Override
    public void updatePlayer(Context context, Player player) {
        Intent intent = new Intent(App.getAppContext(), PlayerServiceImpl.class);
        intent.setAction(ACTION_UPDATE_PLAYER);
        intent.putExtra(EXTRA_UPDATE_PLAYER, player);
        context.startService(intent);
    }

    @Override
    public void removePlayer(Context context, Player player) {
        Intent intent = new Intent(context, PlayerServiceImpl.class);
        intent.setAction(ACTION_DELETE_PLAYER);
        intent.putExtra(EXTRA_DELETE_PLAYER, player);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.i(TAG, " Intent Service started ");
        if(intent != null)
        {
            String action = intent.getAction();
            if(ACTION_ADD_PLAYER.equals(action))
            {
                final Player player = (Player) intent.getSerializableExtra(EXTRA_UPDATE_PLAYER);
                handleAddPlayer(player);
            }else if(ACTION_UPDATE_PLAYER.equals(action))
            {
                final Player player = (Player) intent.getSerializableExtra(EXTRA_UPDATE_PLAYER);
                handleUpdatePlayer(player);
            }else if(ACTION_DELETE_PLAYER.equals(action))
            {
                final Player player = (Player) intent.getSerializableExtra(EXTRA_DELETE_PLAYER);
                handleDeletePlayer(player);
            }
        }

    }

    private void handleAddPlayer(Player player)
    {
        repo.save(player);
    }
    private void handleUpdatePlayer(Player player){
        repo.update(player);
    }
    private void handleDeletePlayer(Player player){
        repo.delete(player);
    }

}
