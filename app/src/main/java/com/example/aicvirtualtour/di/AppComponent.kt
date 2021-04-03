package com.example.aicvirtualtour.di

import android.app.Application
import com.example.aicvirtualtour.BaseApplication
import com.example.aicvirtualtour.application.MainActivityModule
import dagger.BindsInstance
import dagger.Component

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: BaseApplication)
}