package sch.id.aqilah4.elearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.models.History;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Context context;
    private List<History> histories;
    private int rowLayout;

    public HistoryAdapter(Context context, List<History> histories, int rowLayout) {
        this.context = context;
        this.histories = histories;
        this.rowLayout = rowLayout;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        HistoryViewHolder viewHolder    = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = histories.get(position);
        holder.history_title.setText(history.getExamTitle().toString().trim());
        holder.history_weight.setText(history.getExamWeight().toString() + " / " + history.getExamCorrect().toString() + "Poin");
        holder.history_date.setText(history.getExamDate().toString().trim());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.history_title)
        TextView history_title;
        @BindView(R.id.history_date)
        TextView history_date;
        @BindView(R.id.history_weight)
        TextView history_weight;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            history_title = (TextView)itemView.findViewById(R.id.history_title);
            history_date = (TextView)itemView.findViewById(R.id.history_date);
            history_weight = (TextView)itemView.findViewById(R.id.history_weight);
        }
    }
}
