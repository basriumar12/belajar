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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.adapter.CategoryAdapter;
import sch.id.aqilah4.elearning.adapter.LatestAdapter;
import sch.id.aqilah4.elearning.core.dashboard.transactional.TransactionalPresenter;
import sch.id.aqilah4.elearning.core.dashboard.transactional.TransactionalView;
import sch.id.aqilah4.elearning.models.Category;
import sch.id.aqilah4.elearning.models.History;
import sch.id.aqilah4.elearning.models.ListPackage;
import sch.id.aqilah4.elearning.models.PackageLatest;
import sch.id.aqilah4.elearning.models.ResponseCategory;
import sch.id.aqilah4.elearning.models.ResponseHistory;
import sch.id.aqilah4.elearning.models.ResponseLatest;
import sch.id.aqilah4.elearning.utils.RecyclerItemClickListener;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment implements HomeView, TransactionalView {
    private HomePresenter homePresenter;
    private List<Category> categories;
    private List<PackageLatest> latests;
    private List<History> mHistory  ;
    private TransactionalPresenter presenter;
    SwipeRefreshLayout swipe_refersh;


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
        swipe_refersh = view.findViewById(R.id.swipe_refersh);
        home_listlatest = view.findViewById(R.id.home_listlatest);
        home_loading = view.findViewById(R.id.home_loading);
        home_listcategory = view.findViewById(R.id.home_listcategory);


        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        homePresenter   = new HomePresenter(this);
        presenter   = new TransactionalPresenter(this);

        presenter.loadHistory();

        swipe_refersh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadHistory();
                homePresenter.loadAllData();
            }
        });

        // Create List Category
        home_listcategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        home_listcategory.addOnItemTouchListener(selectItemListener());
        home_listcategory.setItemAnimator(new DefaultItemAnimator());
        // Create List Latest
       // home_listlatest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        home_listlatest.setLayoutManager(new GridLayoutManager(getActivity(),2));
        home_listlatest.addOnItemTouchListener(latestClickListener());
        home_listlatest.setItemAnimator(new DefaultItemAnimator());
    }

    private RecyclerItemClickListener selectItemListener(){
        return new RecyclerItemClickListener(getActivity(), home_listcategory, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                    Category category = categories.get(position);
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
                Boolean check = false;
                if (mHistory != null) {
                    for (int i = 0; i < mHistory.size(); i++) {
                        if (mHistory.get(i).getExamTitle().equals(latests.get(position).getExamTitle())) {
                            check = true;
                        }
                    }
                }
                if (!check) {
                    PackageLatest latest = latests.get(position);
                    homePresenter.getItemPackage(latest, getActivity());
                } else {
                    Toast.makeText(getActivity(), "Kamu Sudah Mengikuti "+latests.get(position).getExamTitle(), Toast.LENGTH_SHORT).show();
                }
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
    public void loadHistory(ResponseHistory history) {
        mHistory = new ArrayList<>();
        mHistory = history.getHistory();
        swipe_refersh.setRefreshing(false);
    }

    @Override
    public void loadHistoryError(String message) {
        swipe_refersh.setRefreshing(false);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

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
        swipe_refersh.setRefreshing(false);
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
        swipe_refersh.setRefreshing(false);
        if (latest.getStatus().booleanValue()){
            this.latests    = latest.getPackageLatest();
            home_listlatest.setAdapter(new LatestAdapter(getActivity(), latests, R.layout.item_latest));
        }
    }

    @Override
    public void latestFailed(String failed) {
        swipe_refersh.setRefreshing(false);
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
