package com.bemo.panoramax

import android.app.Application
import com.afollestad.photoaffix.dialogs.ImageSpacingDialog
import com.afollestad.photoaffix.utilities.Injector
import com.afollestad.photoaffix.viewcomponents.SettingsLayout
import com.bemo.panoramax.di.AppComponent
import com.bemo.panoramax.di.DaggerAppComponent
import com.bemo.panoramax.views.MainActivity


class App : Application(), Injector {

  lateinit var appComponent: AppComponent
  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()
  }

   override fun injectInto(target: Any) {
      when (target) {
        is MainActivity -> {
          appComponent.inject(target)
        }
        is SettingsLayout -> {
          appComponent.inject(target)
        }
        is ImageSpacingDialog -> {
          appComponent.inject(target)
        }
        else -> throw IllegalArgumentException("Can't injectInto $target")
      }
    }
}
