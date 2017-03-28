package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

/**
 * Created by wogal on 3/27/2017.
 */

class v8_ItemAdapter extends RecyclerView.Adapter<v8_ItemAdapter.v8_Item_ViewHolder> {

    @Override
    public v8_Item_ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_rec_item_v7, parent, false );
        v8_Item_ViewHolder vh = new v8_Item_ViewHolder( v );


        return vh;
    }

    @Override
    public void onBindViewHolder (v8_Item_ViewHolder holder, int position) {
        holder.mTitle.setText( "wogal" + position );
        String path = "";
        Bitmap bm;
        Bitmap bm_1;
        // put in image
        path = Storage_Helper_Class.GetBaseStorageFilePathAndAddFile( "wogal", "jpg" );
        bm = BitmapFactory.decodeFile( path );
        bm_1 = ImageClassHelper.forMatImage_4_ImageView( bm, path );
        holder.mImageView.setImageBitmap( bm );
    }

    @Override
    public int getItemCount () {
        return 10;
    }


    // is class to hold item views
    public class v8_Item_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AbsListView.OnScrollListener {
        public TextView mTitle;
        public ImageView mImageView;
        private boolean scrollingUp;


        public v8_Item_ViewHolder (View view) {
            super( view );
            view.setOnClickListener( this );


            mTitle = (TextView) view.findViewById( R.id.textV7 );
            mImageView = (ImageView) view.findViewById( R.id.iMageView_v7 );

        }

        @Override
        public void onClick (View v) {
            Toast.makeText( v.getContext(), "Wogal Heck ", Toast.LENGTH_LONG ).show();
        }

        @Override
        public void onScrollStateChanged (AbsListView view, int newState) {
            // Make sure scrolling has stopped before snapping


            /*
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                // layoutManager is the recyclerview's layout manager which you need to have reference in advance
                int visiblePosition = scrollingUp ? layoutManager.findFirstVisibleItemPosition()
                        : layoutManager.findLastVisibleItemPosition();
                int completelyVisiblePosition = scrollingUp ? layoutManager
                        .findFirstCompletelyVisibleItemPosition() : layoutManager
                        .findLastCompletelyVisibleItemPosition();
                // Check if we need to snap
                if (visiblePosition != completelyVisiblePosition) {
                    view.smoothScrollToPosition( visiblePosition );
                    return;
                }
            }
           */


        }

        @Override
        public void onScroll (AbsListView view, int firstVisibleItem, int visibleItemCount,
                              int totalItemCount) {

        }
    }
}

