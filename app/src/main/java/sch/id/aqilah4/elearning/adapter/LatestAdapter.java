package sch.id.aqilah4.elearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.models.PackageLatest;


public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LatestViewHolder>{
    private Context context;
    private List<PackageLatest> packageLatests;
    private int rowLayout;

    public LatestAdapter(Context context, List<PackageLatest> packageLatests, int rowLayout) {
        this.context = context;
        this.packageLatests = packageLatests;
        this.rowLayout = rowLayout;
    }

    @Override
    public LatestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        LatestViewHolder viewHolder = new LatestViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LatestViewHolder holder, int position) {
        PackageLatest latest    = packageLatests.get(position);
        try {
            Glide.with(context)
                    .load(latest.getmExamThumbnail().toString())
                    .into(holder.latest_pict);
            holder.latest_title.setText(latest.getExamTitle().toString());
            holder.latest_time.setText(latest.getExamTime().toString()+ " " + context.getResources().getString(R.string.waktu_soal));
            holder.latest_total.setText(latest.getExamTotal().toString()+ " " + context.getResources().getString(R.string.total_soal));
        }catch (NullPointerException e){
            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return packageLatests.size();
    }

    class LatestViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_latest_pict)
        ImageView latest_pict;
        @BindView(R.id.item_latest_title)
        TextView latest_title;
        @BindView(R.id.item_latest_time)
        TextView latest_time;
        @BindView(R.id.item_latest_total)
        TextView latest_total;
        public LatestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            latest_pict = (ImageView)itemView.findViewById(R.id.item_latest_pict);
            latest_title = (TextView)itemView.findViewById(R.id.item_latest_title);
            latest_time = (TextView)itemView.findViewById(R.id.item_latest_time);
            latest_total = (TextView)itemView.findViewById(R.id.item_latest_total);
        }
    }
}
