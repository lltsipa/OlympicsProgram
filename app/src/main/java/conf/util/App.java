package conf.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by lodz on 2016/04/25.
 */
public class App extends Application {

    public static Context context;

    public void onCreate()
    {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext()
    {
        return App.context;
    }
}
