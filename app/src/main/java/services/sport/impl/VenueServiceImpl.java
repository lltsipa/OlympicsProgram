package services.sport.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import conf.util.App;
import domain.sport.Venue;
import repository.sport.VenueRepository;
import repository.sport.impl.VenueRepositoryImpl;
import services.sport.VenueService;


public class VenueServiceImpl extends IntentService implements VenueService{

    private final VenueRepository repo;
   // public static final String TAG = " Venue services ";
    public static final String ACTION_ADD_VENUE = "services.sport.impl.action.ADD";
    public static final String ACTION_UPDATE_VENUE = "services.sport.impl.action.UPDATE";

    public static final String EXTRA_ADD_VENUE = "services.sport.impl.extra.ADD";
    public static final String EXTRA_UPDATE_VENUE = "services.sport.impl.extra.UPDATE";

    private static VenueServiceImpl service = null;

    public static VenueServiceImpl getInstance() {
        if (service == null)
            service = new VenueServiceImpl();
        return service;
    }

    private VenueServiceImpl() {
        super("VenueServiceImpl");
        repo = new VenueRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addVenue(Context context, Venue venue) {
        Intent intent = new Intent(context, VenueServiceImpl.class);
        intent.setAction(ACTION_ADD_VENUE);
        intent.putExtra(EXTRA_ADD_VENUE, venue);
        context.startService(intent);
    }

    @Override
    public void updateVenue(Context context, Venue venue) {
        Intent intent = new Intent(context, VenueServiceImpl.class);
        intent.setAction(ACTION_UPDATE_VENUE);
        intent.putExtra(EXTRA_UPDATE_VENUE, venue);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_VENUE.equals(action)) {
                final Venue venue = (Venue) intent.getSerializableExtra(EXTRA_ADD_VENUE);
                handleAddVenue(venue);
            }else if(ACTION_UPDATE_VENUE.equals(action))
            {
                final Venue venue = (Venue) intent.getSerializableExtra(EXTRA_UPDATE_VENUE);
                handleUpdateVenue(venue);
            }
        }
    }

    private void handleAddVenue(Venue venue)
    {
        repo.save(venue);
    }

    private void handleUpdateVenue(Venue venue)
    {
        repo.update(venue);
    }

}
