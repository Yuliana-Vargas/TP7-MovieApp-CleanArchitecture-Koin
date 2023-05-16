package com.example.movieapp.mvvmcleanapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainActivityButton.setOnClickListener { viewModel.onPressedButton() }
        viewModel.getValue().observe(this) { buttonState(it) }
    }

    private fun buttonState(buttonData: MainViewModel.MainData) {
        when (buttonData.status) {
            MainViewModel.MainStatus.SHOW_MOVIES -> {
                startActivity(Intent(this, MoviesActivity::class.java))
            }
        }
    }
}
