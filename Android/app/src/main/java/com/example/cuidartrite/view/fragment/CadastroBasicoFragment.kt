package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.cuidartrite.databinding.FragmentCadastroBasicoBinding
import com.example.cuidartrite.view.RegisterActivity
import es.dmoral.toasty.Toasty

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

        val options = mapOf("Masculino" to "M", "Feminino" to "F")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, options.keys.toList())
        binding.inputSexo.setAdapter(adapter)

        binding.inputSexo.setOnItemClickListener { _, _, position, _ ->
            activity.sexo = options.values.toList()[position]
        }

        binding.btnProximo.setOnClickListener {
            if (validateFields(activity.sexo)) {
                activity.nome = binding.inputNome.text.toString()
                activity.idade = binding.inputIdade.text.toString()
                activity.email = binding.inputEmail.text.toString()
                activity.telefone = binding.inputTelefone.text.toString()
                activity.senha = binding.inputSenha.text.toString()

                activity.abrirFragment(ComorbidadesFragment())
            } else {
                Toasty.warning(requireContext(), "Preencha todos os campos antes de continuar.", Toasty.LENGTH_SHORT, true).show()
            }
        }
    }

    private fun validateFields(selectedSexo: String?): Boolean {
        val nome = binding.inputNome.text.toString().trim()
        val senha = binding.inputSenha.text.toString().trim()
        val idade = binding.inputIdade.text.toString().trim()
        val email = binding.inputEmail.text.toString().trim()
        val telefone = binding.inputTelefone.text.toString().trim()

        return !(nome.isEmpty() || senha.isEmpty() || idade.isEmpty() || email.isEmpty() || telefone.isEmpty() || selectedSexo.isNullOrEmpty())
    }
}
