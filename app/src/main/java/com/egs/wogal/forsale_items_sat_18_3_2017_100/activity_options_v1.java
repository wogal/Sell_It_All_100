package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import Dialog_Text_Input_v14.Text_Inp_Dia_Key_Response_Interface_v14;
import Dialog_Text_Input_v14.Text_Input_Dialog_v14;
import Dialog_Timed_Notice.Dialog_Timed_Notice_v15;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Choice_ActionEnums;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Line_Type;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Dialog_Result_Enum;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.File_Helper_Items;
import Select_Post_File_Recylcle.Post_File_Array_Class;

import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.But_select_Post_File_Edit_v1;


public class activity_options_v1 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Wogal v1";

    private TextView mTxtHeaderText_v1;
    private TextView mTxtItemName_v1;
    private TextView mTxtView_txt_in_v14;
    private Intent m_Intent;


    private Button mBut_Test_v4;
    private Button mBut_SelectPost_To_Edit;
    private Button mBut_Select_Edit_To_Post;
    private Button mButMake_NEW_POST;

    private Button mBut_txt_2_speech;

    private int iint = 0;

    // Alert Dialog_ItemName Vars


    // Alert Dialog_ItemName Vars
    private AlertDialog Dialog_ItemName;
    private View mViewItemName;

    private AlertDialog.Builder mBuilderItemName;


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putCharSequence( "mTxtItemName_v1", mTxtItemName_v1.getText() );
        Log.d( TAG, "onSaveInstanceState" );
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        CharSequence str = savedInstanceState.getCharSequence( "mTxtItemName_v1" );
        if (str != null) {
            mTxtItemName_v1.setText( str );
        }
        Log.d( TAG, "onRestoreInstanceState" );
    }

    //endregion   Activity frf

    //region Description -- Activity States
    //region Description
    @Override
    protected void onStart () {
        Log.d( TAG, "osStart" );
        super.onStart();
    }

    @Override
    protected void onRestart () {
        Log.d( TAG, "  Wogal onRestart " );
        super.onRestart();
    }

    @Override
    protected void onResume () {
        Log.d( TAG, "  Wogal onResume " );
        super.onResume();
    }

    @Override
    protected void onPause () {
        Log.d( TAG, "  Wogal onPause " );
        super.onPause();
    }

    @Override
    protected void onStop () {
        Log.d( TAG, "  Wogal onStop " );
        super.onStop();
    }

    @Override
    protected void onDestroy () {
        Log.d( TAG, "  Wogal onDestroy " );
        super.onDestroy();
    }
    //endregion

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v1 );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        m_Intent = new Intent( this, Activity_MakeSalesItem_v8.class );

        mTxtHeaderText_v1 = (TextView) findViewById( R.id.txt_header_v1 );
        mTxtHeaderText_v1.setOnClickListener( this );

        mTxtItemName_v1 = (TextView) findViewById( R.id.txt_v_sales_item_name_v1 );
        mTxtItemName_v1.setOnClickListener( this );

        // get any files


        ArrayList<File> mFiles = Post_File_Array_Class.Get_Post_Files_List( this );


        // buttons
        mBut_Test_v4 = (Button) findViewById( R.id.But_test_activity_v1 );
        mBut_Test_v4.setOnClickListener( this );

        mButMake_NEW_POST = (Button) findViewById( R.id.But_make_NEW_Post_v1 );
        mButMake_NEW_POST.setOnClickListener( this );

        mBut_SelectPost_To_Edit = (Button) findViewById( But_select_Post_File_Edit_v1 );
        mBut_SelectPost_To_Edit.setOnClickListener( this );

        mBut_Select_Edit_To_Post = (Button) findViewById( R.id.But_select_File_To_Post_v1 );
        mBut_Select_Edit_To_Post.setOnClickListener( this );

        mBut_txt_2_speech = (Button) findViewById( R.id.But_select_Text_2_speech_v1 );
        mBut_txt_2_speech.setOnClickListener( this );


        if (mFiles.size() == 0) {
            mBut_Test_v4.setEnabled( false );
            mButMake_NEW_POST.setEnabled( true );
            mBut_SelectPost_To_Edit.setEnabled( false );
            mBut_Select_Edit_To_Post.setEnabled( false );
            mBut_Select_Edit_To_Post.setEnabled( false );
        } else {
            mBut_Test_v4.setEnabled( true );
            mButMake_NEW_POST.setEnabled( true );
            mBut_SelectPost_To_Edit.setEnabled( true );
            mBut_Select_Edit_To_Post.setEnabled( true );
            mBut_Select_Edit_To_Post.setEnabled( true );
        }

    }


    @Override
    public void onLowMemory () {
        super.onLowMemory();
        Log.d( TAG, " ***   onLowMemory   **" );
    }


    @Override
    public void onClick (View v) {
        if (1 == 1) {
            switch (v.getId()) {
                case R.id.But_select_Text_2_speech_v1: {
                    Text_2_Speech();
                    break;
                }
                case But_select_Post_File_Edit_v1: { // Edit Post file
                    Intent SelectPost_File_Intent = new Intent( this, Activity_Select_Post_File_v17.class );
                    SelectPost_File_Intent.putExtra( Choice_ActionEnums.key.toString(), Choice_ActionEnums.choice_Edit_Post_File.toString() );
                    startActivity( SelectPost_File_Intent );
                    break;
                }
                case R.id.But_select_File_To_Post_v1: { // Post file
                    Intent SelectPost_File_Intent = new Intent( this, Activity_Select_Post_File_v17.class );
                    SelectPost_File_Intent.putExtra( Choice_ActionEnums.key.toString(), Choice_ActionEnums.choice_Post_File.toString() );
                    startActivity( SelectPost_File_Intent );
                    break;
                }
                case R.id.But_test_activity_v1: {  // ******************
                    File_Helper_Items.GetAbs_Post_path( this, "Earles Chairs.pst" );
                    m_Intent = new Intent( this, Activity_test_v4.class );
                    startActivity( m_Intent ); // save to move on
                    break;
                }
                case R.id.But_make_NEW_Post_v1: {


                    if (false) {
                        Text_Input_Dialog_v14 mText_input_dialog = new Text_Input_Dialog_v14( this, "New Post Name (1)", Dialog_Line_Type.Dialog_Single_Line, "" );
                        mText_input_dialog.setEventListener_Call_Back( new Text_Inp_Dia_Key_Response_Interface_v14() {
                            @Override
                            public void CallBack_Key_response (Dialog_Result_Enum _dialog_result, String _inputText) {
                                if (true == Make_New_Post_File( _inputText )) {
                                    m_Intent.putExtra( "post_file_name", _inputText );
                                    startActivity( m_Intent ); // save to move on
                                }
                            }
                        } );
                        mText_input_dialog.show();
                    } else {
                        Intent test_intent = new Intent( this,text_input_v100.class );
                        startActivityForResult( test_intent,69 );
                    }
                    break;
                }
            }
        }
    }

    private void Text_2_Speech () {
        Intent txt_2_speech_intent = new Intent( this, ActivityText_2_Speech_v19.class );
        startActivity( txt_2_speech_intent );
    }

    private boolean Make_New_Post_File (String _inputText) {
        String mStr = "";
        if (false == File_Helper_Items.Check_4_Valid_File_NameValidity( this, _inputText )) {
            Dialog_Timed_Notice_v15 mDialog_timed_notice_v15 = new Dialog_Timed_Notice_v15( this, "** Error **", "Post Name Err" ).show();
            return false;
        }
        if (true == File_Helper_Items.Check_and_Create_Post_File( this, _inputText, true )) {
            Dialog_Timed_Notice_v15 mDialog_timed_notice_v15 = new Dialog_Timed_Notice_v15( this, "** Nice **", "File Created" ).show();
            return true;
        } else {
            Dialog_Timed_Notice_v15 mDialog_timed_notice_v15 = new Dialog_Timed_Notice_v15( this, "** Error **", "File Exist's" ).show();
            return false;
        }
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        // should not land here

        iint = requestCode;


    }


}

