package services.player;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CatagoryServiceImpl extends IntentService {
    public static final String TAG = "Catagory Service ";
    public static CatagoryServiceImpl catagoryService = null;

    public static CatagoryServiceImpl getInstance()
    {
        if(catagoryService ==null)
        {
            catagoryService = new CatagoryServiceImpl();
        }
        return catagoryService;
    }

    public CatagoryServiceImpl() {
        super("CatagoryServiceImpl");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG," Intent Catagory Service Started");
    }


}
