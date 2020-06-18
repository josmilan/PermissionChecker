package com.github.josmilan.permissionchecker

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.josmilan.checker.RuntimePermissionChecker

class MainActivity : AppCompatActivity(), RuntimePermissionChecker.PermissionCallback {

    private var permissionChecker: RuntimePermissionChecker? = null
    private val permissionToCheck = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private lateinit var btn: Button
    private lateinit var tvStatus: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermissionChecker()

        btn = findViewById(R.id.btnAskPermission)
        tvStatus = findViewById(R.id.tvStatus)

        btn.setOnClickListener {
            val permissionGranted: Boolean =
                permissionChecker!!.checkPermissions(permissionToCheck)
            if (permissionGranted) {
                tvStatus.text  = "Permission already granted"
            }
        }
    }

    private fun initPermissionChecker() {
        if (supportFragmentManager.findFragmentByTag(RuntimePermissionChecker.TAG) != null)
            permissionChecker =
                supportFragmentManager.findFragmentByTag(RuntimePermissionChecker.TAG) as RuntimePermissionChecker

        if (permissionChecker == null) {
            permissionChecker = RuntimePermissionChecker.newInstance()
            supportFragmentManager.beginTransaction()
                .add(permissionChecker!!, RuntimePermissionChecker.TAG)
                .commit()
        }
    }

    override fun onPermissionGranted() {
        tvStatus.text  = "Permission granted"
    }

    override fun onPermissionDenied() {

    }
}
