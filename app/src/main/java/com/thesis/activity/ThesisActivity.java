package com.thesis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thesis.model.Student;

import thesis.com.thesis.R;

public class ThesisActivity extends ActionBarActivity {

    public static final String EXTRA_STUDENT = "thesis.student";

    private TextView name;
    private TextView surname;
    private TextView bookNumber;
    private LinearLayout thesisLayout;
    private TextView thesisName;
    private TextView thesisDescription;
    private Button action;
    private Button logout;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis);
        this.name = (TextView) findViewById(R.id.name);
        this.surname = (TextView) findViewById(R.id.surname);
        this.bookNumber = (TextView) findViewById(R.id.bookNumber);

        this.thesisName = (TextView) findViewById(R.id.thesisName);
        this.thesisDescription = (TextView) findViewById(R.id.thesisDescription);
        this.thesisLayout = (LinearLayout) findViewById(R.id.thesisLayout);

        this.action = (Button) findViewById(R.id.action );
        this.logout = (Button) findViewById(R.id.logout);
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        this.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeThesis();
            }
        });

        // get data
        this.student = getIntent().getParcelableExtra(EXTRA_STUDENT);

        setData();
    }

    private void setData() {
        this.name.setText(student.getFirstName());
        this.surname.setText(student.getLastName());
        this.bookNumber.setText(student.getStudentBookNumber());
        if(this.student.getThesis()!=null){
            this.thesisLayout.setVisibility(View.VISIBLE);
            this.thesisName.setText(student.getThesis().getName());
            this.thesisDescription.setText(student.getThesis().getDescription());
            this.action.setText("Zmień pracę");
        } else {
            this.thesisLayout.setVisibility(View.GONE);
            this.action.setText("Przypisz pracę");
        }
    }

    private void goBack() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {   //wylacz przycisk back
    }

    private void changeThesis() {
        Intent intent = new Intent(ThesisActivity.this, ChangeThesisActivity.class);
        intent.putExtra(ThesisActivity.EXTRA_STUDENT, this.student);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        student = data.getParcelableExtra(EXTRA_STUDENT);
        setData();
    }
}
