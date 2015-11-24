package com.example.nixoid.taksitest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;

import com.example.nixoid.taksitest.rest.RestService;
import com.example.nixoid.taksitest.rest.models.Geo;
import com.example.nixoid.taksitest.rest.models.GeoObject;
import com.example.nixoid.taksitest.rest.models.Suggest;
import com.example.nixoid.taksitest.rest.models.Suggestion;
import com.example.nixoid.taksitest.utils.GeoAdapter;
import com.example.nixoid.taksitest.utils.SuggestionAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_search)
@OptionsMenu(R.menu.menu)
public class SearchFragment extends Fragment {

    private final static String FILTER_ID = "filter_id";

    private List<Suggestion> sugg = new ArrayList<>();
    private List<GeoObject> geo = new ArrayList<>();
    private SuggestionAdapter suggestionAdapter;
    private GeoAdapter geoAdapter;
    private boolean stopChanged = true;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @OptionsMenuItem(R.id.action_me)
    MenuItem menuItem;

    @ViewById(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @ViewById(R.id.frame_container)
    View container;

    @ViewById(R.id.recycle_view)
    RecyclerView recyclerView;


    //Loader for SuggestList//

    private void loadData() {
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Suggestion>>() {
            @Override
            public Loader<List<Suggestion>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Suggestion>> loader = new AsyncTaskLoader<List<Suggestion>>(getActivity()) {
                    @Override
                    public List<Suggestion> loadInBackground() {
                        return getSuggestionList();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<Suggestion>> loader, final List<Suggestion> sugg) {
                suggestionAdapter = (new SuggestionAdapter(getSuggestionList(), new SuggestionAdapter.ViewHolder.ClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        stopChanged = false;
                        SharedPreferences preferences = PreferenceManager
                                .getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("adress", sugg.get(position).getName());
                        editor.apply();
                        autoCompleteTextView.setText(sugg.get(position).getName());
                        hideSoftKeyboard();
                        sugg.clear();
                        getGeoObjects(getAdress(getContext()));
                    }

                }));
                recyclerView.setAdapter(suggestionAdapter);
            }

            @Override
            public void onLoaderReset(Loader<List<Suggestion>> loader) {
            }
        });
    }

    public static String getAdress(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("adress", "");
    }

    //Loader for GeoObjects//

    private void loadGeo() {
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<GeoObject>>() {
            @Override
            public Loader<List<GeoObject>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<GeoObject>> loader = new AsyncTaskLoader<List<GeoObject>>(getActivity()) {
                    @Override
                    public List<GeoObject> loadInBackground() {
                        return getObjectsList();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<GeoObject>> loader, final List<GeoObject> geoS) {
                geoAdapter = (new GeoAdapter(getObjectsList() , new GeoAdapter.ViewHolder.ClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        MapsFragment_ fragment = new MapsFragment_();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", geoS.get(position).getName());
                        bundle.putDouble("lat", geoS.get(position).getLat());
                        bundle.putDouble("lon", geoS.get(position).getLon());
                        bundle.putString("desc", geoS.get(position).getDesc());
                        hideSoftKeyboard();
                        stopChanged = true;
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.frame_container,
                                fragment).addToBackStack(null).commit();
                    }

                }));
                recyclerView.setAdapter(geoAdapter);
            }

            @Override
            public void onLoaderReset(Loader<List<GeoObject>> loader) {
            }
        });
    }

    private void hideSoftKeyboard(){
        if(getActivity().getCurrentFocus()!=null && getActivity().getCurrentFocus() instanceof AutoCompleteTextView){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
        }
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,int count) {
            try {
                if (s.length() > 2 && stopChanged) {
                    if (sugg.size() != 0) {
                        sugg.clear();
                    }
                    getSuggestion(s.toString());
                }
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        autoCompleteTextView.addTextChangedListener(filterTextWatcher);
    }

    @Background
    void getGeoObjects(String name) {
        RestService restService = new RestService();
        Geo workGeo = restService.getGeo("clid", "apikey", 57.6311059, 39.8366977, name);
        if (workGeo.getError() == 0) {
            for (GeoObject geoObject : workGeo.getGeoObjects()) {
                geo.add(new GeoObject(geoObject.getName(), geoObject.getDesc(), geoObject.getAccuracy(), geoObject.getCountry(),
                        geoObject.getLocality(), geoObject.getThoroughfare(), geoObject.getPremise(),
                        geoObject.getLat(), geoObject.getLon(), geoObject.getActive()));
            }
            loadGeo();
        }
    }

    @Background
    void  getSuggestion(String newText) {
        RestService restService = new RestService();
        Suggest workSugget = restService.getSuggest("clid", "apikey", 57.6311059, 39.8366977, newText);
        if (workSugget.getError() == 0) {
            for (Suggestion geoSuggest : workSugget.getSuggestions()) {
                    sugg.add(new Suggestion(geoSuggest.getName(), geoSuggest.getDesc(),
                            geoSuggest.getLat(), geoSuggest.getLon()));
                }
                loadData();
            }
        }


    private List<Suggestion> getSuggestionList() {
        return sugg;
    }

    private List<GeoObject> getObjectsList() {
        return geo;
    }
}