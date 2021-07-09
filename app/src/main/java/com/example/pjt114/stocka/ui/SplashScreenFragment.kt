package com.example.pjt114.stocka.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.R
import kotlinx.coroutines.*


class SplashScreenFragment : Fragment() {

    private val activityScope = CoroutineScope(Dispatchers.Main)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.hide()

        activityScope.launch {
            if (onBoardingFinished() && hasUserSignUp() ) {
                delay(2000)
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            } else if (onBoardingFinished() && !hasUserSignUp()){
                delay(2000)
                findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
            } else{
                delay(2000)
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }


        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun hasUserSignUp(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("signUpDone", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("SignUpComplete", false)
    }


}