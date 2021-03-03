package com.example.mywanandroid.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywanandroid.R
import com.example.mywanandroid.databinding.ItemArticleBinding
import com.example.mywanandroid.databinding.ItemBannerBinding
import com.example.mywanandroid.entity.Article

class HomeListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemBannerBinding =
            ItemBannerBinding.bind(itemView)
    }


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemArticleBinding =
            ItemArticleBinding.bind(itemView)
    }

    companion object {
        private const val BANNER = 0
        private const val ARTICLE = 1
    }

    lateinit var banner: wowo.kjt.library.Banner
    val articleList = ArrayList<Article>()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> BANNER
            else -> ARTICLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            BANNER -> BannerViewHolder(
                inflater.inflate(
                    R.layout.item_banner,
                    parent,
                    false
                )
            )

            else -> ArticleViewHolder(
                inflater.inflate(
                    R.layout.item_article,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BANNER -> {
                val bannerViewHolder = holder as BannerViewHolder
                banner = bannerViewHolder.binding.banner
            }
            else -> {
                val articleViewHolder = holder as ArticleViewHolder
                articleViewHolder.binding.title.text = articleList[position - 1].title
            }
        }
    }

    override fun getItemCount(): Int {
        return articleList.size + 1
    }

}