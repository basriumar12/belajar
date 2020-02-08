package sch.id.aqilah4.elearning.core.dashboard.account;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.models.ResponseAuth;
import sch.id.aqilah4.elearning.utils.SessionManagement;

public class AccountFragment extends Fragment implements AccountView {
    private HashMap<String, String> user;
    private AccountPresenter accountPresenter;

    @BindView(R.id.detail_fullname)
    TextView fullname;
    @BindView(R.id.detail_username)
    TextView username;
    AppCompatButton detail_change_password;
    public AccountFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        fullname = (TextView)view.findViewById(R.id.detail_fullname);
        username = (TextView)view.findViewById(R.id.detail_username);
        detail_change_password = (AppCompatButton)view.findViewById(R.id.detail_change_password);
        detail_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
        initComponent(view);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initComponent(View view) {
        accountPresenter    = new AccountPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        Load Data Detail
         */
        accountPresenter.loadMyProfile();
    }

    /* Change Password */
    @OnClick(R.id.detail_change_password)
    public void changePassword(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view   = LayoutInflater.from(getActivity()).inflate(R.layout.modal_changepassword, null);
        builder.setView(view);

        final EditText newusername  = (EditText) view.findViewById(R.id.modal_newpassword);
        final EditText oldpassword  = (EditText) view.findViewById(R.id.modal_oldpassword);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String usernamenew = newusername.getText().toString().trim();
                String usernameold  = oldpassword.getText().toString().trim();

                accountPresenter.changePassword(usernamenew, usernameold);
            }
        });
        builder.create().show();
    }

    @Override
    public void loadDetail(HashMap<String, String> user) {
        this.user = user;
        /* Passed Data */
        try {


        fullname.setText(user.get(SessionManagement.key_fullname).toString().trim());
        username.setText(user.get(SessionManagement.key_username).toString().trim());

        } catch (Exception e){
            Log.e("TAG","error "+e);
        }
    }


      public void passwordChangged(ResponseAuth responseAuth) {
        if (responseAuth.getStatus())
            Toast.makeText(getActivity(), responseAuth.getMessage().toString(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), responseAuth.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordChanggedError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accountPresenter.destroyData();
    }
}
