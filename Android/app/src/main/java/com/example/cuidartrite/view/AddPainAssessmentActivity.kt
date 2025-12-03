package com.example.cuidartrite.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.databinding.ActivityAddPainAssessmentBinding
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.network.api.controller.ApiPainAssessmentController
import com.example.cuidartrite.network.models.PainAssessmentRequest
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPainAssessmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPainAssessmentBinding
    private val selectedJoints = mutableSetOf<ImageView>()
    private var painLevel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPainAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupJointSelection()
        setupPainSlider()
        setupSubmitButton()
    }

    private fun setupJointSelection() {
        val joints = listOf(
            binding.jointShoulderLeft,
            binding.jointShoulderRight,
            binding.jointElbowLeft,
            binding.jointElbowRight,
            binding.jointWristLeft,
            binding.jointWristRight,
            binding.jointHipLeft,
            binding.jointHipRight,
            binding.jointKneeLeft,
            binding.jointKneeRight,
            binding.jointAnkleLeft,
            binding.jointAnkleRight
        )

        for (joint in joints) {
            joint.setOnClickListener {
                toggleJointSelection(joint)
            }
        }
    }

    private fun toggleJointSelection(joint: ImageView) {
        if (selectedJoints.contains(joint)) {
            // Deselect
            joint.clearColorFilter()
            selectedJoints.remove(joint)
        } else {
            // Select
            joint.setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.SRC_ATOP)
            selectedJoints.add(joint)
        }

        updateJointLabel()
    }

    private fun updateJointLabel() {
        if (selectedJoints.isEmpty()) {
            binding.jointLabel.text = "Selecione uma articulação"
        } else {
            val jointNames = selectedJoints.map { it.tag.toString() }
            binding.jointLabel.text = "Selecionado: ${jointNames.joinToString(", ")}"
        }
    }

    private fun setupPainSlider() {
        binding.sliderNivelDor.addOnChangeListener { _, value, _ ->
            painLevel = value.toInt()
            binding.tvValorDor.text = "Nível atual: $painLevel"
            binding.tvDescricaoDor.text = getPainDescription(painLevel)
        }
    }

    private fun getPainDescription(level: Int): String {
        return when (level) {
            0 -> "Sem dor."
            1, 2 -> "Dor leve."
            3, 4 -> "Dor moderada."
            5, 6 -> "Dor intensa."
            7, 8 -> "Dor muito intensa."
            9, 10 -> "Dor insuportável."
            else -> "Sem dor."
        }
    }

    private fun setupSubmitButton() {
        binding.btnRegistrarDor.setOnClickListener {
            submitPainAssessment()
        }
    }

    private fun submitPainAssessment() {
        if (selectedJoints.isEmpty()) {
            Toasty.warning(
                this,
                "Selecione pelo menos uma articulação!",
                Toasty.LENGTH_SHORT,
                true
            ).show()
            return
        }

        val comment = binding.commentField.text.toString().ifEmpty { "Sem comentário" }
        val jointNames = selectedJoints.map { it.tag.toString() }.joinToString(", ")

        lifecycleScope.launch(Dispatchers.IO) {
            val user = UserDataStore.read()

            if (user?.id == null) {
                withContext(Dispatchers.Main) {
                    Toasty.error(
                        this@AddPainAssessmentActivity,
                        "Erro: Usuário não encontrado",
                        Toasty.LENGTH_SHORT,
                        true
                    ).show()
                }
                return@launch
            }

            val request = PainAssessmentRequest(
                userId = user.id,
                nivelDor = painLevel,
                descricao = comment,
                localizacaoDor = jointNames
            )

            val success = ApiPainAssessmentController().registrarDor(request)

            withContext(Dispatchers.Main) {
                if (success) {
                    Toasty.success(
                        this@AddPainAssessmentActivity,
                        "Dor registrada com sucesso!",
                        Toasty.LENGTH_LONG,
                        true
                    ).show()
                    finish()
                } else {
                    Toasty.error(
                        this@AddPainAssessmentActivity,
                        "Erro ao registrar dor. Tente novamente.",
                        Toasty.LENGTH_SHORT,
                        true
                    ).show()
                }
            }
        }
    }
}