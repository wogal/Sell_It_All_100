package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Dialog_Numeric_Input_v17.Numeric_Inp_Dia_Key_Response_Interface_v17;
import Dialog_Numeric_Input_v17.Numeric_Input_Dialog_v17;
import Dialog_Text_Input_v14.Text_Inp_Dia_Key_Response_Interface_v14;
import Dialog_Text_Input_v14.Text_Input_Dialog_v14;
import Get_Locale_Info.Get_Locale_Info;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Line_Type;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;

public class Activity_Post_Details_v16 extends AppCompatActivity implements View.OnClickListener {
    // post details
    private TextView mTextView_Post_Details;
    private Button mBut_Post_Details;

    // post cost
    private TextView mTextView_Post_Cost;
    private Button mBut_Post_Cost;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__post__details_v16 );

        // post details
        mTextView_Post_Details = (TextView) findViewById( R.id.text_view_post_details_v16 );
        mTextView_Post_Details.setOnClickListener( this );
        mBut_Post_Details = (Button) findViewById( R.id.But_post_details_v16 );
        mBut_Post_Details.setOnClickListener( this );

        // post cost
        mTextView_Post_Cost = (TextView) findViewById( R.id.text_view_post_cost_v16 );
        mTextView_Post_Cost.setOnClickListener( this );
        mBut_Post_Cost = (Button) findViewById( R.id.But_post_cost_v16 );
        mBut_Post_Cost.setOnClickListener( this );


    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_post_details_v16:
            case R.id.text_view_post_details_v16: {
                Text_Input_Dialog_v14 mText_input_dialog = new Text_Input_Dialog_v14( this, "Post Detail's", Dialog_Line_Type.Dialog_Mult_Line );
                mText_input_dialog.setEventListener_Call_Back( new Text_Inp_Dia_Key_Response_Interface_v14() {
                    @Override
                    public void CallBack_Key_response (Dialog_Result _dialog_result, String _inputText) {
                        if (_dialog_result == Dialog_Result.Dialog_Done && _inputText.length() > 2) {
                            mTextView_Post_Details.setText( _inputText );
                        }
                    }
                } );
                mText_input_dialog.show();
                break;
            }
            case R.id.But_post_cost_v16:
            case R.id.text_view_post_cost_v16: {
                Numeric_Input_Dialog_v17 mNumeric_input_dialog = new Numeric_Input_Dialog_v17( this, "Post Cost" );
                mNumeric_input_dialog.setEventListener_Call_Back( new Numeric_Inp_Dia_Key_Response_Interface_v17() {
                    @Override
                    public void CallBack_Key_response (Dialog_Result _dialog_result, String _inputText) {
                        if (_dialog_result == Dialog_Result.Dialog_Done && _inputText.length() > 0) {
                            String mStr;
                            mStr = Format_PostCost_String( _inputText );
                            mTextView_Post_Cost.setText( mStr );
                        }
                    }
                } );
                mNumeric_input_dialog.show();
                break;
            }
        }
    }

    private String Format_PostCost_String (String _inputText) {
        String Str_Currency;
        Str_Currency = Get_Locale_Info.Get_Locale_Currency_String();
        Str_Currency = String.format( "%s %s", Str_Currency, _inputText );
        return Str_Currency;
    }
}
