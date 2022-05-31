package com.guysapp.ats.ui.home


import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.guysapp.ats.R
import com.guysapp.ats.data.Repository
import com.guysapp.ats.databinding.ActivityHomeBinding
import com.guysapp.ats.ui.base.BaseActivity


class HomeActivity : BaseActivity() {

    var mBinding : ActivityHomeBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        init()
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(mBinding?.bottomNavigationView as BottomNavigationView, navController);
    }


    private fun init(){
        mBinding?.tbHome?.toolbar?.setTitle(getString(R.string.app_name))
    }



}