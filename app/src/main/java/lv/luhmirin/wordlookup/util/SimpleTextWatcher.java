package lv.luhmirin.wordlookup.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Empty implementation of interface to avoid having multiple empty method implementations.
 * Reduces noise in code.
 */
public class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
