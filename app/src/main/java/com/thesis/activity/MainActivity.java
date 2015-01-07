package com.thesis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thesis.com.thesis.singleton.ThesisSingleton;
import com.thesis.model.Student;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import thesis.com.thesis.R;


public class MainActivity extends ActionBarActivity {

    private Button loginButton;
    private EditText index;
    private EditText pesel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.loginButton);
        index = (EditText) findViewById(R.id.indexNumberInput);
        pesel = (EditText) findViewById(R.id.pesel);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonExecute();
            }
        });
    }

    private void loginButtonExecute() {
        Student student = new Student();
        student.setPin(index.getText().toString());
        student.setStudentBookNumber(pesel.getText().toString());

        ThesisSingleton.instance().getDataApi().getStudent(student, new Callback<Student>() {
            @Override
            public void success(Student student, Response response) {
                Intent intent = new Intent(MainActivity.this, ThesisActivity.class);
                intent.putExtra(ThesisActivity.EXTRA_STUDENT, student);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Wystąpił problem. Spróbuj ponownie", Toast.LENGTH_LONG).show();
            }
        });
    }
}


