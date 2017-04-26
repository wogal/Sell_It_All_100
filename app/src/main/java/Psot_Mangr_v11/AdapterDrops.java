package Psot_Mangr_v11;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

/**
 * Created by wogal on 4/26/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {
    private LayoutInflater mInflater;

    public AdapterDrops (Context _context) {
        mInflater = LayoutInflater.from( _context );
    }

    @Override
    public DropHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.activity__mngr__posts_reckl_item_v11, parent, false );
        DropHolder holder = new DropHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder (DropHolder holder, int position) {

    }

    @Override
    public int getItemCount () {
        return 1;
    }

    public static class DropHolder extends RecyclerView.ViewHolder {

        public DropHolder (View itemView) {
            super( itemView );
        }
    }
}
