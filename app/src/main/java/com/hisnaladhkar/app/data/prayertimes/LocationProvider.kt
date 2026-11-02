package com.hisnaladhkar.app.data.prayertimes

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /** يعتمد على آخر موقع معروف (مخزَّن مسبقاً) بدل طلب تحديد فعلي، لإبقاء المنطق بسيطاً وسريعاً. */
    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            ?: return null

        return locationManager.getProviders(true)
            .mapNotNull { provider -> locationManager.getLastKnownLocation(provider) }
            .minByOrNull { it.accuracy }
    }
}
