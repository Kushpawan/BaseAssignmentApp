package com.example.baseassignmentapp.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.baseassignmentapp.home.HomeRepo
import com.example.baseassignmentapp.models.SearchResponse
import com.example.baseassignmentapp.modules.BaseViewModel
import com.example.baseassignmentapp.networking.Resource
import com.example.baseassignmentapp.networking.Result
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepo: HomeRepo,
    application: Application
) : BaseViewModel(application) {

    var searchLiveData = MutableLiveData<Resource<SearchResponse>>()

    fun searchForCocktail(searchQuery: String) {
        searchCall(searchQuery)
    }

    private fun searchCall(searchQuery: String) {
        scope.launch {
            searchLiveData.postValue(Resource.loading())
            when (val result = homeRepo.getResultAsyncCall(searchQuery)) {
                is Result.GenericError -> searchLiveData.postValue(Resource.error(result.error))
                is Result.Success -> searchLiveData.postValue(Resource.success(result.value))
            }
        }
    }
}