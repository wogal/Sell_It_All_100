package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Activity_FaceBook_v10 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "faceBk v10";
    private LoginButton LoginButton;
    private CallbackManager mCallbackManager;
    private Button mButt_FaceBookPost;
    private Button mButt_GetId;
    private TextView mTxtView_HashValue;
    private static final String[] PERMISSIONS = new String[]{"publish_actions","read_stream"};
private Facebook fff;


    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess (LoginResult loginResult) {
            //     AccessToken accessToken = LoginResult.getAccessToken
            Log.d( TAG, " FaceBook onSuccess v10 " );
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                mTxtView_HashValue.setText( "Wellcome " + profile.getName() );
            } else {
                mTxtView_HashValue.setText( " Error profile = null" );
            }
        }

        @Override
        public void onCancel () {
            Log.d( TAG, " FaceBook onCancel v10 " );
        }

        @Override
        public void onError (FacebookException error) {
            Log.d( TAG, " FaceBook onError v10 " );
        }
    };

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        mCallbackManager.onActivityResult( requestCode, resultCode, data );
    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__face_book_v10 );

        mButt_FaceBookPost = (Button) findViewById( R.id.But_facebookPost_v10 );
        mButt_GetId = (Button) findViewById( R.id.But_facebook_ID_v10 );
        mButt_GetId.setOnClickListener( this );
        mTxtView_HashValue = (TextView) findViewById( R.id.txt_hash_key_v10 );

        mCallbackManager = CallbackManager.Factory.create();
        LoginButton = (LoginButton) findViewById( R.id.facebook_login_button_v10 );
        LoginButton.setReadPermissions( "user_friends" );
        LoginButton.registerCallback( mCallbackManager, mCallback );

    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_facebookPost_v10: {
                faceBookPost();
                break;
            }
            case R.id.But_facebook_ID_v10: {
                getId();
                break;
            }
        }
    }

    private void faceBookPost () {


    }

    private void getId () {
        String something = "";
        MessageDigest md;
        try {
            PackageInfo info = getPackageManager().getPackageInfo( "com.egs.wogal.forsale_items_sat_18_3_2017_100", PackageManager.GET_SIGNATURES );
            for (Signature signature : info.signatures) {
                md = MessageDigest.getInstance( "SHA" );
                md.update( signature.toByteArray() );
                something = new String( Base64.encode( md.digest(), 0 ) );
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e( "hash key", something );
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e( "name not found", e1.toString() );
        } catch (NoSuchAlgorithmException e) {
            Log.e( "no such an algorithm", e.toString() );
        } catch (Exception e) {
            Log.e( "exception", e.toString() );
        }
        mTxtView_HashValue.setText( something );
    }


}















