package services.sport;

import android.content.Context;

import domain.sport.Venue;

/**
 * Created by lodz on 2016/05/08.
 */
public interface VenueService {
    void addVenue(Context context, Venue venue);
    void updateVenue(Context context, Venue venue);
}
