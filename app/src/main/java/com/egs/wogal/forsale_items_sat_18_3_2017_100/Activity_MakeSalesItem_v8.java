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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.For_Sale_Item_Object;
import For_Sale_Item_Object_Pkg.SaleItemMakeup;
import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

import static JavaClasses_pkg_100.ImageClassHelper.rotateImage;
import static JavaClasses_pkg_100.Storage_Helper_Class.GetBaseStorageFilePathAndAddFile;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.But_pici_v9;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.But_recall_obj_v8;
import static com.egs.wogal.forsale_items_sat_18_3_2017_100.R.id.text_view_sales_item_name_v2;

public class Activity_MakeSalesItem_v8 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Wogal v8";

    private String mPostFileName;

    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private String mImageFileLocation = null;
    private ImageView mPhotoCaptureImageView;

    // horizontal item pics RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Button mBut_saveItemObj;
    private Button mBut_RecallIemObj;


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


    private AlertDialog Dialog_Itemview;
    private View mView_Itemview;
    private AlertDialog.Builder mBuilderItemView = null;


    private boolean mTestBoolExecuteTrue = false;


    // master item class object
    private For_Sale_Item_Object For_Sale_Item_ObjectCls = null;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_v8 );

        // get extra passed info

        mPostFileName = getIntent().getStringExtra("post_file_name");

        mImageFileLocation = GetBaseStorageFilePathAndAddFile( "Wogals_Temp_Pic_100", "jpg" );

        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        mBtnSalesItemName_v8 = (Button) findViewById( R.id.But_item_name_v8 );
        mBtnSalesItemName_v8.setOnClickListener( this );

        mTxtItemName_v8 = (TextView) findViewById( R.id.txt_v_sales_item_name_v8 );
        mTxtItemName_v8.setOnClickListener( this );

        mBut_itemTextHeader_v8 = (Button) findViewById( R.id.But_text_header_v8 );
        mBut_itemTextHeader_v8.setOnClickListener( this );

        mTxtView_ItemHeaderText_v8 = (TextView) findViewById( R.id.txt_v_text_header_v8 );
        mTxtView_ItemHeaderText_v8.setOnClickListener( this );


        mBut_saveItemObj = (Button) findViewById( R.id.But_save_obj_v8 );
        mBut_saveItemObj.setOnClickListener( this );

        mBut_RecallIemObj = (Button) findViewById( But_recall_obj_v8 );
        mBut_RecallIemObj.setOnClickListener( this );

        RecallItemObj();

        // horizontal Rec View
        if (true) {
            mRecyclerView = (RecyclerView) findViewById( R.id.Rec_itemPics_v8 );
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
            case R.id.But_save_obj_v8: {
                SaveItemObj();
                break;
            }
            case But_recall_obj_v8: {
                takePhoto();
                //   RecallItemObj();
                break;
            }

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

    private For_Sale_Item_Object RecallItemObj () {
        For_Sale_Item_Object fsObj = null;
        String file = "earle.ser";
        String path;
        path = Storage_Helper_Class.MakeOrCheck_If_Folder_Exists( "For_Sale_100" );
        path = path + "/" + file;
        // see if item file exist ,, if not put in blank default values else get from file
        File fs = new File( path );
        if (!fs.exists())
            GetCreateSalesItemObject();
        try {
            ObjectInputStream in = new ObjectInputStream( new FileInputStream( path ) );
            fsObj = (For_Sale_Item_Object) in.readObject();
        } catch (IOException e) {
            fsObj = new For_Sale_Item_Object();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            fsObj = new For_Sale_Item_Object();
            e.printStackTrace();
        } finally {
            //    For_Sale_Item_ObjectCls = new For_Sale_Item_Object();
            //    return ;
        }
        // pop layout ( View )
        mTxtView_ItemHeaderText_v8.setText( fsObj.get_FS_ItemHeaderText() );
        mTxtItemName_v8.setText( fsObj.get_FS_SaleItemName() );
        // make voice arry to sound file
        Storage_Helper_Class.ByteArray_2_File( Storage_Helper_Class.GetVoiceFilePath(), fsObj.get_FS_ItemHeaderVoiceFileData() );
        For_Sale_Item_ObjectCls = fsObj;
        return fsObj;
    }

    private void SaveItemObj () {
        // put in any data
        boolean _test = false;
        _test = Storage_Helper_Class.canWriteToExternalStorage( this );

        if (true) {
            For_Sale_Item_ObjectCls.set_FS_SaleItemName( mTxtItemName_v8.getText().toString() );
            For_Sale_Item_ObjectCls.set_FS_ItemHeaderText( mTxtView_ItemHeaderText_v8.getText().toString() );
            String file = "earle.ser";
            String path;
            path = Storage_Helper_Class.MakeOrCheck_If_Folder_Exists( "For_Sale_100" );
            path = path + "/" + file;
            try {
                ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( path ) );
                out.writeObject( For_Sale_Item_ObjectCls );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.finish();
    }


    private For_Sale_Item_Object GetCreateSalesItemObject () {
        For_Sale_Item_ObjectCls = new For_Sale_Item_Object();
        return For_Sale_Item_ObjectCls;
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
        if (true) {
            if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
                if (false)
                    return;
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
        Button mBut_Voice_Memo_v9;
        Button mBut_Exit_and_save_v9;
        Button mBut_Exit_NO_save_v9;

        // if _itemPosistion == 0 then new item else populate end edit existing item
        if (_itemPosistion > 0) {
            // get pic of selected item
            Bitmap bm;
            SaleItemMakeup iTem = GetSlectedItemGroup( _itemPosistion );
            bm = iTem.get_Bitmap();
            mTextView = (TextView) Dialog_Itemview.findViewById( R.id.txt_view_item_content_header_txt_v9 );
            mPhotoCaptureImageView = (ImageView) Dialog_Itemview.findViewById( R.id.capturePhotoImageView );
            mPhotoCaptureImageView.setImageBitmap( bm );

        }


        mBut_Text_Memo_v9 = (Button) Dialog_Itemview.findViewById( R.id.But_text_memo_v9 );
        mBut_Text_Memo_v9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {

            }
        } );

        mBut_Voice_Memo_v9 = (Button) Dialog_Itemview.findViewById( R.id.But_record_voice_memo_v9 );
        mBut_Voice_Memo_v9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {

            }
        } );

        mBut_Exit_and_save_v9 = (Button) Dialog_Itemview.findViewById( R.id.But_item_save_v9 );
        mBut_Exit_and_save_v9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                // get new item and save
                // put v9 into   For_Sale_Item_Object.get/set_ItemGroupArray< SaleItemMakeup>
                //       For_Sale_Item_ObjectCls.get_SaleItemMakeup();
                Add_SaleItemMakeup_2_set_ItemGroupArray();
                Dialog_Itemview.dismiss();
                mBuilderItemView = null;
            }
        } );
        mBut_Exit_NO_save_v9 = (Button) Dialog_Itemview.findViewById( R.id.But_item_No_save_v9 );
        mBut_Exit_NO_save_v9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Dialog_Itemview.dismiss();
                mBuilderItemView = null;
            }
        } );

        mTextView = (TextView) Dialog_Itemview.findViewById( R.id.txt_view_item_content_header_txt_v9 );
        mPhotoCaptureImageView = (ImageView) Dialog_Itemview.findViewById( R.id.capturePhotoImageView );

        //   if (_itemPosistion == 0)
        //    mTextView.setText( "Item # " + _itemPosistion + ":" );
        if (true) {
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

    private void Add_SaleItemMakeup_2_set_ItemGroupArray () {
        SaleItemMakeup mSaleItemMakeup = new For_Sale_Item_Object();
        ArrayList<SaleItemMakeup> mItemList;
        mSaleItemMakeup.set_Bitmap( ((BitmapDrawable) mPhotoCaptureImageView.getDrawable()).getBitmap() );
        mItemList = For_Sale_Item_ObjectCls.get_ItemGroupArray();
        mItemList.add( mSaleItemMakeup );
        For_Sale_Item_ObjectCls.set_ItemGroupArray( mItemList );
        SaveItemObj();
    }

    private SaleItemMakeup GetSlectedItemGroup (int _posistion) {
        SaleItemMakeup mSaleItemMakeup;
        ArrayList<SaleItemMakeup> mItemList;
        mItemList = For_Sale_Item_ObjectCls.get_ItemGroupArray();
        mSaleItemMakeup = mItemList.get( _posistion - 1 );
        return mSaleItemMakeup;
    }

    //endregion


}

