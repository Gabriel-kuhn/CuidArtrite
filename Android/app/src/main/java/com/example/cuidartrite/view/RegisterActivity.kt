package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.databinding.ActivityRegisterBinding
import com.example.cuidartrite.view.fragment.CadastroBasicoFragment

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    // Dados coletados ao longo do fluxo
    var nome: String? = null
    var sexo: String? = null
    var idade: String? = null
    var email: String? = null
    var telefone: String? = null

    var comorbidadesSelecionadas: List<String> = emptyList()

    var contraste: String? = null
    var tamanhoFonte: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Abre a primeira tela
        abrirFragment(CadastroBasicoFragment())
    }

    fun abrirFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}
