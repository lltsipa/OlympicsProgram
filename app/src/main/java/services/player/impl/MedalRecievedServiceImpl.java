package services.player.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import conf.util.App;
import domain.player.MedalRecieved;
import factories.playerfactory.MedalRecievedFactory;
import repository.player.MedalRecievedRepository;
import repository.player.impl.MedalRecievedRepositoryImpl;
import services.player.MedalRecievedServices;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MedalRecievedServiceImpl extends IntentService implements MedalRecievedServices {

    private final MedalRecievedRepository repo; //= new MedalRecievedRepositoryImpl();
    public static final String ACTION_ADD_MEDAL = " services.player.impl.action.ADDMEDAL ";
    public static final String EXTRA_ADD_MEDAL = " services.player.impl.extra.EXTRAMEDAL ";
    public static MedalRecievedServiceImpl medalRecievedService = null;



    public static MedalRecievedServiceImpl getInstance()
    {
        if(medalRecievedService == null)
        {
            medalRecievedService = new MedalRecievedServiceImpl();
        }
        return medalRecievedService;
    }

    private MedalRecievedServiceImpl() {
        super("MedalRecievedServiceImpl");
        repo = new MedalRecievedRepositoryImpl(App.getAppContext());
    }

    @Override
    public void medals(Context context, MedalRecieved medalRecieved) {
        Intent intent = new Intent(context, MedalRecievedServiceImpl.class);
        intent.setAction(ACTION_ADD_MEDAL);
        intent.putExtra(EXTRA_ADD_MEDAL, medalRecieved);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null)
        {
            String action = intent.getAction();
            if(ACTION_ADD_MEDAL.equals(action))
            {
                final MedalRecieved medalRecieved = (MedalRecieved) intent.getSerializableExtra(EXTRA_ADD_MEDAL);
                handleMedal(medalRecieved);
            }
        }
    }

    private void handleMedal(MedalRecieved medalRecieved)
    {
        repo.save(medalRecieved);
    }


}
