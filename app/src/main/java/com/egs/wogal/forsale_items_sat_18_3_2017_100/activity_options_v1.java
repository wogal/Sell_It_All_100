package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Dialog_Input_v14.Text_Inp_Dia_Key_Response_Interface_v15;
import Dialog_Input_v14.Text_Input_Dialog_v14;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.File_Helper_Items;


public class activity_options_v1 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Wogal v1";
    private Button mBut_Test_v4;
    private TextView mTxtHeaderText_v1;
    private TextView mTxtItemName_v1;
    private TextView mTxtView_txt_in_v14;
    private Intent m_Intent;


    private Button mButMake_NEW_POST;

    // Alert Dialog_ItemName Vars


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
        m_Intent = new Intent( this, Activity_MakeSalesItem_v8.class );

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
                    Text_Input_Dialog_v14 mText_input_dialog = new Text_Input_Dialog_v14( this, "New Post Name" );
                    mText_input_dialog.setEventListener_Call_Back( new Text_Inp_Dia_Key_Response_Interface_v15() {
                        @Override
                        public void CallBack_Key_response (Dialog_Result _dialog_result, String _inputText) {
                            MakeNew_Post_File( _inputText );
                            Toast.makeText( activity_options_v1.this, "Key - " + _dialog_result + " str - " + _inputText, Toast.LENGTH_LONG ).show();
                            TestCode( _inputText );
                            //     startActivity( m_Intent );
                        }
                    } );
                    mText_input_dialog.show();
                    break;
                }
            }
        }
    }

    private void TestCode (String _inputText) {
        String mStr = "";
        File_Helper_Items.Check_4_Valid_File_NameValidity( this,_inputText);
    }

    private void MakeNew_Post_File (String inputText) {


    }
}

