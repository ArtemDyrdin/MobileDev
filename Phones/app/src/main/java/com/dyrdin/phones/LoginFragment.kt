package com.dyrdin.phones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_login, container, false)

        val etLogin = root.findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val etPassword = root.findViewById<EditText>(R.id.editTextTextPassword3)
        val checkBox = root.findViewById<CheckBox>(R.id.checkBox)
        val btnLogin = root.findViewById<Button>(R.id.button4)

        btnLogin.setOnClickListener {
            val inputLogin = etLogin.text.toString()
            val inputPassword = etPassword.text.toString()

            val savedLogin = PrefManager.getLogin(requireContext())
            val savedPassword = PrefManager.getPassword(requireContext())

            if (inputLogin == savedLogin && inputPassword == savedPassword) {
                PrefManager.setAutoLogin(requireContext(), checkBox.isChecked)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}