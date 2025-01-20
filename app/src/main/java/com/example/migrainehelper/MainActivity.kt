package com.example.migrainehelper

import AudioMode
import Brightness
import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var originalBrightness: Int? = null
    @SuppressLint("UseSwitchCompatOrMaterialCode")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivity(intent)

        val sw1: Switch? = findViewById(R.id.switch1)
        val brightnessCls = Brightness()
        val silentMode = AudioMode(this)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        sw1?.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPreferences.edit()) {
                putBoolean("isFilteringEnabled", isChecked)
                apply()
            }

            if (isChecked) {
                if (originalBrightness == null) {
                    originalBrightness = brightnessCls.getCurrentBrightness(this)
                }
                brightnessCls.brightnessFn(this, 10)
                silentMode.audioMode(AudioManager.RINGER_MODE_VIBRATE)
            } else {
                originalBrightness?.let { brightnessCls.brightnessFn(this, it) }
                silentMode.audioMode(AudioManager.RINGER_MODE_NORMAL)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if (!Settings.System.canWrite(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
        }
    }
}