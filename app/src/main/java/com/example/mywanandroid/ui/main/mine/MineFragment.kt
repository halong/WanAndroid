package com.example.mywanandroid.ui.main.mine

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywanandroid.R
import com.example.mywanandroid.databinding.FragmentMineBinding

class MineFragment : Fragment() {
    private lateinit var binding: FragmentMineBinding
    private lateinit var viewModel: MineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        // TODO: Use the ViewModel

        binding.img.setColorFilter(Color.RED) //对vector设置填充色
    }

}