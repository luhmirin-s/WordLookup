package lv.luhmirin.wordlookup.wrapper;


import android.content.res.AssetManager;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lv.luhmirin.MemoryLookup;
import lv.luhmirin.WordLookup;

public class LookupWrapper {

    private static final String FILENAME = "wordlist.txt";

    private static LookupWrapper instance;

    public static LookupWrapper getInstance() {
        if (instance == null) {
            instance = new LookupWrapper();
        }
        return instance;
    }

    private WordLookup wordLookup;
    private boolean hasDictionary = false;

    private Set<LookupReadyListener> listeners = new HashSet<>();


    public void initFromFile(final Handler mainHandler, final AssetManager assets) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                wordLookup = new MemoryLookup();

                final Scanner scanner = new Scanner(assets.open(FILENAME));
                wordLookup.loadDictionary(() -> scanner);

                hasDictionary = true;
                notifyListeners(mainHandler);
            } catch (IOException e) {
                e.printStackTrace();
                wordLookup = null;
                hasDictionary = false;
            }
        });
    }

    private void notifyListeners(Handler mainHandler) {
        mainHandler.post(() -> {
            for (LookupReadyListener listener : listeners) {
                listener.onReady();
            }
        });
    }

    public void setLookupReadyListener(@NotNull LookupReadyListener listener) {
        if (wordLookup != null && hasDictionary) {
            listener.onReady();
        } else {
            listeners.add(listener);
        }
    }

    @NotNull
    public List<String> lookup(@NotNull String digits) {
        if (wordLookup != null) {
            return wordLookup.lookup(digits);
        }
        return Collections.emptyList();
    }
}
