package sch.id.aqilah4.elearning.core.dashboard.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.adapter.CategoryAdapter;
import sch.id.aqilah4.elearning.adapter.LatestAdapter;
import sch.id.aqilah4.elearning.models.Category;
import sch.id.aqilah4.elearning.models.PackageLatest;
import sch.id.aqilah4.elearning.models.ResponseCategory;
import sch.id.aqilah4.elearning.models.ResponseLatest;
import sch.id.aqilah4.elearning.utils.RecyclerItemClickListener;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment implements HomeView {
    private HomePresenter homePresenter;
    private List<Category> categories;
    private List<PackageLatest> latests;

//    @BindView(R.id.home_listcategory)
    RecyclerView home_listcategory;
//    @BindView(R.id.home_loading)
    ProgressBar home_loading;
//    @BindView(R.id.home_listlatest)
    RecyclerView home_listlatest;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        home_listlatest = view.findViewById(R.id.home_listlatest);
        home_loading = view.findViewById(R.id.home_loading);
        home_listcategory = view.findViewById(R.id.home_listcategory);


        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        homePresenter   = new HomePresenter(this);

        // Create List Category
        home_listcategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        home_listcategory.addOnItemTouchListener(selectItemListener());
        home_listcategory.setItemAnimator(new DefaultItemAnimator());
        // Create List Latest
        home_listlatest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        home_listlatest.addOnItemTouchListener(latestClickListener());
        home_listlatest.setItemAnimator(new DefaultItemAnimator());
    }

    private RecyclerItemClickListener selectItemListener(){
        return new RecyclerItemClickListener(getActivity(), home_listcategory, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Category category   = categories.get(position);
                homePresenter.getItemCategory(category, getActivity());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }
    private RecyclerItemClickListener latestClickListener(){
        return new RecyclerItemClickListener(getActivity(), home_listlatest, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PackageLatest latest    = latests.get(position);
                homePresenter.getItemPackage(latest, getActivity());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter.loadAllData();
    }

    @Override
    public void showLoading() {
        home_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
       home_loading.setVisibility(View.GONE);
    }

    @Override
    public void categorySuccess(ResponseCategory responseCategory) {
        if (responseCategory.getStatus()){
            this.categories = responseCategory.getCategory();
            home_listcategory.setAdapter(new CategoryAdapter(getActivity(), categories));
        }
    }

    @Override
    public void categoryFailed(String failed) {
       Toast.makeText(getActivity(), "Terjadi Kesalahan : "+failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void latestSuccess(ResponseLatest latest) {
        if (latest.getStatus().booleanValue()){
            this.latests    = latest.getPackageLatest();
            home_listlatest.setAdapter(new LatestAdapter(getActivity(), latests, R.layout.item_latest));
        }
    }

    @Override
    public void latestFailed(String failed) {
        Toast.makeText(getActivity(), "Terjadi Kesalahan 2 :"+ failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToCategory(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void moveToPackage(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.destroyData();
    }
}
