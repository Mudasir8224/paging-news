package com.example.pagingnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingnews.R
import com.example.pagingnews.data.News
import com.squareup.picasso.Picasso

class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val txt_news_name: TextView = view.findViewById(R.id.txt_news_name)
    val img_news_banner: ImageView = view.findViewById(R.id.img_news_banner)

    fun bind(news: News?) {
        if (news != null) {
            txt_news_name.text = news.title
            if (!news.image.isNullOrEmpty())
                Picasso.get().load(news.image).into(img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}