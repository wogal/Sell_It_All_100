package JavaClasses_pkg_100;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.egs.wogal.forsale_items_sat_18_3_2017_100.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wogal on 3/25/2017.
 */

public class Adapterdrops extends RecyclerView.Adapter<Adapterdrops.DropHolder> {
    private LayoutInflater mInflater;
    private ArrayList<String> mItems = new ArrayList<>();

    public Adapterdrops (Context context) {
        mInflater = LayoutInflater.from( context );
        mItems = genValues();
    }

    public static ArrayList<String> genValues () {
        ArrayList<String> Slist = new ArrayList<>();
        for (int cnt = 0; cnt != 10; cnt++) {
            Slist.add( "Item -> " + cnt );
        }
        return Slist;
    }


    @Override
    public void onBindViewHolder (DropHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder( holder, position, payloads );
    }

    @Override
    public DropHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.layout_v7, parent, false );
        DropHolder holder = new DropHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder (DropHolder holder, int position) {
        holder.mTxtview.setText( mItems.get( position ));
    }

    @Override
    public int getItemCount () {
        return mItems.size();
    }

    public static class DropHolder extends RecyclerView.ViewHolder {
        private TextView mTxtview;
        private ImageView mImgView;


        public DropHolder (View itemView) {
            super( itemView );
            mTxtview = (TextView) itemView.findViewById( R.id.textV7 );

        }
    }
}





