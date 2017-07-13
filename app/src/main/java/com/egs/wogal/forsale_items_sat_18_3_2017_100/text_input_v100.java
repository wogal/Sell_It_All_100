package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import Dialog_Text_Input_v14.Text_Inp_Dia_Key_Response_Interface_v14;
import FaceBook_Java_Helpers.Text_Input_Consts;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Line_Type;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result_Enum;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


// C:\Users\wogal\Desktop\Cloaned_forSaleItems\Earles_For_Sale_Item\Sell_It_All_100\app\build.gradle

// wogal
public class text_input_v100 extends AppCompatActivity {

    public int test = 0;
    public String mStr = "";
    @BindView(R.id.But_item_name_done_v100)
    Button mBtn_done;
    // text input hint on input text
    private EditText mEditText_Input;
    // dialog header text
    private TextView mTextView_header;
    // done button
    //    Button mBtn_done;
    // single of multi line input
    private Dialog_Line_Type mSingleLine_input;
    // speech to text
    private Button mButSpeech_2_Text;
    private int aInt = 0;
    private Text_Inp_Dia_Key_Response_Interface_v14 mText_inp_dia_key_response_Listerner;

    @OnClick(R.id.But_item_name_done_v100)
    public void ButtOnclick_Done (View view) {
        Toast.makeText( this, "done but (100)", Toast.LENGTH_LONG ).show();
        // exit with result's
        Intent data = new Intent();
        String mstr = mEditText_Input.getText().toString();
         data.putExtra( Text_Input_Consts.txt_inp_text,mstr );
        data.putExtra( Text_Input_Consts.txt_inp_text,"Earle_100");
        data.putExtra( Text_Input_Consts.txt_inp_source, "keyboard" );

        setResult( RESULT_OK, data );
        finish();
    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.text_input_v100 );
        ButterKnife.bind( this );
        //   ButterKnife.inject(this);
        // text input hint on input text
        mEditText_Input = (EditText) findViewById( R.id.edit_text_input_v14 );
        if (Dialog_Line_Type.Dialog_Single_Line == mSingleLine_input || 1 == 1) {
            mEditText_Input.setInputType( InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
            mEditText_Input.setLines( 1 );
        } else {
            //  mEditText_Input.setInputType(  );
            mEditText_Input.setLines( 3 );
        }

        mEditText_Input.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey (View v, int keyCode, KeyEvent event) {
                aInt = v.getId();
                return false;
            }
        } );

        mEditText_Input.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction (TextView v, int actionId, KeyEvent event) {
                String mStr = "";
                aInt = 12;
                if ((actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService( Activity.INPUT_METHOD_SERVICE );
                    //Hide:
                    imm.toggleSoftInput( InputMethodManager.HIDE_IMPLICIT_ONLY, 0 );
                    //Show -- for ref only
                    //    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                    // get text
                    mStr = mEditText_Input.getText().toString();
                    return true;
                }
                return false;
            }
        } );

        mEditText_Input.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                aInt = 12;
            }
        } );
    }


    private void Invoke_Call_Back (Dialog_Result_Enum _dialog_result) {
        if (mText_inp_dia_key_response_Listerner == null)
            return;
        String str;
        str = mEditText_Input.getText().toString();
        if (str.isEmpty()) {
            str = "Empty";
        }
        mText_inp_dia_key_response_Listerner.CallBack_Key_response( _dialog_result, str );
        //   mText_Input_Dialog.dismiss();
    }


    public void Speech_Recon (View view) {
        Start_Speech_Text_1( this );
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
