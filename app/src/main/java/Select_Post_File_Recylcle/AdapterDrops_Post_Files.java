package Select_Post_File_Recylcle;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.util.ArrayList;

/**
 * Created by wogal on 5/6/2017.
 */

public class AdapterDrops_Post_Files extends RecyclerView.Adapter<AdapterDrops_Post_Files.DropHolder_Post_Files> {
    private LayoutInflater mInflater;
    private ArrayList<String> mItems = new ArrayList<>();

    public AdapterDrops_Post_Files (Context _context) {
        mInflater = LayoutInflater.from( _context );
        mItems = genValues();

    }


    public static ArrayList<String> genValues () {
        ArrayList<String> Slist = new ArrayList<>();
        for (int cnt = 0; cnt != 20; cnt++) {
            Slist.add( "Item -> " + cnt );
        }
        return Slist;
    }


    @Override
    public DropHolder_Post_Files onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.layout_post_file_layout_v18, parent, false );
        DropHolder_Post_Files holder = new DropHolder_Post_Files( view );
        return holder;
    }

    @Override
    public void onBindViewHolder (DropHolder_Post_Files holder, int position) {
        String path = "";
        Bitmap bm;
        Bitmap bm_1;
        holder.mTxtview.setText( mItems.get( position ) );
        // put in image
        //   path = Storage_Helper_Class.GetBaseStorageFilePathAndAddFile( "wogal", "jpg" );
        //  bm = BitmapFactory.decodeFile( path );
        //  bm_1 = ImageClassHelper.forMatImage_4_ImageView( bm, path );
        // holder.mImgView.setImageBitmap( bm );
        holder.mImgView.setImageResource( R.drawable.bug );
        path = "";
    }

    @Override
    public int getItemCount () {
        return mItems.size();
    }

    public static class DropHolder_Post_Files extends RecyclerView.ViewHolder {
        private TextView mTxtview;
        private ImageView mImgView;

        public DropHolder_Post_Files (View itemView) {
            super( itemView );
            mTxtview = (TextView) itemView.findViewById( R.id.text_v18 );
            mImgView = (ImageView) itemView.findViewById( R.id.Image_View_v18 );
        }

    }


}
