package com.example.imdumb.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.imdumb.BuildConfig
import com.example.imdumb.data.local.LocalConfig
import com.example.imdumb.databinding.ActivitySplashBinding
import com.example.imdumb.presentation.main.MainActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var isNavigating = false

    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig

    @Inject
    lateinit var localConfig: LocalConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVersion.text = "v${BuildConfig.VERSION_NAME}"
        binding.tvWelcome.text = localConfig.getConfig("welcome_text") ?: "Cargando..."

        // Timeout de seguridad: Si en 6 segundos Firebase no responde, avanzamos con lo que tengamos
        binding.root.postDelayed({
            navigateToMain()
        }, 6000)

        fetchRemoteConfig()
    }

    private fun fetchRemoteConfig() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { 
                // Al completar (sea éxito o error), actualizamos datos y navegamos
                updateLocalConfigAndUI()
                
                // Delay corto para que el usuario vea el texto si se descargó rápido
                binding.root.postDelayed({
                    navigateToMain()
                }, 2000)
            }
    }

    private fun updateLocalConfigAndUI() {
        try {
            val welcomeText = remoteConfig.getString("welcome_text")
            val homeTitle = remoteConfig.getString("home_title")
            val enableRecommendation = remoteConfig.getBoolean("enable_recommendation")
            val appTheme = remoteConfig.getString("app_theme")

            // Guardar localmente
            localConfig.saveConfig("welcome_text", welcomeText)
            localConfig.saveConfig("home_title", homeTitle)
            localConfig.saveBoolean("enable_recommendation", enableRecommendation)
            localConfig.saveConfig("app_theme", appTheme)

            // Actualizar UI de inmediato
            if (welcomeText.isNotEmpty()) {
                binding.tvWelcome.text = welcomeText
            }
            applyTheme(appTheme)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToMain() {
        if (isNavigating) return
        isNavigating = true
        
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun applyTheme(theme: String) {
        val mode = if (theme == "dark") {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        
        if (AppCompatDelegate.getDefaultNightMode() != mode) {
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }
}
