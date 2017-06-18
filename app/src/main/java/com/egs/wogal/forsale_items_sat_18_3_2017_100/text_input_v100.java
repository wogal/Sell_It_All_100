package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Line_Type;
import butterknife.OnClick;

// wogal
public class text_input_v100 extends AppCompatActivity {

    public int test = 0;
    public String mStr = "";

    // text input hint on input text
    private EditText mEditText_Input;
    // dialog header text
    private TextView mTextView_header;
    // done button
    private Button mBtn_done;
    // single of multi line input
    private Dialog_Line_Type mSingleLine_input;
    // speech to text
    private Button mButSpeech_2_Text;
    private int aInt = 0;

     @OnClick(R.id.But_item_name_done_v100)
     public void ButtOnclick_Done(View view){
         Toast.makeText( this,"done but",Toast.LENGTH_LONG );
     }



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.text_input_v100 );

     //   mBtn_done = findViewById( R.id. )





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
