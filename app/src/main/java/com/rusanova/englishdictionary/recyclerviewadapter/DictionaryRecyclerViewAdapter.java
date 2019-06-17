package com.rusanova.englishdictionary.recyclerviewadapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rusanova.englishdictionary.createelementactivity.CreateElementActivity;
import com.rusanova.englishdictionary.mainactivity.DataAction;
import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.element.Element;

import java.util.List;

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.ViewHolder> {
    private List<Element> mElements;

    public DictionaryRecyclerViewAdapter(List elements) {
        this.mElements = elements;
    }

    private static final String ACTION = "action";
    private static final String NAME = "mName";
    private static final String DESCRIPTION = "mDescription";
    private static final String ID = "id";

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDescription;
        private ImageButton mChangeButton;
        private CheckBox mCheckBox;
        private int id;

        public ViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.name);
            mDescription = (TextView) view.findViewById(R.id.description);
            mChangeButton = (ImageButton) view.findViewById(R.id.change_button);
            mCheckBox = (CheckBox) view.findViewById(R.id.check_box_delete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view2, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mElements.get(i).getName());
        viewHolder.mDescription.setText(mElements.get(i).getDescription());
        viewHolder.id = mElements.get(i).getId();
        viewHolder.mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateElementActivity.class);
                intent.putExtra(NAME, viewHolder.mName.getText());
                intent.putExtra(DESCRIPTION, viewHolder.mDescription.getText());
                intent.putExtra(ID, viewHolder.id);
                intent.putExtra(ACTION, DataAction.UPDATE);
                v.getContext().startActivity(intent);
            }
        });


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