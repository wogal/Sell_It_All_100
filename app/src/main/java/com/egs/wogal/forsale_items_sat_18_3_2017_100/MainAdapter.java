package com.egs.wogal.forsale_items_sat_18_3_2017_100;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wogal on 3/27/2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder (MainAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount () {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ViewHolder (View itemView) {
            super( itemView );
        }
    }
}
