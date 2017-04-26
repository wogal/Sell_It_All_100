package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import Psot_Mangr_v11.AdapterDrops;

public class Activity_Mngr_Posts_v11 extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__mngr__posts_v11 );
        mRecyclerView = (RecyclerView) findViewById( R.id.Rec_Posts_v11 );
        LinearLayoutManager manger = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( manger );
        mRecyclerView.setAdapter( new AdapterDrops( this ) );

    }
}
