package com.example.vukitapp.di.component

import com.example.vukitapp.di.module.ApplicationModule
import com.example.vukitapp.presentation.MainFragment
import com.example.vukitapp.presentation.ProductsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    fun inject(mainFragment: MainFragment)
    fun inject(productsFragment: ProductsFragment)
}