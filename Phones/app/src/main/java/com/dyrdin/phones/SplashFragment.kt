package com.dyrdin.phones

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            when {
                // Пользователь ещё не регистрировался
                currentUser == null && !PrefManager.isUserRegistered(requireContext()) -> {
                    findNavController().navigate(R.id.action_splashFragment_to_registrationFragment)
                }
                // Пользователь зарегистрирован, но автовход выключен
                currentUser == null || !PrefManager.isAutoLoginEnabled(requireContext()) -> {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
                // Автовход включён — сразу на главную
                else -> {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }
        }, 2000)

        return root
    }
}