package sch.id.aqilah4.elearning.core.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flepsik.github.com.progress_ring.ProgressRingView;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.dashboard.DashboardActivity;
import sch.id.aqilah4.elearning.core.examination.ExaminationActivity;

public class ReportActivity extends AppCompatActivity {
    @BindView(R.id.report_title)
    TextView report_title;
    @BindView(R.id.report_date)
    TextView report_date;
    @BindView(R.id.report_indicator)
    ProgressRingView report_indicator;
    @BindView(R.id.report_total)
    TextView report_total;
    @BindView(R.id.report_correct)
    TextView report_correct;
    @BindView(R.id.report_grade)
    TextView report_grade;
    AppCompatButton report_tryagai;
    AppCompatButton report_session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        report_title = (TextView)findViewById(R.id.report_title);
        report_date = (TextView)findViewById(R.id.report_date);
        report_correct = (TextView)findViewById(R.id.report_correct);
        report_grade = (TextView)findViewById(R.id.report_grade);
        report_total = (TextView)findViewById(R.id.report_total);
        report_indicator = (ProgressRingView)findViewById(R.id.report_indicator) ;
        report_tryagai = (AppCompatButton)findViewById(R.id.report_tryagai);
        report_session = (AppCompatButton)findViewById(R.id.report_session);


        this.setTitle("Laporan Pembelajaran");
        initData();
        report_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(ReportActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
        report_title.setText(getIntent().getStringExtra("title"));
        report_date.setText("Minggu, 4 Juni 2017");
        float data  = Float.valueOf(getIntent().getStringExtra("weight").toString().trim())/100;
        report_indicator.setProgress(data);
        report_total.setText("Total : "+ getIntent().getStringExtra("total"));
        report_correct.setText("Benar : "+ getIntent().getStringExtra("correct"));
        report_grade.setText("Bobot : "+getIntent().getStringExtra("weight"));
    }
    @OnClick(R.id.report_tryagai)
    public void moveToExamination(){
        Intent intent   = new Intent(ReportActivity.this, ExaminationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title", getIntent().getStringExtra("title").trim());
        intent.putExtra("id", getIntent().getStringExtra("id").trim());
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.report_session)
    public void moveToSession(){
        Toast.makeText(this, "Comingsoon", Toast.LENGTH_SHORT).show();
    }
}
