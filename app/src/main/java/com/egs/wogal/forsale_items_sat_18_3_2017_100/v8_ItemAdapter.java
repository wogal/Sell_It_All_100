package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import For_Sale_Item_Object_Pkg.For_Sale_Item_Object;
import For_Sale_Item_Object_Pkg.SaleItemMakeup;
import JavaClasses_pkg_100.ImageClassHelper;
import JavaClasses_pkg_100.Storage_Helper_Class;

/**
 * Created by wogal on 3/27/2017.
 */

public class v8_ItemAdapter extends RecyclerView.Adapter<v8_ItemAdapter.v8_Item_ViewHolder> {

    public Context m_Context = null;
    public ImageView mImageView;
    public LinearLayout m_ItemRecle_Id;
    Activity_MakeSalesItem_v8 m_activity_makeSalesItem_v8;
    private AlertDialog Dialog_Itemview;
    private View mView_Itemview;
    private For_Sale_Item_Object mFor_Sale_Item_ObjectCls;


    private int _itemPosistion = 0;

    public v8_ItemAdapter (Activity_MakeSalesItem_v8 _activity_makeSalesItem_v8, For_Sale_Item_Object _for_Sale_Item_ObjectCls) {
        mFor_Sale_Item_ObjectCls = _for_Sale_Item_ObjectCls;
        m_activity_makeSalesItem_v8 = _activity_makeSalesItem_v8;
        mView_Itemview = _activity_makeSalesItem_v8.get_view();
    }

    @Override
    public v8_Item_ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_rec_item_v7, parent, false );
        v8_Item_ViewHolder vh = new v8_Item_ViewHolder( v, m_Context );

        m_ItemRecle_Id = (LinearLayout) v.findViewById( R.id.ItermView_v7 );
        m_ItemRecle_Id.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                switch (v.getId()) {
                    case R.id.ItermView_v7: {
                        m_activity_makeSalesItem_v8.MakeDialog( v, _itemPosistion );
                        break;
                    }
                }
            }
        } );
        return vh;
    }


    @Override
    public void onBindViewHolder (v8_Item_ViewHolder holder, int position) {
        _itemPosistion = position;
        if (holder.getOldPosition() == position)
            return;
        ;
        if (position == 0) {
            // hake new item header
            String path = "";
            Bitmap bm;
            Bitmap bm_1;
            // put in image
            path = Storage_Helper_Class.GetBaseStorageFilePathAndAddFile( "wogal", "jpg" );
            bm = BitmapFactory.decodeFile( path );
            bm_1 = ImageClassHelper.forMatImage_4_ImageView( bm, path );
            holder.mImageView.setImageBitmap( bm );
            holder.mTitle.setText( "Make New Item" );
            holder.mTitle.setTextSize( 25 );
            holder.mTitle.setTextColor( 0xFFFF5044 );
            holder.mImageView.setVisibility( View.GONE );
        } else {
            SaleItemMakeup mSaleItemMakeup;
            ArrayList<SaleItemMakeup> mItemList;
            mItemList = mFor_Sale_Item_ObjectCls.get_ItemGroupArray();
            if (mItemList.size() == 0)
                return;
            mSaleItemMakeup = mItemList.get( position - 1 );
            holder.mImageView.setImageBitmap( mSaleItemMakeup.get_Bitmap() );

            if (true) {
                String path;
                Bitmap bm;
                // put in image
                mSaleItemMakeup = mItemList.get( position - 1 );
                bm = mSaleItemMakeup.get_Bitmap();
                holder.mImageView.setImageBitmap( bm );
                holder.mTitle.setText( mFor_Sale_Item_ObjectCls.get_FS_SaleItemName() );
                holder.mTitle.setText( "Pos -> " + position );
                holder.mImageView.setVisibility( View.VISIBLE );
            }
        }
        holder.itemView.setHorizontalScrollBarEnabled( true );
    }

    @Override
    public int getItemCount () {
        int cnt;
        cnt = mFor_Sale_Item_ObjectCls.get_ItemGroupArray().size();
        return cnt + 1;
    }


    // is class to hold item views
    public class v8_Item_ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImageView;


        private AlertDialog Dialog_Itemview;
        private View mView_Itemview;

        public v8_Item_ViewHolder (View _view, Context _Context) {
            super( _view );
            mTitle = (TextView) _view.findViewById( R.id.textV7 );
            mImageView = (ImageView) _view.findViewById( R.id.iMageView_v7 );
            //   _view.setOnClickListener( this );
        }

    }
}












