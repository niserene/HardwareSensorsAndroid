package com.example.hardwaresensors

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService<SensorManager>()
        if(sensorManager == null){
            Toast.makeText(this, "Could not get the sensors", Toast.LENGTH_LONG).show()
            finish()
        }
        else{
            val sensors:MutableList<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.forEach{
                Log.d("HWS", "${it.name} | ${it.type}  |  ${it.vendor}")
            }
        }
    }
}