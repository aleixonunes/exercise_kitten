package com.example.exercise_kitten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.exercise_kitten.Service.APIInterface
import com.example.exercise_kitten.Service.APIClient
import com.example.exercise_kitten.Service.ResponseData
import com.example.exercise_kitten.databinding.FragmentMenuFrameBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MenuFrameFragment : Fragment() {

    private var _binding: FragmentMenuFrameBinding? = null
    private val binding get() = _binding!!
    private var myCompositeDisposable: CompositeDisposable? = null
    private var apiService: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCompositeDisposable = CompositeDisposable()
        apiService = APIClient().getClient().create(APIInterface::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuFrameBinding.inflate(inflater, container, false)
        binding.doneBtn.setOnClickListener {
            performRequest()
        }

        return binding.root
    }

    private fun performRequest() {
        binding.progressBar.visibility = View.VISIBLE
        myCompositeDisposable?.add(
            apiService!!.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleResponse(it)
                }, {
                    handleErrorResponse()
                }))
    }

    private fun handleResponse(response: ResponseData) {
        binding.completeProfileTv.text = response.data.title
        binding.takeAFewStepsTv.text = response.data.message
        binding.doneBtn.text = resources.getString(R.string.success)
        binding.doneBtn.setBackgroundResource(R.drawable.green_gradient_background)
        binding.doneBtn.isEnabled = false
        binding.profilePicBig.setImageResource(R.drawable.profile_pic_stroke_green)
        binding.progressBar.visibility = View.GONE

    }

    private fun handleErrorResponse() {
        Toast.makeText(context, "An error occurred. Please try again", Toast.LENGTH_LONG).show()
        binding.progressBar.visibility = View.GONE
    }
}