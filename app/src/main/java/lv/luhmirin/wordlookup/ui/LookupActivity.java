package lv.luhmirin.wordlookup.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.luhmirin.wordlookup.LookupWrapper;
import lv.luhmirin.wordlookup.R;
import lv.luhmirin.wordlookup.util.SimpleTextWatcher;

public class LookupActivity extends AppCompatActivity implements LookupContract {

    @BindView(R.id.lookup_input_layout) TextInputLayout inputLayout;
    @BindView(R.id.lookup_input) EditText input;
    @BindView(R.id.lookup_results) RecyclerView results;
    @BindView(R.id.lookup_placeholder) TextView placeholder;

    LookupResultAdapter resultsAdapter;

    private LookupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
        ButterKnife.bind(this);

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
