package com.example.fabian.mm.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fabian.mm.R;
import com.example.fabian.mm.fragments.HistoryFragment;
import com.example.fabian.mm.fragments.SearchFragment;
import com.example.fabian.mm.interfaces.ActionsListener;
import com.facebook.stetho.Stetho;

public class HomeActivity extends AppCompatActivity implements ActionsListener, View.OnClickListener {

    private Button btnSearch;
    private Button btnHistory;
    private SearchFragment searchFragment;
    private HistoryFragment historyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        initializeViews();

        searchFragment = new SearchFragment();
        changeFragment(searchFragment);
        historyFragment = new HistoryFragment();
    }

    private void initializeViews() {
        btnHistory = (Button) findViewById(R.id.history_button_fragment);
        btnSearch = (Button) findViewById(R.id.search_button_fragment);
        btnHistory.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_placeholder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_button_fragment:
                changeFragment(searchFragment);
                break;
            case R.id.history_button_fragment:
                changeFragment(historyFragment);
                break;
            default:
                break;
        }
    }
}
