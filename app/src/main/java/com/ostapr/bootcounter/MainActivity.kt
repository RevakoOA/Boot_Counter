package com.ostapr.bootcounter

import android.Manifest.permission.RECEIVE_BOOT_COMPLETED
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val permissionChecker = PermissionChecker()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionChecker.checkPermissionsAndRun(this, arrayOf(RECEIVE_BOOT_COMPLETED)) {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionChecker.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}