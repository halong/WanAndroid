package com.example.mywanandroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mywanandroid.R
import com.example.mywanandroid.databinding.ActivityMain2Binding
import com.example.mywanandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
//        ContextCompat.getDrawable(this,R.drawable.ic_dashboard_black_24dp)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController:NavController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}