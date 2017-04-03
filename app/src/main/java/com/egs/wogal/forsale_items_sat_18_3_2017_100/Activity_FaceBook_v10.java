package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Activity_FaceBook_v10 extends AppCompatActivity implements View.OnClickListener {

    private LoginButton mFaceBookLoginButton;
    private CallbackManager mCallbackManager;
    private Button mButt_FaceBookPost;
    private Button mButt_GetId;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess (LoginResult loginResult) {
            //     AccessToken accessToken = LoginResult.getAccessToken
        }

        @Override
        public void onCancel () {

        }

        @Override
        public void onError (FacebookException error) {

        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__face_book_v10 );

        mFaceBookLoginButton = (LoginButton) findViewById( R.id.facebook_login_button_v10 );
        mFaceBookLoginButton.setReadPermissions( "user_friends" );
        mCallbackManager = CallbackManager.Factory.create();
        mFaceBookLoginButton.registerCallback( mCallbackManager, mCallback );
        mButt_FaceBookPost = (Button) findViewById( R.id.But_facebookPost_v10 );
        mButt_GetId = (Button) findViewById( R.id.But_facebook_ID_v10 );
        mButt_GetId.setOnClickListener( this );


    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_facebookPost_v10: {
                getId();
                break;
            }
        }
    }

    private void getId () {
        MessageDigest md;
        try {
            PackageInfo info = getPackageManager().getPackageInfo( "com.you.name", PackageManager.GET_SIGNATURES );
            for (Signature signature : info.signatures) {
                md = MessageDigest.getInstance( "SHA" );
                md.update( signature.toByteArray() );
                String something = new String( Base64.encode( md.digest(), 0 ) );
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
    }


}
