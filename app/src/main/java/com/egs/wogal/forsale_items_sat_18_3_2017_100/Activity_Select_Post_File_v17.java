package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import Select_Post_File_Recylcle.AdapterDrops_Post_Files;

public class Activity_Select_Post_File_v17 extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__select__post__file_v17 );
        // init
        mRecyclerView = (RecyclerView) findViewById( R.id.RecView_SelectPost_File_v17 );
        LinearLayoutManager manager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( manager );
        mRecyclerView.setAdapter( new AdapterDrops_Post_Files( this ) );
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView( mRecyclerView );


    }
}
