package com.example.mywanandroid.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywanandroid.databinding.FragmentHomeBinding
import com.orhanobut.logger.Logger
import wowo.kjt.library.onPageClickListener


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val homeListAdapter = HomeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.apply {
            homeList.layoutManager = LinearLayoutManager(this@HomeFragment.context)
            homeList.adapter = homeListAdapter
        }

        viewModel.apply {
            bannerData.observe(viewLifecycleOwner, Observer {
                val mImgIdUrls = ArrayList<String>()
                for (item in it) {
                    mImgIdUrls.add(item.imagePath)
                }

                homeListAdapter.banner.setDuration(2000)
                    .setOnPageClickListener(object : onPageClickListener {
                        override fun onPageClick(index: Int) {
                            Toast.makeText(
                                this@HomeFragment.context,
                                index.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    .setDataFromUrl(mImgIdUrls)
                    .startLoop()
            })

            articleData.observe(viewLifecycleOwner, Observer {
                for (item in it.datas) {
                    homeListAdapter.articleList.add(item)
                }
                homeListAdapter.notifyDataSetChanged()

            })

            errorData.observe(viewLifecycleOwner, Observer {
                Logger.d(it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeListAdapter.banner.stopLoop()
    }

}