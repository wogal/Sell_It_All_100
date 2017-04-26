package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Button;

import JavaClasses_pkg_100.Storage_Helper_Class;
import Post_Manager_v11.AdapterDrops;

public class Activity_Mngr_Posts_v11 extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mBut_test_1;
    private Button mBut_test_2;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        setContentView( R.layout.activity__mngr__posts_v11 );
        mRecyclerView = (RecyclerView) findViewById( R.id.Rec_Posts_v11 );
        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( mLayoutManager );
        mRecyclerView.setAdapter( new AdapterDrops( this ) );
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView( mRecyclerView );
        // standard init stuff
        mBut_test_1 = (Button) findViewById( R.id.But_Invoke_test_1_v11 );
        mBut_test_1.setOnClickListener( this );
        mBut_test_2 = (Button) findViewById( R.id.But_Invoke_test_2_v11 );
        mBut_test_2.setOnClickListener( this );

    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_Invoke_test_1_v11: {
                invoke_test_1();
                break;
            }
            case R.id.But_Invoke_test_2_v11: {
                break;
            }
        }
    }

    private void invoke_test_1 () {
        Storage_Helper_Class.MakeOrCheck_If_Folder_Exists("dogs_with_logs cats_with_dogs");
    }
}
