package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class Activity_Mngr_Posts_v11 extends AppCompatActivity {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__mngr__posts_v11 );
        mRecyclerView = (RecyclerView) findViewById( R.id.Rec_Posts_v11 );

    }
}
