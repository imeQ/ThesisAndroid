package com.thesis.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.thesis.model.Student;

import thesis.com.thesis.R;

public class ThesisActivity extends ActionBarActivity {

    public static final String EXTRA_STUDENT = "thesis.student";

    private TextView name;
    private TextView surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis);
        name = (TextView) findViewById(R.id.nameOutput);
        surname = (TextView) findViewById(R.id.surnameOutput);

        // get data
        Student student = getIntent().getParcelableExtra(EXTRA_STUDENT);
    }
}
