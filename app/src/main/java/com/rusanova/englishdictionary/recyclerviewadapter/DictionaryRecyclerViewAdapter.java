package com.rusanova.englishdictionary.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.element.Dictionary;

import java.util.List;

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.ViewHolder> {
    private List<Dictionary> mElements;

    public DictionaryRecyclerViewAdapter(List elements){
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view2, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
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

    public void setElements(List<Dictionary> elements) {
        mElements = elements;
    }
}