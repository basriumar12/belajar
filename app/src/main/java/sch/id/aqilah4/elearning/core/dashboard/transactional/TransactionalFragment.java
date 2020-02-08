package sch.id.aqilah4.elearning.core.dashboard.transactional;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.adapter.HistoryAdapter;
import sch.id.aqilah4.elearning.models.History;
import sch.id.aqilah4.elearning.models.ResponseHistory;

public class TransactionalFragment extends Fragment implements TransactionalView {
    private List<History> histories;
    private TransactionalPresenter presenter;

//    @BindView(R.id.history_list)
    RecyclerView history_list;
//    @BindView(R.id.history_loading)
    ProgressBar history_loading;

    public TransactionalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_transactional, container, false);
//        ButterKnife.bind(this, view);
//        initComponent(view);
        View view = inflater.inflate(R.layout.fragment_transactional, container, false);
        history_loading = view.findViewById(R.id.history_loading);
        history_list = view.findViewById(R.id.history_list);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent(view);
    }

    private void initComponent(View view) {
        presenter   = new TransactionalPresenter(this);
        histories   = new ArrayList<>();
        presenter.loadHistory();
        history_list.setLayoutManager(new LinearLayoutManager(getContext()));
        history_list.setItemAnimator(new DefaultItemAnimator());
        history_list.setAdapter(new HistoryAdapter(getContext(), histories, R.layout.item_history));

    }
    @Override
    public void loadHistory(ResponseHistory history) {
        if (history.getStatus().booleanValue()){
            histories   = history.getHistory();
           history_list.setAdapter(new HistoryAdapter(getContext(), histories, R.layout.item_history));
        }else{
            Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadHistoryError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        history_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        history_loading.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyData();
    }
}
