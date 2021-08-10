package com.example.myinventory.presentation.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.local.sharedpref.SharedPreferenceManager
import com.example.domain.model.Resource
import com.example.myinventory.databinding.ActivityMainBinding
import com.example.myinventory.presentation.base.BaseActivity
import com.example.myinventory.presentation.utils.DialogUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity(), DialogInterface.OnClickListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private lateinit var bd: ActivityMainBinding

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var sharedPrefMgr: SharedPreferenceManager

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

        //for testing purpose
        sharedPrefMgr.getUserId()

        DialogUtils.showDialog(context = this, title = "hello", clickListener = this, cancelListener = this, dismissListener = this)
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        //TODO("Not yet implemented")
        return
    }

    override fun onCancel(p0: DialogInterface?) {
        //TODO("Not yet implemented")
        return
    }

    override fun onDismiss(p0: DialogInterface?) {
        //TODO("Not yet implemented")
        return
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