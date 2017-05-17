package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ActivityText_2_Speech_v19 extends AppCompatActivity implements View.OnClickListener {

    private Button mBut_Txt_2_Speech;
    private Button mBut_Exit;
    private TextView mTxtV_text;
    private Context mContext;

    //   public ActivityText_2_Speech_v19 () {
    //  }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_text_2__speech_v19 );
        String value;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getString("wogal");
            //The key argument here must match that used in the other activity

    //    mContext = (Context) extras.getSerializable( "context");


        }



        mBut_Txt_2_Speech = (Button) findViewById( R.id.But_text_2_speech_v19 );
        mBut_Txt_2_Speech.setOnClickListener( this );
        mTxtV_text = (TextView) findViewById( R.id.txtV_text_v19 );
        mBut_Exit = (Button) findViewById( R.id.But_text_2_speech_exit_v19 );
        mBut_Exit.setOnClickListener( this );

    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_text_2_speech_v19: {
                Start_Speech_Text_1( this );
                break;
            }
            case R.id.But_text_2_speech_exit_v19:{
               this.setResult( 100 );
                this.finish();
                break;
            }
        }
    }

    private void Start_Speech_Text_1 (Context _context) {
        String mStr = "";
        Activity mActivity = (Activity) _context;
        Class<? extends String> cls;

        cls = RecognizerIntent.ACTION_RECOGNIZE_SPEECH.getClass();


        Intent mIntent = null;
        try {
            mIntent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
            //    mIntent = new Intent(  );
        } catch (Exception e) {
            e.printStackTrace();
            mStr = e.getMessage();
            mStr += "";
        }

        mIntent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );

        mIntent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault() );
        mIntent.putExtra( RecognizerIntent.EXTRA_PROMPT, "Say Som it" );

        try {

            startActivityForResult( mIntent, 100 );
        } catch (Exception e) {
            e.printStackTrace();
            mStr = e.getMessage();
            mStr += "";
        }
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );

                    mTxtV_text.setText( text.get( 0 ) );
                    int a;

                    a = text.get( 0 ).length();



                }
                break;
            }
        }
    }


}
