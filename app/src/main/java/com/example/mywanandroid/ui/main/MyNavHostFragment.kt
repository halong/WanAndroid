package com.example.mywanandroid.ui.main

import android.view.View
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.example.mywanandroid.R

class MyNavHostFragment : NavHostFragment() {
    private fun getContainerId(): Int {
        return if (id != 0 && id != View.NO_ID) {
            id
        } else R.id.nav_host_fragment_container
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return FixFragmentNavigator(
            requireContext(),
            childFragmentManager,
            getContainerId()
        )
    }
}