package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_Main_v0 extends AppCompatActivity implements View.OnClickListener {

    Button mBtn_startselling_vn;
    private AlertDialog Dialog_ItemName;
    private TextView mTxtView_init_header_v12;
    private TextView mTxtView_init_action_v12;
    private AlertDialog.Builder mBuilderItemName;
    private View mViewItemName;
    private Button mBut_name_item_GoBack;


    // start sreen
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        setContentView( R.layout.layout_v0 );
        mBtn_startselling_vn = (Button) findViewById( R.id.butt_lets_start_selling_v0 );
        mBtn_startselling_vn.setOnClickListener( this );
        // invoke system initialize Actions
        Initialize_System( this );

    }

    private void Initialize_System (Activity _activity_main_v0) {
        // make sure system folders are set up


        if (false) {

        } else {
            mBuilderItemName = new AlertDialog.Builder( Activity_Main_v0.this );
            mViewItemName = getLayoutInflater().inflate( R.layout.layout_init_alrt_dialog_v12, null );
            mBut_name_item_GoBack = (Button) mViewItemName.findViewById( R.id.But_item_name_done_v12 );
            mTxtView_init_header_v12 = (TextView) mViewItemName.findViewById( R.id.text_view_init_header_v12 );
            mTxtView_init_action_v12 = (TextView) mViewItemName.findViewById( R.id.text_view_init_actions_v12 );
            mTxtView_init_header_v12.setText( "Init Actions (v12)" );
            // stops AlertDialog from dismissing on touch out side the AlertDialog
            mBuilderItemName.setCancelable( false );
            mBut_name_item_GoBack.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    switch (v.getId()) {
                        case R.id.But_item_name_done_v12:
                            Dialog_ItemName.dismiss();
                            break;
                    }
                }
            } );
            mBuilderItemName.setView( mViewItemName );
            Dialog_ItemName = mBuilderItemName.create();
            Dialog_ItemName.show();
        }
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.butt_lets_start_selling_v0: {
                Intent intent = new Intent( this, activity_options_v1.class );
                startActivity( intent );
            }
        }
    }
}
