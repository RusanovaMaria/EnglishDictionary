package com.rusanova.englishdictionary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DictionaryListFragment extends Fragment {
    private static final int PAGE_NUMBER = 1;
    private RecyclerView mRecyclerView;
    private ElementRecyclerViewAdapter mAdapter;

    public DictionaryListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        DictionaryList dictionaryList = DictionaryList.get(getActivity());
        mAdapter = new ElementRecyclerViewAdapter(dictionaryList.getDictionaries());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI(){
        DictionaryList dictionaryList = DictionaryList.get(getActivity());
        List<Element> dictionaries = dictionaryList.getDictionaries();
        if(mAdapter == null) {
            mAdapter = new ElementRecyclerViewAdapter(dictionaries);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setElements(dictionaries);
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
