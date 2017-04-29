package Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME;

import android.app.Activity;
import android.os.Environment;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.io.File;

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

        mResult =  CheckIfPath_exits(_context, mConcaternated_path,true);

        mNewfolder = new File( mConcaternated_path );
        if (!mNewfolder.exists()) {
            mNewfolder.mkdirs();
        }
        try {
            mNewfolder = new File( mConcaternated_path,_fileName );
            mNewfolder.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // get path to check for file name validity

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
