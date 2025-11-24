package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_TECHINIQUE_TYPE_ID
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityExerciseListBinding
import com.example.cuidartrite.enums.TechiniqueType
import com.example.cuidartrite.network.api.controller.ApiTecnicaController
import com.example.cuidartrite.network.models.User
import com.example.cuidartrite.view.adapter.ExerciseListAdapter
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTechiniqueList.layoutManager = LinearLayoutManager(this)
        val techiniqueType = intent.getSerializableExtra(EXTRA_TECHINIQUE_TYPE_ID) as TechiniqueType
        val user: User = intent.extras!!.getParcelable(EXTRA_USER)!!

        binding.tvTitle.text = techiniqueType.title

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("Exercise List Activity", techiniqueType.id.toString())
            val response = ApiTecnicaController().listarTecnicas(techiniqueType)

            withContext(Dispatchers.Main) {
                if (response == null) {
                    Toasty.error(this@ExerciseListActivity, "Falha ao carregar a lista de exercício", Toasty.LENGTH_SHORT, true).show()
                    return@withContext
                }
                Log.d("Exercise List Activity", response.toString())


                if (response.isEmpty()) {
                    binding.tvWarningEmpty.visibility = VISIBLE
                    return@withContext
                }

                val adapter = ExerciseListAdapter(response) { item ->
                    val intent = Intent(this@ExerciseListActivity, PracticingTechiniqueActivity::class.java)
                    intent.putExtra(EXTRA_EXERCISE, item)
                    intent.putExtra(EXTRA_USER, user)
                    startActivity(intent)
                    Log.d("CLICK", "Clicked: ${item.titulo}")
                }

                binding.rvTechiniqueList.adapter = adapter
            }
        }
    }
}