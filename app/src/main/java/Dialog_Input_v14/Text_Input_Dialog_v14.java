package Dialog_Input_v14;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;

/**
 * Created by wogal on 4/27/2017.
 */

public class Text_Input_Dialog_v14 implements View.OnClickListener {
    private Text_Inp_Dia_Key_Response_Interface_v14 mText_inp_dia_key_response_Listerner;

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


    public Text_Input_Dialog_v14 (Context _context, String _Dialog_Title) {
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
        mDialog_View = mActivy.getLayoutInflater().inflate( R.layout.dialog_txt_input_v14, null );
        mBuilderItemName.setCancelable( false );

        // text input hint on input text
        mEditText_Input = (EditText) mDialog_View.findViewById( R.id.edit_text_input_v14 );

        // dialog header text
        mTextView_header = (TextView) mDialog_View.findViewById( R.id.text_view_txt_input_header_v14 );

        // dialog done button
        mBtn_done = (Button) mDialog_View.findViewById( R.id.But_item_name_done_v14 );
        mBtn_done.setOnClickListener( this );


    }

    private void Invoke_Call_Back (Dialog_Result _dialog_result) {
        if (mText_inp_dia_key_response_Listerner == null)
            return;
        String str;
        str = mEditText_Input.getText().toString();
        if (str.isEmpty()) {
            str = "Empty";
        }
        mText_inp_dia_key_response_Listerner.CallBack_Key_response( _dialog_result, str );
        mText_Input_Dialog.dismiss();
    }


    public Text_Input_Dialog_v14 show () {
        mBuilderItemName.setView( mDialog_View );
        mText_Input_Dialog = mBuilderItemName.create();
        mText_Input_Dialog.show();
        return this;
    }


    public void setEventListener_Call_Back (Text_Inp_Dia_Key_Response_Interface_v14 _eventListener) {
        mText_inp_dia_key_response_Listerner = _eventListener;
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_item_name_done_v14: {
                Invoke_Call_Back( Dialog_Result.Dialog_Done );
                break;
            }
        }
    }
}
