import android.content.Context
import android.media.AudioManager

class AudioMode(private val context: Context) {

    fun audioMode(mode: Int) {
        val audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        when (mode) {
            AudioManager.RINGER_MODE_VIBRATE,
            AudioManager.RINGER_MODE_NORMAL,
            AudioManager.RINGER_MODE_SILENT -> {
                audioManager.ringerMode = mode
            }
            else -> throw IllegalArgumentException("Invalid ringer mode")
        }
    }
}
