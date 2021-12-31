package com.example.pagingnews

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingnews.adapter.NewsListAdapter
import com.example.pagingnews.data.State
import com.example.pagingnews.viewModel.NewsListViewModel
import androidx.recyclerview.widget.LinearLayoutManager




class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter
    private var recyclerView: RecyclerView? = null
    private lateinit var progress_bar: ProgressBar
    private lateinit var txt_error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        initView()
        initAdapter()
        initState()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
        progress_bar = findViewById(R.id.progress_bar)
        txt_error = findViewById(R.id.txt_error)
    }


    private fun initAdapter() {
        newsListAdapter = NewsListAdapter { viewModel.retry() }

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.setLayoutManager(llm)

        recyclerView?.adapter = newsListAdapter
        viewModel.newsList.observe(this,
            Observer {
                newsListAdapter.submitList(it)
            })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.SUCCESS)
            }
        })
    }
}