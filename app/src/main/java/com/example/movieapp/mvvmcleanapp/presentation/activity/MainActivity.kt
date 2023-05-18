package com.example.movieapp.mvvmcleanapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.mvvmcleanapp.presentation.adapter.fragment.ErrorDialogFragment
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainActivityButton.setOnClickListener { viewModel.onPressedButton() }
        binding.mainActivityDialogButton.setOnClickListener { viewModel.onShowDialogErrorPressedButton() }
        viewModel.getValue().observe(this) { buttonState(it) }
    }

    private fun buttonState(buttonData: MainViewModel.MainData) {
        when (buttonData.status) {
            MainViewModel.MainStatus.SHOW_MOVIES -> {
                startActivity(Intent(this, MoviesActivity::class.java))
            }
            MainViewModel.MainStatus.SHOW_DIALOG_ERROR -> {
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        val dialog = ErrorDialogFragment.newInstance(getString(R.string.error_dialog_fragment_error_message))
        dialog.show(supportFragmentManager, ErrorDialogFragment.TAG)
    }
}
