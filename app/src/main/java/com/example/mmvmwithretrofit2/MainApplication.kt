package com.example.mmvmwithretrofit2

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy
import com.datadog.android.rum.tracking.ComponentPredicate

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val clientToken = ""
        val applicationId = ""

        val environmentName = "android-test"
        val appVariantName = "android-test"

        val configuration = Configuration.Builder(
            rumEnabled = true,
            crashReportsEnabled = true,
            logsEnabled = true,
            tracesEnabled = true
        )
            .trackInteractions()
            .trackLongTasks(100L)
            .useViewTrackingStrategy(ActivityViewTrackingStrategy(true, predicate))
            .useSite(DatadogSite.US1)
            .build()
        val credentials = Credentials(clientToken,environmentName,appVariantName,applicationId)
        Datadog.initialize(this, credentials, configuration, TrackingConsent.GRANTED)
        GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
        Datadog.setVerbosity(Log.VERBOSE)
    }



    private val predicate: ComponentPredicate<Activity> = object : ComponentPredicate<Activity> {
        override fun accept(component: Activity): Boolean {
            return true
        }

        override fun getViewName(component: Activity): String? {
            return component::class.java.name
        }
    }

}