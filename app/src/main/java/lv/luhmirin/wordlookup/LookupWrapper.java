package lv.luhmirin.wordlookup;


import android.content.res.AssetManager;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import lv.luhmirin.MemoryLookup;
import lv.luhmirin.WordLookup;

public class LookupWrapper {

    private static LookupWrapper instance;

    public static LookupWrapper getInstance() {
        if (instance == null) {
            instance = new LookupWrapper();
        }
        return instance;
    }

    private WordLookup wordLookup;


    void initFromFile(AssetManager assets, String filename) {
        try {
            final Scanner scanner = new Scanner(assets.open(filename));
            wordLookup = new MemoryLookup();

            Iterable<String> iterable = new Iterable<String>() {
                @Override
                public Iterator<String> iterator() {
                    return scanner;
                }
            };
            Log.wtf("LOG_LOG", "started");
            wordLookup.loadDictionary(iterable);
            Log.wtf("LOG_LOG", "done");

        } catch (IOException e) {
            e.printStackTrace();
            wordLookup = null;
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
