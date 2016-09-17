package lv.luhmirin.wordlookup.ui;


import java.util.List;

/**
 * This is what is usually called View interface in MVP architecture.
 *
 * Different name to avoid confusion with android.view.view
 */
interface LookupContract {

    void prepareResultListView();

    void enableInput();

    void disableInput();

    void prepareInputListener();

    void setHighlightLength(int newHighlightLength);

    void setResultList(List<String> newResults);

    void showPlaceHolder();

    void hidePlaceHolder();

}
