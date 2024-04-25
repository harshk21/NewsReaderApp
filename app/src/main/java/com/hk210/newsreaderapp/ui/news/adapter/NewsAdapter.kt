package com.hk210.newsreaderapp.ui.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreaderapp.databinding.NewsLayoutBinding
import com.hk210.newsreaderapp.model.Article
import com.hk210.newsreaderapp.utils.loadImage

class NewsAdapter(
    private val context: Context,
    private val items: List<Article?>,
    private val onItemClickListener: (Article) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.newsTitle.text = article?.title
            binding.newsAuthor.text = article?.author ?: article?.source?.name
            binding.newsImage.loadImage(context, article?.urlToImage.toString())
            binding.newsSource.text = article?.source?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            items[position]?.let { article -> onItemClickListener.invoke(article) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
