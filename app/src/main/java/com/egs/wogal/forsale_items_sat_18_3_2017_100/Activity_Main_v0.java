package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_Main_v0 extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "Init -";
    Button mBtn_startselling_vn;
    private AlertDialog Dialog_ItemName;
    private TextView mTxtView_init_header_v12;
    private TextView mTxtView_init_action_v12;
    private AlertDialog.Builder mBuilderItemName;
    private View mViewItemName;
    private Button mBut_name_item_GoBack;


    // start sreen
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        setContentView( R.layout.layout_v0 );
        mBtn_startselling_vn = (Button) findViewById( R.id.butt_lets_start_selling_v0 );
        mBtn_startselling_vn.setOnClickListener( this );
        // invoke system initialize Actions
        Initialize_System( this );


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void Initialize_System (Activity _activity_main_v0) {
        // make sure system folders are set up


        if (false) {

        } else {

            int buildVer;
            buildVer = Build.VERSION.SDK_INT;
            if (buildVer >= 23) {
                if (checkSelfPermission( android.Manifest.permission.WRITE_EXTERNAL_STORAGE )
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v( TAG, "Permission is granted" );
                    return;
                } else {

                    Log.v( TAG, "Permission is revoked" );
                    ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1 );
                    return;
                }
            } else { //permission is automatically granted on sdk<23 upon installation
                Log.v( TAG, "Permission is granted" );
                return;
            }

        }

        mBuilderItemName = new AlertDialog.Builder( Activity_Main_v0.this );
        mViewItemName = getLayoutInflater().inflate( R.layout.layout_init_alrt_dialog_v12, null );
        mBut_name_item_GoBack = (Button) mViewItemName.findViewById( R.id.But_item_name_done_v12 );
        mTxtView_init_header_v12 = (TextView) mViewItemName.findViewById( R.id.text_view_init_header_v12 );
        mTxtView_init_action_v12 = (TextView) mViewItemName.findViewById( R.id.text_view_init_actions_v12 );
        mTxtView_init_header_v12.setText( "Init Actions (v12)" );
        // stops AlertDialog from dismissing on touch out side the AlertDialog
        mBuilderItemName.setCancelable( false );
        mBut_name_item_GoBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                switch (v.getId()) {
                    case R.id.But_item_name_done_v12:
                        Dialog_ItemName.dismiss();
                        break;
                }
            }
        } );
        mBuilderItemName.setView( mViewItemName );
        Dialog_ItemName = mBuilderItemName.create();
        Dialog_ItemName.show();
    }



    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.butt_lets_start_selling_v0: {
                Intent intent = new Intent( this, activity_options_v1.class );
                startActivity( intent );
            }
        }
    }
}
