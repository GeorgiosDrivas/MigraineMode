package com.example.migrainehelper

import android.content.ContentResolver
import android.content.Context
import android.provider.Settings

class brightness {

    fun brightnessFn(ts: Context, v: Int){
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
                v
            )
        } else {
            println("Permission not granted. Please enable WRITE_SETTINGS.")
        }
    }

    fun getCurrentBrightness(context: Context): Int{
        val contentResolver: ContentResolver = context.contentResolver

        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )

        val br = Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS
        )

        return br;
    }

}


