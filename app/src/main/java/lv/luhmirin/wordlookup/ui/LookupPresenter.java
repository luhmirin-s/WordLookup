package lv.luhmirin.wordlookup.ui;


import java.util.Collections;
import java.util.List;

import lv.luhmirin.wordlookup.wrapper.LookupWrapper;

/**
 * POJO class that encapsulates business logic. 100% unit testable :)
 */
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
        viewContract.disableInput();

        lookupWrapper.setLookupReadyListener(viewContract::enableInput);
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
        viewContract.setResultList(Collections.emptyList());
        viewContract.showPlaceHolder();
    }
}
