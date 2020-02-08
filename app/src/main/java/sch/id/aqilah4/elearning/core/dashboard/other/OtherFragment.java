package sch.id.aqilah4.elearning.core.dashboard.other;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.authentication.AuthActivity;

public class OtherFragment extends Fragment implements OtherView {
    private OtherPresenter presenter;
    AppCompatButton other_signout;
    AppCompatButton other_about;
    AppCompatButton other_helpme;


    public OtherFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, view);
        other_about = (AppCompatButton)view.findViewById(R.id.other_about);
        other_helpme = (AppCompatButton)view.findViewById(R.id.other_helpme);
        other_signout = (AppCompatButton)view.findViewById(R.id.other_signout);

        other_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoutProcess();
            }
        });
        other_helpme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpMe();
            }
        });
        other_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUs();
            }
        });
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        presenter   = new OtherPresenter(this);
    }

    @OnClick(R.id.other_signout)

    public void signoutProcess(){
        presenter.signoutSystem();
    }
    @OnClick(R.id.other_about)
    public void aboutUs(){
        Toast.makeText(getActivity(), "Develop by Ali Yasin", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.other_helpme)
    public void helpMe(){
        Uri uri = Uri.parse("https://smkinsanaqilah4jkt.sch.id/contact");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    @Override
    public void signout() {
        Intent intent   = new Intent(getActivity(), AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}
