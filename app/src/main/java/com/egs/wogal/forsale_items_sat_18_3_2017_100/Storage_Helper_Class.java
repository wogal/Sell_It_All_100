package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wogal on 3/17/2017.
 */

public class Storage_Helper_Class {

    private Context mContext;


    public Storage_Helper_Class (Context base) {
        mContext = base;
    }

    public static String GetVoiceFilePath () {
        String imageFileName = "Wogals_Voice_0";
        File strorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        //    File Tmp_image = File.createTempFile(imageFileName, ".jpg", strorageDirectory);
        String AbsFilePath = strorageDirectory + "/wogals_voice.3gp";
        return AbsFilePath;
    }

    public static String MakeAbsoulteFromPathAndFile (String _path, String _filename) {
        String fullAbsPath = "";
        try {
            fullAbsPath = MakeOrCheck_If_Folder_Exists( _path );
        } catch (IOException e) {
            e.printStackTrace();
        }
        fullAbsPath = fullAbsPath + "/" + _filename;
        return (fullAbsPath);
    }

    public static String MakeOrCheck_If_Folder_Exists (String path) throws IOException {
        String state;
        // see if fisrt char in path is "/" if not make sure it starts and ends with a "/"
        // remove all "/"  ( if eny exists )
        path = path.replaceAll( "/", "" );
        // now add "/" to start & end of path
        path = new StringBuilder( path ).insert( 0, "/" ).toString();
        path = new StringBuilder( path ).insert( path.length(), "/" ).toString();
        state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals( state )) {
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File( Root.getAbsolutePath() + path );
            if (!Dir.exists()) {
                Dir.mkdir();
            }
            path = Dir.toString();
            return (path);
        }
        return ""; // not mounter or error
    }

    public String SgetExternalStorageDirectory () {
        File path;
        String str;
        path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        str = path.getAbsolutePath();
        return (str);
    }

//
//    public static ArrayList<File> GetListOfImageFiles(String _fileToken) {
//        ArrayList<File>  arrayList = new ArrayList<>();
//
//
//
//    }

    public void copy (File src, File dst) throws IOException {
        InputStream in = new FileInputStream( src );
        OutputStream out = new FileOutputStream( dst );

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read( buf )) > 0) {
            out.write( buf, 0, len );
        }
        in.close();
        out.close();
    }
}
