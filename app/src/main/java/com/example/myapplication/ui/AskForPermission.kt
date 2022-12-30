package com.example.myapplication.dimodule

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.core.app.ActivityCompat

var LOCATION_PERMISSION_REQUEST_CODE = 1
fun askForLocationPermissions(cont: Activity) {


    if (ActivityCompat.shouldShowRequestPermissionRationale(
            cont,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) {
        AlertDialog.Builder(cont)
            .setTitle("Location permessions needed")
            .setMessage("you need to allow this permission!")
            .setPositiveButton("Sure", DialogInterface.OnClickListener { dialog, which ->
                ActivityCompat.requestPermissions(
                    cont, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )

            })
            .setNegativeButton("Not now", { dialog, which ->
                //                                        //Do nothing
            })
            .show()


    } else {

        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions(
            cont, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )

    }
}


