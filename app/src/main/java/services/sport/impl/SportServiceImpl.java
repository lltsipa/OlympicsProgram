package services.sport.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import conf.util.App;
import domain.sport.Sport;
import repository.sport.SportRepository;
import repository.sport.impl.SportRepositoryImpl;
import services.sport.SportService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SportServiceImpl extends IntentService implements SportService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private final SportRepository repo; // = SportServiceImpl.getInstance();
    private static final String ACTION_ADD_SPORT = "services.sport.impl.action.ADD";
    private static final String EXTRA_ADD_SPORT= "services.sport.impl.extra.ADD";

    
    private static SportServiceImpl service = null;
    
        public static SportServiceImpl getInstance() {
            if (service == null)
                service = new SportServiceImpl();
            return service;
        }
    public void addSport(Context context, Sport sport) {
        Intent intent = new Intent(context, SportServiceImpl.class);
        intent.setAction(ACTION_ADD_SPORT);
        intent.putExtra(EXTRA_ADD_SPORT, sport);
        context.startService(intent);
    }


    private SportServiceImpl() {
        super("SportServiceImpl");
        repo = new SportRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_SPORT.equals(action)) {
                final Sport sport = (Sport) intent.getSerializableExtra(EXTRA_ADD_SPORT);
                handleAddSport(sport);
            }
        }
    }

    private void handleAddSport(Sport sport){
        repo.save(sport);
    }

}
