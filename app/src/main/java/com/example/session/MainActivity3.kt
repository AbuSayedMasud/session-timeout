package com.example.session

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import kotlinx.coroutines.*

class MainActivity3 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val waiter = Waiter(60000, this)
        setContent {
            JetpackComposeApp(waiter)
        }
        waiter.run()
    }
}

object MyConstants {
    const val IS_SESSION_TIMEOUT = "is_session_timeout"
}

class Waiter(
    private var period: Long,
    private var context: Context
) {
    private val SLEEP_TIME: Long = 1 * 1000
    private var lastUsed: Long = 0
    private var stop = false

    private val TAG = Waiter::class.java.name

    fun run() {
        CoroutineScope(Dispatchers.IO).launch {
            var idle: Long = 0
            touch()
            while (!stop) {
                idle = System.currentTimeMillis() - lastUsed
                Log.d(TAG, "Application is idle for $idle ms")
                delay(SLEEP_TIME) // check every 5 seconds
                if (idle > period) {
                    idle = 0
                    showTimeoutDialogInMainThread()
                    stopp()
                }
            }
            Log.d(TAG, "Finishing Waiter thread")
        }
    }

    private fun showTimeoutDialogInMainThread() {
        CoroutineScope(Dispatchers.Main).launch {
            showTimeoutDialog()
        }
    }

    private fun showTimeoutDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Session Timeout")
        builder.setMessage("Your session has timed out. Please log in again.")
        builder.setPositiveButton("OK") { dialog, _ ->
            moveToLogin()
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun moveToLogin() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(MyConstants.IS_SESSION_TIMEOUT, true)
        }
        context.startActivity(intent)
    }

    private fun touch() {
        lastUsed = System.currentTimeMillis()
    }

    fun forceInterrupt() {
        // You can use coroutine cancellation here
    }

    // Soft stopping of the thread
    private fun stopp() {
        stop = true
    }

    fun setPeriod(period: Long) {
        this.period = period
    }
}

@Composable
fun JetpackComposeApp(waiter: Waiter) {
    Column {
        BasicText(text = "Jetpack Compose Example")
    }
}
