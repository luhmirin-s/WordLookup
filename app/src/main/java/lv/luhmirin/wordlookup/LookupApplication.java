package lv.luhmirin.wordlookup;


import android.app.Application;

public class LookupApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        LookupWrapper.INSTANCE.initFromFile(getAssets(), "wordlist.txt");
    }
}
