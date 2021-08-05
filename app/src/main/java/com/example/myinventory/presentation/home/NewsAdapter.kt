package com.example.myinventory.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.model.NewsDomain
import com.example.myinventory.R
import com.example.myinventory.databinding.VhNewsListItemBinding


class NewsAdapter constructor(private var newsList: List<NewsDomain>): RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vh_news_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsList.get(position))
    }

    override fun getItemCount() = newsList.size

    fun refresh(updatedList: List<NewsDomain>) {
        newsList = updatedList
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var bd : VhNewsListItemBinding = VhNewsListItemBinding.bind(itemView)

    fun bindData(newsDomain: NewsDomain) {
        bd.newsTitle.text = newsDomain.title
        bd.articleImage.load(newsDomain.imageUrl)
    }

}