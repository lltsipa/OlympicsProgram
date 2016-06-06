package services.sport;

import android.content.Context;

import domain.sport.Sport;
import domain.sport.Venue;

/**
 * Created by lodz on 2016/05/10.
 */
public interface FixtureService {
    String getFixture(Context context,Venue venue,Sport sport);
}
