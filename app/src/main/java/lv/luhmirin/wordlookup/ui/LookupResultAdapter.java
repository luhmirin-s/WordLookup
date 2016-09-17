package lv.luhmirin.wordlookup.ui;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.luhmirin.wordlookup.R;

class LookupResultAdapter extends RecyclerView.Adapter<LookupResultAdapter.LookupResultViewHolder> {

    private final Context context;
    private final List<String> results;
    private int spanLength = 0;

    private final ForegroundColorSpan colorSpan;

    LookupResultAdapter(Context context) {
        this.context = context;
        this.results = new ArrayList<>();

        this.colorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.accent));
    }

    @Override
    public LookupResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LookupResultViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lookup, parent, false));
    }

    @Override
    public void onBindViewHolder(LookupResultViewHolder holder, int position) {
        Spannable item = new SpannableString(results.get(position));

        item.setSpan(colorSpan, 0, spanLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.text.setText(item);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    void updateResults(List<String> newResults) {
        results.clear();
        results.addAll(newResults);

        notifyDataSetChanged();
    }

    void setSpanLength(int spanLength) {
        this.spanLength = spanLength;
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
