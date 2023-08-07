package com.example.iqsectest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.iqsectest.R
import com.example.iqsectest.databinding.ActivityMainBinding
import com.example.iqsectest.di.manager.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    @Inject
    lateinit var navigation: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navController = findNavController(R.id.main_container)

        navigation.onNavigateComponents(navController)
    }
}