package com.example.cuidartrite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cuidartrite.databinding.FragmentAcessibilidadeBinding
import com.example.cuidartrite.view.RegisterActivity

class AcessibilidadeFragment : Fragment() {

    private lateinit var binding: FragmentAcessibilidadeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAcessibilidadeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as RegisterActivity

        binding.btnFinalizar.setOnClickListener {
            activity.contraste = binding.inputContraste.text.toString()
            activity.tamanhoFonte = binding.inputTamanhoFonte.text.toString()

            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
