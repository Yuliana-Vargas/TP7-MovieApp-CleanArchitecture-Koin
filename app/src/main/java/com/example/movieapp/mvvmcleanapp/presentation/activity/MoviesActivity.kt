package com.example.movieapp.mvvmcleanapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMoviesBinding
import com.example.movieapp.mvvmcleanapp.presentation.adapter.MovieAdapter
import com.example.movieapp.mvvmcleanapp.presentation.adapter.fragment.ErrorDialogFragment
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MoviesActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MoviesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.callService()

        binding.moviesActivityBackButton.setOnClickListener { viewModel.onBackButtonPressed() }
        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MoviesViewModel.MovieData) {
        when (data.status) {
            MoviesViewModel.MovieStatus.SHOW_BUTTON_PRESSED -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
            MoviesViewModel.MovieStatus.BACK_BUTTON_PRESSED -> {
                finish()
            }
            MoviesViewModel.MovieStatus.EMPTY_STATE -> {
                showErrorMessage()
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        val dialog = ErrorDialogFragment.newInstance(getString(R.string.error_dialog_fragment_error_message))
        dialog.show(supportFragmentManager, ErrorDialogFragment.TAG)
    }

    private fun showErrorMessage() {
        binding.recycler.isGone = true
        binding.movieActivityErrorMessage.isGone = false
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}
