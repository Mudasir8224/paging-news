package com.example.pagingnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingnews.R
import com.example.pagingnews.data.State

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val progress_bar: ProgressBar = view.findViewById(R.id.progress_bar)
    val txt_error: TextView = view.findViewById(R.id.txt_error)


    fun bind(status: State?) {
        progress_bar.visibility = if (status == State.LOADING) View.VISIBLE else View.INVISIBLE
        txt_error.visibility = if (status == State.ERROR) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }

}