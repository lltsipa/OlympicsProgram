package services.player.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import conf.util.App;
import domain.player.Nationality;
import repository.player.NationalityRepository;
import repository.player.impl.NationalityRepositoryImpl;
import services.player.NationalityService;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class NationalityServiceImpl extends IntentService implements NationalityService {

    private final NationalityRepository repo;
    public static final String ACTION_ADD_QUALIFIED = " services.player.impl.action.ADDQUALIFIED ";
    public static final String ACTION_DELETE_DISQUALIFIED = " services.player.impl.action.DELETEDISQUALIFIED ";

    public static final String EXTRA_ADD_QUALIFIED = " services.player.impl.extra.ADDQUALIFIED ";
    public static final String EXTRA_DELETE_DISQUALIFIED = " services.player.impl.extra.DELETEDISQUALIFIED ";

    public static NationalityServiceImpl nationalityService = null;

    public static NationalityServiceImpl getInstance(){
        if(nationalityService == null)
        {
            nationalityService = new NationalityServiceImpl();
        }
        return nationalityService;
    }
    private NationalityServiceImpl() {
        super("NationalityServiceImpl");
        repo = new NationalityRepositoryImpl(App.getAppContext());
    }

    @Override
    public void qualified(Context context, Nationality nationality) {
        Intent intent = new Intent(context, NationalityServiceImpl.class);
        context.startService(intent);
    }

    public void disqualified(Context context, Nationality nationality) {
        Intent intent = new Intent(context, NationalityServiceImpl.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.i(TAG, " Intent service started ");
        if(intent != null)
        {
            String action = intent.getAction();
            if(ACTION_ADD_QUALIFIED.equals(action))
            {
                final Nationality nationality = (Nationality) intent.getSerializableExtra(EXTRA_ADD_QUALIFIED);
                handleQualified(nationality);
            }else if(ACTION_DELETE_DISQUALIFIED.equals(action))
            {
                final Nationality nationality = (Nationality) intent.getSerializableExtra(EXTRA_DELETE_DISQUALIFIED);
                handleDisqualified(nationality);
            }
        }
    }

    private void handleQualified(Nationality nationality){
        repo.save(nationality);
    }

    private void handleDisqualified(Nationality nationality){
        repo.delete(nationality);
    }

}
