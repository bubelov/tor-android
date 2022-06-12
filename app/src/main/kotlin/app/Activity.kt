package app

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bubelov.tor.databinding.ActivityBinding
import org.torproject.jni.TorService

class Activity : AppCompatActivity() {

    private lateinit var binding: ActivityBinding

    private val torBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val status = intent.getStringExtra(TorService.EXTRA_STATUS)
            binding.toolbar.subtitle = status

            if (status == TorService.STATUS_ON) {
                binding.webView.visibility = View.VISIBLE
                binding.webView.webViewClient = TorifiedWebViewClient()
                binding.webView.loadUrl("https://check.torproject.org/")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerReceiver(
            torBroadcastReceiver,
            IntentFilter(TorService.ACTION_STATUS),
        )

        bindService(
            Intent(this, TorService::class.java),
            SilentServiceConnection(),
            BIND_AUTO_CREATE,
        )
    }

    private class SilentServiceConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {}
        override fun onServiceDisconnected(name: ComponentName) {}
    }
}