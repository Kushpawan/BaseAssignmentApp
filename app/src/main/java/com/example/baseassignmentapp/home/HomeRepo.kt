package com.example.baseassignmentapp.home

import com.example.baseassignmentapp.models.SearchResponse
import com.example.baseassignmentapp.networking.Result

interface HomeRepo {
    suspend fun getResultAsyncCall(
        query: String
    ): Result<SearchResponse>
}