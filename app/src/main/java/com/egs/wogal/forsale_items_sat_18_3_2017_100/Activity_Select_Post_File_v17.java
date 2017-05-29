package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.TextView;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Choice_ActionEnums;
import Select_Post_File_Recylcle.AdapterDrops_Post_Files;
import Select_Post_File_Recylcle.Post_File_Array_Class;

public class Activity_Select_Post_File_v17 extends AppCompatActivity {
    public String mPostActionChoice_Str;
    private RecyclerView mRecyclerView;
    private TextView mTxtV_header_v17;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__select__post__file_v17 );
        // get extra info

        mPostActionChoice_Str = getIntent().getStringExtra( Choice_ActionEnums.key.toString() );


        Post_File_Array_Class.Get_Post_Files_List( this );

        mTxtV_header_v17 = (TextView) findViewById( R.id.txt_header_v17 );

        mTxtV_header_v17.setText( mPostActionChoice_Str );

        Bundle mBundle = new Bundle();
        String mStr;

        mBundle.putString(Choice_ActionEnums.key.toString()  , mPostActionChoice_Str );

        mRecyclerView = (RecyclerView) findViewById( R.id.RecView_SelectPost_File_v17 );
        LinearLayoutManager manager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( manager );
        mRecyclerView.setAdapter( new AdapterDrops_Post_Files( this, mBundle ) );
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView( mRecyclerView );

    }
}
