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



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v4 );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mBut_test = (Button) findViewById( R.id.But_test_button_v4 );
        mBut_test.setOnClickListener( this );

        m_txt_view = (TextView) findViewById( R.id.txt_test_v4 );
        m_txt_view.setText( "-- --" );

        mBut_Done = (Button) findViewById( R.id.But_Done_v4 );
        mBut_Done.setOnClickListener( this );

    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_Done_v4: {
                this.finish();
                break;
            }
            case R.id.But_test_button_v4: {
            // test camera
                Intent intent = new Intent( this, Activity_Camera_v5.class );
                startActivity( intent );
                break;
            }
        }
    }
}

