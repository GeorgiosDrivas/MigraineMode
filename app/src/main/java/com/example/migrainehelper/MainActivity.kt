package com.example.migrainehelper

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sw1: Switch? = findViewById(R.id.switch1)

        sw1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Brightness(this);
            } else {
                println("Not checked")
            }
        }


    }
}

fun Brightness(ts: Context){
    if (Settings.System.canWrite(ts)) {
        println("Checked")
        Settings.System.putInt(
            ts.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )

        Settings.System.putInt(
            ts.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            15
        )
    } else {
        println("Permission not granted. Please enable WRITE_SETTINGS.")
    }
}