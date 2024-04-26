package com.hk210.newsreaderapp.ui.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreaderapp.R
import com.hk210.newsreaderapp.databinding.NewsLayoutBinding
import com.hk210.newsreaderapp.model.Article
import com.hk210.newsreaderapp.utils.loadImage

class NewsAdapter(
    private val context: Context,
    private val articles: List<Article?>,
    private val onArticleClicked: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.newsTitle.text = article?.title
            binding.newsAuthor.text = context.getString(R.string.news_author, article?.author ?: article?.source?.name)
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
        holder.bind(articles[position])
        holder.itemView.setOnClickListener {
            articles[position]?.let { article -> onArticleClicked.invoke(article) }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
