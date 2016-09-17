package lv.luhmirin.wordlookup.ui;


import java.util.Collections;
import java.util.List;

import lv.luhmirin.wordlookup.LookupWrapper;

class LookupPresenter {

    private final LookupContract viewContract;
    private final LookupWrapper lookupWrapper;

    LookupPresenter(LookupContract viewContract, LookupWrapper lookupWrapper) {
        this.viewContract = viewContract;
        this.lookupWrapper = lookupWrapper;
    }

    void onCreate() {
        viewContract.prepareResultListView();
        viewContract.prepareInputListener();
    }


    void inputTextChanged(String newInput) {
        viewContract.setHighlightLength(newInput.length());

        if (newInput.length() == 0) {
            showPlaceholder();
        } else {
            List<String> newResults = lookupWrapper.lookup(newInput);
            if (newResults.size() > 0) {
                viewContract.hidePlaceHolder();
                viewContract.setResultList(newResults);
            } else {
                showPlaceholder();
            }
        }
    }

    private void showPlaceholder() {
        viewContract.setResultList(Collections.<String>emptyList());
        viewContract.showPlaceHolder();
    }
}
