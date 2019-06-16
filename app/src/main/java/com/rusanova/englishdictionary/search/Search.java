package com.rusanova.englishdictionary.search;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;

import com.rusanova.englishdictionary.element.Element;
import com.rusanova.englishdictionary.list.DictionaryList;
import com.rusanova.englishdictionary.list.WordList;
import com.rusanova.englishdictionary.pagefragment.PageFragmentFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Search {
    private static List<Element> sElements;

    public static void execute(final Context context, SearchView searchView, final ViewPager pager) {
        final int pageNumber = pager.getCurrentItem();

        SearchObservable.fromView(searchView)
                .debounce(1, TimeUnit.SECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) throws Exception {
                        if (text.isEmpty()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .map(new Function<String, List<Element>>() {
                    @Override
                    public List<Element> apply(String query) {
                        return map(context, pageNumber, query);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext(pager),
                        onError(),
                        onComplete(searchView));

    }

    private static Consumer<List<Element>> onNext(final ViewPager pager) {
        return new Consumer<List<Element>>() {
            int pageNumber = pager.getCurrentItem();

            @Override
            public void accept(List<Element> elements) throws Exception {
                pager.setCurrentItem(pageNumber);
                PageFragmentFactory.form(pageNumber, elements);
            }
        };
    }

    private static Consumer<Throwable> onError() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        };
    }

    private static Action onComplete(final SearchView searchView) {
        return new Action() {
            @Override
            public void run() throws Exception {
                searchView.clearFocus();
            }
        };
    }

    private static List<Element> map(Context context, int pageNumber, String query) {
        switch (pageNumber) {
            case 0:
                return DictionaryList.get(context).search(query);
            case 1:
                return WordList.get(context).search(query);
        }
        throw new IllegalArgumentException("Обращение к бд за несуществующим типом даных");
    }

}
