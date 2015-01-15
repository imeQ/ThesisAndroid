package com.thesis.rest;

import com.thesis.model.Student;
import com.thesis.model.Thesis;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by Piotr on 2015-01-06.
 */
public interface DataApi {
    @POST("/student/login/")
    void getStudent(@Body Student user, Callback<Student> studentCallback);

    @GET("/thesis/list")
    void getThesisList(Callback<List<Thesis>> studentCallback);

    @POST("/student/changeThesis/{studentId}/{thesisId}")
    void changeThesis(@Path("studentId") Long studentId, @Path("thesisId") Long thesisId, Callback<Student> callback);
}
