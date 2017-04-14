package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
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
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Arrays;

import FaceBook_Java_Helpers.HlpFbook_Posts;


public class Activity_FaceBook_v10 extends AppCompatActivity {

    public static final String TAG = "faceBk v10";
    // START

    public static final String WogalstestGroup = "357763397958712";

    private static final String PERMISSION = "publish_actions";
    private static final Location SEATTLE_LOCATION = new Location( "" ) {
        {
            setLatitude( 47.6097 );
            setLongitude( -122.3331 );
        }
    };

    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.egs.wogal.forsale_items_sat_18_3_2017_100:PendingAction";

    private String mStr;
    private Button postStatusUpdateButton;
    private Button postPhotoButton;
    private Button mBtnGetPermissions;
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private ShareDialog shareDialog;
    private TextView txtview;


    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel () {
            Log.d( "HelloFacebook", "Canceled" );
        }

        @Override
        public void onError (FacebookException error) {
            Log.d( "HelloFacebook", String.format( "Error: %s", error.toString() ) );
            String title = getString( R.string.error );
            String alertMessage = error.getMessage();
            showResult( title, alertMessage );
        }

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
    private AccessToken accessToken;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

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

        setContentView( R.layout.activity__face_book_v10 );

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

        mBtnGetPermissions = (Button) findViewById( R.id.GetPermmisionsButton );
        mBtnGetPermissions.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                onclickGetPermissions();
            }
        } );


        // Can we present the share dialog for regular links?
        canPresentShareDialog = ShareDialog.canShow(
                ShareLinkContent.class );

        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = ShareDialog.canShow(
                SharePhotoContent.class );

        txtview = (TextView) findViewById( R.id.textView1 );

        txtview.setText( "First \n Second" );
        txtview.setText( "First \n Second" );
        txtview.setText( "First \n Second" );
        txtview.setText( "First \n Second" );

    }


    @Override
    protected void onResume () {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState( outState );

        outState.putString( PENDING_ACTION_BUNDLE_KEY, pendingAction.name() );
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        callbackManager.onActivityResult( requestCode, resultCode, data );
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        profileTracker.stopTracking();
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

    private void onClickPostStatusUpdate () {
        performPublish( PendingAction.POST_STATUS_UPDATE, canPresentShareDialog );
    }

    private void postStatusUpdate () {
        Profile profile = Profile.getCurrentProfile();
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle( "Hello Facebook" )
                .setContentDescription(
                        "Test Integration " )
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

    private void onClickPostPhoto () {
        performPublish( PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos );
    }

    private void postPhoto () {
        Bitmap image = BitmapFactory.decodeResource( this.getResources(), R.drawable.for_sale_logo_640_480 );
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
                    Arrays.asList( PERMISSION ) );
        }
    }

    private boolean hasPublishPermission () {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains( "publish_actions" );
    }

    private void performPublish (PendingAction action, boolean allowNoToken) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null || allowNoToken) {
            pendingAction = action;
            handlePendingAction();
        }
    }

    private void onclickGetPermissions () {
        /* make the API call */
        String usrIdstr;
        String pic_url;

        pic_url = "https://scontent-ort2-1.xx.fbcdn.net/v/t1.0-9/419047_10150790611320550_2085280315_n.jpg?oh=2bf0137a575e4e4977b8b0d3c655e9c1&oe=59971A40";

        accessToken = AccessToken.getCurrentAccessToken();

        usrIdstr = accessToken.getUserId();

        Bundle params = new Bundle();
        //   params.putString( "url", pic_url );
        //  params.putString( "url", "https://scontent-ort2-1.xx.fbcdn.net/v/t1.0-9/419047_10150790611320550_2085280315_n.jpg?oh=2bf0137a575e4e4977b8b0d3c655e9c1&oe=59971A40");
        //   params.putString( "url", "https://scontent-ort2-1.xx.fbcdn.net/v/t1.0-0/p206x206/421308_290584514340476_678502771_n.jpg?oh=726ec2e4d190451066467cca84198a23&oe=594C65DD" );
        //  params.putString( "url", "https://scontent-ort2-1.xx.fbcdn.net/v/t31.0-8/322113_290584694340458_745919865_o.jpg?oh=a821994bc205abd8e07b8b836af07f12&oe=598826BE" );
        //   params.putString( "url", "https://scontent-ort2-1.xx.fbcdn.net/v/t31.0-8/331774_290584881007106_1250734524_o.jpg?oh=3c5dbad582df75ce28211c9d9bf914c5&oe=5999D235" );

        //   params.putString( "url", "https://scontent-ort2-1.xx.fbcdn.net/v/t31.0-8/323667_290589747673286_387270164_o.jpg?oh=9518de188a40a2fb10ab2b9534734007&oe=594E6A34" );


        pic_url = "https://scontent-ort2-1.xx.fbcdn.net/v/t1.0-9/419047_10150790611320550_2085280315_n.jpg?oh=2bf0137a575e4e4977b8b0d3c655e9c1&oe=59971A40";
        HlpFbook_Posts.postOPbj_2_Grp( this, WogalstestGroup, "dogs with logs ", HlpFbook_Posts.PostImageType.POST_BITMAP_PHOTO, true, R.drawable.heart );
        HlpFbook_Posts.postOPbj_2_Grp( this, WogalstestGroup, "cats with dogs", HlpFbook_Posts.PostImageType.POST_IMAGE_PHOTO, true, pic_url );


        if (false) {


            GraphRequest request = GraphRequest.newGraphPathRequest(
                    accessToken,
                    "/me/permissions",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted (GraphResponse response) {
                            mStr = response.toString();
                            mStr += "";
                        }
                    } );
            request.executeAsync();

            params = new Bundle();
            params.putString( "message", "embedded test 12345 qwert " );
            new GraphRequest(
                    accessToken,
                    "/WogalstestGroup/feed",
                    params,
                    HttpMethod.POST,
                    new GraphRequest.Callback() {
                        public void onCompleted (GraphResponse response) {

                            mStr = response.toString();
                            mStr += "";
                        }
                    }
            ).executeAsync();


            params.putString( "url", pic_url );

            params.putString( "message", "test  Post 100 id - 1125553 " );
/* make the API call */
            new GraphRequest(
                    accessToken,
                    "/1006171692755468/photos",
                    params,
                    HttpMethod.POST,
                    new GraphRequest.Callback() {
                        public void onCompleted (GraphResponse response) {
                            mStr = response.toString();
                            mStr += "";
                        }
                    }
            ).executeAsync();

        }
    }

    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }


    //    END

}












