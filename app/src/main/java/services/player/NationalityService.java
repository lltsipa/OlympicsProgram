package services.player;

import android.content.Context;

import java.util.Set;

import domain.player.Nationality;

/**
 * Created by lodz on 2016/05/08.
 */
public interface NationalityService {
    void qualified(Context context, Nationality nationality);
    void disqualified(Context context, Nationality nationality);
}
