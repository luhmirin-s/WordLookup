package lv.luhmirin.wordlookup.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.luhmirin.wordlookup.R;
import lv.luhmirin.wordlookup.util.SimpleTextWatcher;
import lv.luhmirin.wordlookup.wrapper.LookupWrapper;

public class LookupActivity extends AppCompatActivity implements LookupContract {

    @BindView(R.id.lookup_input_layout) TextInputLayout inputLayout;
    @BindView(R.id.lookup_input) EditText input;
    @BindView(R.id.lookup_results) RecyclerView results;
    @BindView(R.id.lookup_placeholder) TextView placeholder;

    private LookupResultAdapter resultsAdapter;

    private LookupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
        ButterKnife.bind(this);


        // Loading dictionary here because doing it in Application class messes up UI testing flow, for some reason.
        LookupWrapper.getInstance().initFromFile(new Handler(Looper.getMainLooper()), getAssets());

        presenter = new LookupPresenter(this, LookupWrapper.getInstance());
        presenter.onCreate();
    }

    @Override
    public void prepareResultListView() {
        resultsAdapter = new LookupResultAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        results.setLayoutManager(layoutManager);
        results.setAdapter(resultsAdapter);
    }

    @Override
    public void enableInput() {
        input.setEnabled(true);
        placeholder.setText(R.string.lookup_placeholder);
    }

    @Override
    public void disableInput() {
        input.setEnabled(false);
        placeholder.setText(R.string.lookup_placeholder_loading);
    }

    @Override
    public void prepareInputListener() {
        input.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.inputTextChanged(editable.toString());
            }
        });
    }

    @Override
    public void setHighlightLength(int newHighlightLength) {
        resultsAdapter.setSpanLength(newHighlightLength);
    }

    @Override
    public void setResultList(List<String> newResults) {
        resultsAdapter.updateResults(newResults);
    }

    @Override
    public void showPlaceHolder() {
        placeholder.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePlaceHolder() {
        placeholder.setVisibility(View.GONE);
    }
}
