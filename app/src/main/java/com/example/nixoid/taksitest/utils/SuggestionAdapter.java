package com.example.nixoid.taksitest.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nixoid.taksitest.R;
import com.example.nixoid.taksitest.rest.models.Suggestion;

import java.util.Collection;
import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder>  {

    List<Suggestion> suggestions;

    private Context context;
    private ViewHolder.ClickListener clickListener;

    public SuggestionAdapter(List<Suggestion> suggestions, ViewHolder.ClickListener clickListener ){
        this.suggestions = suggestions;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Suggestion suggestion = suggestions.get(i);
        viewHolder.name.setText(suggestion.getName());
    }

    public void clearAll(Collection<Suggestion> items) {
        suggestions.removeAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    private TextView name;
    private ClickListener clickListener;

        public ViewHolder(View itemView, ClickListener clickListener) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.content_text);
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClicked(getAdapterPosition());
        }

        public interface ClickListener {

            void onItemClicked(int position);

        }
    }
}


