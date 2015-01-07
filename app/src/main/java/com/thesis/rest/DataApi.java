package com.thesis.rest;

import com.thesis.model.Student;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Piotr on 2015-01-06.
 */
public interface DataApi {
    @POST("/student/login/")
    void getStudent(@Body Student user, Callback<Student> studentCallback);
}
