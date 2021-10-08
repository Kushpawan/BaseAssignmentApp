package com.example.baseassignmentapp.home

import com.example.baseassignmentapp.models.SearchResponse
import com.example.baseassignmentapp.networking.Result
import com.example.baseassignmentapp.networking.safeApiCall
import com.example.baseassignmentapp.retrofit.SearchApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeRepoImp(
    private val service: SearchApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepo {

    override suspend fun getResultAsyncCall(query: String): Result<SearchResponse> {
        return safeApiCall(dispatcher) {
            service.getSearchAddress(query)
        }
    }
}