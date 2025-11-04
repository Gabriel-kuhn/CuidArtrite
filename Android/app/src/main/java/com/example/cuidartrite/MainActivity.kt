package com.example.cuidartrite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.databinding.ActivityMainBinding
import com.example.cuidartrite.view.HomeActivity
import com.example.cuidartrite.view.RegisterActivity
import com.example.gerenciadordetc.network.api.controller.ApiLoginController
import com.example.gerenciadordetc.network.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiLoginController = ApiLoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            //login()
            startActivity(Intent(this, HomeActivity::class.java))
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

            withContext(Dispatchers.Main) {
                if (response != null) {
                    Toast.makeText(
                        this@MainActivity,
                        "Bem-vindo, ${response.nome}",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Usuário ou senha inválidos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}