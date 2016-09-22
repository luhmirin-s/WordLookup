package lv.luhmirin.wordlookup.wrapper;


import android.content.res.AssetManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

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

/**
 * Singleton wrapper for actual in-memory WordLookup instance.
 *
 * This class is singleton to make sure that we do not load unnecessary copies of full dictionary.
 * In ideal world this class would be instantiated and controlled by Dagger, but it seems unnecessary
 * for such trivial application.
 */
public class LookupWrapper {

    private static final String FILENAME = "wordlist.txt";

    private static LookupWrapper instance;

    public synchronized static LookupWrapper getInstance() {
        if (instance == null) {
            instance = new LookupWrapper();
        }
        return instance;
    }

    @Nullable private WordLookup wordLookup;
    @Nullable private CountingIdlingResource idlingResource;
    @NotNull private Set<LookupReadyListener> listeners = new HashSet<>();
    private boolean hasDictionary = false;

    public void initFromFile(@NotNull final Handler mainHandler, @NotNull final AssetManager assets) {
        if (hasDictionary) return;

        if (idlingResource != null) {
            idlingResource.increment();
        }

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

    private void notifyListeners(@NotNull Handler mainHandler) {
        mainHandler.post(() -> {
            for (LookupReadyListener listener : listeners) {
                listener.onReady();
            }
            if (idlingResource != null) {
                idlingResource.decrement();
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

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new CountingIdlingResource("dictionary_loading");
        }
        return idlingResource;
    }

}
