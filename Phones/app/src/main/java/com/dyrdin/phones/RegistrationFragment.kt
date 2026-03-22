package com.dyrdin.phones

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {

    private var isEmailMode = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_registration, container, false)

        val btnByNumber = root.findViewById<Button>(R.id.button)
        val btnByEmail = root.findViewById<Button>(R.id.button2)
        val etRegisterField = root.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val etPassword = root.findViewById<EditText>(R.id.editTextTextPassword)
        val etConfirmPassword = root.findViewById<EditText>(R.id.editTextTextPassword2)
        val btnRegister = root.findViewById<Button>(R.id.button3)

        val phoneRegex = Regex("""^\+\d{10,15}$""")
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""")

        changeEnterMode(btnByEmail, btnByNumber, etRegisterField)

        btnByEmail.setOnClickListener {
            isEmailMode = true
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
            etPassword.text.clear()
            etConfirmPassword.text.clear()
        }

        btnByNumber.setOnClickListener {
            isEmailMode = false
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
            etPassword.text.clear()
            etConfirmPassword.text.clear()
        }

        btnRegister.setOnClickListener {
            val loginInput = etRegisterField.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            var isValid = true

            if (isEmailMode) {
                if (!emailRegex.matches(loginInput)) {
                    Toast.makeText(requireContext(), "Неверный email", Toast.LENGTH_SHORT).show()
                    isValid = false
                }
            } else {
                if (!phoneRegex.matches(loginInput)) {
                    Toast.makeText(requireContext(), "Неверный номер", Toast.LENGTH_SHORT).show()
                    isValid = false
                }
            }

            if (password.length < 8) {
                Toast.makeText(requireContext(), "Пароль минимум 8 символов", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {
                val auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(loginInput, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            PrefManager.setUserRegistered(requireContext(), true)
                            PrefManager.setAutoLogin(requireContext(), false)
                            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                    }
            }
        }

        return root
    }

    private fun changeEnterMode(btnByEmail: Button, btnByNumber: Button, etRegisterField: EditText) {
        val activeColor = ContextCompat.getColor(requireContext(), R.color.purple_200)
        val inactiveColor = "#757575".toColorInt()

        if (isEmailMode) {
            btnByEmail.setTextColor(activeColor)
            btnByNumber.setTextColor(inactiveColor)
            etRegisterField.hint = "Введите email"
            etRegisterField.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        } else {
            btnByNumber.setTextColor(activeColor)
            btnByEmail.setTextColor(inactiveColor)
            etRegisterField.hint = "Введите номер телефона"
            etRegisterField.inputType = InputType.TYPE_CLASS_PHONE
        }
        etRegisterField.text.clear()
    }
}