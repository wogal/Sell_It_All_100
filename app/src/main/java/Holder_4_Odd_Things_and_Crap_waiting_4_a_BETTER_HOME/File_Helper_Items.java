package Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME;

import android.app.Activity;
import android.os.Environment;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by wogal on 4/28/2017.
 * String joinedPath = new File(path1, path2).toString();
 */


public class File_Helper_Items {

    public File_Helper_Items (int a) {
    }


    private static boolean CheckIfPath_exits (Activity _context, String _pathName, boolean _createIfNot_exist) {
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
        String mConcaternated_path;
        File mNewfolder = null;

        mExtStoragePath = IsExternal_StorageMounted();
        mSystemBasePath = _context.getString( R.string.BaseSystemFolder );
        mTemporaryFilePath = _context.getString( R.string.Temporary_SystemFolder );
        // concatenate paths
        mConcaternated_path = new File( mExtStoragePath, mSystemBasePath ).toString();
        mConcaternated_path = new File( mConcaternated_path, mTemporaryFilePath ).toString();

        mResult = CheckIfPath_exits( _context, mConcaternated_path, true );

        mNewfolder = new File( mConcaternated_path );
        if (!mNewfolder.exists()) {
            mNewfolder.mkdirs();
        }
        try {
            mNewfolder = new File( mConcaternated_path, _fileName );
            mNewfolder.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // delete file we just created
        mNewfolder.delete();
        return true;
    }

    public static String GetAbs_Post_path(String _post){
        return "";
    }

    public static boolean Check_and_Create_Post_File (Activity _context, String _fileName, boolean _create_if_not_Exits) {
        boolean mResult = false;
        // make full path and file name from component parts

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

        // check and create path if not exist's
        mResult = CheckIfPath_exits( _context, mConcatenated_path, true );
        // add file and extension to path
        mNewfolder_and_File = String.format( "%s/%s.%s", mConcatenated_path, _fileName, mPostFile_ext );
        // check if file exists
        File file = new File( mNewfolder_and_File );
        if (file.exists())
            return false; // file exits so exit with error
        // create and format post file
        try {
            file.createNewFile();
        } catch (IOException e) {
            return false; // should never happen i know if it can it will ,, lol
        }
        return true;
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

    private String getStringResourceByName (String aString) {
        return "";
    }/* String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);*/


}
