package com.rusanova.englishdictionary;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ElementRecyclerViewAdapter extends RecyclerView.Adapter<ElementRecyclerViewAdapter.ViewHolder> {
    private List<Element> mElements;

    public ElementRecyclerViewAdapter(List elements){
        this.mElements = elements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        TextView description;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cv);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
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

    public void setElements(List<Element> elements) {
        mElements = elements;
    }
}