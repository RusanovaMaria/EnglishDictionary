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

import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.element.Word;
import com.rusanova.englishdictionary.list.WordList;
import com.rusanova.englishdictionary.recyclerviewadapter.WordRecyclerViewAdapter;

import java.util.List;

public class WordListFragment extends Fragment {
    private static final int PAGE_NUMBER = 1;
    private RecyclerView mRecyclerView;
    private WordRecyclerViewAdapter mAdapter;

    public WordListFragment() {

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
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        WordList wordList = WordList.get(getActivity());
        mAdapter = new WordRecyclerViewAdapter(wordList.getWords());
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

    private void updateUI() {
        WordList wordList = WordList.get(getActivity());
        List<Word> words = wordList.getWords();
        if (mAdapter == null) {
            mAdapter = new WordRecyclerViewAdapter(words);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setElements(words);
            mAdapter.notifyDataSetChanged();
        }
    }

    public static int getPageNumber() {
        return PAGE_NUMBER;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
