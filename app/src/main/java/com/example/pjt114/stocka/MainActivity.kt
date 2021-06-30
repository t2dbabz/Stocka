package com.example.pjt114.stocka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pjt114.stocka.databinding.ActivityMainBinding
import com.example.pjt114.stocka.db.AppDatabase
import com.example.pjt114.stocka.repository.AppRepository
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import com.example.pjt114.stocka.viewmodel.SharedViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val appRepository = AppRepository(AppDatabase(this))
        val viewModelProviderFactory = SharedViewModelFactory(appRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SharedViewModel::class.java)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Setup the bottom navigation view with navController
        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)

        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(navController, appBarConfiguration)



        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.viewPagerFragment
                || destination.id == R.id.splashScreenFragment
                || destination.id == R.id.accountSetUpFragment
                || destination.id == R.id.signUpFragment
                || destination.id == R.id.loginFragment) {

                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_container)
        return super.onSupportNavigateUp()|| navController.navigateUp(appBarConfiguration)
    }
}