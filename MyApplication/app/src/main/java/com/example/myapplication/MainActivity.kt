package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myContext = applicationContext
        setContentView(R.layout.activity_main)
        batteryTxt = findViewById<TextView>(R.id.batteryTxt)
        powerStatus = findViewById<TextView>(R.id.powerStatus)
        this.registerReceiver(this.mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        this.registerReceiver(this.plugInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private lateinit var powerStatus: TextView

    private val plugInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)

            if (plugged == BatteryManager.BATTERY_PLUGGED_AC)
                powerStatus!!.text = "Plugged to AC"
            else if (plugged == BatteryManager.BATTERY_PLUGGED_USB)
                powerStatus!!.text = "Plugged to USB"
            else if (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS)
                powerStatus!!.text = "Plugged to Wireless Charger"
            else
                powerStatus!!.text = "Not plugged"
        }
    }


    private lateinit var batteryTxt: TextView

    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)

            batteryTxt!!.text = "$level%"
        }
    }
}
