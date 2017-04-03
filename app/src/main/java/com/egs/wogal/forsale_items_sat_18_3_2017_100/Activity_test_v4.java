package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_test_v4 extends AppCompatActivity implements View.OnClickListener {

    private Button mBut_test;
    private TextView m_txt_view;
    private Button mBut_Done;
    private Button mbut_Recyclerview;
    private Button mBut_FacebookIntergration;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v4 );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        mBut_test = (Button) findViewById( R.id.But_Invoke_Camera_v4 );
        mBut_test.setOnClickListener( this );

        m_txt_view = (TextView) findViewById( R.id.txt_test_v4 );
        m_txt_view.setText( "-- --" );

        mBut_Done = (Button) findViewById( R.id.But_Done_v4 );
        mBut_Done.setOnClickListener( this );

        mbut_Recyclerview = (Button) findViewById( R.id.But_recyclerview_v4 );
        mbut_Recyclerview.setOnClickListener( this );

        mBut_FacebookIntergration = (Button) findViewById( R.id.But_facebookIntergration_v4 );
        mBut_FacebookIntergration.setOnClickListener( this );


    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_facebookIntergration_v4:{
             Intent intentFaceBook_v10 = new Intent( this,Activity_FaceBook_v10.class );
                startActivity( intentFaceBook_v10 );
                break;
            }
            case R.id.But_Done_v4: {
                this.finish();
                break;
            }
            case R.id.But_recyclerview_v4: {
                // test recyclerview
                Intent intent = new Intent( this, Activity_Recycle_v6.class );
                startActivity( intent );
                break;
            }
            case R.id.But_Invoke_Camera_v4: {
                // test camera
                Intent intent = new Intent( this, Activity_Camera_v5.class );
                startActivity( intent );
                break;
            }
        }
    }




}

