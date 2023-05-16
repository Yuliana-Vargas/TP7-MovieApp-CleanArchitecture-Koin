package com.example.movieapp.mvvmcleanapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMoviesBinding
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvm.viewmodel.factory.ViewModelFactory
import com.example.movieapp.mvvmcleanapp.data.database.MovieDataBaseImpl
import com.example.movieapp.mvvmcleanapp.data.database.MoviesRoomDataBase
import com.example.movieapp.mvvmcleanapp.data.service.MovieRequestGenerator
import com.example.movieapp.mvvmcleanapp.data.service.MovieServiceImpl
import com.example.movieapp.mvvmcleanapp.data.service.api.MovieClient
import com.example.movieapp.mvvmcleanapp.di.model.MoviesModel
import com.example.movieapp.mvvmcleanapp.presentation.adapter.MovieAdapter
import com.example.movieapp.mvvmcleanapp.presentation.fragment.ErrorDialogFragment
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var viewModel: MoviesContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBase: MoviesRoomDataBase by lazy {
            Room.databaseBuilder(this, MoviesRoomDataBase::class.java, "Movie-DataBase").build()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MoviesModel(
                        MovieServiceImpl(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDataBaseImpl(dataBase.movieDao()),
                    ),
                ),
            ),
        )[MoviesViewModel::class.java]

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
