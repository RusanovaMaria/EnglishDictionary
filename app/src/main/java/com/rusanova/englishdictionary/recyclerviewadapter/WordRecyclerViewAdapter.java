package com.rusanova.englishdictionary.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rusanova.englishdictionary.R;;
import com.rusanova.englishdictionary.element.Word;

import java.util.List;

public class WordRecyclerViewAdapter extends RecyclerView.Adapter<WordRecyclerViewAdapter.ViewHolder> {
    private List<Word> mElements;

    public WordRecyclerViewAdapter(List elements) {
        this.mElements = elements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    @Override
    public WordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view1, viewGroup, false);
        WordRecyclerViewAdapter.ViewHolder viewHolder = new WordRecyclerViewAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WordRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(mElements.get(i).getName());
        viewHolder.description.setText(mElements.get(i).getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mElements.size();
    }

    public void setElements(List<Word> elements) {
        mElements = elements;
    }
}
