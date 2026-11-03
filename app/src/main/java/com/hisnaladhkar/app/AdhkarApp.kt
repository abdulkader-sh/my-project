package com.hisnaladhkar.app

import android.app.Application
import com.hisnaladhkar.app.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AdhkarApp : Application() {

    @Inject lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        notificationHelper.createChannel()
    }
}
