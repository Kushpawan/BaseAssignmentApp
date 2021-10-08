package com.example.baseassignmentapp.retrofit

import com.example.baseassignmentapp.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("1/search.php")
   suspend fun getSearchAddress(
        @Query("s") query: String?
    ): SearchResponse
}