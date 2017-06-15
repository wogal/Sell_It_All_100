package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;
// wogal
public class test_acticity_v100 extends AppCompatActivity {

    public int test = 0;
    public String mStr = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.test_acticity_v100 );
    }

    public void Speech_Recon (View view) {
        Start_Speech_Text_1 ( this);
    }

    public void Exit (View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra( "Spoken_Text", "Wogal Heck" );
        this.setResult( 123 );
        this.finish();
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
        test = resultCode;
        mStr = data.getStringExtra( "Spoken_Text" );

    }


}
