package com.thesis.singleton;

import com.thesis.common.Const;
import com.thesis.model.Student;
import com.thesis.rest.DataApi;

import retrofit.RestAdapter;

/**
 * Created by Piotr on 2015-01-06.
 */
public class ThesisSingleton {

    private static ThesisSingleton instance;
    private DataApi dataApi;

    private ThesisSingleton(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.API_URL)
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

}
