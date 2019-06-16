package com.rusanova.englishdictionary.pagefragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rusanova.englishdictionary.element.Dictionary;
import com.rusanova.englishdictionary.element.Element;
import com.rusanova.englishdictionary.recyclerviewadapter.DictionaryRecyclerViewAdapter;
import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.list.DictionaryList;

import java.util.List;

public class DictionaryPageFragment extends Fragment {
    private static final String PAGE_TITLE = "Dictionaries";
    private static RecyclerView sRecyclerView;
    private static DictionaryRecyclerViewAdapter sAdapter;
    private static DictionaryPageFragment sListFragment;

    public DictionaryPageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_list, container, false);
        Context context = view.getContext();
        sRecyclerView = (RecyclerView) view;
        sRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        DictionaryList dictionaryList = DictionaryList.get(context);
        sAdapter = new DictionaryRecyclerViewAdapter(dictionaryList.getDictionaries());
        sRecyclerView.setAdapter(sAdapter);
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

    private void updateUI() {
        DictionaryList dictionaryList = DictionaryList.get(getActivity());
        List<Element> dictionaries = dictionaryList.getDictionaries();
        if (sAdapter == null) {
            sAdapter = new DictionaryRecyclerViewAdapter(dictionaries);
            sRecyclerView.setAdapter(sAdapter);
        } else {
            sAdapter.setElements(dictionaries);
            sAdapter.notifyDataSetChanged();
        }
    }

    public static Fragment newInstance(List<Element> dictionaries) {
        if (sListFragment == null) {
            sListFragment = new DictionaryPageFragment();
        }
        if (dictionaries != null) {
            sAdapter.setElements(dictionaries);
            sAdapter.notifyDataSetChanged();
        }
        return sListFragment;
    }

    public static String getTitle() {
        return PAGE_TITLE;
    }

    public interface OnFragmentInteractionListener {
        // TODO: UPDATE argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
