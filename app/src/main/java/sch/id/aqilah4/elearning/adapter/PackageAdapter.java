package sch.id.aqilah4.elearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.models.ListPackage;


public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {
    private Context context;
    private List<ListPackage> packages;
    private int rowLayout;

    public PackageAdapter(Context context, List<ListPackage> packages, int rowLayout) {
        this.context = context;
        this.packages = packages;
        this.rowLayout = rowLayout;
    }

    @Override
    public PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        PackageViewHolder viewHolder    = new PackageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PackageViewHolder holder, int position) {
        ListPackage listPackage = packages.get(position);
        try {
            holder.package_title.setText(listPackage.getExamTitle().toString());
            holder.package_detail.setText(listPackage.getExamDetail().toString());
            holder.package_time.setText(listPackage.getExamTime().toString() + " " + context.getResources().getString(R.string.waktu_soal));
            holder.package_total.setText(listPackage.getExamTotal().toString() + " " + context.getResources().getString(R.string.total_soal));
        }catch (NullPointerException e){
            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    class PackageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_list_package_title)
        TextView package_title;
        @BindView(R.id.item_list_package_detail)
        TextView package_detail;
        @BindView(R.id.item_list_package_time)
        TextView package_time;
        @BindView(R.id.item_list_package_total)
        TextView package_total;

        public PackageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            package_title = (TextView)itemView.findViewById(R.id.item_list_package_title);
            package_detail = (TextView)itemView.findViewById(R.id.item_list_package_detail);
            package_time = (TextView)itemView.findViewById(R.id.item_list_package_time);
            package_total = (TextView)itemView.findViewById(R.id.item_list_package_total);

        }
    }
}
