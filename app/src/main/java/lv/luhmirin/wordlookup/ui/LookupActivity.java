package lv.luhmirin.wordlookup.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.luhmirin.wordlookup.LookupWrapper;
import lv.luhmirin.wordlookup.R;
import lv.luhmirin.wordlookup.util.SimpleTextWatcher;

public class LookupActivity extends AppCompatActivity {

    @BindView(R.id.lookup_input_layout) TextInputLayout inputLayout;
    @BindView(R.id.lookup_input) EditText input;
    @BindView(R.id.lookup_results) RecyclerView results;

    LookupResultAdapter resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
        ButterKnife.bind(this);

        prepareRecycler();

        input.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                resultsAdapter.setSpanLength(editable.length());
                if (editable.length() == 0) {
                    resultsAdapter.updateResults(Collections.<String>emptyList());
                } else {
                    resultsAdapter.updateResults(LookupWrapper.INSTANCE.lookup(editable.toString()));
                }
            }
        });
    }

    private void prepareRecycler() {
        resultsAdapter = new LookupResultAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        results.setLayoutManager(layoutManager);
        results.setAdapter(resultsAdapter);
    }

}
