import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.provider.Settings

class AudioMode(private val context: Context) {

    fun audioMode(mode: Int) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (!notificationManager.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            context.startActivity(intent)
        } else {
            val audioManager =
                context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            when (mode) {
                AudioManager.RINGER_MODE_VIBRATE,
                AudioManager.RINGER_MODE_NORMAL,
                AudioManager.RINGER_MODE_SILENT -> {
                    audioManager.ringerMode = mode
                }
                else -> throw IllegalArgumentException("Invalid ringer mode")
            }        }
    }
}
