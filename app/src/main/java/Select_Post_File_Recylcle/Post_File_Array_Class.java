package Select_Post_File_Recylcle;

import android.app.Activity;
import android.util.Log;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.io.File;
import java.util.ArrayList;

import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.File_Helper_Items;

/**
 * Created by wogal on 5/7/2017.
 */

public class Post_File_Array_Class {

    public static ArrayList<File> Get_Post_Files_List (Activity _context) {
        ArrayList<File> mFileArrayList = new ArrayList<>();
        String mstr_PostFile_Path;
        String mPostFile_ext;
        mPostFile_ext = _context.getString( R.string.post_file_ext );
        String mExt;
        String mFileName;
        mstr_PostFile_Path = File_Helper_Items.GetAbs_Post_path( _context, "" );
        File directory = new File( mstr_PostFile_Path );
        File[] files = directory.listFiles();
        Log.d( "Files", "Size: " + files.length );
        for (int i = 0; i < files.length; i++) {
            mFileName = files[i].getName();
            int test;
            mExt = mFileName.substring( mFileName.lastIndexOf( "." ) + 1 );
            test = mExt.toLowerCase().compareTo( mPostFile_ext.toLowerCase() );
            if (test == 0) {
                mFileArrayList.add( files[i] );
            }
        }
        return mFileArrayList;
    }


}
