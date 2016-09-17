package lv.luhmirin.wordlookup;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }

    private void prepareRecycler() {
        resultsAdapter = new LookupResultAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        results.setLayoutManager(layoutManager);
        results.setAdapter(resultsAdapter);
    }

}
