package sch.id.aqilah4.elearning.core.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.adapter.PackageAdapter;
import sch.id.aqilah4.elearning.models.ListPackage;
import sch.id.aqilah4.elearning.models.ResponseDetailCategory;
import sch.id.aqilah4.elearning.utils.RecyclerItemClickListener;

public class CategoryActivity extends AppCompatActivity implements CategoryView {
    private CategoryPresenter categoryPresenter;
    private List<ListPackage> listPackages;

    @BindView(R.id.detail_category_detail)
    TextView detail_category;
    @BindView(R.id.detail_category_title)
    TextView title_category;
    @BindView(R.id.detail_category_loading)
    ProgressBar loading_detail_category;
    @BindView(R.id.detail_category_package)
    RecyclerView detail_category_package;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        this.setTitle(getIntent().getStringExtra("title"));
        initComponent();
        // Load Data
        categoryPresenter.loadDataDetail(getIntent().getStringExtra("id"));
    }
    private void initComponent(){
        categoryPresenter   = new CategoryPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        detail_category_package.setLayoutManager(new LinearLayoutManager(this));
        detail_category_package.addOnItemTouchListener(itemClickListener());
        detail_category_package.setItemAnimator(new DefaultItemAnimator());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showLoading() {
        loading_detail_category.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading_detail_category.setVisibility(View.GONE);
    }

    @Override
    public void categoryDetailSuccess(ResponseDetailCategory responseDetailCategory) {
        if (responseDetailCategory.getStatus()){
            // Load Data
            detail_category.setText(responseDetailCategory.getCategoryDetail().toString());
            title_category.setText(responseDetailCategory.getCategoryName().toString());
            // List Package
            this.listPackages   = responseDetailCategory.getListPackage();
            detail_category_package.setAdapter(new PackageAdapter(getApplicationContext(),
                    listPackages, R.layout.item_package));
        }
    }
    private RecyclerItemClickListener itemClickListener(){
        return new RecyclerItemClickListener(getApplicationContext(), detail_category_package, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ListPackage listPackage = listPackages.get(position);
                categoryPresenter.getItemExamination(listPackage, CategoryActivity.this);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }
    @Override
    public void categoryDetailFailed(String message) {
        Toast.makeText(this, "Error: "+ message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToPackage(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryPresenter.destroyData();
    }
}
