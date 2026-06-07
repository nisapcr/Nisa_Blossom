package com.example.nisa_blossom.Data.Api

import com.example.nisa_blossom.Data.Model.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostModel>>

}