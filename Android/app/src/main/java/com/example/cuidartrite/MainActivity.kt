package com.example.cuidartrite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityMainBinding
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.view.HomeActivity
import com.example.cuidartrite.view.LoginActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        lifecycleScope.launch {
            val user = UserDataStore.read()
            if (user != null) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra(EXTRA_USER, user)
                startActivity(intent)
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}