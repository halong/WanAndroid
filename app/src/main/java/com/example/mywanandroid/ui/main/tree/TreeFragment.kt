package com.example.mywanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywanandroid.R

class TreeFragment : Fragment() {

    private lateinit var treeViewModel: TreeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        treeViewModel =
                ViewModelProvider(this).get(TreeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tree, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        treeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}