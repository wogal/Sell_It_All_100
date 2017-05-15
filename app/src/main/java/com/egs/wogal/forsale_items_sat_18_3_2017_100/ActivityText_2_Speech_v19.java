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

public class ActivityText_2_Speech_v19 extends AppCompatActivity implements View.OnClickListener{

    private Button mBut_Txt_2_Speech;
    private TextView mTxtV_text;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_text_2__speech_v19 );
        mBut_Txt_2_Speech = (Button) findViewById( R.id.But_text_2_speech_v19);
        mBut_Txt_2_Speech.setOnClickListener( this );
        mTxtV_text = (TextView) findViewById( R.id.txtV_text_v19);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()){
            case R.id.But_text_2_speech_v19:{
                Start_Speech_Text_1(this);
                break;
            }
        }
    }


    private void Start_Speech_Text_1 (Context _context) {
        String mStr = "";
        Activity mActivity = (Activity) _context;



        //    Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH );

        //  Intent i = new Intent( _context, Text_Input_Dialog_v14.class );
        //   Intent i = new Intent( _context, Text_Input_Dialog_v14.class );
        //  Intent i = new Intent( _context, Text_Input_Dialog_v14.class );
        //   Intent i = new Intent( _context, Text_Input_Dialog_v14.class );


        Intent i = null;
        try {
            i = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        } catch (Exception e) {
            e.printStackTrace();
            mStr = e.getMessage();
            mStr += "";
        }


        try {
            i.putExtra( RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        } catch (Exception e) {
            e.printStackTrace();
            mStr = e.getMessage();
            mStr += "";
        }


        //   i.putExtra( RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault() );
        //   i.putExtra( RecognizerIntent.EXTRA_PROMPT, "Say Som it" );

        try {
            startActivityForResult( i, 100 );
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
