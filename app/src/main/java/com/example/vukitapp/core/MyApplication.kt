package com.example.vukitapp.core

import android.app.Application
import com.example.vukitapp.di.component.ApplicationComponent
import com.example.vukitapp.di.component.DaggerApplicationComponent
import com.example.vukitapp.di.module.ApplicationModule

/**
 * @author Axel Sanchez
 */
class MyApplication: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
}