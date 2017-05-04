package Dialog_Numeric_Input_v17;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;

/**
 * Created by wogal on 5/2/2017.
 */


// TODO code needs numeric mask help is in the " Custom XML ( Styles ) " then sub folder -> " Masked_Keyboard_Input " folder


public class Numeric_Input_Dialog_v17 implements View.OnClickListener {
    private Numeric_Inp_Dia_Key_Response_Interface_v17 mNumeric_imp_dea_key_response_Listerner;

    // Alert mText_Input_Dialog Vars
    private AlertDialog mText_Input_Dialog;
    private View mDialog_View;
    private Context mContext;
    private AlertDialog.Builder mBuilderItemName;

    // text input hint on input text
    private EditText mEditText_Input;
    // dialog header text
    private TextView mTextView_header;
    // done button
    private Button mBtn_done;


    public Numeric_Input_Dialog_v17 (Context _context, String _Dialog_Title) {
        mContext = _context;

        invoke_Dialog();
        if (_Dialog_Title != null) {
            if (_Dialog_Title.length() > 2)
                mTextView_header.setText( _Dialog_Title );
        }
    }

    private void invoke_Dialog () {
        mBuilderItemName = new AlertDialog.Builder( mContext );
        Activity mActivy = (Activity) mContext;
        mDialog_View = mActivy.getLayoutInflater().inflate( R.layout.dialog_numeric_input_v17, null );
        mBuilderItemName.setCancelable( false );

        // text input hint on input text
        mEditText_Input = (EditText) mDialog_View.findViewById( R.id.edit_text_input_v17 );
        // set either single or multi line inputs

        //    mEditText_Input.setInputType( InputType.PHONE );
        mEditText_Input.setRawInputType( Configuration.KEYBOARD_12KEY );
        //    mEditText_Input.setDi

        mEditText_Input.setLines( 1 );


        // dialog header text
        mTextView_header = (TextView) mDialog_View.findViewById( R.id.text_view_txt_input_header_v17 );

        // dialog done button
        mBtn_done = (Button) mDialog_View.findViewById( R.id.But_item_name_done_v17 );
        mBtn_done.setOnClickListener( this );
    }


    private void Invoke_Call_Back (Dialog_Result _dialog_result) {
        if (mNumeric_imp_dea_key_response_Listerner == null)
            return;
        String str;
        str = mEditText_Input.getText().toString();
        if (str.isEmpty()) {
            str = "Empty";
        }
        mNumeric_imp_dea_key_response_Listerner.CallBack_Key_response( _dialog_result, str );
        mText_Input_Dialog.dismiss();
    }


    public Numeric_Input_Dialog_v17 show () {
        mBuilderItemName.setView( mDialog_View );
        mText_Input_Dialog = mBuilderItemName.create();
        mText_Input_Dialog.show();
        return this;
    }


    public void setEventListener_Call_Back (Numeric_Inp_Dia_Key_Response_Interface_v17 _eventListener) {
        mNumeric_imp_dea_key_response_Listerner = _eventListener;
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_item_name_done_v17: {
                Invoke_Call_Back( Dialog_Result.Dialog_Done );
                break;
            }
        }
    }


}


























