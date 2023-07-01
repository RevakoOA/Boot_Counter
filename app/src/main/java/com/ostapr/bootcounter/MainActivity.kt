package com.ostapr.bootcounter

import android.Manifest.permission.RECEIVE_BOOT_COMPLETED
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission: String = RECEIVE_BOOT_COMPLETED
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf<String>(permission), REQUEST_BOOT_PERMISSION)
            }
        }
    }
}