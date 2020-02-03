package sch.id.aqilah4.elearning.core.authentication.signin;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.authentication.signup.SignUpFragment;
import sch.id.aqilah4.elearning.models.ResponseAuth;
import sch.id.aqilah4.elearning.utils.ActivityUtils;
import sch.id.aqilah4.elearning.utils.SessionManagement;

public class SignInFragment extends Fragment implements SignInView {
    private SignInPresenter signInPresenter;
    private ProgressDialog loading;
    private SessionManagement mSessionManagement;

//    @BindView(R.id.signin_username)
//    TextInputEditText signin_username;
//
//    @BindView(R.id.signin_password)
//    TextInputEditText signin_password;

    //    @BindView(R.id.signin_button)
    AppCompatButton signin_button;

    //    @BindView(R.id.signin_anchor_signup)
    TextView signin_anchor_signup;

    public SignInFragment() {
    }

    TextInputEditText username, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        initComponent(view);

        username = view.findViewById(R.id.signin_username);
        password = view.findViewById(R.id.signin_password);
        signin_button = view.findViewById(R.id.signin_button);

        return view;
    }

    private void initComponent(View view) {
        signInPresenter = new SignInPresenter(this);
        loading = new ProgressDialog(getActivity());
        mSessionManagement = new SessionManagement(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mSessionManagement.is_login()) {
            mSessionManagement.checkAuth();
            getActivity().finish();
        }

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get string uname and pass
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity(), "Input Spesific Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                signInPresenter.loginProcess(uname, pass);

            }
        });
    }


//    @OnClick(R.id.signin_button)
//    public void signInNow(){
//        String username = signin_username.getText().toString();
//        String password = signin_password.getText().toString();
//        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
//            Toast.makeText(getActivity(), "Input Spesific Data", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        signInPresenter.loginProcess(username, password);
//    }

    @OnClick(R.id.signin_anchor_signup)
    public void anchorSignUp() {
        ActivityUtils.goToFragment(getFragmentManager(), new SignUpFragment(), R.id.frame_auth);
    }

    @Override
    public void showLoading(String loadingMessages) {
        loading.setMessage(loadingMessages);
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);

        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void dataFailed(String message) {
        Toast.makeText(getActivity(), "Error : " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataSuccess(ResponseAuth responseAuth) {
        if (responseAuth.getStatus()) {
            Toast.makeText(getActivity(), responseAuth.getMessage().toString(), Toast.LENGTH_SHORT).show();

            mSessionManagement.createSession(
                    responseAuth.getAuth().getUsername().toString(),
                    responseAuth.getAuth().getToken().toString(),
                    responseAuth.getAuth().getFullname().toString(),
                    responseAuth.getAuth().getUserid().toString(),
                    responseAuth.getAuth().getUserkon().toString()
            );
        } else {
            this.dataFailed(responseAuth.getMessage().toString());
        }
        mSessionManagement.checkAuth();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        signInPresenter.destroyData();
    }
}
