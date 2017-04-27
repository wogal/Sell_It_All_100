package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class activity_options_v1 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Wogal v1";
    private Button mBut_Test_v4;
    private TextView mTxtHeaderText_v1;
    private TextView mTxtItemName_v1;
    private TextView mTxtView_txt_in_v14;


    private Button mButMake_NEW_POST;

    // Alert Dialog_ItemName Vars
    private EditText mTxtInputText_v2;
    private TextView mTextEntersTextField_v2;
    // Alert Dialog_ItemName Vars
    private AlertDialog Dialog_ItemName;
    private View mViewItemName;

    private AlertDialog.Builder mBuilderItemName;


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putCharSequence( "mTxtItemName_v1", mTxtItemName_v1.getText() );
        Log.d( TAG, "onSaveInstanceState" );
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        CharSequence str = savedInstanceState.getCharSequence( "mTxtItemName_v1" );
        if (str != null) {
            mTxtItemName_v1.setText( str );
        }
        Log.d( TAG, "onRestoreInstanceState" );
    }

    //endregion   Activity frf

    //region Description -- Activity States
    //region Description
    @Override
    protected void onStart () {
        Log.d( TAG, "osStart" );
        super.onStart();
    }

    @Override
    protected void onRestart () {
        Log.d( TAG, "  Wogal onRestart " );
        super.onRestart();
    }

    @Override
    protected void onResume () {
        Log.d( TAG, "  Wogal onResume " );
        super.onResume();
    }

    @Override
    protected void onPause () {
        Log.d( TAG, "  Wogal onPause " );
        super.onPause();
    }

    @Override
    protected void onStop () {
        Log.d( TAG, "  Wogal onStop " );
        super.onStop();
    }

    @Override
    protected void onDestroy () {
        Log.d( TAG, "  Wogal onDestroy " );
        super.onDestroy();
    }
    //endregion

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v1 );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        mTxtHeaderText_v1 = (TextView) findViewById( R.id.txt_header_v1 );
        mTxtHeaderText_v1.setOnClickListener( this );

        mTxtItemName_v1 = (TextView) findViewById( R.id.txt_v_sales_item_name_v1 );
        mTxtItemName_v1.setOnClickListener( this );


        mBut_Test_v4 = (Button) findViewById( R.id.But_test_activity_v1 );
        mBut_Test_v4.setOnClickListener( this );

        mButMake_NEW_POST = (Button) findViewById( R.id.But_make_NEW_Post_v1 );
        mButMake_NEW_POST.setOnClickListener( this );
    }


    @Override
    public void onLowMemory () {
        super.onLowMemory();
        Log.d( TAG, " ***   onLowMemory   **" );
    }


    @Override
    public void onClick (View v) {
        if (1 == 1) {
            switch (v.getId()) {
                case R.id.But_make_NEW_Post_v1: {
                    mBuilderItemName = new AlertDialog.Builder( activity_options_v1.this );
                    mViewItemName = getLayoutInflater().inflate( R.layout.dialog_txt_input_v14, null );
                    mTextEntersTextField_v2 = (TextView) mViewItemName.findViewById( R.id.text_view_sales_item_name_v14 );
                    // stops AlertDialog from dismissing on touch out side the AlertDialog
                    mBuilderItemName.setCancelable( false );
                    // set key listeners & initialize Dialog
                    mTxtView_txt_in_v14 = (TextView) mViewItemName.findViewById( R.id.text_view_txt_input_header_v14 );
                    mTxtView_txt_in_v14.setText( "New Post Name " );

                    mBuilderItemName.setView( mViewItemName );
                    Dialog_ItemName = mBuilderItemName.create();
                    Dialog_ItemName.show();

                    Intent intent = new Intent( this, Activity_MakeSalesItem_v8.class );
                    startActivity( intent );
                    break;
                }
            }
        }
    }

}

