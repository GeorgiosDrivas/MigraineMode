package com.example.migrainehelper

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.provider.Settings
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
        val sw1: Switch? = findViewById(R.id.switch1)
        val brightnessCls = brightness();
        val currentBr = brightnessCls.getCurrentBrightness(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sw1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                brightnessCls.brightnessFn(this, 10);
            } else {
                brightnessCls.brightnessFn(this, currentBr);
                println("Not checked")
            }
        }
    }
}