package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import JavaClasses_pkg_100.Sound_Play_Record_Helper;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.text_view_sales_item_name_v2;
import static java.lang.Math.log;
import static java.lang.Math.pow;

// import static android.view.KeyEvent.KEYCODE_ENTER;
// import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.text_view_sales_item_name_v2;


// import com.egs.wogal.for_sale_app_100.R;
// import static com.egs.wogal.for_sale_app.R.id.text_view_sales_item_name_v2;
//     Toast.makeText( getApplicationContext(), " Wogal Test Activity -> ", Toast.LENGTH_LONG ).show();
// wogal

public class activity_options_v1 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Wogal v1";
    private Button mBut_Test_v4;
    private TextView mTxtHeaderText_v1;
    private TextView mTxtItemName_v1;
    private Button mBtnSalesItemName_v1;
    private Button mBtnVoiceMeme_v1;
    private Button mBut_name_item_GoBack;

    private Button mButSound_Start_Stop_Play;
    private Button mButSound_Start_Stop_Record;
    private Button mBut_Sound_Done;
    private Button mBut_Sound_Stop;
    private Button mButRecordTimrLeft;


    private Button mButMakeSalesItem;
    private boolean mTestBoolExecuteTrue = false;
    private int mRecordTimeLeft = 0;

    private Sound_Play_Record_Helper mSound_Play_Record_Helper = null;
    private ProgressBar pb;
    // Alert Dialog_ItemName Vars
    private EditText mTxtInputText_v2;
    private TextView mTextEntersTextField_v2;
    // Alert Dialog_ItemName Vars
    private AlertDialog Dialog_ItemName;
    private View mViewItemName;
    // Alert Dialog_Sound Vars
    private AlertDialog Dialog_SoundRecord;
    private View mViewSound;

    private CountDownTimer mmCountDownTimer;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            String _str = "----";
            int scalednum;
            int Max = 255;
            int _amp = msg.arg1;
            scalednum = (int) (log( _amp ) / log( pow( 2, 32 ) ) * Max);
            //  scalednum = value/ (Integer.MAX_VALUE/255);
            //  scalednum = _amp % Max + 1;
            pb.setMax( 200 );
            pb.setProgress( scalednum );
        }
    };

    private Handler handleRecTimer = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            String _str;
            if (msg.arg1 >= 0) {
                _str = "" + msg.arg1;
            } else {
                _str = "--";
            }
            mButRecordTimrLeft.setText( _str );
        }
    };


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

        mBtnSalesItemName_v1 = (Button) findViewById( R.id.But_item_name_v1 );
        mBtnSalesItemName_v1.setOnClickListener( this );

        mBtnVoiceMeme_v1 = (Button) findViewById( R.id.But_item_voice_v1 );
        mBtnVoiceMeme_v1.setOnClickListener( this );

        mBut_Test_v4 = (Button) findViewById( R.id.But_test_activity_v1 );
        mBut_Test_v4.setOnClickListener( this );

        mButMakeSalesItem = (Button) findViewById( R.id.But_make_item_v1 );
        mButMakeSalesItem.setOnClickListener( this );
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
                case R.id.But_make_item_v1: {
                    Intent intent = new Intent( this, Activity_MakeSalesItem_v8.class );
                    startActivity( intent );
                    break;
                }
                case R.id.But_test_activity_v1: {
                    Intent intent = new Intent( this, Activity_test_v4.class );
                    startActivity( intent );
                    break;
                }
                case R.id.But_sound_exit_v3: {
                    Dialog_SoundRecord.dismiss();
                    break;
                }

                case R.id.But_sound_stop_v3: {
                    //    mSound_Play_Record_Helper.DummyInVokeEvent(5);
                    pb.setProgress( pb.getProgress() + 5 );
                    break;
                }

                case R.id.But_sound_play_v3: {
                    if (mSound_Play_Record_Helper == null)
                        break;
                    if (mSound_Play_Record_Helper.isM_IsRecording()) {
                        // we are Recording So Disable Play Option
                        mButSound_Start_Stop_Play.setText( "--" );
                        mButSound_Start_Stop_Play.setEnabled( false );
                        break;
                    }
                    if (mSound_Play_Record_Helper.isM_IsPlaying() == true) {
                        // we are already playing SO STPO
                        SoundDoneButton_Ena_dis( true );
                        mSound_Play_Record_Helper.Set_Play_Action( false );
                        // ebnable button
                        mButSound_Start_Stop_Play.setText( "Star Play" );
                        mButSound_Start_Stop_Play.setEnabled( true );
                        mButSound_Start_Stop_Record.setText( "Start Recoding" );
                        mButSound_Start_Stop_Record.setEnabled( true );
                    } else {
                        // disable Record button
                        SoundDoneButton_Ena_dis( false );
                        mButSound_Start_Stop_Record.setText( "-" );
                        mButSound_Start_Stop_Record.setEnabled( false );
                        // we are not playing so we need to start
                        mSound_Play_Record_Helper.Set_Play_Action( true );
                        // ebnable button
                        mButSound_Start_Stop_Play.setText( "-----" );
                        mButSound_Start_Stop_Play.setEnabled( false );
                    }
                    break;
                }
                case R.id.But_sound_record_v3: {
                    if (mSound_Play_Record_Helper == null)
                        break;
                    if (mSound_Play_Record_Helper.isM_IsPlaying() == true)
                        break;

                    if (mSound_Play_Record_Helper.isM_IsRecording() == true) {
                        // im already recording so pls stop me & re enable the buttons
                        ForceStopRecording();
                    } else {
                        // i haven't even started recording to lets go
                        mSound_Play_Record_Helper.Set_Record_Action( true );
                        mButSound_Start_Stop_Record.setText( "Stop Recoding" );
                        mButSound_Start_Stop_Record.setEnabled( true );
                        // disable play
                        mButSound_Start_Stop_Play.setText( "-  -" );
                        mButSound_Start_Stop_Play.setEnabled( false );
                        // enable progress bar
                        pb.setVisibility( View.VISIBLE );
                        // enable record length timer
                        mRecordTimeLeft = 10;
                        if (null == mmCountDownTimer) {
                            mmCountDownTimer = new CountDownTimer( 11000, 1000 ) {
                                @Override
                                public void onTick (long millisUntilFinished) {
                                    Message message = Message.obtain();
                                    mRecordTimeLeft = (int) (millisUntilFinished / 1000);
                                    message.arg1 = mRecordTimeLeft--;
                                    SoundDoneButton_Ena_dis( false );
                                    handleRecTimer.sendMessage( message );
                                }

                                @Override
                                public void onFinish () {
                                    ForceStopRecording();

                                    mmCountDownTimer = null;
                                    Message message = Message.obtain();
                                    message.arg1 = -1;
                                    handleRecTimer.sendMessage( message );
                                }
                            }.start();
                        }
                    }
                    break;
                }
                case R.id.But_item_name_done_v2: {
                    String str;
                    str = mTxtInputText_v2.getText().toString();
                    if (!str.isEmpty()) {
                        mTxtItemName_v1.setText( str );
                    }
                    Dialog_ItemName.dismiss();
                    break;
                }
                case R.id.edit_text_item_name_v2: {
                    Toast.makeText( getApplicationContext(), "hi Item test sale_item_name_101 ", Toast.LENGTH_LONG ).show();
                    break;
                }
                case R.id.But_item_voice_v1: { // SOUND RECORDER
                    AlertDialog.Builder mBuilderSound = new AlertDialog.Builder( activity_options_v1.this );
                    mViewSound = getLayoutInflater().inflate( R.layout.layout_sound_recorder_v3, null );

                    mBut_Sound_Done = (Button) mViewSound.findViewById( R.id.But_sound_exit_v3 );
                    mBut_Sound_Done.setOnClickListener( this );

                    mButSound_Start_Stop_Play = (Button) mViewSound.findViewById( R.id.But_sound_play_v3 );
                    mButSound_Start_Stop_Play.setOnClickListener( this );

                    mButSound_Start_Stop_Record = (Button) mViewSound.findViewById( R.id.But_sound_record_v3 );
                    mButSound_Start_Stop_Record.setOnClickListener( this );

                    mBut_Sound_Stop = (Button) mViewSound.findViewById( R.id.But_sound_stop_v3 );
                    mBut_Sound_Stop.setOnClickListener( this );

                    mButRecordTimrLeft = (Button) mViewSound.findViewById( R.id.But_sound_record_time_v3 );

                    mButRecordTimrLeft.setText( "--" );
                    pb = (ProgressBar) mViewSound.findViewById( R.id.progressBar );
                    pb.setProgress( 10 );
                    pb.setMax( 100 );
                    pb.setProgress( 25 );
                    mBuilderSound.setView( mViewSound );
                    mButSound_Start_Stop_Play.setText( "Start Playing" );
                    mButSound_Start_Stop_Record.setText( "Start Recoding" );

                    if (!mTestBoolExecuteTrue) {
                        mSound_Play_Record_Helper = new Sound_Play_Record_Helper();
                        // set default values
                        pb.setVisibility( View.GONE );
                        if (mSound_Play_Record_Helper.isSafeToPlayFile() == false) {
                            // disable play button
                            mButSound_Start_Stop_Play.setEnabled( false );
                            mButSound_Start_Stop_Play.setText( "----" );
                        }
                        mSound_Play_Record_Helper.setOnAmplitudeEventListener( new Sound_Play_Record_Helper.OnAmplitudeEventCallBack() {
                            @Override
                            public int GotMicAmplitude (String _str, int _amp) {
                                Message message = Message.obtain();
                                message.arg1 = _amp;
                                handler.sendMessage( message );
                                return 0;
                            }
                        } );

                        mSound_Play_Record_Helper.setOnStopTrackEventListener( new Sound_Play_Record_Helper.OnStopTrackEventListener() {
                            @Override
                            public int onStopTrack (int a) {
                                mButSound_Start_Stop_Record.setEnabled( true );
                                mButSound_Start_Stop_Record.setText( "Start Recoding" );
                                mButSound_Start_Stop_Play.setEnabled( true );
                                mButSound_Start_Stop_Play.setText( "Start Play" );
                                SoundDoneButton_Ena_dis( true );
                                pb.setVisibility( View.GONE );
                                return 10;
                            }
                        } );
                        Dialog_SoundRecord = mBuilderSound.create();
                        Dialog_SoundRecord.show();
                    }
                    break;
                }
                case R.id.But_item_name_v1: {
                    AlertDialog.Builder mBuilderItemName = new AlertDialog.Builder( activity_options_v1.this );
                    mViewItemName = getLayoutInflater().inflate( R.layout.layout_v2, null );
                    mTextEntersTextField_v2 = (TextView) mViewItemName.findViewById( text_view_sales_item_name_v2 );
                    mBut_name_item_GoBack = (Button) mViewItemName.findViewById( R.id.But_item_name_done_v2 );
                    mBut_name_item_GoBack.setOnClickListener( this );
                    mTxtInputText_v2 = (EditText) mViewItemName.findViewById( R.id.edit_text_item_name_v2 );
                    mTxtInputText_v2.setOnKeyListener( new View.OnKeyListener() {
                        @Override
                        public boolean onKey (View v, int keyCode, KeyEvent event) {
                            //    Log.d(TAG, "key parsed - " + keyCode );
                            if (KEYCODE_ENTER == keyCode) {
                                String str;
                                str = mTxtInputText_v2.getText().toString();
                                if (!str.isEmpty()) {
                                    mTxtItemName_v1.setText( str );
                                    mTextEntersTextField_v2.setText( str );
                                }
                                mTxtInputText_v2.setText( "" );
                                mTxtInputText_v2.clearFocus();
                                Toast.makeText( getApplicationContext(), "hi Item Key-> " + keyCode + " " + str, Toast.LENGTH_LONG ).show();
                                Dialog_ItemName.dismiss();
                            }
                            return false;
                        }
                    } );
                    mBuilderItemName.setView( mViewItemName );
                    Dialog_ItemName = mBuilderItemName.create();
                    Dialog_ItemName.show();
                    break;
                }
                case R.id.inputLayoutFullName: {
                    Toast.makeText( this, "hi Click", Toast.LENGTH_SHORT ).show();
                }
                break;
            }
        }
    }

    private void SoundDoneButton_Ena_dis (boolean _enable) {
        if (_enable == true) {
            mBut_Sound_Done.setEnabled( true );
            mBut_Sound_Done.setText( "Done" );
        } else {
            mBut_Sound_Done.setEnabled( false );
            mBut_Sound_Done.setText( "--" );
        }
    }

    private void ForceStopRecording () {
        // im already recording so pls stop me & re enable the buttons
        mSound_Play_Record_Helper.Set_Record_Action( false );

        mButSound_Start_Stop_Play.setText( "Start Playing" );
        mButSound_Start_Stop_Play.setEnabled( true );

        mButSound_Start_Stop_Record.setText( "Start Recoding" );
        mButSound_Start_Stop_Record.setEnabled( true );
        // stop and quit the rec timer
        if (mmCountDownTimer != null) {
            mmCountDownTimer.cancel();
            mmCountDownTimer = null;
        }
        mmCountDownTimer = null;
        Message message = Message.obtain();
        message.arg1 = -1;
        handleRecTimer.sendMessage( message );
        // Dis enable progress bar
        pb.setVisibility( View.GONE );
        // enable done button
        SoundDoneButton_Ena_dis( true );
    }
}

