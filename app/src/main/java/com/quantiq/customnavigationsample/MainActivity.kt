package com.quantiq.customnavigationsample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.quantiq.customnavigationsample.databinding.ActivityMainBinding
import com.quantiq.customnavigationsample.navigation.Navigator
import com.quantiq.customnavigationsample.navigation.RootRouter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var router : RootRouter
    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpNavigation()
    }

    private fun setUpNavigation(){
        navigator = Navigator(this)
        router = RootRouter(navigator).apply { start() }
    }
}