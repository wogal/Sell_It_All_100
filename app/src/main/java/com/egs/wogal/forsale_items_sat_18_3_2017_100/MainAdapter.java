package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

/**
 * Created by wogal on 3/27/2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_rec_item_v7, parent, false );
        ViewHolder vh = new ViewHolder( v );


        return vh;
    }

    @Override
    public void onBindViewHolder (MainAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText( "wogal" + position );
        String path = "";
        Bitmap bm;
        Bitmap bm_1;


        // put in image
        path = Storage_Helper_Class.GetBaseStorageFilePathAndAddFile( "wogal", "jpg" );
        bm = BitmapFactory.decodeFile( path );
        bm_1 = ImageClassHelper.forMatImage_4_ImageView( bm, path );
        holder.mImageView.setImageBitmap( bm );
        //    holder.mImgView.setImageResource( R.drawable.bug );
        path = "";
    }

    @Override
    public int getItemCount () {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImageView;

        public ViewHolder (View itemView) {
            super( itemView );
            mTitle = (TextView) itemView.findViewById( R.id.textV7 );
            mImageView = (ImageView) itemView.findViewById( R.id.iMageView_v7 );

        }
    }
}
