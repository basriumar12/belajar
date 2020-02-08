package sch.id.aqilah4.elearning.core.examination;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.quiz.QuizActivity;
import sch.id.aqilah4.elearning.core.quiz.QuizV2Activity;
import sch.id.aqilah4.elearning.models.ResponsePackage;
import sch.id.aqilah4.elearning.models.ResponsePassedExam;

public class ExaminationActivity extends AppCompatActivity implements ExaminationView {
    private ExaminationPresenter presenter;
    private String examId;
    private ProgressDialog loading;
    @BindView(R.id.exam_title)
    TextView exam_title;
    @BindView(R.id.exam_time)
    TextView exam_time;
    @BindView(R.id.exam_total)
    TextView exam_total;
    @BindView(R.id.exam_join)
    AppCompatButton exam_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        exam_title = (TextView) findViewById(R.id.exam_title);
        exam_time = (TextView) findViewById(R.id.exam_time);
        exam_total = (TextView) findViewById(R.id.exam_total);
        exam_join = (AppCompatButton) findViewById(R.id.exam_join);
        ButterKnife.bind(this);
        initComponent();
    }
    private void initComponent(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.setTitle(getIntent().getStringExtra("title"));
        }
        presenter   = new ExaminationPresenter(this);
        examId  = getIntent().getStringExtra("id");
        loading = new ProgressDialog(this);

        // Load Data
        presenter.loadAll(examId);
        exam_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                joinExam();
            }
        });
    }

    @Override
    public void showLoading() {
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage("Loading...");
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void dataExamination(ResponsePackage responsePackage) {
        if (responsePackage.getStatus()){
            exam_title.setText(responsePackage.getExamTitle().toString() + " - " + responsePackage.getExamDetail().toString());
            exam_time.setText(responsePackage.getExamTime().toString() + " " + getResources().getString(R.string.waktu_soal));
            exam_total.setText(responsePackage.getExamTotal().toString() + " " + getResources().getString(R.string.total_soal));
        }
    }

    @Override
    public void dataExaminationFailed(String message) {
        Toast.makeText(this, "Error:"+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataPassedExamination(ResponsePassedExam responsePassedExam) {

    }

    @Override
    public void dataPassedExaminationFailed(String message) {

    }

    @OnClick(R.id.exam_join)
    public void joinExam(){
        Intent intent   = new Intent(this, QuizActivity.class);
        intent.putExtra("id", examId);
        intent.putExtra("title", getIntent().getStringExtra("title"));
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyData();
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
}
