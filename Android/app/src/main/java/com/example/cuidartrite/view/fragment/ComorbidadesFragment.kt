package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cuidartrite.databinding.FragmentComorbidadesBinding
import com.example.cuidartrite.view.RegisterActivity

class ComorbidadesFragment : Fragment() {

    private lateinit var binding: FragmentComorbidadesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentComorbidadesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as RegisterActivity

        binding.btnProximo.setOnClickListener {
            val selecionadas = mutableListOf<String>()
            if (binding.chkOpcao1.isChecked) selecionadas.add("Comorbidade 1")
            if (binding.chkOpcao2.isChecked) selecionadas.add("Comorbidade 2")
            activity.comorbidadesSelecionadas = selecionadas

            activity.abrirFragment(AcessibilidadeFragment())
        }

        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
