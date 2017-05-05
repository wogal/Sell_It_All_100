package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Dialog_Numeric_Input_v17.Numeric_Inp_Dia_Key_Response_Interface_v17;
import Dialog_Numeric_Input_v17.Numeric_Input_Dialog_v17;
import Dialog_Text_Input_v14.Text_Inp_Dia_Key_Response_Interface_v14;
import Dialog_Text_Input_v14.Text_Input_Dialog_v14;
import For_Sale_Item_Object_Pkg.Post_Sales_Master_Object;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Add_Remove_Decoration;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Line_Type;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.System_Locale_Helpers;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.System_Shared_Constants;

import static Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.System_Locale_Helpers.Format_PostCost_String;


public class Activity_Post_Details_v16 extends AppCompatActivity implements View.OnClickListener {

    // post details
    private TextView mTxt_Post_Details_v16;
    private Button mBut_Post_Details;
    // post cost
    private TextView mTxtV_post_cost_v16;
    private Button mBut_Post_Cost;
    // done button
    private Button mBut_post_done;

    private Post_Sales_Master_Object mPost_sales_master_object;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__post__details_v16 );
        String mStr;
        Float mFloat;
        // easy place to hold post data / info ( and keep name consistency )
        mPost_sales_master_object = new Post_Sales_Master_Object();

        // get " Post_Sales_Master_Object " from calling parent
        Intent This_Intent = getIntent();
        mPost_sales_master_object = (Post_Sales_Master_Object) This_Intent.getSerializableExtra( System_Shared_Constants.const_bundle_post_OBJECT );

        // post details
        mTxt_Post_Details_v16 = (TextView) findViewById( R.id.text_view_post_details_v16 );
        mTxt_Post_Details_v16.setOnClickListener( this );
        mBut_Post_Details = (Button) findViewById( R.id.But_post_details_v16 );
        mBut_Post_Details.setOnClickListener( this );

        // post cost
        mTxtV_post_cost_v16 = (TextView) findViewById( R.id.text_view_post_cost_v16 );
        mTxtV_post_cost_v16.setOnClickListener( this );
        mBut_Post_Cost = (Button) findViewById( R.id.But_post_cost_v16 );
        mBut_Post_Cost.setOnClickListener( this );
        // put in value from v8
        mFloat = mPost_sales_master_object.get_FS_PostCost();
        mStr = Format_PostCost_String( mFloat );
        mTxtV_post_cost_v16.setText( mStr );
        // post done
        mBut_post_done = (Button) findViewById( R.id.But_post_done_v16 );
        mBut_post_done.setOnClickListener( this );

        // update layout
        Update_Layout_from_Post_Sales_Master_Object( mPost_sales_master_object );


    }

    @Override
    public void onClick (View v) {
        String mStr;
        switch (v.getId()) {
            case R.id.But_post_details_v16:
            case R.id.text_view_post_details_v16: {
                // get present value of post details and pass to Text_Input_Dialog_v14
                mStr = (String) mTxt_Post_Details_v16.getText();
                Text_Input_Dialog_v14 mText_input_dialog = new Text_Input_Dialog_v14( this, "Post Detail's", Dialog_Line_Type.Dialog_Mult_Line, mStr );
                mText_input_dialog.setEventListener_Call_Back( new Text_Inp_Dia_Key_Response_Interface_v14() {
                    @Override
                    public void CallBack_Key_response (Dialog_Result _dialog_result, String _inputText) {
                        if (_dialog_result == Dialog_Result.Dialog_Done && _inputText.length() > 2) {
                            mTxt_Post_Details_v16.setText( _inputText );
                        }
                    }
                } );
                mText_input_dialog.show();
                break;
            }
            case R.id.But_post_cost_v16:
            case R.id.text_view_post_cost_v16: {
                // get present value of post details and pass to Text_Input_Dialog_v14
                Float mFloat;
                mFloat = System_Locale_Helpers.Get_Float_From_String( (String) mTxtV_post_cost_v16.getText() );
                mStr = String.format( "%3.2f", mFloat );
                Numeric_Input_Dialog_v17 mNumeric_input_dialog = new Numeric_Input_Dialog_v17( this, "Post Cost", mStr );
                mNumeric_input_dialog.setEventListener_Call_Back( new Numeric_Inp_Dia_Key_Response_Interface_v17() {
                    @Override
                    public void CallBack_Key_response (Dialog_Result _dialog_result, String _inputText) {
                        if (_dialog_result == Dialog_Result.Dialog_Done && _inputText.length() > 0) {
                            String mStr;
                            Float mFloat;
                            String str = _inputText;
                            mFloat = Float.parseFloat( str );
                            mStr = Format_PostCost_String( mFloat );
                            mTxtV_post_cost_v16.setText( mStr );
                        }
                    }
                } );
                mNumeric_input_dialog.show();
                break;
            }
            case R.id.But_post_done_v16: {
                Intent resultIntent = new Intent();
                //
                resultIntent.putExtra( "some_key", "Wogal Heck" );
                // update " Post_Sales_Master_Object " from layout
                Update_Post_Sales_Master_Object_from_Layout( mPost_sales_master_object );
                // pass back post details
                resultIntent.putExtra( System_Shared_Constants.const_bundle_post_OBJECT, mPost_sales_master_object );
                setResult( Activity.RESULT_OK, resultIntent );
                this.finish();
                break;
            }
        }
    }

    /**
     * Will take items in layout and populate "Post_Sales_Master_Object" with date
     *
     * @return object populated from layout
     */
    private Post_Sales_Master_Object Update_Post_Sales_Master_Object_from_Layout (Post_Sales_Master_Object _Post_Sales_Master_Object) {
        Post_Sales_Master_Object mPost_sales_master_object;
        mPost_sales_master_object = _Post_Sales_Master_Object;
        // post details
        mPost_sales_master_object.set_FS_Post_Details_Text( (String) mTxt_Post_Details_v16.getText() );
        // post cost (special case USE float from POST OBJECT )
        // get substring after locale currency char
        String mCostStr;
        mCostStr = (String) mTxtV_post_cost_v16.getText();
        mPost_sales_master_object.set_FS_PostCost( System_Locale_Helpers.Get_Float_From_String( mCostStr ) );
        return mPost_sales_master_object;
    }

    /**
     * Will update layout from info in "Post_Sales_Master_Object"
     *
     * @param _Post_Sales_Master_Object
     */
    private void Update_Layout_from_Post_Sales_Master_Object (Post_Sales_Master_Object _Post_Sales_Master_Object) {
        mTxt_Post_Details_v16.setText( Add_Remove_Decoration.Add_Decoration_Post_details() );
        // post cost
        mTxtV_post_cost_v16.setText( System_Locale_Helpers.Format_PostCost_String( _Post_Sales_Master_Object.get_FS_PostCost() ) );
    }
}

















