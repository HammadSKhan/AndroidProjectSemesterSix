package com.example.androidprojectprep25may.helper.api;


import com.example.androidprojectprep25may.apiresources.CreateUserResource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

//    @GET("/api/unknown")
//    Call<UserListResource> doGetListResources();

    @POST("/api/users")
    Call<CreateUserResource> createUser(@Body CreateUserResource user);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
