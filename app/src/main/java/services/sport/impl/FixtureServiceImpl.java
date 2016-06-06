package services.sport.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import domain.sport.Sport;
import domain.sport.Venue;
import services.sport.FixtureService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FixtureServiceImpl extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_ADD_FIXTURE = "services.sport.impl.action.FIXTURE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD_FIXTURE = "services.sport.impl.extra.FIXTURE";

  private static FixtureServiceImpl service = null;
  
      public static FixtureServiceImpl getInstance() {
          if (service == null)
              service = new FixtureServiceImpl();
          return service;
      }


    public static void getFixture(Context context,Venue venue,Sport sport) {
        Intent intent = new Intent(context, FixtureServiceImpl.class);
        intent.setAction(ACTION_ADD_FIXTURE);
        intent.putExtra(EXTRA_ADD_FIXTURE, sport);
        intent.putExtra(EXTRA_ADD_FIXTURE, venue);
        context.startService(intent);
    }


    private FixtureServiceImpl() {
        super("FixtureServiceImpl");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_FIXTURE.equals(action)) {
                final Venue venue = (Venue) intent.getSerializableExtra(EXTRA_ADD_FIXTURE);
                final Sport sport = (Sport) intent.getSerializableExtra(EXTRA_ADD_FIXTURE);
                handleFixture(venue,sport);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleFixture(Venue venue, Sport sport) {
       //Handle how to manage fixtures
    }

}
