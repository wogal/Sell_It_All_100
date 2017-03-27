package JavaClasses_pkg_100;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public static String saveImage (Bitmap b, String _name, String _extension) {
        OutputStream fOutputStream;
        fOutputStream = null;
        String AbsPath = GetBaseStorageFilePathAndAddFile( _name, _extension );
        try {
            fOutputStream = new FileOutputStream( AbsPath );
            b.compress( Bitmap.CompressFormat.JPEG, 100, fOutputStream );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fOutputStream.flush();
            fOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AbsPath;
    }

    public static String GetVoiceFilePath () {
        String imageFileName = "Wogals_Voice_0";
        File strorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        //    File Tmp_image = File.createTempFile(imageFileName, ".jpg", strorageDirectory);
        String AbsFilePath = strorageDirectory + "/wogals_voice.3gp";
        return AbsFilePath;
    }

    public static String GetBaseStorageFilePathAndAddFile (String _file, String _file_ext) {
        File strorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        //    File Tmp_image = File.createTempFile(imageFileName, ".jpg", strorageDirectory);
        String AbsFilePath = strorageDirectory + "/" + _file + "." + _file_ext; //    "/wogals_voice.3gp";
        return AbsFilePath;
    }


    public static String MakeAbsoulteFromPathAndFile (String _path, String _filename) {
        String fullAbsPath = "";
        fullAbsPath = MakeOrCheck_If_Folder_Exists( _path );
        fullAbsPath = fullAbsPath + "/" + _filename;
        return (fullAbsPath);
    }

    public static String MakeOrCheck_If_Folder_Exists (String _path) {
        String state;
        // see if fisrt char in path is "/" if not make sure it starts and ends with a "/"
        // remove all "/"  ( if eny exists )
        _path = _path.replaceAll( "/", "" );
        // now add "/" to start & end of path
        _path = new StringBuilder( _path ).insert( 0, "/" ).toString();
        _path = new StringBuilder( _path ).insert( _path.length(), "/" ).toString();
        state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals( state )) {
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File( Root.getAbsolutePath() + _path );
            if (!Dir.exists()) {
                Dir.mkdir();
            }
            _path = Dir.toString();
            return (_path);
        }

        return _path; // not mounter or error
    }

    public String getExternalStorageDirectory () {
        File path;
        String str;
        path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        str = path.getAbsolutePath();
        return (str);
    }


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
