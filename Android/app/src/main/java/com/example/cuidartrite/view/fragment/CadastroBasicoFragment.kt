package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cuidartrite.databinding.FragmentCadastroBasicoBinding
import com.example.cuidartrite.view.RegisterActivity

class CadastroBasicoFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBasicoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroBasicoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as RegisterActivity

        binding.btnProximo.setOnClickListener {
            activity.nome = binding.inputNome.text.toString()
            activity.sexo = binding.inputSexo.text.toString()
            activity.idade = binding.inputIdade.text.toString()
            activity.email = binding.inputEmail.text.toString()
            activity.telefone = binding.inputTelefone.text.toString()

            activity.abrirFragment(ComorbidadesFragment())
        }
    }
}
