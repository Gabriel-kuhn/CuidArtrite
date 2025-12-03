package com.example.cuidartrite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.datastore.UserDataStore
import com.example.cuidartrite.view.HomeActivity
import com.example.cuidartrite.view.LoginActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

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