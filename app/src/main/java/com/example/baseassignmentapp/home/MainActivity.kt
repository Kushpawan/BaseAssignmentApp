package com.example.baseassignmentapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseassignmentapp.R
import com.example.baseassignmentapp.models.Drinks
import com.example.baseassignmentapp.networking.Status
import com.example.baseassignmentapp.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()
    private var searchRecyclerAdapter: SearchRecyclerAdapter? = null
    private var drinksList: MutableList<Drinks?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        setupRecyclerView()

        viewModel.searchForCocktail("mojito")
    }

    private fun setupRecyclerView() {
        // recycler view to show search result
        if (searchRecyclerAdapter == null) {
            searchRecyclerAdapter =
                SearchRecyclerAdapter(
                    this,
                    drinksList
                )
            search_recycler.layoutManager = GridLayoutManager(this, 2)
            search_recycler.adapter = searchRecyclerAdapter
            search_recycler.itemAnimator = DefaultItemAnimator()
            search_recycler.isNestedScrollingEnabled = true
        } else {
            searchRecyclerAdapter!!.notifyDataSetChanged()
        }
    }

    private fun observeViewModel() {
        viewModel.searchLiveData.observe(this, androidx.lifecycle.Observer { response ->
            when (response.status) {
                Status.SUCCESS -> response.data?.let {
                    // hideProgress()
                    drinksList.addAll( it.drinks)
                    setupRecyclerView()
                    searchRecyclerAdapter!!.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    response.message?.detail?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    // hideProgress()
                }
                Status.LOADING -> {
                    // showProgress(this.requireContext(), "Searching cocktail..")
                }
            }
        })
    }

}