package com.example.myapplication.dimodule

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.myapplication.ui.permission
import org.koin.core.KoinApplication

var LOCATION_PERMISSION_REQUEST_CODE=1
fun askForLocationPermissions(cont: Activity) {


    if (ActivityCompat.shouldShowRequestPermissionRationale(
            cont ,
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
            .setNegativeButton("Not now", DialogInterface.OnClickListener { dialog, which ->
                //                                        //Do nothing
            })
            .show()

        // Show an expanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
    } else {

        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions(
            cont, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
        println(LOCATION_PERMISSION_REQUEST_CODE.toString()+" REQUESTCODE")



        // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
        // app-defined int constant. The callback method gets the
        // result of the request.
    } }


/*fun isPermissionGranted(
    grantPermissions: Array<String?>, grantResults: IntArray,
    permission: String
): Boolean {
    for (i in grantPermissions.indices) {
        if (permission == grantPermissions[i]) {
            return grantResults[i] == PackageManager.PERMISSION_GRANTED
        }
    }
    return false
}
*/