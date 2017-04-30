package Dialog_Timed_Notice;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;

/**
 * Created by wogal on 4/28/2017.
 */

public class Dialog_Timed_Notice_v15 implements View.OnClickListener {
    private Dialog_Timed_Interface_v15 mText_inp_dia_key_response_Listerner;

    // Alert mText_Input_Dialog Vars
    private AlertDialog mText_Input_Dialog;
    private View mDialog_View;
    private Context mContext;
    private AlertDialog.Builder mBuilderItemName;

    // dialog header text
    private TextView mTextView_header;
    private String mTextHeader_Copy;
    // dialog status message text
    private TextView mTextView_message;
    // done button
    private Button mBtn_done;
    // dialog active duration timer
    private CountDownTimer mCountDownTimer;

    public Dialog_Timed_Notice_v15 (Context _context, String _Dialog_Title, String _Dialog_notice) {
        mContext = _context;
        invoke_Dialog();

        if (_Dialog_Title != null) {
            if (_Dialog_Title.length() > 2)
                mTextView_header.setText( _Dialog_Title );
            mTextHeader_Copy = _Dialog_Title;
        }
        if (_Dialog_notice != null) {
            if (_Dialog_notice.length() > 2) {
                mTextView_message.setText( _Dialog_notice );
            }
        }
    }

    private void invoke_Dialog () {

        mBuilderItemName = new AlertDialog.Builder( mContext );

        final Activity mActivy = (Activity) mContext;
        mDialog_View = mActivy.getLayoutInflater().inflate( R.layout.dialog_timed_notice_v15, null );
        mBuilderItemName.setCancelable( false );
        // dialog header text
        mTextView_header = (TextView) mDialog_View.findViewById( R.id.text_view_header_v15 );
        // dialog message text
        mTextView_message = (TextView) mDialog_View.findViewById( R.id.text_view_message_v15 );
        // dialog done button
        mBtn_done = (Button) mDialog_View.findViewById( R.id.But_item_name_done_v15 );
        mBtn_done.setOnClickListener( this );
        mCountDownTimer = new CountDownTimer( 10000, 1000 ) {
            @Override
            public void onTick (long millisUntilFinished) {
                if (true) {
                    String mStr;
                    int secsLeft;
                    secsLeft = (int) (millisUntilFinished / 1000) % 60;

                    mStr = String.format( "%s (%d)", mTextHeader_Copy, secsLeft );

                    mTextView_header.setText( mStr );
                }
            }

            @Override
            public void onFinish () {
                Invoke_Call_Back( Dialog_Result.Dialog_Done );
            }
        }.start();

    }


    public void setEventListener_Call_Back (Dialog_Timed_Interface_v15 _eventListener) {
        mText_inp_dia_key_response_Listerner = _eventListener;
    }


    private void Invoke_Call_Back (Dialog_Result _dialog_result) {
        mText_Input_Dialog.dismiss();
        if (mText_inp_dia_key_response_Listerner == null)
            return;
        mText_inp_dia_key_response_Listerner.CallBack_Key_response( _dialog_result );
    }


    public Dialog_Timed_Notice_v15 show () {
        mBuilderItemName.setView( mDialog_View );
        mText_Input_Dialog = mBuilderItemName.create();
        mText_Input_Dialog.show();
        return this;
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_item_name_done_v15: {
                Invoke_Call_Back( Dialog_Result.Dialog_Done );
                break;
            }
        }
    }

}
