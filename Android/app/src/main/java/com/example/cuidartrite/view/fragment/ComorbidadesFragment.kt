package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

            getCheckBoxValue(binding.chkArtrite, selecionadas)
            getCheckBoxValue(binding.chkDoencasReumaticas, selecionadas)

            activity.comorbidadesSelecionadas = selecionadas

            activity.abrirFragment(AcessibilidadeFragment())
        }

        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getCheckBoxValue(checkBox: CheckBox, selecionadas: MutableList<String>) {
        checkBox.let {
            if (it.isChecked) {
                selecionadas.add(it.text.toString())
            }
        }
    }
}
