package com.thesis.com.thesis.singleton;

import com.thesis.model.Student;
import com.thesis.rest.DataApi;

import retrofit.RestAdapter;

/**
 * Created by Piotr on 2015-01-06.
 */
public class ThesisSingleton {

    private static ThesisSingleton instance;
    private Student loggedStudent;

    private DataApi dataApi;

    private ThesisSingleton(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.0.2.7:9090")
                .build();

        dataApi = restAdapter.create(DataApi.class);
    }

    public static ThesisSingleton instance () {
        if(instance==null){
            instance = new ThesisSingleton();
        }
        return instance;
    }

    public DataApi getDataApi() {
        return dataApi;
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }
}
