package edu.msoe.demastri.foreground

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonStop = findViewById<Button>(R.id.buttonStop)
        buttonStart.setOnClickListener(View.OnClickListener {
            MyForegroundService.startService(this, "Foreground Service is running...")
        })
        buttonStop.setOnClickListener(View.OnClickListener {
            MyForegroundService.stopService(this)
        })
        var myContext = this
        lifecycleScope.launch {
            // Trigger the flow and consume its elements using collect
            someState.factory().outFlow().collect {
                setLabel(it)
            }
        }
        Timer().scheduleAtFixedRate(timerTask {
                MyForegroundService.startService(myContext, "Foreground Service is running...")
        },2000,5000)
    }

    fun setLabel(newInt : Int) {
        val updater = findViewById<TextView>(R.id.updater)
        updater.setText(newInt.toString())
    }
}
