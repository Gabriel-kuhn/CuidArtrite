package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra
import com.example.cuidartrite.databinding.ActivityHomeBinding
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.network.models.User
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private var user: User? = null
    private lateinit var binding: ActivityHomeBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        user = intent.extras!!.getParcelable(ConstantsExtra.Companion.EXTRA_USER)

        binding.tvUserName.text = user!!.nome

        binding.btnPainAssessment.setOnClickListener { v: View? ->
            val intent = Intent(this, PainAssessmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnReliefTechniques.setOnClickListener { v: View? ->
            val intent = Intent(this, ReliefTechiniquesActivity::class.java)
            startActivity(intent)
        }

        binding.btnAgenda.setOnClickListener({ v ->
            val intent = Intent(this, AgendaActivity::class.java)
            startActivity(intent)
        })

        binding.btnEducationalContent.setOnClickListener { v: View? ->
            val intent = Intent(this, EducationalContentActivity::class.java)
            startActivity(intent)
        }

        binding.fabLogout.setOnClickListener { v: View? ->
            lifecycleScope.launch {
                UserDataStore.clear()
            }
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }
    }
}
