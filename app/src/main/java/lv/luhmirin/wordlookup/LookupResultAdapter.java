package lv.luhmirin.wordlookup;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class LookupResultAdapter extends RecyclerView.Adapter<LookupResultAdapter.LookupResultViewHolder> {

    private final Context context;
    private final List<String> results;

    public LookupResultAdapter(Context context) {
        this.context = context;
        this.results = new ArrayList<>();
    }

    @Override
    public LookupResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LookupResultViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lookup, null));
    }

    @Override
    public void onBindViewHolder(LookupResultViewHolder holder, int position) {
        String item = results.get(position);

        // TODO: spannable goes here

        holder.text.setText(item);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void updateResults(List<String> newResults) {
        results.clear();
        results.addAll(newResults);

        notifyDataSetChanged();
    }

    static class LookupResultViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lookup_result_text)
        TextView text;

        LookupResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
