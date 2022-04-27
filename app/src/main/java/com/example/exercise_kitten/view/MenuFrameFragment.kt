package com.example.exercise_kitten.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.exercise_kitten.R
import com.example.exercise_kitten.model.ResponseData
import com.example.exercise_kitten.databinding.FragmentMenuFrameBinding
import com.example.exercise_kitten.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.exercise_kitten.utils.NetworkResult

@AndroidEntryPoint
class MenuFrameFragment : Fragment() {

    private lateinit var _binding: FragmentMenuFrameBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuFrameBinding.inflate(inflater, container, false)

        _binding.doneBtn.setOnClickListener {
            fetchData()
        }

        return _binding.root
    }

    private fun fetchData() {
        fetchResponse()

        mainViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success<*> -> {
                    handleResponse(response.data!!)
                }

                is NetworkResult.Error<*> -> {
                    handleErrorResponse()
                }

                is NetworkResult.Loading<*> -> {
                    _binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun fetchResponse() {
        mainViewModel.fetchDataResponse()
        _binding.progressBar.visibility = View.VISIBLE
    }

    private fun handleResponse(response: ResponseData) {
        _binding.completeProfileTv.text = response.data.title
        _binding.takeAFewStepsTv.text = response.data.message
        _binding.doneBtn.text = resources.getString(R.string.success)
        _binding.doneBtn.setBackgroundResource(R.drawable.green_gradient_background)
        _binding.doneBtn.isEnabled = false
        _binding.profilePicBig.setImageResource(R.drawable.profile_pic_stroke_green)
        _binding.progressBar.visibility = View.GONE

    }

    private fun handleErrorResponse() {
        Toast.makeText(context, "An error occurred. Please try again", Toast.LENGTH_LONG).show()
        _binding.progressBar.visibility = View.GONE
    }
}