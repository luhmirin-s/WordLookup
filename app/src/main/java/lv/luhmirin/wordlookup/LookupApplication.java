package lv.luhmirin.wordlookup;


import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import lv.luhmirin.wordlookup.wrapper.LookupWrapper;

public class LookupApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LookupWrapper.getInstance().initFromFile(new Handler(Looper.getMainLooper()), getAssets());
    }
}
