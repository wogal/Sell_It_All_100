package Psot_Mangr_v11;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wogal on 4/26/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder>{
    @Override
    public DropHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder (DropHolder holder, int position) {

    }

    @Override
    public int getItemCount () {
        return 0;
    }

    public static class DropHolder extends RecyclerView.ViewHolder{

      public DropHolder (View itemView) {
          super( itemView );
      }
  }
}
