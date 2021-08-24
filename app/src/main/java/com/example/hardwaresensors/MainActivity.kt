package com.example.hardwaresensors

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var mProximity: Sensor
    lateinit var mAccel: Sensor
    var colors = arrayOf(Color.BLUE, Color.RED, Color.YELLOW, Color.GRAY, Color.GREEN, Color.CYAN, Color.MAGENTA)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        mProximity = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        mAccel = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if(event!!.sensor.type == Sensor.TYPE_PROXIMITY){
            if(event!!.values[0] > 0){
                Log.d("hello", "from the proxy :) ")
                proxSensor.setBackgroundColor(colors[Random.nextInt(colors.size)])
            }
        }
        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
                Log.d("hello", "from the aacc :) ")
                accSensor.setBackgroundColor(acclColor(event.values[0], event.values[1], event.values[2]))
        }
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                this, mProximity, 1000*1000
        )
        sensorManager.registerListener(
                this, mAccel, 1000*1000
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    private fun acclColor(x1: Float, x2: Float, x3: Float): Int {
        val R = (((x1 + 12) / 24) * 255).toInt()
        val G = (((x2 + 12) / 24) * 255).toInt()
        val B = (((x3 + 12) / 24) * 255).toInt()
        return Color.rgb(R, G, B)
    }
}