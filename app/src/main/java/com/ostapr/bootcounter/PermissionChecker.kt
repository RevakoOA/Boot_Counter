package com.ostapr.bootcounter

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionChecker {
    private var codeToRun: (() -> Unit)? = null

    fun checkPermissionsAndRun(activity: AppCompatActivity, permissions: Array<String>, codeToRun: () -> Unit) {
        this.codeToRun = codeToRun
        if (arePermissionsGranted(activity, permissions)) {
            codeToRun.invoke()
        } else {
            requestPermissions(activity, permissions)
        }
    }

    private fun arePermissionsGranted(activity: AppCompatActivity, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestPermissions(activity: AppCompatActivity, permissions: Array<String>) {
        ActivityCompat.requestPermissions(
            activity,
            permissions,
            PERMISSION_REQUEST_CODE
        )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val granted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (granted) {
                codeToRun?.invoke()
            }
            codeToRun = null
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
    }
}