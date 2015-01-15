package com.thesis.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thesis.model.Student;
import com.thesis.model.Thesis;
import com.thesis.singleton.ThesisSingleton;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import thesis.com.thesis.R;


public class ChangeThesisActivity extends ActionBarActivity {

    private List<Thesis> thesisList;
    private ListView listView;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_thesis_activity);
        listView = (ListView) findViewById(R.id.thesisListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Thesis item  = (Thesis) parent.getAdapter().getItem(position);
                assignThesis(item);
            }
        });
        this.student = getIntent().getParcelableExtra(ThesisActivity.EXTRA_STUDENT);
        getFileList();
    }

    private void assignThesis(Thesis item) {
        ThesisSingleton.instance().getDataApi().changeThesis(student.getId(), item.getId(), new Callback<Student>() {

            @Override
            public void success(Student student, Response response) {

                Toast.makeText(ChangeThesisActivity.this, "Przypisano", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra(ThesisActivity.EXTRA_STUDENT, student);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ChangeThesisActivity.this, "Blad " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getFileList() {
        ThesisSingleton.instance().getDataApi().getThesisList(new Callback<List<Thesis>>() {
            @Override
            public void success(List<Thesis> list, Response response) {
                ChangeThesisActivity.this.thesisList = list;
                listView.setAdapter(new ThesisxAdapter(ChangeThesisActivity.this, thesisList));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ChangeThesisActivity.this, "Blad pobierania listy prac" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ThesisxAdapter extends ArrayAdapter<Thesis> {

        private LayoutInflater layoutInflater;

        public ThesisxAdapter(Context context, List<Thesis> objects) {
            super(context, R.layout.thesis_item, objects);
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.thesis_item, parent, false);
            }

            TextView nameTextView = (TextView) convertView.findViewById(R.id.itemName);
            TextView descTextView = (TextView) convertView.findViewById(R.id.itemDesc);

            Thesis item = getItem(position);

            nameTextView.setText(item.getName());
            descTextView.setText(item.getDescription());

            return convertView;
        }
    }
}
