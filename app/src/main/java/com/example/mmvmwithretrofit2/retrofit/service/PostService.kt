package com.example.mmvmwithretrofit2.retrofit.service

import com.example.mmvmwithretrofit2.retrofit.entity.PersonEntity
import retrofit2.Call
import retrofit2.http.GET

/*
 * This interface encapsulates all the operations of API related to the entity Person
 * */

interface PostService {

    /*
     * Between the diamond operator of Call, we should put what we expect to get when we call the method. In this case, on calling list()
     * we wish to get a list of PersonEntity. We also have to use an annotation and pass to it the URN of the resource.
     * */
    @GET("emanoellucasml/simple-api-mock/main/response.txt")
    fun list() : Call<List<PersonEntity>>

}