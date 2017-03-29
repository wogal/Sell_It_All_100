package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import JavaClasses_pkg_100.Adapterdrops;

public class Activity_Recycle_v6 extends AppCompatActivity implements View.OnClickListener {
    private Button mBut_Done_v6;
    private RecyclerView mRecView;




    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v6 );
        mBut_Done_v6 = (Button) findViewById( R.id.But_done_v6 );
        mBut_Done_v6.setOnClickListener( this );
        mRecView = (RecyclerView) findViewById( R.id.Rec_View_v6 );
        mRecView.setAdapter( new Adapterdrops( this ) );
        LinearLayoutManager manger = new LinearLayoutManager( this );
        mRecView.setLayoutManager( manger );

    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_done_v6: {
                this.finish();
                break;
            }
        }
    }
}
