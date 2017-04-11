package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;


public class Activity_FaceBook_v10 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "faceBk v10";
    //    private static final String[] PERMISSIONS = new String[]{"publish_actions", "read_stream"};
    private static final String[] PERMISSIONS = new String[]{"publish_actions"};
    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.egs.wogal.forsale_items_sat_18_3_2017_100:PendingAction";
    public GraphRequest graphRequest;
    private String response = "";
    private String paramiters = "";
    private LoginButton LoginButton;
    private CallbackManager mCallbackManager;
    private Button mButt_FaceBookPost;
    private Button mButt_GetId;
    private Button mButt_PostPic;
    private TextView mTxtView_HashValue;
    private AccessToken MASTER_AccessToken;
    protected FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess (LoginResult loginResult) {
            //      AccessToken accessToken = LoginResult.getAccessToken();
            Log.d( TAG, " FaceBook onSuccess v10 " );
            MASTER_AccessToken = loginResult.getAccessToken();
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
    //  private ShareDialog shareDialog;
    private ProfileTracker profileTracker;
    private boolean canPresentShareDialogWithPhotos;
    private ShareDialog shareDialog;
    private String str;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private CallbackManager callbackManager;
    private Button postStatusUpdateButton;
    private Button postPhotoButton;
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel () {
            Log.d( "HelloFacebook", "Canceled" );
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onError (FacebookException error) {
            Log.d( "HelloFacebook", String.format( "Error: %s", error.toString() ) );
            String title = getString( R.string.error );
            String alertMessage = error.getMessage();
            showResult( title, alertMessage );
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onSuccess (Sharer.Result result) {
            Log.d( "HelloFacebook", "Success!" );
            if (result.getPostId() != null) {
                String title = getString( R.string.success );
                String id = result.getPostId();
                String alertMessage = getString( R.string.successfully_posted_post, id );
                showResult( title, alertMessage );
            }
        }

        private void showResult (String title, String alertMessage) {
            new AlertDialog.Builder( Activity_FaceBook_v10.this )
                    .setTitle( title )
                    .setMessage( alertMessage )
                    .setPositiveButton( R.string.ok, null )
                    .show();
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


        if (false) {
            mButt_FaceBookPost = (Button) findViewById( R.id.But_facebookPost_v10 );
            mButt_GetId = (Button) findViewById( R.id.But_facebook_ID_v10 );
            mButt_GetId.setOnClickListener( this );
            mTxtView_HashValue = (TextView) findViewById( R.id.txt_hash_key_v10 );


            mCallbackManager = CallbackManager.Factory.create();
            LoginButton = (LoginButton) findViewById( R.id.facebook_login_button_v10QQQ );

            String permissionsString;
            permissionsString = "user_friends,user_birthday,user_birthday,user_location,user_photos,user_friends,email";

            //     LoginButton.setReadPermissions( permissionsString );


            LoginButton.setPublishPermissions( "publish_actions " );

            LoginButton.registerCallback( mCallbackManager, mCallback );

            Button mbutt_FbUser_Permissions_v10 = (Button) findViewById( R.id.But_fb_permissions_v10 );
            mbutt_FbUser_Permissions_v10.setOnClickListener( this );

            mButt_FaceBookPost = (Button) findViewById( R.id.But_facebookPost_v10 );
            mButt_FaceBookPost.setOnClickListener( this );

            mButt_PostPic = (Button) findViewById( R.id.But_fb_post_pic_v10 );
            mButt_PostPic.setOnClickListener( this );
        }

        // ******************  START ************************************

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback( callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess (LoginResult loginResult) {
                        handlePendingAction();
                        updateUI();
                    }

                    @Override
                    public void onCancel () {
                        if (pendingAction != PendingAction.NONE) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }

                        updateUI();
                    }

                    @Override
                    public void onError (FacebookException exception) {
                        if (pendingAction != PendingAction.NONE
                                && exception instanceof FacebookAuthorizationException) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    private void showAlert () {
                        new AlertDialog.Builder( Activity_FaceBook_v10.this )
                                .setTitle( R.string.cancelled )
                                .setMessage( R.string.permission_not_granted )
                                .setPositiveButton( R.string.ok, null )
                                .show();
                    }
                } );

        shareDialog = new ShareDialog( this );
        shareDialog.registerCallback(
                callbackManager,
                shareCallback );

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString( PENDING_ACTION_BUNDLE_KEY );
            pendingAction = PendingAction.valueOf( name );
        }


        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged (Profile oldProfile, Profile currentProfile) {
                updateUI();
                // It's possible that we were waiting for Profile to be populated in order to
                // post a status update.
                handlePendingAction();
            }
        };

        profilePictureView = (ProfilePictureView) findViewById( R.id.profilePicture );
        greeting = (TextView) findViewById( R.id.greeting );

        postStatusUpdateButton = (Button) findViewById( R.id.postStatusUpdateButton );
        postStatusUpdateButton.setOnClickListener( new View.OnClickListener() {
            public void onClick (View view) {
                onClickPostStatusUpdate();
            }
        } );

        postPhotoButton = (Button) findViewById( R.id.postPhotoButton );
        postPhotoButton.setOnClickListener( new View.OnClickListener() {
            public void onClick (View view) {
                onClickPostPhoto();
            }
        } );


        // *************   END    ********************************************

    }


    private void onClickPostPhoto () {
        performPublish( PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos );
    }


    private void onClickPostStatusUpdate () {
        performPublish( PendingAction.POST_STATUS_UPDATE, canPresentShareDialog );
    }

    private void updateUI () {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        postStatusUpdateButton.setEnabled( enableButtons || canPresentShareDialog );
        postPhotoButton.setEnabled( enableButtons || canPresentShareDialogWithPhotos );

        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            profilePictureView.setProfileId( profile.getId() );
            greeting.setText( getString( R.string.hello_user, profile.getFirstName() ) );
        } else {
            profilePictureView.setProfileId( null );
            greeting.setText( null );
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

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.But_fb_post_pic_v10: {
                performPublish( PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos );
                //    PostPicky();
                //    Post_Picky_100();
                break;
            }
            case R.id.But_fb_permissions_v10: {
                GetUserPermissions();
                break;
            }
            case R.id.But_facebookPost_v10: {
                if (true) {
                    Intent intent = new Intent( Intent.ACTION_SEND );
                    intent.setType( "text/plain" );
                    intent.putExtra( Intent.EXTRA_TEXT, "Share Something" );
                    startActivity( Intent.createChooser( intent, "Share with" ) );
                } else {

                    mPost_100();
                }
                //       shareOnWall();
            }
            //    faceBookPost();
            break;

            case R.id.But_facebook_ID_v10: {
                getId();
                break;
            }
            //   case R.id.shar_key_v10: {
            //   }
            //   break;
        }
    }

    private void mPost_100 () {
        str = Profile.getCurrentProfile().getId();
        mTxtView_HashValue.setText( str );
        Bundle params = new Bundle();
        params.putString( "message", "This is a test message" );
/* make the API call */

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + str + "/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse response) {
            /* handle the result */
                        str = response.toString();
                        str += "";
                    }
                }
        ).executeAsync();
    }

    private void hhhhGetUserPermissions () {

        response = Profile.getCurrentProfile().getId();
        mTxtView_HashValue.setText( response );

        paramiters = "/" + response + "/permissions";

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                paramiters,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse Aresponse) {
                        response = Aresponse.toString();
            /* handle the result */
                    }
                }
        ).executeAsync();
    }

    private void Post_Picky () {

        Bitmap _bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.bug );

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        _bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream );
        byte[] mbitmap = stream.toByteArray();

        Bundle bundle = new Bundle();
        bundle.putByteArray( "object_attachment", mbitmap );// object attachment must be either byteArray or bitmap image
        bundle.putString( "message", "some message here" );

        response = Profile.getCurrentProfile().getId();
        mTxtView_HashValue.setText( response );
        paramiters = "{" + response + "}" + "/photos";


        GraphRequest request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                paramiters,
                bundle,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse Aresponse) {
                        response = Aresponse.toString();
                        str = response + "";
                    }
                }
        );   // .executeAsync();
        request.executeAsync();
    }


    private void Post_Picky_100 () {


        Init_Graph();

        String token;

        token = AccessToken.getCurrentAccessToken().getToken();


        if (AccessToken.getCurrentAccessToken() != null) {
     /*   Bitmap image=BitmapFactory.decodeResource(getResources(),R.drawable.ll);
         ByteArrayOutputStream blob1=new ByteArrayOutputStream();
                 image.compress(Bitmap.CompressFormat.JPEG,0,blob1);
        byte[] bitmapdata = blob1.toByteArray();*/
            Bitmap image = BitmapFactory.decodeResource( getResources(), R.drawable.bug );
            byte[] data = null;

            Bitmap bi = BitmapFactory.decodeResource( getResources(),
                    R.drawable.bug );
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bi.compress( Bitmap.CompressFormat.JPEG, 100, baos );
            data = baos.toByteArray();
            response = Profile.getCurrentProfile().getId();
            mTxtView_HashValue.setText( response );
            paramiters = response + "/photos";

            GraphRequest graphRequest = GraphRequest.newPostRequest( AccessToken.getCurrentAccessToken(), paramiters, null, new GraphRequest.Callback() {
                @Override
                public void onCompleted (GraphResponse graphResponse) {
                    str = graphResponse.toString();
                }
            } );
            Bundle postParams = graphRequest.getParameters();

            postParams.putByteArray( "picture", data );

            postParams.putString( "caption", " test 101" );

            graphRequest.setParameters( postParams );

            graphRequest.executeAsync();


        } else {

            //     Toast.makeText(AndroidFacebookConnectActivity.this,"You are not logged into Facebook", Toast.LENGTH_SHORT).show();
        }
    }


    private void Init_Graph () {

        // *********************************************


        GraphRequest request = null;
        if (false) {
            request = GraphRequest.newMeRequest( AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted (JSONObject object, GraphResponse response) {
                            try {
                                String email = object.getString( "email" );
                                Log.d( TAG + "user email ", email );
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }

                    } );
            request.executeAsync();
        }


        // *******************************************


        //    AccessToken token = new AccessToken(  )
        AccessToken token;
        String str_token = "";
        String userId;

        //     String accessToken = "1867682216852926|raauEUhsIhO2n2KHxHQZr4bd4qo";
        String accessToken = "1867682216852926|f00c8578f528df4128e36fb6aef1259c";

        String appId = "1867682216852926";

        //    userId = "100001667882988";
        userId = "935766826488905"; // ********   WORKS ************
        //    userId = "427001480698778";

        token = new AccessToken( accessToken, appId, userId, null, null, null, null, null );
        str_token = new AccessToken( accessToken, appId, userId, null, null, null, null, null ).getToken();


        request = GraphRequest.newGraphPathRequest(
                MASTER_AccessToken,
                //    token,
                "/me/",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted (GraphResponse response) {
                        // Insert your code here
                        str = response.toString();
                    }
                } );

        Bundle parameters = new Bundle();
        parameters.putString( "fields", "birthday,education,first_name,work,id,cover" );
        request.setParameters( parameters );
        request.executeAsync();


        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged (AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        token = AccessToken.getCurrentAccessToken();
    }


    private void GetUserPermissions () {
        response = Profile.getCurrentProfile().getId();
        mTxtView_HashValue.setText( response );

        GraphRequest request = GraphRequest.newGraphPathRequest(
                MASTER_AccessToken,
                "/me/permissions",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted (GraphResponse response) {
                        // Insert your code here
                        str = response.toString();
                        str += " ";
                    }
                } );
        request.executeAsync();
    }


    private void PostPicky () {
        response = Profile.getCurrentProfile().getId();
        mTxtView_HashValue.setText( response );

        Bundle params = new Bundle();

        //  LoginManager.getInstance().logInWithReadPermissions(params);

        //    LoginManager.getInstance().logInWithPublishPermissions( getApplicationContext());

        getCallingActivity();


        params.putString( "message", "wogal heck" );
        params.putString( "name", "Cricket Fantasy" );
        params.putString( "caption", "New team" );
        params.putString( "description", "Description about Application" );
        params.putString( "url", "https://scontent.xx.fbcdn.net/v/t31.0-8/s720x720/16402546_1452534648145451_4902155126574263230_o.jpg?oh=fb9a29d36e8bdac0ecab8133c092646d&oe=5958BA67" );
        ;

/* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted (GraphResponse response) {
                        str = response.toString();
                        str += " -- ";
                    }
                }
        ).executeAsync();
    }


    private void postPhoto () {
        Bitmap image = BitmapFactory.decodeResource( this.getResources(), R.drawable.bug );
        SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap( image ).build();
        ArrayList<SharePhoto> photos = new ArrayList<>();
        photos.add( sharePhoto );

        SharePhotoContent sharePhotoContent =
                new SharePhotoContent.Builder().setPhotos( photos ).build();
        if (canPresentShareDialogWithPhotos) {
            shareDialog.show( sharePhotoContent );
        } else if (hasPublishPermission()) {
            ShareApi.share( sharePhotoContent, shareCallback );
        } else {
            pendingAction = PendingAction.POST_PHOTO;
            // We need to get new permissions, then complete the action when we get called back.
            LoginManager.getInstance().logInWithPublishPermissions(
                    this,
                    Arrays.asList( PERMISSIONS ) );
        }
    }

    private void postStatusUpdate () {
        Profile profile = Profile.getCurrentProfile();
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle( "Hello Facebook" )
                .setContentDescription(
                        "The 'Hello Facebook' sample  showcases simple Facebook integration" )
                .setContentUrl( Uri.parse( "http://developers.facebook.com/docs/android" ) )
                .build();
        if (canPresentShareDialog) {
            shareDialog.show( linkContent );
        } else if (profile != null && hasPublishPermission()) {
            ShareApi.share( linkContent, shareCallback );
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }

    private void handlePendingAction () {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case NONE:
                break;
            case POST_PHOTO:
                postPhoto();
                break;
            case POST_STATUS_UPDATE:
                postStatusUpdate();
                break;
        }
    }

    private boolean hasPublishPermission () {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains( "publish_actions" );
    }

    // send pic
    private void performPublish (PendingAction action, boolean allowNoToken) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null || allowNoToken) {
            pendingAction = action;
            handlePendingAction();
        }
    }


    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }


}












