package com.example.session

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//class MainActivity2 : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val waiter = Waiter(60000, this)
//        setContent {
//            JetpackComposeApp()
//        }
//        waiter.run()
//    }
//}
//
//object Constants {
//    const val IS_SESSION_TIMEOUT = "is_session_timeout"
//}
//
//class Waiter(
//    private var period: Long,
//    private var context: Context
//) {
//    private val SLEEP_TIME: Long = 5 * 1000
//    private var lastUsed: Long = 0
//    private var stop = false
//
//    private val TAG = Waiter::class.java.name
//
//    fun run() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var idle: Long = 0
//            touch()
//            while (!stop) {
//                idle = System.currentTimeMillis() - lastUsed
//                Log.d(TAG, "Application is idle for $idle ms")
//                delay(SLEEP_TIME) // check every 5 seconds
//                if (idle > period) {
//                    idle = 0
//                    moveToLogin()
//                    stopp()
//                }
//            }
//            Log.d(TAG, "Finishing com.example.session.com.example.session.com.example.session.com.example.session.Waiter thread")
//        }
//    }
//
//    private fun moveToLogin() {
//        val intent = Intent(context, MainActivity::class.java).apply {
//            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            putExtra(Constants.IS_SESSION_TIMEOUT, true)
//        }
//        context.startActivity(intent)
//    }
//
//    private fun touch() {
//        lastUsed = System.currentTimeMillis()
//    }
//
//    fun forceInterrupt() {
//        // You can use coroutine cancellation here
//    }
//
//    // Soft stopping of the thread
//    private fun stopp() {
//        stop = true
//    }
//
//    fun setPeriod(period: Long) {
//        this.period = period
//    }
//}
//
//@Composable
//fun JetpackComposeApp() {
//    Column {
//        BasicText(text = "Jetpack Compose Example")
//    }
//}
