package services.player;

import android.content.Context;

import domain.player.Player;

/**
 * Created by lodz on 2016/05/08.
 */
public interface PlayerService {
    void addPlayer(Context context, Player player);
    void updatePlayer(Context context, Player player);
    void removePlayer(Context context, Player player);
}
