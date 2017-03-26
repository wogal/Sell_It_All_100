package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.text_view_sales_item_name_v2;

public class Activity_MakeSalesItem_v8 extends AppCompatActivity implements View.OnClickListener {

    private View mViewItemName;
    private Button mBut_name_item_GoBack;
    private Button mBut_itemTextHeader_v8;
    private TextView mTxtView_ItemHeaderText_v8;


    private EditText mTxtInputText_v2;
    private TextView mTextEntersTextField_v2;
    private Button mBtnSalesItemName_v8;
    private AlertDialog Dialog_ItemName;
    private TextView mTxtItemName_v8;
    private AlertDialog.Builder mBuilderItemName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v8 );

        mBtnSalesItemName_v8 = (Button) findViewById( R.id.But_item_name_v8 );
        mBtnSalesItemName_v8.setOnClickListener( this );

        mTxtItemName_v8 = (TextView) findViewById( R.id.txt_v_sales_item_name_v8 );
        mTxtItemName_v8.setOnClickListener( this );

        mBut_itemTextHeader_v8 = (Button) findViewById( R.id.But_text_header_v8 );
        mBut_itemTextHeader_v8.setOnClickListener( this );

        mTxtView_ItemHeaderText_v8 = (TextView) findViewById( R.id.txt_v_text_header_v8 );
        mTxtView_ItemHeaderText_v8.setOnClickListener( this );

    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.txt_v_text_header_v8:
            case R.id.But_text_header_v8:
                mBuilderItemName = new AlertDialog.Builder( Activity_MakeSalesItem_v8.this );
                mViewItemName = getLayoutInflater().inflate( R.layout.layout_v2, null );

                mTextEntersTextField_v2 = (TextView) mViewItemName.findViewById( text_view_sales_item_name_v2 );

                mBut_name_item_GoBack = (Button) mViewItemName.findViewById( R.id.But_item_name_done_v2 );
                mBut_name_item_GoBack.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        switch (v.getId()) {
                            case R.id.But_item_name_done_v2:
                                String str;
                                str = mTxtInputText_v2.getText().toString();
                                if (!str.isEmpty()) {
                                    mTxtView_ItemHeaderText_v8.setText( str );
                                }
                                Dialog_ItemName.dismiss();
                                break;
                        }
                    }
                } );

                mTxtInputText_v2 = (EditText) mViewItemName.findViewById( R.id.edit_text_item_name_v2 );
                mTxtInputText_v2.setOnKeyListener( new View.OnKeyListener() {
                    @Override
                    public boolean onKey (View v, int keyCode, KeyEvent event) {
                        //    Log.d(TAG, "key parsed - " + keyCode );
                        if (KEYCODE_ENTER == keyCode) {
                            String str;
                            str = mTxtInputText_v2.getText().toString();
                            if (!str.isEmpty()) {
                                mTxtView_ItemHeaderText_v8.setText( str );
                                mTextEntersTextField_v2.setText( str );
                            }
                            mTxtInputText_v2.setText( "" );
                            mTxtInputText_v2.clearFocus();
                            Toast.makeText( getApplicationContext(), "hi Item Key-> " + keyCode + " " + str, Toast.LENGTH_LONG ).show();
                            Dialog_ItemName.dismiss();
                        }
                        return false;
                    }
                } );
                mBuilderItemName.setView( mViewItemName );
                Dialog_ItemName = mBuilderItemName.create();
                Dialog_ItemName.show();
                break;

            case R.id.But_item_name_done_v2:
                String str;
                str = mTxtInputText_v2.getText().toString();
                if (!str.isEmpty()) {
                    mTxtItemName_v8.setText( str );
                }
                Dialog_ItemName.dismiss();
                break;

            case R.id.txt_v_sales_item_name_v8:
            case R.id.But_item_name_v8:
                mBuilderItemName = new AlertDialog.Builder( Activity_MakeSalesItem_v8.this );
                mViewItemName = getLayoutInflater().inflate( R.layout.layout_v2, null );
                mTextEntersTextField_v2 = (TextView) mViewItemName.findViewById( text_view_sales_item_name_v2 );
                mBut_name_item_GoBack = (Button) mViewItemName.findViewById( R.id.But_item_name_done_v2 );
                mBut_name_item_GoBack.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        switch (v.getId()) {
                            case R.id.But_item_name_done_v2:
                                String str;
                                str = mTxtInputText_v2.getText().toString();
                                if (!str.isEmpty()) {
                                    mTxtItemName_v8.setText( str );
                                }
                                Dialog_ItemName.dismiss();
                                break;
                        }
                    }
                } );

                mTxtInputText_v2 = (EditText) mViewItemName.findViewById( R.id.edit_text_item_name_v2 );
                mTxtInputText_v2.setOnKeyListener( new View.OnKeyListener() {
                    @Override
                    public boolean onKey (View v, int keyCode, KeyEvent event) {
                        //    Log.d(TAG, "key parsed - " + keyCode );
                        if (KEYCODE_ENTER == keyCode) {
                            String str;
                            str = mTxtInputText_v2.getText().toString();
                            if (!str.isEmpty()) {
                                mTxtItemName_v8.setText( str );
                                mTextEntersTextField_v2.setText( str );
                            }
                            mTxtInputText_v2.setText( "" );
                            mTxtInputText_v2.clearFocus();
                            Toast.makeText( getApplicationContext(), "hi Item Key-> " + keyCode + " " + str, Toast.LENGTH_LONG ).show();
                            Dialog_ItemName.dismiss();
                        }
                        return false;
                    }
                } );
                mBuilderItemName.setView( mViewItemName );
                Dialog_ItemName = mBuilderItemName.create();
                Dialog_ItemName.show();
                break;
        }
    }
}

