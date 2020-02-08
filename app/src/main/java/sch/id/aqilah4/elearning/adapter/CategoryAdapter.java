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
import sch.id.aqilah4.elearning.models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categories;
    private int rowLayout;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.rowLayout = rowLayout;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        CategoryViewHolder  viewHolder  = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category   = categories.get(position);
        try {
            Glide.with(context)
                    .load(category.getCategoryThumbnail().toString())
                    .into(holder.category_pict);
            holder.category_title.setText(category.getCategoryName().toString());
        }catch (NullPointerException e){
            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_category_pict)
        ImageView category_pict;
        @BindView(R.id.item_category_title)
        TextView category_title;
        public CategoryViewHolder(View itemView) {
            super(itemView);
           category_pict = (ImageView)itemView.findViewById(R.id.item_category_pict);
           category_title = (TextView)itemView.findViewById(R.id.item_category_title);
        }
    }
}
