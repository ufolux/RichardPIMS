package com.richardlu.richardpims;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class NoDataFound extends ActionBarActivity {

    public void init()
    {

    }
    public void setupView()
    {
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_data_found);
        init();
        setupView();
    }
    /**
     * A placeholder fragment containing a simple view.
     */



}
