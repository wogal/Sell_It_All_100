package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
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

import FaceBook_Java_Helpers.Graph_Custom_Post_CallBack_Interface;
import FaceBook_Java_Helpers.Graph_OnCallBackFunction_EachPost_Interface;
import FaceBook_Java_Helpers.Graph_OnfinalPost_CallBack_Interface;
import FaceBook_Java_Helpers.HlpFbook_Posts;
import For_Sale_Item_Object_Pkg.For_Sale_Item_Object;
import For_Sale_Item_Object_Pkg.SaleItemMakeup;

public class Activity_FaceBook_v10 extends AppCompatActivity implements View.OnClickListener, Graph_OnfinalPost_CallBack_Interface, Graph_OnCallBackFunction_EachPost_Interface, Graph_Custom_Post_CallBack_Interface {

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
    // thread testing
    Thread mThread;
    private String mStr;
    private Button postPhotoButton;
    private Button mBtnPost_Multiple_Images;
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private ShareDialog shareDialog;
    private ProgressBar mProgressBar_Post_Progress;
    private TextView mTextView;

    private Handler mHandler_Text;
    private Handler mHandler_ProgressBar;

    private HlpFbook_Posts mHlpFbook_posts = null;


    private Button mBut_FB_1;
    private Button mBut_FB_2;
    private Button mBut_FB_3;
    private CheckBox mChkBoxiTemPerPost;

    private int mPostProgressIndex;


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


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {

        }
    };

    private Handler CrossThreadHandler_ShowPostProgress = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            int mCnt;
            String mStr;
            mCnt = msg.arg1;
            mStr = (String) msg.obj;

            mTextView.setText( mStr );
            mProgressBar_Post_Progress.setProgress( mCnt );
            mTextView.invalidate();
            mProgressBar_Post_Progress.invalidate();
        }
    };


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        callbackManager = CallbackManager.Factory.create();
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        //region "    LoginManager.getInstance().registerCallback( callbackManager, "
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
        //endregion

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


        mBtnPost_Multiple_Images = (Button) findViewById( R.id.PostMultibule_Imagges );
        mBtnPost_Multiple_Images.setOnClickListener( this );


        // Can we present the share dialog for regular links?
        canPresentShareDialog = ShareDialog.canShow(
                ShareLinkContent.class );

        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = ShareDialog.canShow(
                SharePhotoContent.class );


        mProgressBar_Post_Progress = (ProgressBar) findViewById( R.id.post_progress_bar );
        mProgressBar_Post_Progress.setProgress( 0 );

        mTextView = (TextView) findViewById( R.id.txt_status_v10 );

        mBut_FB_1 = (Button) findViewById( R.id.FB_1 );
        mBut_FB_1.setOnClickListener( this );

        mBut_FB_2 = (Button) findViewById( R.id.FB_2 );
        mBut_FB_2.setOnClickListener( this );

        mBut_FB_3 = (Button) findViewById( R.id.FB_3 );
        mBut_FB_3.setOnClickListener( this );


        mHandler_Text = new Handler() {
            @Override
            public void handleMessage (Message msg) {
                String msgvalue;
                msgvalue = (String) msg.obj;
                String mStr;
                mStr = "Post Cnt -> " + msgvalue;
                mTextView.setText( mStr );
            }
        };
        mChkBoxiTemPerPost = (CheckBox) findViewById( R.id.chkBx_sepPosts_v10 );
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


    private void Post_multibule_Images () {
        int mCnt;
        For_Sale_Item_Object mfor_sale_item_object;
        mfor_sale_item_object = For_Sale_Item_Object.RecallItemObj();
        mCnt = mfor_sale_item_object.get_ItemGroupArray().size();
        // set progress bar to total amount of posts
        boolean bool_image_per_post;

        bool_image_per_post = mChkBoxiTemPerPost.isChecked();

        mHlpFbook_posts = new HlpFbook_Posts( mfor_sale_item_object.get_ItemGroupArray(), this, WogalstestGroup, mfor_sale_item_object.get_FS_SaleItemName(), bool_image_per_post );
        mHlpFbook_posts.setEventListener( this );
        mHlpFbook_posts.setEventListener_Final_Post( this );
        mHlpFbook_posts.setEventListener_custom_Post( this );
        mPostProgressIndex = 0;
        mTextView.setText( "Posting Invoked" );
        mHlpFbook_posts.run();
        mProgressBar_Post_Progress.setMax( mCnt + 10 );

    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.PostMultibule_Imagges: {
                Post_multibule_Images();
                break;
            }
            case R.id.FB_1: {
                // test post

                break;
            }
            case R.id.FB_2: {
                break;
            }
            case R.id.FB_3: {
                break;
            }
        }
    }

    @Override
    public int CallBack_OnFinal_On_Mulit_Image_Post (int _post_Index, String _dest_id, GraphResponse _response, ArrayList<GraphResponse> _Response, ArrayList<SaleItemMakeup> _items_2_Post) {
        Message message = Message.obtain();
        String mStr = "";
        mPostProgressIndex++;
        mStr = "Post Index -> " + mPostProgressIndex;
        message.obj = mStr;
        message.arg1 = mPostProgressIndex;
        CrossThreadHandler_ShowPostProgress.sendMessageAtFrontOfQueue( message );
        return 0;
    }

    @Override
    public int CallBack_On_Custom_Post (int _post_Index, String _destination_id, GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post) {
        return 0;
    }

    @Override
    public int CallBackFunction_EachPost (int _post_Index, String _destination_id, GraphResponse _response, ArrayList<GraphResponse> mMultiPost_Response, ArrayList<SaleItemMakeup> _items_2_Post) {
        Message message = Message.obtain();
        message.arg1 = _post_Index;

        CrossThreadHandler_ShowPostProgress.sendMessage( message );
        return 0;
    }

    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }


//    END
}












