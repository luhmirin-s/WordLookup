package lv.luhmirin.wordlookup;


import android.app.Application;

public class LookupApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LookupWrapper.getInstance().initFromFile(getAssets());
    }
}
