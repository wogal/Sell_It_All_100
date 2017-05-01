package Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME;

import android.app.Activity;
import android.os.Environment;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import For_Sale_Item_Object_Pkg.For_Sale_Item_Object;

/**
 * Created by wogal on 4/28/2017.
 * String joinedPath = new File(path1, path2).toString();
 */


public class File_Helper_Items {

    public File_Helper_Items (int a) {
    }


    private static boolean CheckIfPath_exits (String _pathName, boolean _createIfNot_exist) {
        boolean mPathStatus;
        File mNewfolder;
        mNewfolder = new File( _pathName );
        mPathStatus = mNewfolder.exists();
        if ((!mPathStatus) && (_createIfNot_exist == true)) {
            mPathStatus = mNewfolder.mkdirs();
        }
        return mPathStatus;
    }

    // will create a temp file ( and delete if successful ) and return true if valid
    public static boolean Check_4_Valid_File_NameValidity (Activity _context, String _fileName) {
        boolean mResult = false;
        String mTemporaryFilePath = "";
        String mExtStoragePath = "";
        String mTotalFilePath = "";
        String mSystemBasePath = "";
        String mConcatenated_path;
        File mNewfolder = null;

        mExtStoragePath = IsExternal_StorageMounted();
        mSystemBasePath = _context.getString( R.string.BaseSystemFolder );
        mTemporaryFilePath = _context.getString( R.string.Temporary_SystemFolder );
        // concatenate paths
        mConcatenated_path = new File( mExtStoragePath, mSystemBasePath ).toString();
        mConcatenated_path = new File( mConcatenated_path, mTemporaryFilePath ).toString();

        mResult = CheckIfPath_exits( mConcatenated_path, true );

        mNewfolder = new File( mConcatenated_path );
        if (!mNewfolder.exists()) {
            mNewfolder.mkdirs();
        }
        try {
            mNewfolder = new File( mConcatenated_path, _fileName );
            mNewfolder.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // delete file we just created
        mNewfolder.delete();
        return true;
    }

    public static String GetAbs_Post_path (Activity _context, String _fileName) {
        String mExtStoragePath;
        String mPostfolderPath;
        String mSystemBasePath;
        String mConcatenated_path;
        String mNewfolder_and_File;
        String mPostFile_ext;

        mExtStoragePath = IsExternal_StorageMounted();
        mSystemBasePath = _context.getString( R.string.BaseSystemFolder );
        mPostfolderPath = _context.getString( R.string.post_sub_folder );
        mPostFile_ext = _context.getString( R.string.post_file_ext );
        // concatenate paths
        mConcatenated_path = new File( mExtStoragePath, mSystemBasePath ).toString();
        mConcatenated_path = new File( mConcatenated_path, mPostfolderPath ).toString();
        // check path exists if not create ( need to rationalize this )
        CheckIfPath_exits( mConcatenated_path, true );
        mNewfolder_and_File = mConcatenated_path;
        if (_fileName.isEmpty() == false && _fileName.length() > 2) {
            if (_fileName.contains( "." )) { // contains extension ( i think )
                mNewfolder_and_File = String.format( "%s/%s", mConcatenated_path, _fileName );
                return mNewfolder_and_File;
            }
            mNewfolder_and_File = String.format( "%s/%s.%s", mConcatenated_path, _fileName, mPostFile_ext );
        }
        return mNewfolder_and_File;
    }

    public static boolean Check_and_Create_Post_File (Activity _context, String _fileName, boolean _create_if_not_Exits) {
        // make full path and file name from component parts
        String mNewfolder_and_File;
        For_Sale_Item_Object mFor_sale_item_object;
        mNewfolder_and_File = GetAbs_Post_path( _context, _fileName );
        File file = new File( mNewfolder_and_File );
        if (file.exists())
            return false; // file exits so exit with error
        // create and format post file
        if (_create_if_not_Exits) {
            try {
                file.createNewFile();
                // initialize Post File with new "For_Sale_Item_Object"
                mFor_sale_item_object = new For_Sale_Item_Object();
            } catch (IOException e) {
                return false; // should never happen i know if it can it will ,, lol
            }
        }
        return true;
    }


    // will return For_Sale_Item_Object object pointed to by _fileName ( will add path & extension ) , else null if error
    public static For_Sale_Item_Object Get_4_Sale_ItemObj (Activity _context, String _fileName) {
        For_Sale_Item_Object mFor_sale_item_object = null;
        String mAbsFullPath_and_extension;
        // make full abs path and extension
        mAbsFullPath_and_extension = GetAbs_Post_path( _context, _fileName );
        File fs = new File( mAbsFullPath_and_extension );
        if (!fs.exists()) // should never happen
            mFor_sale_item_object = new For_Sale_Item_Object();
        try {
            ObjectInputStream in = new ObjectInputStream( new FileInputStream( mAbsFullPath_and_extension ) );
            mFor_sale_item_object = (For_Sale_Item_Object) in.readObject();
        } catch (IOException e) {
            mFor_sale_item_object = new For_Sale_Item_Object();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            mFor_sale_item_object = new For_Sale_Item_Object();
            e.printStackTrace();
        } finally {
            //    For_Sale_Item_ObjectCls = new For_Sale_Item_Object();
            //    return ;
        }


        return mFor_sale_item_object;
    }


    // will ret length == 0 id ext storage is not available
    public static String IsExternal_StorageMounted () {
        String state;
        String extStoragePath = "";

        state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals( state )) {
            File Root = Environment.getExternalStorageDirectory();
            extStoragePath = Root.getAbsolutePath();
            return (extStoragePath);
        }
        return extStoragePath; // not mounter or error
    }


}
