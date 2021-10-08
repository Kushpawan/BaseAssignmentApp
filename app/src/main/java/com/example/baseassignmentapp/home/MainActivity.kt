package com.example.baseassignmentapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseassignmentapp.R
import com.example.baseassignmentapp.others.hideProgress
import com.example.baseassignmentapp.models.Drinks
import com.example.baseassignmentapp.networking.Status
import com.example.baseassignmentapp.others.showProgress
import com.example.baseassignmentapp.home.viewModel.HomeViewModel
import com.example.baseassignmentapp.others.toEditable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // injected viewModel using Koin
    private val viewModel by viewModel<HomeViewModel>()
    private var searchRecyclerAdapter: SearchRecyclerAdapter? = null
    private var drinksList: MutableList<Drinks?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSearchView()
        observeViewModel()
        setupRecyclerView()

        // added first static search
        search_EditText.text = "Rum".toEditable()

    }

    private fun initSearchView() {
        // edit text change listener for entering text in search field
        search_EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                val queryText = editable.toString()
                if (queryText.isNotEmpty()) {
                    if (queryText.length >= 3) {
                        viewModel.searchForCocktail(queryText)
                    }
                } else {
                    notifyData()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        // recycler view to show search result
        if (searchRecyclerAdapter == null) {
            searchRecyclerAdapter = SearchRecyclerAdapter(this, drinksList) {
                openNextActivity(it)
            }
            search_recycler.layoutManager = GridLayoutManager(this, 2)
            search_recycler.adapter = searchRecyclerAdapter
            search_recycler.itemAnimator = DefaultItemAnimator()
        } else {
            notifyData()
        }
    }

    private fun observeViewModel() {
        // observer created for result of the search API
        viewModel.searchLiveData.observe(this, { response ->
            when (response.status) {
                Status.SUCCESS -> response.data?.let {
                    hideProgress()
                    drinksList.clear()
                    drinksList.addAll(it.drinks)
                    notifyData()
                }
                Status.ERROR -> {
                    response.message?.detail?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    hideProgress()
                }
                Status.LOADING -> {
                    showProgress(this, "Searching..")
                }
            }
        })
    }

    private fun openNextActivity(drink: Drinks?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("details_drink", drink)
        startActivity(intent)
    }

    private fun notifyData() {
        searchRecyclerAdapter!!.notifyDataSetChanged()
    }


}