package com.example.updatechecker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UpdateCheckActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_check)

        statusTextView = findViewById(R.id.status_text)

        checkVersion()
    }

    private fun checkVersion() {
        val currentVersion = packageManager.getPackageInfo(packageName, 0).versionName

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                VersionInfoService.service.getTextFile()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    statusTextView.text = "Error checking version: ${e.message}"
                }
                return@launch
            }

            val latestVersion = response.body()?.string()?.trim()

            withContext(Dispatchers.Main) {
                if (latestVersion == null) {
                    statusTextView.text = "Failed to fetch version info"
                    return@withContext
                }

                if (latestVersion == currentVersion) {
                    // Forward to main
                    forwardToMain()
                } else {
                    statusTextView.text = "Update required! ($currentVersion → $latestVersion)"
                    // Here you could add a button to download or exit.
                }
            }
        }
    }

    private fun forwardToMain() {
        // NOTE: You can provide a custom MAIN class name using intent extras
        val mainIntent = Intent(this, Class.forName("com.example.megatron.MainActivity"))
        startActivity(mainIntent)
        finish()
    }
}