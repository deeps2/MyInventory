package com.example.myinventory.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Resource
import com.example.myinventory.databinding.ActivityMainBinding
import com.example.myinventory.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var bd: ActivityMainBinding

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var adapter: NewsAdapter

    //@Inject
    //lateinit var repo: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bd.root)

        setSupportActionBar(bd.toolbar)

        val dividerItemDecoration = DividerItemDecoration(this, (bd.newsRecycler.layoutManager as LinearLayoutManager).orientation)
        bd.newsRecycler.addItemDecoration(dividerItemDecoration)

        adapter = NewsAdapter(emptyList())
        bd.newsRecycler.adapter = adapter

        viewModel.fetchData()

        viewModel.newsList.observe(this, {
            when(it) {
                is Resource.Loading -> {
                    showOnlyLoader()
                }

                is Resource.Success -> {
                    showDataLayout()
                    adapter.refresh(it.data)
                }

                is Resource.Failure -> {
                    showOnlyErrorMessage()
                }
            }
        })
    }

    private fun showOnlyLoader() {
        bd.loader.visibility = View.VISIBLE
        bd.newsRecycler.visibility = View.INVISIBLE
    }

    private fun showDataLayout() {
        bd.loader.visibility = View.GONE
        bd.newsRecycler.visibility = View.VISIBLE
    }

    private fun showOnlyErrorMessage() {
        bd.loader.visibility = View.INVISIBLE
        bd.newsRecycler.visibility = View.INVISIBLE
        Toast.makeText(this@HomeActivity, "Unable to Fetch News", Toast.LENGTH_SHORT).show()
    }


}