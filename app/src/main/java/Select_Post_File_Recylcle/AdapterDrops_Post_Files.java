package Select_Post_File_Recylcle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.Activity_MakeSalesItem_v8;
import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.io.File;
import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.Post_Sales_Item_MakeUp;
import For_Sale_Item_Object_Pkg.Post_Sales_Master_Object;
import Holder_4_Odd_Things_and_Crap_waiting_4_a_BETTER_HOME.File_Helper_Items;

/**
 * Created by wogal on 5/6/2017.
 */

public class AdapterDrops_Post_Files extends RecyclerView.Adapter<AdapterDrops_Post_Files.DropHolder_Post_Files> {
    private static RecyclerViewClickListener itemListener;
    public ArrayList<File> mFiles = new ArrayList<>();
    public ArrayList<Post_Sales_Master_Object> mPost_Object = new ArrayList<>();
    public LinearLayout m_ItemRecle_Id;
    public int mCount = 0;
    private LayoutInflater mInflater;
    private Context mContext;

    public AdapterDrops_Post_Files (Context _context) {
        mFiles = new ArrayList<>();
        mPost_Object = new ArrayList<>();
        mInflater = LayoutInflater.from( _context );
        mContext = _context;
        String mAbsPath_str;
        mFiles = Post_File_Array_Class.Get_Post_Files_List( (Activity) mContext );
        // get master post object
        for (int i = 0; i != mFiles.size(); i++) {
            mAbsPath_str = mFiles.get( i ).getName();
            Post_Sales_Master_Object mPostObj = File_Helper_Items.Get_4_Sale_Post_Obj( (Activity) _context, mAbsPath_str );
            mPost_Object.add( mPostObj );
        }


    }


    public static ArrayList<String> genValues () {
        ArrayList<String> Slist = new ArrayList<>();
        for (int cnt = 0; cnt != 20; cnt++) {
            Slist.add( "Item -> " + cnt );
        }
        return Slist;
    }


    @Override
    public DropHolder_Post_Files onCreateViewHolder (final ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.layout_post_file_layout_v18, parent, false );
        DropHolder_Post_Files holder = new DropHolder_Post_Files( view, mContext, this );

        if (false) {
            m_ItemRecle_Id = (LinearLayout) view.findViewById( R.id.ItemView_v18 );
            m_ItemRecle_Id.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    switch (v.getId()) {
                        case R.id.ItemView_v18: {
                            //       Toast.makeText( null, "Wogal Heck ", Toast.LENGTH_LONG ).show();
                            mCount = R.layout.layout_post_file_layout_v18;
                            //  Intent intent_v0 = new Intent(null,Activity_layout_v9_item_content.class );
                            Intent m_Intent = new Intent( mContext, Activity_MakeSalesItem_v8.class );
                            String mFileString;
                            m_Intent.putExtra( "post_file_name", "Wogalt.pst" );
                            mContext.startActivity( m_Intent );
                            break;
                        }
                    }
                }
            } );
        }
        return holder;
    }

    @Override
    public void onBindViewHolder (DropHolder_Post_Files holder, int position) {
        String path = "";
        Bitmap bm;
        Bitmap bm_1;
        Post_Sales_Master_Object mPost_sales_master_object;
        Post_Sales_Item_MakeUp mPost_sales_item_makeUp;
        // put in post file name
        if (position == 1)
            bm = null;
        String mStr1, mStr2, mStr3;
        mStr1 = mFiles.get( position ).getName();
        mStr2 = String.format( "Cost $ %.2f", mPost_Object.get( position ).get_FS_PostCost() );
        mStr3 = String.format( "%s\n%s", mStr1, mStr2 );
        holder.mTxtview.setText( mStr3 );
        // get pic ( if present )
        mPost_sales_master_object = mPost_Object.get( position );
        if (mPost_sales_master_object.get_ItemGroupArray().size() == 0) {
            holder.mImgView.setImageResource( R.drawable.bug );
        } else { // get 1st pic
            bm = mPost_sales_master_object.get_ItemGroupArray().get( 0 ).get_Bitmap();
            holder.mImgView.setImageBitmap( bm );
        }


        //   holder.

        // put in image
        //   path = Storage_Helper_Class.GetBaseStorageFilePathAndAddFile( "wogal", "jpg" );
        //  bm = BitmapFactory.decodeFile( path );
        //  bm_1 = ImageClassHelper.forMatImage_4_ImageView( bm, path );
        // holder.mImgView.setImageBitmap( bm );

        path = "";
    }

    @Override
    public int getItemCount () {
        return mFiles.size();
    }

/*
    @Override
    public void recyclerViewListClicked (View v, int position) {
        mCount = position;
    }*/


    public static class DropHolder_Post_Files extends RecyclerView.ViewHolder {
        public int mTest = 0;
        private TextView mTxtview;
        private ImageView mImgView;
        private AdapterDrops_Post_Files mAdapterDrops_post_files;
        private Context mContext;

        public DropHolder_Post_Files (View itemView, Context _Context, AdapterDrops_Post_Files _adapterDrops_post_files) {
            super( itemView );

            // AdapterDrops_Post_Files

            mAdapterDrops_post_files = _adapterDrops_post_files;

            //  _adapterDrops_post_files.mCount;

            mContext = _Context;


            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent m_Intent = new Intent( mContext, Activity_MakeSalesItem_v8.class );
                    String mFileString;
                    int pos;
                    pos = getLayoutPosition();
                    File mFile;
                    mFile = mAdapterDrops_post_files.mFiles.get( pos );
                      mFileString = mFile.getName();
                    m_Intent.putExtra( "post_file_name",mFileString );
                    mContext.startActivity( m_Intent );
                }
            } );

            // get list of post files and place into mFiles[]
            //     mFiles = Post_File_Array_Class.Get_Post_Files_List( (Activity) mContext );
            mTxtview = (TextView) itemView.findViewById( R.id.text_v18 );
            mImgView = (ImageView) itemView.findViewById( R.id.Image_View_v18 );
        }


    }
}






















