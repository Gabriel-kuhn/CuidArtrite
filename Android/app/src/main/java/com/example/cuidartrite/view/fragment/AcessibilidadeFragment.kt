package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.databinding.FragmentAcessibilidadeBinding
import com.example.cuidartrite.enums.FontSize
import com.example.cuidartrite.network.api.controller.ApiCadastroController
import com.example.cuidartrite.network.models.User
import com.example.cuidartrite.view.RegisterActivity
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch

class AcessibilidadeFragment : Fragment() {

    private lateinit var binding: FragmentAcessibilidadeBinding
    private var tamanhoFonteSelecionado: FontSize? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAcessibilidadeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as RegisterActivity

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            FontSize.lista.map { it.descricao }
        )
        binding.inputTamanhoFonte.setAdapter(adapter)

        binding.inputTamanhoFonte.setOnItemClickListener { _, _, position, _ ->
            tamanhoFonteSelecionado = FontSize.lista[position]
        }

        binding.btnFinalizar.setOnClickListener {
            if (tamanhoFonteSelecionado == null) {
                Toasty.warning(requireContext(), "Selecione o tamanho da fonte.", Toasty.LENGTH_SHORT, true).show()
                return@setOnClickListener
            }

            val user = User(
                null,
                username = activity.usuario!!,
                nome = activity.nome!!,
                email = activity.email!!,
                idade = activity.idade?.toIntOrNull() ?: 0,
                sexo = activity.sexo!!,
                telefone = activity.telefone!!,
                tamanhoFonte = tamanhoFonteSelecionado!!.valor,
                contraste = if (binding.chkContraste.isChecked) 1 else 0,
                leituraVoz = 1,
                coletarDados = 1,
                password = activity.senha!!
            )

            Log.d("Login", "User: $user")
            Toasty.info(requireContext(), "Cadastrando usuário...", Toasty.LENGTH_SHORT, true).show()

            lifecycleScope.launch {
                val result = ApiCadastroController().cadastrar(user)

                if (result != null) {
                    Toasty.success(requireContext(), "Usuário cadastrado com sucesso!", Toasty.LENGTH_SHORT, true).show()
                    activity.finish()
                } else {
                    Toasty.error(requireContext(), "Erro ao cadastrar. Tente novamente.", Toasty.LENGTH_SHORT, true).show()
                }
            }
        }

        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
