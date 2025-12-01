package com.example.cuidartrite.view

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityHomeBinding
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.network.api.controller.ApiExportController
import com.example.cuidartrite.network.models.User
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    private var user: User? = null
    private lateinit var binding: ActivityHomeBinding

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        user = intent.extras!!.getParcelable(EXTRA_USER)

        binding.tvUserName.text = user!!.nome

        binding.btnPainAssessment.setOnClickListener { v: View? ->
            val intent = Intent(this, PainAssessmentActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            startActivity(intent)
        }

        binding.btnReliefTechniques.setOnClickListener { v: View? ->
            val intent = Intent(this, ReliefTechiniquesActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
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

        binding.btnExportData.setOnClickListener {
            exportPainData()
        }

        binding.fabLogout.setOnClickListener { v: View? ->
            lifecycleScope.launch {
                UserDataStore.clear()
            }
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }
    }

    private fun exportPainData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10+ doesn't need storage permission for app-specific directories
            downloadAndSaveData()
        } else {
            // For Android 9 and below, check permission
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                downloadAndSaveData()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadAndSaveData()
            } else {
                Toasty.error(
                    this,
                    "Permissão necessária para salvar o arquivo",
                    Toasty.LENGTH_SHORT,
                    true
                ).show()
            }
        }
    }

    private fun downloadAndSaveData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val csvData = ApiExportController().downloadPains()

                withContext(Dispatchers.Main) {
                    if (csvData != null) {
                        saveToFile(csvData)
                    } else {
                        Toasty.error(
                            this@HomeActivity,
                            "Erro ao baixar dados",
                            Toasty.LENGTH_SHORT,
                            true
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toasty.error(
                        this@HomeActivity,
                        "Erro: ${e.message}",
                        Toasty.LENGTH_SHORT,
                        true
                    ).show()
                }
            }
        }
    }

    private fun saveToFile(csvData: String) {
        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "CuidArtrite_Dores_$timestamp.csv"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Android 10+ - Use MediaStore
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val uri = contentResolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    contentValues
                )

                uri?.let {
                    contentResolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(csvData.toByteArray())
                    }
                    showOpenFileDialog(it, fileName)
                } ?: run {
                    Toasty.error(
                        this,
                        "Erro ao criar arquivo",
                        Toasty.LENGTH_SHORT,
                        true
                    ).show()
                }
            } else {
                // Android 9 and below - Use traditional file system
                val downloadsDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(downloadsDir, fileName)

                FileOutputStream(file).use { outputStream ->
                    outputStream.write(csvData.toByteArray())
                }

                val uri = FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider",
                    file
                )
                showOpenFileDialog(uri, fileName)
            }
        } catch (e: Exception) {
            Toasty.error(
                this,
                "Erro ao salvar arquivo: ${e.message}",
                Toasty.LENGTH_SHORT,
                true
            ).show()
        }
    }

    private fun showOpenFileDialog(uri: Uri, fileName: String) {
        AlertDialog.Builder(this)
            .setTitle("Arquivo Exportado")
            .setMessage("O arquivo '$fileName' foi salvo com sucesso em Downloads.\n\nDeseja abrir o arquivo agora?")
            .setPositiveButton("Abrir") { _, _ ->
                openFile(uri)
            }
            .setNegativeButton("Fechar") { dialog, _ ->
                dialog.dismiss()
                Toasty.success(
                    this,
                    "Arquivo salvo em Downloads",
                    Toasty.LENGTH_SHORT,
                    true
                ).show()
            }
            .setCancelable(false)
            .show()
    }

    private fun openFile(uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "text/csv")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            // Check if there's an app that can handle CSV files
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // If no CSV viewer, try with generic text viewer
                val genericIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "text/*")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                if (genericIntent.resolveActivity(packageManager) != null) {
                    startActivity(genericIntent)
                } else {
                    // Show chooser as last resort
                    startActivity(Intent.createChooser(intent, "Abrir arquivo CSV"))
                }
            }
        } catch (e: Exception) {
            Toasty.error(
                this,
                "Nenhum aplicativo encontrado para abrir arquivos CSV. Você pode encontrar o arquivo na pasta Downloads.",
                Toasty.LENGTH_LONG,
                true
            ).show()
        }
    }
}
