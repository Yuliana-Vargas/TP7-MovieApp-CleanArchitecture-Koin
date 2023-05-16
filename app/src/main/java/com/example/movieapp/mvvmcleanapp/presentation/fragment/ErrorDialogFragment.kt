package com.example.movieapp.mvvmcleanapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.movieapp.databinding.CustomDialogBinding

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: CustomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogBinding.inflate(layoutInflater)
        val message = requireArguments().getString(ARG_MESSAGE)
        binding.errorDialogFragmentMessage.text = message
        binding.errorDialogFragmentButtonOk.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TAG = "ErrorDialogFragment"
        private const val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(message: String): ErrorDialogFragment {
            val args = Bundle()
            args.putString(ARG_MESSAGE, message)
            val fragment = ErrorDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
