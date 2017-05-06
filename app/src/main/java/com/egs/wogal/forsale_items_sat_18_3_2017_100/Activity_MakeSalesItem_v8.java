package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.Post_Sales_Item_MakeUp;
import For_Sale_Item_Object_Pkg.Post_Sales_Master_Object;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.Add_Remove_Decoration;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.File_Helper_Items;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.System_Locale_Helpers;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.System_Shared_Constants;
import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

import static JavaClasses_pkg_100.ImageClassHelper.rotateImage;
import static JavaClasses_pkg_100.Storage_Helper_Class.GetBaseStorageFilePathAndAddFile;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.But_pici_v9;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.But_recall_obj_v8;

public class Activity_MakeSalesItem_v8 extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "Wogal v8";
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private String mPostFileName;
    private String mImageFileLocation = null;
    private ImageView mPhotoCaptureImageView;

    // horizontal item pics RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Button mBut_saveItemObj;
    private Button mBut_RecallIemObj;

    private LinearLayout mLinearLayout;


    private Button mBtn_Sales_Post_Item_Details_v8;


    private AlertDialog Dialog_Itemview;
    private View mView_Itemview;
    private AlertDialog.Builder mBuilderItemView = null;

    // post display name file name
    private TextView mTxtView_PostFileName;
    // post details
    private TextView mTxt_Post_Details_v8;
    // post cost
    private TextView mTxtV_post_cost;


    // master item class object
    private Post_Sales_Master_Object For_Sale_Item_ObjectCls = null;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v8 );

        // get extra passed info
        mPostFileName = getIntent().getStringExtra( "post_file_name" );

        mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        mBtn_Sales_Post_Item_Details_v8 = (Button) findViewById( R.id.But_Sales_Post_Item_Details_v8 );
        mBtn_Sales_Post_Item_Details_v8.setOnClickListener( this );

        mTxt_Post_Details_v8 = (TextView) findViewById( R.id.txt_v_Sales_Post_Item_Details_v8 );
        mTxt_Post_Details_v8.setOnClickListener( this );

        mTxtView_PostFileName = (TextView) findViewById( R.id.txt_file_name_v8 );
        mTxtView_PostFileName.setText( String.format( "File Name : %s.%s", mPostFileName, getString( R.string.post_file_ext ) ) );

        mBut_saveItemObj = (Button) findViewById( R.id.But_save_obj_v8 );
        mBut_saveItemObj.setOnClickListener( this );

        mBut_RecallIemObj = (Button) findViewById( But_recall_obj_v8 );
        mBut_RecallIemObj.setOnClickListener( this );

        mTxtV_post_cost = (TextView) findViewById( R.id.txt_v_post_cost_v8 );
        mTxtV_post_cost.setOnClickListener( this );

        mLinearLayout = (LinearLayout) findViewById( R.id.LinLayOut_PostItem_details_v8 );
        mLinearLayout.setOnClickListener( this );



        // RECALL and update layout from recalled post item object ( for consistency )
        For_Sale_Item_ObjectCls = RecallItemObj();
        Update_Layout_from_Post_Sales_Master_Object( For_Sale_Item_ObjectCls );

        For_Sale_Item_ObjectCls = Update_Post_Sales_Master_Object_from_Layout( For_Sale_Item_ObjectCls );


        // horizontal Rec View
        if (true) {
            mRecyclerView = (RecyclerView) findViewById( R.id.Add_Items_v8 );
            mRecyclerView.setHasFixedSize( true );
            mLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false );

            mRecyclerView.setLayoutManager( mLayoutManager );
            Activity mActivity = this;
            mAdapter = new v8_ItemAdapter( (Activity_MakeSalesItem_v8) mActivity, For_Sale_Item_ObjectCls );
            mRecyclerView.setAdapter( mAdapter );

            SnapHelper snapHelper = new LinearSnapHelper();
            //     SnapHelper snapHelper = new GravitySnapHelper ();
            snapHelper.attachToRecyclerView( mRecyclerView );
        }
    }


    public View get_view () {
        View v = null;
        return v;
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.LinLayOut_PostItem_details_v8:
            case R.id.txt_v_Sales_Post_Item_Details_v8:
            case R.id.txt_v_post_cost_v8:
            case R.id.But_Sales_Post_Item_Details_v8: { // call update POST DETAILS v16
                Intent intent_post_item_details = new Intent( this, Activity_Post_Details_v16.class );
                // get latest info from Layout ->  " For_Sale_Item_ObjectCls "
                For_Sale_Item_ObjectCls = Update_Post_Sales_Master_Object_from_Layout( For_Sale_Item_ObjectCls );
                // send copy of " Post_Sales_Master_Object " to child process
                intent_post_item_details.putExtra( System_Shared_Constants.const_bundle_post_OBJECT, For_Sale_Item_ObjectCls );
                startActivityForResult( intent_post_item_details, System_Shared_Constants.const_Data_From_Post_Details );
                break;
            }
            case R.id.But_save_obj_v8: {
                SaveItemObj();
                break;
            }
        }
    }


    private Post_Sales_Master_Object RecallItemObj () {
        Post_Sales_Master_Object fsObj = null;
        String mAbsFullPath_and_extension;
        For_Sale_Item_ObjectCls = File_Helper_Items.Get_4_Sale_Post_Obj( this, mPostFileName );

        return For_Sale_Item_ObjectCls;
    }

    private void SaveItemObj () {
        // put in any data
        File_Helper_Items.Save_4_Sale_Post_Obj( this, mPostFileName, For_Sale_Item_ObjectCls );
        //      this.finish();
    }


    //endregion   Activity frf
    //region Description -- Activity States
    //region Description
    @Override
    protected void onStart () {
        Log.d( TAG, "osStart v8" );
        //   RecallItemObj();
        super.onStart();
    }

    @Override
    protected void onRestart () {
        Log.d( TAG, "  Wogal onRestart v8" );
        //       RecallItemObj();
        super.onRestart();
    }

    @Override
    protected void onResume () {
        Log.d( TAG, "  Wogal onResume v8" );
        //       RecallItemObj();
        super.onResume();
    }

    @Override
    protected void onPause () {
        Log.d( TAG, "  Wogal onPause v8" );
        //        SaveItemObj();
        super.onPause();
    }

    @Override
    protected void onStop () {
        Log.d( TAG, "  Wogal onStop v8" );
        //     SaveItemObj();
        super.onStop();
    }

    @Override
    protected void onDestroy () {
        Log.d( TAG, "  Wogal onDestroy v8 " );
        //     SaveItemObj();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        super.onConfigurationChanged( newConfig );
        Log.d( TAG, " onConfigurationChanged " );
    }
    //endregion


    private void ItemContentTakePicture_v9 () {
        takePhoto();
    }

    public void takePhoto () {
        Log.d( TAG, "takePhoto start" );
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction( MediaStore.ACTION_IMAGE_CAPTURE );
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        // puts bitmap into file and writes to storage ( temp file )
        callCameraApplicationIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( photoFile ) );
        startActivityForResult( callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP );
        Log.d( TAG, "takePhoto end" );
    }

    private File createImageFile () throws IOException {
        Log.d( TAG, "createImageFile start " );
        String AbsFilePath = mImageFileLocation;
        File image = new File( AbsFilePath );
        Log.d( TAG, "createImageFile end " );
        return (image);
    }

    @Override
    public void onLowMemory () {
        super.onLowMemory();
        Log.d( TAG, " ***   onLowMemory   **" );
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Toast.makeText( this, "Wogal Heck ", Toast.LENGTH_LONG ).show();
        Log.d( TAG, "onActivityResult start " );

        switch (requestCode) {
            case ACTIVITY_START_CAMERA_APP: {
                if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
                    Bitmap bm_in;
                    Bitmap bm_out;
                    bm_in = BitmapFactory.decodeFile( mImageFileLocation );
                    //    bm_out = rotateImage( bm_in );
                    bm_in = ImageClassHelper.getResizedBitmap( bm_in, 700, 700 );
                    String path;
                    bm_in = rotateImage( bm_in, mImageFileLocation );
                    path = Storage_Helper_Class.saveImage( bm_in, "wogal", "jpg" );
                    Log.d( TAG, "onActivityResult end ( save pressed " );
                    mPhotoCaptureImageView.setImageBitmap( bm_in );
                }
                break;
            }
            case System_Shared_Constants.const_Data_From_Post_Details: {

                For_Sale_Item_ObjectCls = (Post_Sales_Master_Object) data.getSerializableExtra( System_Shared_Constants.const_bundle_post_OBJECT );
                // update layout
                Update_Layout_from_Post_Sales_Master_Object( For_Sale_Item_ObjectCls );
                break;
            }
        }
    }


    // Make Item Content dialog v9
    //region Description
    public void MakeDialog (View v, int _itemPosistion) {
        if (mBuilderItemView != null)
            return;
        //   SaveItemObj();
        TextView mTextView;

        mBuilderItemView = new AlertDialog.Builder( Activity_MakeSalesItem_v8.this );
        mView_Itemview = getLayoutInflater().inflate( R.layout.layout_v9_item_content, null );
        mBuilderItemView.setView( mView_Itemview );
        Dialog_Itemview = mBuilderItemView.create();
        Dialog_Itemview.show();
        // action button allocations
        Button mBut_Take_Pic_v9;
        Button mBut_Text_Memo_v9;
        Button mBut_Exit_and_save_v9;
        Button mBut_Exit_NO_save_v9;

        // if _itemPosistion == 0 then new item else populate end edit existing item
        if (_itemPosistion > 0) {
            // get pic of selected item
            Bitmap bm;
            Post_Sales_Item_MakeUp iTem = GetSelectedItemGroup( _itemPosistion );
            bm = iTem.get_Bitmap();
            mTextView = (TextView) Dialog_Itemview.findViewById( R.id.txt_view_item_content_header_txt_v9 );
            mPhotoCaptureImageView = (ImageView) Dialog_Itemview.findViewById( R.id.capturePhotoImageView );
            mPhotoCaptureImageView.setImageBitmap( bm );
        }

        mTextView = (TextView) Dialog_Itemview.findViewById( R.id.txt_view_item_content_header_txt_v9 );
        mPhotoCaptureImageView = (ImageView) Dialog_Itemview.findViewById( R.id.capturePhotoImageView );


        if (true) { // action buttons on " layout_v9_item_content "
            mBut_Exit_NO_save_v9 = (Button) Dialog_Itemview.findViewById(R.id.But_item_No_save_v9 );
            mBut_Exit_NO_save_v9.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    // save item
              //      For_Sale_Item_ObjectCls =    Update_For_Sale_Item_ObjectCls_from_Layout(For_Sale_Item_ObjectCls);
                    Dialog_Itemview.dismiss();
                    return;
                 //   Dialog_Itemview = null;
                }
            } );


            mBut_Take_Pic_v9 = (Button) Dialog_Itemview.findViewById( But_pici_v9 );
            mBut_Take_Pic_v9.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Toast.makeText( getApplicationContext(), "hi Take Picture ", Toast.LENGTH_LONG ).show();
                    Log.d( TAG, " Take Picture -- v8 " );
                    ItemContentTakePicture_v9();
                }
            } );

        }
    }


    private Post_Sales_Item_MakeUp GetSelectedItemGroup (int _posistion) {
        Post_Sales_Item_MakeUp mPostSalesItemMakeUp;
        ArrayList<Post_Sales_Item_MakeUp> mItemList;
        mItemList = For_Sale_Item_ObjectCls.get_ItemGroupArray();
        mPostSalesItemMakeUp = mItemList.get( _posistion - 1 );
        return mPostSalesItemMakeUp;
    }


    /**
     * Will take items in layout and populate "Post_Sales_Master_Object" with data
     *
     * @return object populated from layout
     */
    private Post_Sales_Master_Object Update_Post_Sales_Master_Object_from_Layout (Post_Sales_Master_Object _Post_Sales_Master_Object) {
        Post_Sales_Master_Object mPost_sales_master_object;
        mPost_sales_master_object = _Post_Sales_Master_Object;
        String mStr;
        // post details
        mStr = Add_Remove_Decoration.Remove_Decoration_Post_details( mTxt_Post_Details_v8 );
        mPost_sales_master_object.set_FS_Post_Details_Text( mStr );
        // post cost (special case USE float from POST OBJECT )
        // get substring after locale currency char
        String mSubStr;
        String mCostStr;
        mCostStr = (String) mTxtV_post_cost.getText();
        mSubStr = mCostStr.substring( mCostStr.lastIndexOf( System_Locale_Helpers.GetLocaleCurrency_char() ) + 1 );

        mPost_sales_master_object.set_FS_PostCost( Float.parseFloat( mSubStr ) );
        return mPost_sales_master_object;

    }

    /**
     * Will update layout from info in "Post_Sales_Master_Object"
     *
     * @param _Post_Sales_Master_Object
     */
    private void Update_Layout_from_Post_Sales_Master_Object (Post_Sales_Master_Object _Post_Sales_Master_Object) {

        mTxt_Post_Details_v8.setText( Add_Remove_Decoration.Add_Decoration_Post_details( _Post_Sales_Master_Object ) );

        mTxtV_post_cost.setText( System_Locale_Helpers.Format_PostCost_String( _Post_Sales_Master_Object.get_FS_PostCost() ) );
    }


    private Post_Sales_Item_MakeUp Update_For_Sale_Item_ObjectCls_from_Layout(Post_Sales_Item_MakeUp _Post_sales_item_makeUp) {

        Post_Sales_Item_MakeUp mPostSalesItemMakeUp = new Post_Sales_Master_Object();
        ArrayList<Post_Sales_Item_MakeUp> mItemList;
        mPostSalesItemMakeUp.set_Bitmap( ((BitmapDrawable) mPhotoCaptureImageView.getDrawable()).getBitmap() );
        mItemList = For_Sale_Item_ObjectCls.get_ItemGroupArray();
        mItemList.add( mPostSalesItemMakeUp );
        For_Sale_Item_ObjectCls.set_ItemGroupArray( mItemList );
        SaveItemObj();


        return For_Sale_Item_ObjectCls;
    }

    //endregion


}

