package com.maksimyurau.android.flashlight

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mSwitchOnOffFlashLightButton: Button
    var flashLightStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        openFlashLight()
    }

    private fun init() {
        mSwitchOnOffFlashLightButton = findViewById(R.id.switch_on_off_flashlight_button)
    }

    private fun openFlashLight() {
        mSwitchOnOffFlashLightButton.setOnClickListener {
            val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
            val cameraListId = cameraManager.cameraIdList[0]
            if (!flashLightStatus) {
                try {
                    cameraManager.setTorchMode(cameraListId, true)
                    mSwitchOnOffFlashLightButton.setText(R.string.off)
                    flashLightStatus = true
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            } else {
                try {
                    cameraManager.setTorchMode(cameraListId, false)
                    mSwitchOnOffFlashLightButton.setText(R.string.on)
                    flashLightStatus = false
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }
}