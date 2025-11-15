package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityMainBinding
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.network.api.controller.ApiLoginController
import com.example.cuidartrite.network.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiLoginController = ApiLoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            login()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val user = binding.etUsuario.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (user.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha usuário e senha!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val response = apiLoginController.login(LoginRequest(user, password))
            Log.d("Login", response.toString())

            withContext(Dispatchers.Main) {
                if (response != null) {
                    Toast.makeText(this@LoginActivity, "Bem-vindo, ${response.nome}", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)

                    lifecycleScope.launch {
                        UserDataStore.write(response)
                    }

                    intent.putExtra(EXTRA_USER, response)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@LoginActivity, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}